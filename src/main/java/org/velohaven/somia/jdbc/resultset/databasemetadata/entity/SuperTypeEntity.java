package org.velohaven.somia.jdbc.resultset.databasemetadata.entity;

/**
 * Represents a row in the result set of the {@link java.sql.DatabaseMetaData#getSuperTypes} method.
 */
public class SuperTypeEntity {

    public String tableCatalog;
    public String tableSchema;
    public String tableName;
    public String superTypeCatalog;
    public String superTypeSchema;
    public String superTypeName;

}