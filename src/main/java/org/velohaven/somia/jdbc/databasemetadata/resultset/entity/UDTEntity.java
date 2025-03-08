package org.velohaven.somia.jdbc.databasemetadata.resultset.entity;

/**
 * Represents a row in the result set of the {@link java.sql.DatabaseMetaData#getUDTs} method.
 */
public class UDTEntity {

    public String tableCatalog;
    public String tableSchema;
    public String typeName;
    public String className;
    public int dataType;
    public String remarks;
    public short baseType;

}