package org.velohaven.somia.model;

import lombok.NonNull;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.RdbmsAwareReaderFactory;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.MetadataReaderFactory;
import org.velohaven.somia.jdbc.datatype.SqlDataTypeInfo;
import org.velohaven.somia.jdbc.resultset.databasemetadata.entity.BestRowIdentifierEntity;
import org.velohaven.somia.jdbc.resultset.databasemetadata.entity.CatalogEntity;
import org.velohaven.somia.jdbc.resultset.databasemetadata.entity.ColumnEntity;
import org.velohaven.somia.jdbc.resultset.databasemetadata.entity.IndexInfoEntity;
import org.velohaven.somia.jdbc.resultset.databasemetadata.entity.PrimaryKeyColumnEntity;
import org.velohaven.somia.jdbc.resultset.databasemetadata.entity.SchemaEntity;
import org.velohaven.somia.jdbc.resultset.databasemetadata.entity.TableEntity;
import org.velohaven.somia.jdbc.resultset.databasemetadata.entity.TypeInfoEntity;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.BestIdentifierReader;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.CatalogReader;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.ColumnReader;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.IndexInfoReader;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.PrimaryKeyReader;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.SchemaReader;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.TableReader;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.TypeInfoReader;
import org.velohaven.somia.json.JsonUtils;
import org.velohaven.somia.model.factory.PrimaryKeyFactory;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import static java.sql.DatabaseMetaData.bestRowTemporary;
import static java.sql.DatabaseMetaData.tableIndexClustered;
import static java.sql.DatabaseMetaData.tableIndexHashed;
import static java.sql.DatabaseMetaData.tableIndexOther;
import static java.sql.DatabaseMetaData.tableIndexStatistic;
import static java.sql.DatabaseMetaData.typeNoNulls;
import static java.sql.DatabaseMetaData.typeNullable;
import static java.sql.DatabaseMetaData.typePredNone;
import static java.sql.DatabaseMetaData.typeSearchable;
import static java.sql.Types.NULL;
import static org.velohaven.somia.model.NameUtils.getFullQuotedIdentifier;

public class ModelReader {

    private <T> T ifNull(T value, T defaultValue) {
        return value == null ? defaultValue : value;
    }

    public void addObjectDictionaries(@NonNull final Model model, @SuppressWarnings("rawtypes") @NonNull final DatabaseObject databaseObject) {

        Catalog catalog;
        if (!model.catalogs().containsName(databaseObject.catalogName())) {
            catalog = new Catalog();
            catalog.name(databaseObject.catalogName());
        } else {
            catalog = model.catalogs().getByName(databaseObject.catalogName());
        }

        Schema schema;
        if (!catalog.schemas().containsName(databaseObject.schemaName())) {
            schema = new Schema();
            schema.name(databaseObject.schemaName());
            schema.catalogName(databaseObject.catalogName());
        }
    }

    public Model readModel(@NonNull final Connection connection, final int limit) throws SQLException {

        Model model = new Model();
        Catalogs catalogs = readCatalogs(connection);
        model.catalogs(catalogs);

        Map<String, Table> tableMap = readTables(connection, null, null, null, limit);

        for (Table table : tableMap.values()) {

            addObjectDictionaries(model, table);

            Schema schema = model.catalogs().getByName(table.catalogName()).schemas().getByName(table.schemaName());
            schema.tables().append(table);

        }

        Set<TypeInfo> typeInfos = readTypeInfos(connection);
        model.typeInfos(typeInfos);

        return model;
    }

    public Catalogs readCatalogs(@NonNull final Connection connection) throws SQLException {

        Catalogs catalogs = new Catalogs();

        try (ResultSet catalogsResultSet = connection.getMetaData().getCatalogs()) {

            MetadataReaderFactory rowReaderFactory = new RdbmsAwareReaderFactory();

            CatalogReader catalogRowReader = rowReaderFactory.createCatalogRowReader(catalogsResultSet, true);

            List<CatalogEntity> catalogEntities = catalogRowReader.readAll();

            for (CatalogEntity catalogEntity : catalogEntities) {

                Catalog catalog = new Catalog();
                catalog.name(ifNull(catalogEntity.tableCatalog, ""));

                Schemas schemas = readSchemas(connection, catalog.name(), null, 0);
                catalog.schemas().appendAll(schemas);

                catalogs.append(catalog);

            }

        } catch (Exception exception) {
            throw new SQLException("Catalogs could not be read.", exception);
        }


        return catalogs;

    }

    public Schemas readSchemas(@NonNull final Connection connection, final String catalogName,
                               final String schemaNamePattern, final int limit) throws SQLException {

        DatabaseMetaData databaseMetaData = connection.getMetaData();

        SortedMap<String, SortedMap<String, Schema>> catalogSchemas =
                readSchemas(databaseMetaData, catalogName, schemaNamePattern, limit == 0 ? Integer.MAX_VALUE : limit);

        Schemas schemas = new Schemas();

        // append all schemas to the result in the order of the catalog's name and the schema's name
        for (SortedMap<String, Schema> schemaMap : catalogSchemas.values()) {
            schemas.appendAll(schemaMap.values());
        }

        return schemas;

    }

    /**
     * Reads the schemas from the database and returns them in a map of catalog names to a map of schema names to
     * schemas in the order of the catalog's name and the schema's name for a deterministically ordered result.
     *
     * @param databaseMetaData  database metadata
     * @param catalogName       catalog name to filter the schemas or null for no filtering
     * @param schemaNamePattern pattern to filter the schemas or null for no filtering
     * @param limit             maximum number of schemas to read
     * @return map of catalog names to a map of schema names to schemas sorted by catalog name and schema name
     */
    public SortedMap<String, SortedMap<String, Schema>> readSchemas(@NonNull final DatabaseMetaData databaseMetaData,
                                                                    final String catalogName,
                                                                    final String schemaNamePattern, final int limit) throws SQLException {

        SortedMap<String, SortedMap<String, Schema>> catalogSchemas = new TreeMap<>();

        try (ResultSet schemasResultSet = databaseMetaData.getSchemas(catalogName, schemaNamePattern)) {

            SortedMap<String, Schema> schemas;
            int schemaCount = 0;

            MetadataReaderFactory rowReaderFactory = new RdbmsAwareReaderFactory();
            SchemaReader schemaRowReader = rowReaderFactory.createSchemaRowReader(schemasResultSet, true);

            List<SchemaEntity> schemaEntities = schemaRowReader.readAll();

            for (SchemaEntity schemaEntity : schemaEntities) {

                Schema schema = new Schema();
                schema.catalogName(ifNull(schemaEntity.tableCatalog, ""));
                schema.name(ifNull(schemaEntity.tableSchema, ""));

                schemas = catalogSchemas.computeIfAbsent(schema.catalogName(), k -> new TreeMap<>());
                schemas.put(schema.name(), schema);

                schemaCount++;
            }

        } catch (Exception exception) {
            throw new SQLException("Schemas could not be read.", exception);
        }

        return catalogSchemas;

    }

    public Table readTable(@NonNull final Connection connection,
                           final String catalogName,
                           final String schemaName,
                           @NonNull final String tableName) throws SQLException {

        Map<String, Table> tables = readTables(connection, catalogName, schemaName, tableName, 1);

        return tables.isEmpty() ? null : tables.values().iterator().next();

    }

    Map<String, Table> readTables(@NonNull final Connection connection,
                                  final String catalogName, final String schemaNamePattern, final String tableNamePattern, final int limit
    ) throws SQLException {

        // read table info (without columns)
        Map<String, Table> tablesInfo = readTablesInfo(
                connection, catalogName, schemaNamePattern, tableNamePattern, limit
        );

        // read columns
        Map<String, SortedMap<Integer, Column>> tableColumns =
                readColumns(connection, catalogName, schemaNamePattern, tableNamePattern);

        // add columns to tables
        for (Table table : tablesInfo.values()) {
            String fullQuotedIdentifier = table.fullQuotedIdentifier();
            SortedMap<Integer, Column> columns = tableColumns.get(fullQuotedIdentifier);
            table.columns().appendAll(columns.values());
        }

        return tablesInfo;

    }

    /**
     * Reads the table information from the database, including primary key and indexes but without columns.
     *
     * @param connection         Connection to the database
     * @param catalogNamePattern Catalog name pattern to filter the tables or null for no filtering
     * @param schemaNamePattern  Schema name pattern to filter the tables or null for no filtering
     * @param tableNamePattern   Table name pattern to filter the tables or null for no filtering
     * @param limit              Maximum number of tables to read
     * @return Map of full table names to tables sorted by full table name
     */
    Map<String, Table> readTablesInfo(@NonNull final Connection connection,
                                      final String catalogNamePattern, final String schemaNamePattern,
                                      final String tableNamePattern, final int limit) throws SQLException {

        // Map of full table names to tables sorted by full table name
        Map<String, Table> tables = new TreeMap<>();

        String fullName = getFullQuotedIdentifier(catalogNamePattern, schemaNamePattern, tableNamePattern);

        DatabaseMetaData databaseMetaData = connection.getMetaData();

        try (ResultSet tablesResultSet = databaseMetaData.getTables(catalogNamePattern, schemaNamePattern,
                tableNamePattern,
                null)
        ) {

            Table table;
            MetadataReaderFactory rowReaderFactory = new RdbmsAwareReaderFactory();
            TableReader tableRowReader = rowReaderFactory.createTableRowReader(tablesResultSet, true);

            // TODO:
            // read the information which has been given to identify the table, because the default catalog/schema could
            // be returned if null for catalog/schema was passed and to retrieve the original capitalization of
            // the table within the db. For SQL Server it is the other way round, a passed catalog argument is
            // returned as null in the resultSet. Below maximum info is kept.

            List<TableEntity> tableEntities = tableRowReader.read(1, limit);

            for (TableEntity tableEntity : tableEntities) {
                table = new Table();
                table.catalogName(ifNull(tableEntity.tableCatalog, ""));
                table.schemaName(ifNull(tableEntity.tableSchema, ""));
                table.name(ifNull(tableEntity.tableName, ""));
                table.collation("");
                table.comment(ifNull(tableEntity.remarks, ""));
                table.typeName(ifNull(tableEntity.tableType, ""));

                PrimaryKeyFactory primaryKeyFactory = new PrimaryKeyFactory();
                PrimaryKey primaryKey = primaryKeyFactory.readPrimaryKey(connection, table);
                table.primaryKey(primaryKey);

                table.indexes().appendAll(readIndexes(connection, table));

                List<BestRowIdentifierColumn> bestRowIdentifierColumns = readBestRowIdentifier(connection, table);

                if (!bestRowIdentifierColumns.isEmpty()) {
                    table.bestRowIdentifier(new BestRowIdentifier());
                    table.bestRowIdentifier().columns().appendAll(bestRowIdentifierColumns);
                }

                tables.put(table.fullQuotedIdentifier(), table);

            }

        } catch (Exception exception) {
            throw new SQLException("Table " + fullName + " could not be read.", exception);
        }

        return tables;

    }

    Map<String, SortedMap<Integer, Column>> readColumns(
            @NonNull final Connection connection,
            String catalogNamePattern,
            String schemaNamePattern,
            String namePattern
    ) throws SQLException {

        // Map of full table names to a map of column positions to columns
        Map<String, SortedMap<Integer, Column>> fullTableNameToColumns = new HashMap<>();

        // Map of column positions to columns for the current table
        SortedMap<Integer, Column> tableColumns;

        try (ResultSet columnsResultSet = connection.getMetaData().getColumns(
                catalogNamePattern, schemaNamePattern, namePattern, null)
        ) {

            MetadataReaderFactory rowReaderFactory = new RdbmsAwareReaderFactory();
            ColumnReader columnRowReader = rowReaderFactory.createColumnRowReader(columnsResultSet, true);

            List<ColumnEntity> columnEntities = columnRowReader.readAll();

            for (ColumnEntity columnEntity : columnEntities) {

                String fullTableName = getFullQuotedIdentifier(
                        columnEntity.tableCatalog, columnEntity.tableSchema, columnEntity.tableName
                );

                tableColumns = fullTableNameToColumns.computeIfAbsent(fullTableName, k -> new TreeMap<>());

                tableColumns.put(columnEntity.ordinalPosition, createColumn(columnEntity));

            }

        } catch (SQLException exception) {
            throw new SQLException(
                    "Columns of " + getFullQuotedIdentifier(catalogNamePattern, schemaNamePattern, namePattern)
                            + " could not be read.", exception);
        }
        return fullTableNameToColumns;
    }

    /**
     * Creates a column with the given parameters as they were read from the database.
     *
     * @param columnEntity column entity
     * @return created Column
     */
    Column createColumn(@NonNull final ColumnEntity columnEntity) {

        return createColumn(
                columnEntity.columnName,
                columnEntity.remarks,
                columnEntity.columnDef,
                createDataType(columnEntity)
        );

    }

    /**
     * Creates a Column for a pseudo column with the given parameters as they were read from the database.
     *
     * @param bestRowIdentifier best row identifier entity
     * @return created Column
     */
    Column createPseudoColumn(@NonNull final BestRowIdentifierEntity bestRowIdentifier) {
        return createColumn(
                ifNull(bestRowIdentifier.columnName, ""),
                "",
                "",
                createDataType(bestRowIdentifier)
        );
    }

    /**
     * Creates a column with the given parameters as they were read from the database. This method replaces null values
     * with empty strings or 0.
     *
     * @param columnName column name
     * @param remarks    column remarks/comment
     * @param columnDef  column default value definition
     * @param dataType   data type of the column
     * @return created TableColumn
     */
    Column createColumn(final String columnName, final String remarks, final String columnDef, final JdbcDataType dataType) {

        Column column = new Column();
        column.name(columnName);
        column.comment(ifNull(remarks, ""));
        column.collation("");
        column.defaultDefinition(ifNull(columnDef, ""));
        column.type(dataType);
        return column;
    }

    /**
     * Creates a data type with the given parameters as they were read from the database.
     *
     * @param bestRowIdentifier best row identifier entity
     * @return created JdbcDataType
     */
    JdbcDataType createDataType(@NonNull final BestRowIdentifierEntity bestRowIdentifier) {
        return createDataType(
                bestRowIdentifier.dataType,
                bestRowIdentifier.typeName,
                bestRowIdentifier.columnSize,
                bestRowIdentifier.decimalDigits,
                null,
                null,
                null
        );
    }

    /**
     * Creates a data type with the given parameters as they were read from the database.
     *
     * @param columnEntity column entity
     * @return created JdbcDataType
     */
    JdbcDataType createDataType(@NonNull final ColumnEntity columnEntity) {
        return createDataType(
                columnEntity.dataType,
                columnEntity.typeName,
                columnEntity.columnSize,
                columnEntity.decimalDigits,
                columnEntity.isNullable,
                columnEntity.isAutoIncrement,
                columnEntity.numericPrecisionRadix
        );
    }


    /**
     * Creates a data type with the given parameters as they are returned ResultSet of {@link java.sql.DatabaseMetaData#getColumns} method.
     *
     * @param dataType              data type number from column DATA_TYPE
     * @param typeName              data type name from column TYPE_NAME
     * @param columnSize            column size from column COLUMN_SIZE
     * @param decimalDigits         decimal digits from column DECIMAL_DIGITS
     * @param isNullable            Nullability from column IS_NULLABLE
     * @param isAutoIncrement       Auto increment from column IS_AUTOINCREMENT
     * @param numericPrecisionRadix Numeric precision radix from column NUMERIC_PRECISION_RADIX
     * @return created JdbcDataType
     */
    JdbcDataType createDataType(
            Integer dataType,
            String typeName,
            Integer columnSize,
            Integer decimalDigits,
            String isNullable,
            String isAutoIncrement,
            Integer numericPrecisionRadix) {

        JdbcDataType dataTypeDefaults;

        int typeNumber = ifNull(dataType, NULL);
        int precision = ifNull(columnSize, 0);
        int scale = ifNull(decimalDigits, 0);

        if (SqlDataTypeInfo.supportsPrecision(typeNumber) && SqlDataTypeInfo.supportsSize(typeNumber)) {
            dataTypeDefaults = JdbcDataType.of(typeNumber, precision, scale);
        } else if (SqlDataTypeInfo.supportsPrecision(typeNumber) || SqlDataTypeInfo.supportsSize(typeNumber)) {
            dataTypeDefaults = JdbcDataType.of(typeNumber, precision);
        } else if (SqlDataTypeInfo.supportsScale(typeNumber)) {
            dataTypeDefaults = JdbcDataType.of(typeNumber, scale);
        } else {
            dataTypeDefaults = JdbcDataType.of(typeNumber);
        }


        @SuppressWarnings("UnnecessaryLocalVariable")
        JdbcDataType jdbcDataType = new JdbcDataType(
                ifNull(typeName, dataTypeDefaults.name()),
                ifNull(dataType, dataTypeDefaults.typeNumber()),
                dataTypeDefaults.className(),
                ifNull(columnSize, 0),
                ifNull(decimalDigits, 0),
                "YES".equalsIgnoreCase(ifNull(isNullable, "YES")),
                "YES".equalsIgnoreCase(ifNull(isAutoIncrement, "NO")),
                ifNull(numericPrecisionRadix, 0),
                dataTypeDefaults.isCaseSensitive(),
                dataTypeDefaults.isSearchable(),
                dataTypeDefaults.isSigned(),
                dataTypeDefaults.isCurrency(),
                dataTypeDefaults.columnDisplaySize(),
                null
        );

        return jdbcDataType;
    }

    /**
     * Reads the primary key of a table from the database.
     *
     * @param connection Connection to the database
     * @param table      Table to read the primary key for
     * @return PrimaryKey object with the primary key columns
     * @throws SQLException if the primary key could not be read
     */
    PrimaryKey readPrimaryKey(@NonNull final Connection connection, @NonNull final Table table)
            throws SQLException {

        // Primary key columns come in the order of the column name, so we need to sort them by keySeq
        SortedMap<Integer, PrimaryKeyColumn> primaryKeyColumns = new TreeMap<>();

        PrimaryKey primaryKey = null;

        try (
                ResultSet primaryKeyColumnResultSet =
                        connection.getMetaData().getPrimaryKeys(table.catalogName(), table.schemaName(), table.name())
        ) {

            MetadataReaderFactory rowReaderFactory = new RdbmsAwareReaderFactory();
            PrimaryKeyReader primaryKeyRowReader = rowReaderFactory.createPrimaryKeyRowReader(primaryKeyColumnResultSet, true);

            PrimaryKeyColumnEntity primaryKeyEntity;

            PrimaryKeyColumn keyColumn;

            List<PrimaryKeyColumnEntity> primaryKeyColumnEntities = primaryKeyRowReader.readAll();

            for (PrimaryKeyColumnEntity primaryKeyColumnEntity : primaryKeyColumnEntities) {

                primaryKeyEntity = primaryKeyColumnEntity;

                if (primaryKeyEntity.keySeq == 1) {
                    primaryKey = new PrimaryKey();
                    primaryKey.name(primaryKeyEntity.primaryKeyName);
                    primaryKey.parent(table);
                }

                keyColumn = new PrimaryKeyColumn();
                keyColumn.parent(primaryKey);
                keyColumn.name(primaryKeyEntity.columnName);

                primaryKeyColumns.put(primaryKeyEntity.keySeq, keyColumn);

            }

        } catch (SQLException exception) {
            throw new SQLException("Primary key of " + table.fullQuotedIdentifier() + " could not be read.", exception);
        }

        if (primaryKey != null) {
            primaryKey.columns().appendAll(primaryKeyColumns.values());
        }

        return primaryKey;

    }

    /**
     * Reads the indexes of a table from the database.
     *
     * @param connection Connection to the database
     * @param table      Table to read the indexes for
     * @return List of Index objects with the index columns
     * @throws SQLException if the indexes could not be read
     */
    List<Index> readIndexes(@NonNull final Connection connection, @NonNull final Table table) throws SQLException {

        Map<String, Index> fullNameToIndex = new HashMap<>();
        Map<String, SortedMap<Integer, IndexColumn>> indexToOrderedIndexColumns = new HashMap<>();

        try (ResultSet indexesResultSet =
                     connection.getMetaData()
                             .getIndexInfo(table.catalogName(), table.schemaName(), table.name(), false, true)) {

            MetadataReaderFactory rowReaderFactory = new RdbmsAwareReaderFactory();
            IndexInfoReader indexInfoRowReader = rowReaderFactory.createIndexInfoRowReader(indexesResultSet, false);

            Index index;
            IndexColumn indexColumn;

            List<IndexInfoEntity> indexInfoEntities = indexInfoRowReader.readAll();

            for (IndexInfoEntity indexInfoEntity : indexInfoEntities) {
                String indexName = ifNull(indexInfoEntity.indexName, "");
                int ordinalPosition = ifNull(indexInfoEntity.ordinalPosition, 0);

                // Uniqueness is not necessarily unique over all columns of the index from getIndexInfo. So called
                // INCLUDE-columns are not part of the index key, but are stored in the leaf level of the index. They
                // are marked as non-unique and are sorted after the real index columns.
                boolean isUnique = !ifNull(indexInfoEntity.nonUnique, true);

                if (ordinalPosition <= 1) {

                    index = new Index();
                    index.parent(table);
                    index.name(indexName);
                    index.comment("");
                    index.whereClause(ifNull(indexInfoEntity.filterCondition, ""));
                    // TODO check this for Oracle
                    index.qualifierName(ifNull(indexInfoEntity.indexQualifier, ""));

                    // the first column determines the uniqueness of the index
                    index.isUnique(isUnique);
                    index.isStatistic(ifNull(indexInfoEntity.type, tableIndexOther) == tableIndexStatistic);
                    index.isClustered(ifNull(indexInfoEntity.type, tableIndexOther) == tableIndexClustered);
                    index.isHashed(ifNull(indexInfoEntity.type, tableIndexOther) == tableIndexHashed);

                    index.cardinality(ifNull(indexInfoEntity.cardinality, 0L));
                    index.pages(ifNull(indexInfoEntity.pages, 0L));

                    fullNameToIndex.put(indexName, index);

                } else {
                    index = fullNameToIndex.get(indexName);
                }

                indexColumn = new IndexColumn();

                // use "" as default, e.g. for statistics, which have no column
                indexColumn.name(ifNull(indexInfoEntity.columnName, ""));

                indexToOrderedIndexColumns.computeIfAbsent(indexName, k -> new TreeMap<>());

                if (!indexColumn.name().isEmpty()) {
                    String order = ifNull(indexInfoEntity.ascOrDesc, "");
                    if (order.equalsIgnoreCase("A")) {
                        order = "ASC";
                    } else if (order.equalsIgnoreCase("D")) {
                        order = "DESC";
                    }
                    indexColumn.order(order);

                    indexColumn.isIncluded(index.isUnique() && !isUnique);

                    indexToOrderedIndexColumns.get(indexName).put(ordinalPosition, indexColumn);
                }
            }

        } catch (SQLException exception) {
            throw new SQLException("Indexes of " + table.fullQuotedIdentifier() + " could not be read.", exception);

        }

        for (Index index : fullNameToIndex.values()) {
            Map<Integer, IndexColumn> indexColumnsMap = indexToOrderedIndexColumns.get(index.name());
            Collection<IndexColumn> indexColumns = indexColumnsMap.values();
            index.columns().appendAll(indexColumns);
        }

        return new ArrayList<>(fullNameToIndex.values());
    }

    void readUserDefinedTypes(@NonNull Connection connection, String catalogName, String schemaName,
                              String udtName) throws SQLException {
        try (ResultSet udtResultSet = connection.getMetaData().getUDTs(catalogName, schemaName, udtName, null)) {
            System.out.println(JsonUtils.serialize(udtResultSet));
        }
    }

    Set<TypeInfo> readTypeInfos(@NonNull Connection connection) throws SQLException {

        Set<TypeInfo> typeInfos = new LinkedHashSet<>(); // LinkedHashSet to keep the order of the types

        TypeInfoReader typeInfoRowReader;
        TypeInfo typeInfo;

        try (ResultSet typeInfoResultSet = connection.getMetaData().getTypeInfo()) {

            MetadataReaderFactory rowReaderFactory = new RdbmsAwareReaderFactory();
            typeInfoRowReader = rowReaderFactory.createTypeInfoRowReader(typeInfoResultSet, true);

            List<TypeInfoEntity> typeInfoEntities = typeInfoRowReader.readAll();

            for (TypeInfoEntity typeInfoEntity : typeInfoEntities) {

                typeInfo = new TypeInfo();

                typeInfo.name(ifNull(typeInfoEntity.typeName, ""));
                typeInfo.dataType(ifNull(typeInfoEntity.dataType, NULL));
                typeInfo.localTypeName(ifNull(typeInfoEntity.localTypeName, ""));
                typeInfo.minimumScale(ifNull(typeInfoEntity.minimumScale, 0));

                typeInfo.precision(ifNull(typeInfoEntity.precision, 0));

                typeInfo.createParams(ifNull(typeInfoEntity.createParams, ""));
                typeInfo.literalPrefix(ifNull(typeInfoEntity.literalPrefix, ""));
                typeInfo.literalSuffix(ifNull(typeInfoEntity.literalSuffix, ""));

                typeInfo.isNullable(ifNull(typeInfoEntity.nullable, typeNoNulls) == typeNullable);
                typeInfo.isCaseSensitive(ifNull(typeInfoEntity.caseSensitive, false));
                typeInfo.isSearchable(ifNull(typeInfoEntity.searchable, typePredNone) == typeSearchable);
                typeInfo.isUnsignedAttribute(ifNull(typeInfoEntity.unsignedAttribute, false));
                typeInfo.isAutoIncrement(ifNull(typeInfoEntity.autoIncrement, false));

                typeInfo.precision(ifNull(typeInfoEntity.precision, 0));
                typeInfo.numericPrecisionRadix(ifNull(typeInfoEntity.numericPrecisionRadix, 0));

                typeInfo.isFixedPrecisionScale(ifNull(typeInfoEntity.fixedPrecisionScale, false));
                typeInfo.minimumScale(ifNull(typeInfoEntity.minimumScale, 0));
                typeInfo.maximumScale(ifNull(typeInfoEntity.maximumScale, 0));

                typeInfos.add(typeInfo);

            }

        } catch (SQLException exception) {
            throw new SQLException("Type information could not be read.", exception);
        }

        return typeInfos;
    }

    public List<BestRowIdentifierColumn> readBestRowIdentifier(@NonNull Connection connection, Table table)
            throws SQLException {

        List<BestRowIdentifierColumn> bestRowIdentifiers = new ArrayList<>();

        try (ResultSet bestRowIdentifierResultSet = connection.getMetaData().getBestRowIdentifier(
                table.catalogName(),
                table.schemaName(),
                table.name(),
                //bestRowSession,
                2,
                true
        )) {

            MetadataReaderFactory rowReaderFactory = new RdbmsAwareReaderFactory();

            BestIdentifierReader bestRowIdentifierReader =
                    rowReaderFactory.createBestRowIdentifierRowReader(bestRowIdentifierResultSet, true);

            List<BestRowIdentifierEntity> bestRowIdentifierEntities = bestRowIdentifierReader.readAll();

            for (BestRowIdentifierEntity bestRowIdentifierEntity : bestRowIdentifierEntities) {
                BestRowIdentifierColumn bestRowIdentifier = new BestRowIdentifierColumn();

                bestRowIdentifier.name(ifNull(bestRowIdentifierEntity.columnName, ""));

                Column pseudoColumn = createPseudoColumn(bestRowIdentifierEntity);
                bestRowIdentifier.pseudoColumnBaseColumn(pseudoColumn);

                bestRowIdentifier.scope(ifNull(bestRowIdentifierEntity.scope, bestRowTemporary));
                bestRowIdentifier.pseudoColumn(bestRowIdentifierEntity.pseudoColumn);

                bestRowIdentifiers.add(bestRowIdentifier);
            }

        } catch (SQLException exception) {
            throw new SQLException("BestRowIdentifier " + table.fullQuotedIdentifier() + " could not be read.",
                    exception);
        }

        return bestRowIdentifiers;

    }

}
