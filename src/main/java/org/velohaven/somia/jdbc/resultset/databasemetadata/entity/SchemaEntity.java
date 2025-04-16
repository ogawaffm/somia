package org.velohaven.somia.jdbc.resultset.databasemetadata.entity;

/**
 * Represents a row in the result set of the {@link java.sql.DatabaseMetaData#getSchemas} method.
 */
public class SchemaEntity {

    public String tableSchema;
    public String tableCatalog;

}