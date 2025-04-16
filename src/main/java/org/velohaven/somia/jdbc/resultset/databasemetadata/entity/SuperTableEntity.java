package org.velohaven.somia.jdbc.resultset.databasemetadata.entity;

/**
 * Represents a row in the result set of the {@link java.sql.DatabaseMetaData#getSuperTables} method.
 */
public class SuperTableEntity {

    public String tableCatalog;
    public String tableSchema;
    public String tableName;
    public String superTableName;

}