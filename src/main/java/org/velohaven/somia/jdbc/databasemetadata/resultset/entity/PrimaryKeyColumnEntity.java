package org.velohaven.somia.jdbc.databasemetadata.resultset.entity;

/**
 * Represents a row in the result set of the {@link java.sql.DatabaseMetaData#getPrimaryKeys} method.
 */
public class PrimaryKeyColumnEntity {

    public String tableCatalog;
    public String tableSchema;
    public String tableName;
    public String columnName;
    public Integer keySeq;
    public String primaryKeyName;

}