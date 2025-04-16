package org.velohaven.somia.jdbc.resultset.databasemetadata.entity;

/**
 * Represents a row in the result set of the {@link java.sql.DatabaseMetaData#getTables} method.
 */
public class TableEntity {

    public String tableCatalog;
    public String tableSchema;
    public String tableName;
    public String tableType;
    public String remarks;
    public String typeCatalog;
    public String typeSchema;
    public String typeName;
    public String selfReferencingColName;
    public String refGeneration;

}