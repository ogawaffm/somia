package org.velohaven.somia.jdbc.databasemetadata.resultset.entity;

/**
 * Represents a row in the result set of the {@link java.sql.DatabaseMetaData#getFunctionColumns} method.
 */
public class FunctionColumnEntity {

    public String functionCatalog;
    public String functionSchema;
    public String functionName;
    public String columnName;
    public Short columnType;
    public Integer dataType;
    public String typeName;
    public Integer precision;
    public Integer length;
    public Short scale;
    public Short numericPrecisionRadix;
    public Short nullable;
    public String remarks;
    public Integer charOctetLength;
    public Integer ordinalPosition;
    public String isNullable;
    public String specificName;

}