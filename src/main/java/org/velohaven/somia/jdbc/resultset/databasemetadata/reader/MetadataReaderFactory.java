package org.velohaven.somia.jdbc.resultset.databasemetadata.reader;

import lombok.NonNull;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.oracle.OracleMetadataReaderFactory;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.AttributeReader;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.BestIdentifierReader;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.CatalogReader;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.ClientInfoPropertyReader;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.ColumnPrivilegeReader;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.ColumnReader;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.CrossReferenceColumnReader;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.ExportedKeyColumnReader;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.FunctionColumnReader;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.FunctionReader;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.ImportedKeyColumnReader;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.IndexInfoReader;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.PrimaryKeyReader;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.ProcedureColumnReader;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.ProcedureReader;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.PseudoColumnReader;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.SchemaReader;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.StandardMetadataReaderFactory;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.SuperTableReader;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.SuperTypeReader;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.TablePrivilegeReader;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.TableReader;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.TableTypeReader;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.TypeInfoReader;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.UDTReader;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.VersionColumnReader;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;

public interface MetadataReaderFactory {

    /**
     * Checks if the given string contains all the specified substrings (case-insensitive).
     *
     * @param str        String to search in
     * @param substrings Substrings to search for
     * @return true if the string contains any of the substrings, false otherwise
     */
    static boolean contains(String str, String... substrings) {
        if (str == null || substrings == null) {
            return false;
        }
        String string = str.toLowerCase();
        for (String substring : substrings) {
            if (string.contains(substring)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determines and returns the appropriate RowReaderFactory based on the class name. If the class name does not
     * match any known database types, it falls back to the default JdbcRowReaderFactory.
     *
     * @param clazz Class to check
     * @return The appropriate RowReaderFactory for the class
     */
    static MetadataReaderFactory createRowReaderFactory(@NonNull Class<?> clazz) {
        if (contains(clazz.getName(), "oracle")) {
            return new OracleMetadataReaderFactory();
        } else if (contains(clazz.getName(), "postgresql")) {
            return new StandardMetadataReaderFactory();
        } else if (contains(clazz.getName(), "mariadb")) {
            return new StandardMetadataReaderFactory();
        } else if (contains(clazz.getName(), "mysql")) {
            return new StandardMetadataReaderFactory();
            // see: http://github.com/microsoft/mssql-jdbc
        } else if (contains(clazz.getName(), "microsoft", "sql")) {
            return new StandardMetadataReaderFactory();
        } else {
            return new StandardMetadataReaderFactory();
        }
    }

    /**
     * Determines and returns the appropriate RowReaderFactory based on the database product name from the
     * DatabaseMetaData. If the database product name is not recognized, it falls back to the class name of
     * the DatabaseMetaData. The default JdbcRowReaderFactory is used if the database type is not recognized.
     *
     * @param databaseMetaData The DatabaseMetaData object to determine the database type.
     * @return The appropriate RowReaderFactory for the database type.
     */
    static MetadataReaderFactory createRowReaderFactory(@NonNull DatabaseMetaData databaseMetaData) {
        try {
            String rdbmsName = databaseMetaData.getDatabaseProductName();
            if (contains(rdbmsName, "oracle")) {
                return new OracleMetadataReaderFactory();
            } else if (contains(rdbmsName, "postgresql")) {
                return new StandardMetadataReaderFactory();
            } else if (contains(rdbmsName, "mariadb")) {
                return new StandardMetadataReaderFactory();
            } else if (contains(rdbmsName, "mysql")) {
                return new StandardMetadataReaderFactory();
            } else if (contains(rdbmsName, "microsoft", "sql", "server")) {
                return new StandardMetadataReaderFactory();
            } else {
                return createRowReaderFactory(databaseMetaData.getClass());
            }
        } catch (Exception e) {
            return createRowReaderFactory(databaseMetaData.getClass());
        }
    }

    /**
     * Determines and returns the appropriate RowReaderFactory based on the Connection object's database metadata.
     * If the database metadata is not available, it falls back to the class name of the Connection. The default
     * JdbcRowReaderFactory is used if the database type is not recognized.
     *
     * @param connection The Connection object to determine the database type.
     * @return The appropriate RowReaderFactory for the database type.
     */
    static MetadataReaderFactory createRowReaderFactory(@NonNull Connection connection) {
        try {
            return createRowReaderFactory(connection.getMetaData());
        } catch (Exception e) {
            return createRowReaderFactory(connection.getClass());
        }
    }

    /**
     * Determines and returns the appropriate RowReaderFactory based on the Statement object's database metadata via
     * its connection. If the database metadata is not available, it falls back to the class name of the Statement.
     * The default JdbcRowReaderFactory is used if the database type is not recognized.
     *
     * @param statement The Statement object to determine the database type.
     * @return The appropriate RowReaderFactory for the database type.
     */
    static MetadataReaderFactory createRowReaderFactory(@NonNull Statement statement) {
        try {
            return createRowReaderFactory(statement.getConnection());
        } catch (Exception e) {
            return createRowReaderFactory(statement.getClass());
        }
    }

    /**
     * Determines and returns the appropriate RowReaderFactory based on the ResultSet object's database metadata via
     * its statement's connection. If the database metadata is not available, it falls back to the class name of
     * the ResultSet. The default JdbcRowReaderFactory is used if the database type is not recognized.
     *
     * @param resultSet The ResultSet object to determine the database type.
     * @return The appropriate RowReaderFactory for the database type.
     */
    static MetadataReaderFactory createRowReaderFactory(@NonNull ResultSet resultSet) {
        try {
            return createRowReaderFactory(resultSet.getStatement());
        } catch (Exception e) {
            return createRowReaderFactory(resultSet.getClass());
        }
    }

    AttributeReader createAttributeRowReader(@NonNull final ResultSet resultSet, boolean strict);

    BestIdentifierReader createBestRowIdentifierRowReader(@NonNull final ResultSet resultSet, boolean strict);

    CatalogReader createCatalogRowReader(@NonNull final ResultSet resultSet, boolean strict);

    ClientInfoPropertyReader createClientInfoPropertyRowReader(@NonNull final ResultSet resultSet, boolean strict);

    ColumnPrivilegeReader createColumnPrivilegeRowReader(@NonNull final ResultSet resultSet, boolean strict);

    ColumnReader createColumnRowReader(@NonNull final ResultSet resultSet, boolean strict);

    CrossReferenceColumnReader createCrossReferenceColumnRowReader(@NonNull final ResultSet resultSet, boolean strict);

    ExportedKeyColumnReader createExportedKeyColumnRowReader(@NonNull final ResultSet resultSet, boolean strict);

    FunctionColumnReader createFunctionColumnRowReader(@NonNull final ResultSet resultSet, boolean strict);

    FunctionReader createFunctionRowReader(@NonNull final ResultSet resultSet, boolean strict);

    ImportedKeyColumnReader createImportedKeyColumnRowReader(@NonNull final ResultSet resultSet, boolean strict);

    IndexInfoReader createIndexInfoRowReader(@NonNull final ResultSet resultSet, boolean strict);

    PrimaryKeyReader createPrimaryKeyRowReader(@NonNull final ResultSet resultSet, boolean strict);

    ProcedureColumnReader createProcedureColumnRowReader(@NonNull final ResultSet resultSet, boolean strict);

    PrimaryKeyReader createPrimaryKeyRowReader(@NonNull final ResultSet resultSet);

    ProcedureColumnReader createProcedureColumnRowReader(@NonNull final ResultSet resultSet);

    ProcedureReader createProcedureRowReader(@NonNull final ResultSet resultSet, boolean strict);

    PseudoColumnReader createPseudoColumnRowReader(@NonNull final ResultSet resultSet, boolean strict);

    SchemaReader createSchemaRowReader(@NonNull final ResultSet resultSet, boolean strict);

    SuperTableReader createSuperTableRowReader(@NonNull final ResultSet resultSet, boolean strict);

    SuperTypeReader createSuperTypeRowReader(@NonNull final ResultSet resultSet, boolean strict);

    TablePrivilegeReader createTablePrivilegeRowReader(@NonNull final ResultSet resultSet, boolean strict);

    TableReader createTableRowReader(@NonNull final ResultSet resultSet, boolean strict);

    TablePrivilegeReader createTablePrivilegeRowReader(@NonNull final ResultSet resultSet);

    TypeInfoReader createTypeInfoRowReader(@NonNull final ResultSet resultSet, boolean strict);

    TableTypeReader createTableTypeRowReader(@NonNull final ResultSet resultSet, boolean strict);

    UDTReader createUDTRowReader(@NonNull final ResultSet resultSet, boolean strict);

    VersionColumnReader createVersionColumnRowReader(@NonNull final ResultSet resultSet, boolean strict);
}
