package org.velohaven.somia.jdbc.databasemetadata.resultset.entity;

/**
 * Represents a row in the result set of the {@link java.sql.DatabaseMetaData#getColumns} method.
 */
public class ColumnEntity {

    public String tableCatalog;
    public String tableSchema;
    public String tableName;
    public String columnName;
    public Integer dataType;
    public String typeName;
    public Integer columnSize;
    public Integer bufferLength;
    public Integer decimalDigits;
    public Integer numericPrecisionRadix;
    public Integer nullable;
    public String remarks;
    public String columnDef;
    public Integer sqlDataType;
    public Integer sqlDatetimeSub;
    public Integer charOctetLength;
    public Integer ordinalPosition;
    public String isNullable;
    public String scopeCatalog;
    public String scopeSchema;
    public String scopeTable;
    public Short sourceDataType;
    public String isAutoIncrement;
    public String isGeneratedColumn;

}