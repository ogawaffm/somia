package org.velohaven.somia.jdbc.resultset.databasemetadata.entity;

/**
 * Represents a row in the result set of the {@link java.sql.DatabaseMetaData#getFunctions} method.
 */
public class FunctionEntity {

    public String functionCatalog;
    public String functionSchema;
    public String functionName;
    public String remarks;
    public Short functionType;
    public String specificName;

}