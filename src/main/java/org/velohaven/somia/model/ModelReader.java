package org.velohaven.somia.model;

import lombok.NonNull;
import org.velohaven.somia.jdbc.datatype.SqlDataTypeInfo;
import org.velohaven.somia.jdbc.databasemetadata.resultset.entity.BestRowIdentifierEntity;
import org.velohaven.somia.jdbc.databasemetadata.resultset.entity.CatalogEntity;
import org.velohaven.somia.jdbc.databasemetadata.resultset.entity.ColumnEntity;
import org.velohaven.somia.jdbc.databasemetadata.resultset.entity.IndexInfoEntity;
import org.velohaven.somia.jdbc.databasemetadata.resultset.entity.PrimaryKeyColumnEntity;
import org.velohaven.somia.jdbc.databasemetadata.resultset.entity.SchemaEntity;
import org.velohaven.somia.jdbc.databasemetadata.resultset.entity.TableEntity;
import org.velohaven.somia.jdbc.databasemetadata.resultset.entity.TypeInfoEntity;
import org.velohaven.somia.jdbc.databasemetadata.resultset.reader.BestRowIdentifierRowReader;
import org.velohaven.somia.jdbc.databasemetadata.resultset.reader.CatalogRowReader;
import org.velohaven.somia.jdbc.databasemetadata.resultset.reader.ColumnRowReader;
import org.velohaven.somia.jdbc.databasemetadata.resultset.reader.IndexInfoRowReader;
import org.velohaven.somia.jdbc.databasemetadata.resultset.reader.PrimaryKeyRowReader;
import org.velohaven.somia.jdbc.databasemetadata.resultset.reader.SchemaRowReader;
import org.velohaven.somia.jdbc.databasemetadata.resultset.reader.TableRowReader;
import org.velohaven.somia.jdbc.databasemetadata.resultset.reader.TypeInfoRowReader;
import org.velohaven.somia.json.JsonUtils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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

    public void addObjectDictionaries(@NonNull final Model model, @NonNull final DatabaseObject databaseObject) {

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

    public Model readModel(@NonNull final Connection connection, final int limit)
        throws SQLException {

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

            Catalog catalog;
            int catalogCount = 0;

            CatalogRowReader catalogRowReader = new CatalogRowReader(catalogsResultSet, true);

            CatalogEntity catalogEntity;

            while (catalogsResultSet.next()) {

                catalogEntity = new CatalogEntity();
                catalogRowReader.read(catalogEntity);

                catalog = new Catalog();
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
     * @param databaseMetaData  database metadata
     * @param catalogName       catalog name to filter the schemas or null for no filtering
     * @param schemaNamePattern pattern to filter the schemas or null for no filtering
     * @param limit             maximum number of schemas to read
     * @return map of catalog names to a map of schema names to schemas sorted by catalog name and schema name
     * @throws SQLException
     */
    public SortedMap<String, SortedMap<String, Schema>> readSchemas(@NonNull final DatabaseMetaData databaseMetaData,
        final String catalogName,
        final String schemaNamePattern, final int limit) throws SQLException {

        SortedMap<String, SortedMap<String, Schema>> catalogSchemas = new TreeMap<>();

        try (ResultSet schemasResultSet = databaseMetaData.getSchemas(catalogName, schemaNamePattern)) {

            SortedMap<String, Schema> schemas;
            int schemaCount = 0;

            Schema schema;
            SchemaEntity schemaEntity;
            SchemaRowReader schemaRowReader = new SchemaRowReader(schemasResultSet, true);

            while (schemasResultSet.next() && schemaCount < limit) {

                schemaEntity = new SchemaEntity();
                schemaRowReader.read(schemaEntity);

                schema = new Schema();
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
     * @param connection         Connection to the database
     * @param catalogNamePattern Catalog name pattern to filter the tables or null for no filtering
     * @param schemaNamePattern  Schema name pattern to filter the tables or null for no filtering
     * @param tableNamePattern   Table name pattern to filter the tables or null for no filtering
     * @param limit              Maximum number of tables to read
     * @return Map of full table names to tables sorted by full table name
     * @throws SQLException
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
            TableRowReader tableRowReader = new TableRowReader(tablesResultSet, true);

            // TODO:
            // read the information which has been given to identify the table, because the default catalog/schema could
            // be returned if null for catalog/schema was passed and to retrieve the original capitalization of
            // the table within the db. For SQL Server it is the other way round, a passed catalog argument is
            // returned as null in the resultSet. Below maximum info is kept.

            while (tablesResultSet.next() && tables.size() < limit) {

                table = createTable(tableRowReader);

                table.primaryKey(readPrimaryKey(connection, table));

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

    Table createTable(@NonNull final TableRowReader tableRowReader) throws SQLException {

        TableEntity tableEntity = new TableEntity();
        tableRowReader.read(tableEntity);

        Table table = new Table();
        table.catalogName(ifNull(tableEntity.tableCatalog, ""));
        table.schemaName(ifNull(tableEntity.tableSchema, ""));
        table.name(tableEntity.tableName);
        table.collation("");
        table.comment(ifNull(tableEntity.remarks, ""));
        table.typeName(tableEntity.tableType);

        return table;

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

            ColumnRowReader columnRowReader = new ColumnRowReader(columnsResultSet, true);
            ColumnEntity columnEntity;

            while (columnsResultSet.next()) {

                columnEntity = readColumn(columnRowReader);

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

    ColumnEntity readColumn(@NonNull final ColumnRowReader columnRowReader) throws SQLException {

        ColumnEntity columnEntity = new ColumnEntity();
        columnRowReader.read(columnEntity);
        return columnEntity;
    }

    Column createColumn(@NonNull final ColumnEntity columnEntity) {

        return createColumn(
            columnEntity.columnName,
            columnEntity.remarks,
            columnEntity.columnDef,
            createDataType(columnEntity)
        );

    }

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
     * @param columnName column name
     * @param remarks    column remarks/comment
     * @param columnDef  column default value definition
     * @param dataType   data type of the column
     * @return created TableColumn
     */
    Column createColumn(String columnName, String remarks, String columnDef, JdbcDataType dataType) {

        Column column = new Column();
        column.name(columnName);
        column.comment(ifNull(remarks, ""));
        column.collation("");
        column.defaultDefinition(ifNull(columnDef, ""));
        column.type(dataType);
        return column;
    }

    JdbcDataType oldCreateDataType(@NonNull final ColumnEntity columnEntity) {

        JdbcDataType dataTypeDefaults;

        int typeNumber = ifNull(columnEntity.dataType, NULL);
        int precision = ifNull(columnEntity.columnSize, 0);
        int scale = ifNull(columnEntity.decimalDigits, 0);

        if (SqlDataTypeInfo.supportsPrecision(typeNumber) && SqlDataTypeInfo.supportsPrecision(typeNumber)) {
            dataTypeDefaults = JdbcDataType.of(typeNumber, precision, scale);
        } else if (SqlDataTypeInfo.supportsPrecision(typeNumber) || SqlDataTypeInfo.supportsSize(typeNumber)) {
            dataTypeDefaults = JdbcDataType.of(typeNumber, precision);
        } else if (SqlDataTypeInfo.supportsScale(typeNumber)) {
            dataTypeDefaults = JdbcDataType.of(typeNumber, scale);
        } else {
            dataTypeDefaults = JdbcDataType.of(typeNumber);
        }

        JdbcDataType dataType = new JdbcDataType(
            ifNull(columnEntity.typeName, dataTypeDefaults.name()),
            ifNull(columnEntity.dataType, dataTypeDefaults.typeNumber()),
            dataTypeDefaults.className(),
            ifNull(columnEntity.columnSize, 0),
            ifNull(columnEntity.decimalDigits, 0),
            "YES".equalsIgnoreCase(ifNull(columnEntity.isNullable, "YES")),
            "YES".equalsIgnoreCase(ifNull(columnEntity.isAutoIncrement, "NO")),
            ifNull(columnEntity.numericPrecisionRadix, 0),
            dataTypeDefaults.isCaseSensitive(),
            dataTypeDefaults.isSearchable(),
            dataTypeDefaults.isSigned(),
            dataTypeDefaults.isCurrency(),
            dataTypeDefaults.columnDisplaySize(),
            null
        );

        return dataType;
    }

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

        if (SqlDataTypeInfo.supportsPrecision(typeNumber) && SqlDataTypeInfo.supportsPrecision(typeNumber)) {
            dataTypeDefaults = JdbcDataType.of(typeNumber, precision, scale);
        } else if (SqlDataTypeInfo.supportsPrecision(typeNumber) || SqlDataTypeInfo.supportsSize(typeNumber)) {
            dataTypeDefaults = JdbcDataType.of(typeNumber, precision);
        } else if (SqlDataTypeInfo.supportsScale(typeNumber)) {
            dataTypeDefaults = JdbcDataType.of(typeNumber, scale);
        } else {
            dataTypeDefaults = JdbcDataType.of(typeNumber);
        }

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

    PrimaryKey readPrimaryKey(@NonNull final Connection connection, @NonNull final Table table)
        throws SQLException {

        // Primary key columns come in the order of the column name, so we need to sort them by keySeq
        SortedMap<Integer, PrimaryKeyColumn> primaryKeyColumns = new TreeMap<>();

        PrimaryKey primaryKey = null;

        ResultSet primaryKeyColumnResultSet2 =
            connection.getMetaData().getPrimaryKeys(table.catalogName(), table.schemaName(), table.name());
        System.err.println(JsonUtils.serialize(primaryKeyColumnResultSet2));
        primaryKeyColumnResultSet2.close();

        try (
            ResultSet primaryKeyColumnResultSet =
                connection.getMetaData().getPrimaryKeys(table.catalogName(), table.schemaName(), table.name())
        ) {

            PrimaryKeyRowReader primaryKeyRowReader = new PrimaryKeyRowReader(primaryKeyColumnResultSet, true);

            PrimaryKeyColumnEntity primaryKeyEntity;

            PrimaryKeyColumn keyColumn;

            while (primaryKeyColumnResultSet.next()) {

                primaryKeyEntity = new PrimaryKeyColumnEntity();
                primaryKeyRowReader.read(primaryKeyEntity);

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

    List<Index> readIndexes(@NonNull final Connection connection, @NonNull final Table table) throws SQLException {

        Map<String, Index> fullNameToIndex = new HashMap<>();
        Map<String, SortedMap<Integer, IndexColumn>> indexToOrderedIndexColumns = new HashMap<>();

        ResultSet indexesResultSet2 =
            connection.getMetaData()
                .getIndexInfo(table.catalogName(), table.schemaName(), table.name(), false, true);

        System.err.println(JsonUtils.serialize(indexesResultSet2));
        indexesResultSet2.close();
        try (ResultSet indexesResultSet =
            connection.getMetaData()
                .getIndexInfo(table.catalogName(), table.schemaName(), table.name(), false, true)) {

            IndexInfoRowReader indexInfoRowReader = new IndexInfoRowReader(indexesResultSet, false);

            IndexInfoEntity indexInfoEntity;
            Index index;
            IndexColumn indexColumn;

            while (indexesResultSet.next()) {

                indexInfoEntity = new IndexInfoEntity();

                indexInfoRowReader.read(indexInfoEntity);

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
            //index.columns().appendAll(indexToOrderedIndexColumns.get(index.fullQuotedIdentifier()).values());
        }

        return new ArrayList<>(fullNameToIndex.values());
    }

    List<Table> oldReadTables(@NonNull final Connection connection,
        final String catalogName, final String schemaNamePattern,
        final String tableNamePattern, final int limit) throws SQLException {

        List<Table> tables = new ArrayList<>();

        String fullName = getFullQuotedIdentifier(catalogName, schemaNamePattern, tableNamePattern);

        DatabaseMetaData databaseMetaData = connection.getMetaData();

        try (ResultSet tablesResultSet = databaseMetaData.getTables(catalogName, schemaNamePattern, tableNamePattern,
            null)
        ) {

            Table table;
            TableRowReader tableRowReader = new TableRowReader(tablesResultSet, true);

            // read the information which has been given to identify the table, because the default catalog/schema could
            // be returned if null for catalog/schema was passed and to retrieve the original capitalization of
            // the table within the db. For SQL Server it is the other way round, a passed catalog argument is
            // returned as null in the resultSet. Below maximum info is kept.

            while (tablesResultSet.next() && tables.size() < limit) {

                table = createTable(tableRowReader);

                try (ResultSet columnsResultSet = databaseMetaData.getColumns(
                    table.catalogName(), table.schemaName(), table.name(), null)
                ) {

                    ColumnRowReader columnRowReader = new ColumnRowReader(columnsResultSet, true);
                    ColumnEntity columnEntity;

                    while (columnsResultSet.next()) {

                        columnEntity = readColumn(columnRowReader);

                        // is this column part of the current table?
                        if (table.catalogName().equals(columnEntity.tableCatalog) &&
                            table.schemaName().equals(columnEntity.tableSchema) &&
                            table.name().equals(columnEntity.tableName)) {

                            // yes, this column belongs to the current table so add it
                            table.columns().append(createColumn(columnEntity));

                        } else {
                            // no, this column does not belong to the current table, but to the next one
                            columnsResultSet.previous();
                            break;
                        }
                    }

                }

                if (table.columns().isEmpty()) {
                    throw new IllegalStateException("Columns of " + fullName + " could not be read.");
                }

                table.primaryKey(readPrimaryKey(connection, table));

                table.indexes().appendAll(readIndexes(connection, table));

                tables.add(table);

            }

        } catch (Exception exception) {
            throw new SQLException("Table " + fullName + " could not be read.", exception);
        }

        // e.g. H2 returns the tables sorted by table type, so sort them by name as defined in the method signature
        Collections.sort(tables);

        return tables;

    }

    void readUserDefinedTypes(@NonNull Connection connection, String catalogName, String schemaName,
        String udtName) throws SQLException {
        try (ResultSet udtResultSet = connection.getMetaData().getUDTs(catalogName, schemaName, udtName, null)) {
            System.out.println(JsonUtils.serialize(udtResultSet));
        }
    }

    Set<TypeInfo> readTypeInfos(@NonNull Connection connection) throws SQLException {

        Set<TypeInfo> typeInfos = new LinkedHashSet<>(); // LinkedHashSet to keep the order of the types

        TypeInfoRowReader typeInfoRowReader;
        TypeInfoEntity typeInfoEntity;
        TypeInfo typeInfo;

        try (ResultSet typeInfoResultSet = connection.getMetaData().getTypeInfo()) {

            typeInfoRowReader = new TypeInfoRowReader(typeInfoResultSet, true);

            while (typeInfoResultSet.next()) {
                typeInfoEntity = new TypeInfoEntity();
                typeInfoRowReader.read(typeInfoEntity);

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

            BestRowIdentifierEntity bestRowIdentifierEntity;
            BestRowIdentifierRowReader bestRowIdentifierReader =
                new BestRowIdentifierRowReader(bestRowIdentifierResultSet, true);

            while (bestRowIdentifierResultSet.next()) {

                bestRowIdentifierEntity = new BestRowIdentifierEntity();
                bestRowIdentifierReader.read(bestRowIdentifierEntity);

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
