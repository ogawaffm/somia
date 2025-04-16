package org.velohaven.somia.jdbc.resultset.databasemetadata.entity;

/**
 * Represents a row in the result set of the {@link java.sql.DatabaseMetaData#getPseudoColumns} method.
 */
public class PseudoColumnEntity {

    public String tableCatalog;
    public String tableSchema;
    public String tableName;
    public String columnName;
    public Integer dataType;
    public Integer columnSize;
    public Integer decimalDigits;
    public Integer numericPrecisionRadix;
    public String columnUsage;
    public String remarks;
    public Integer charOctetLength;
    public String isNullable;

}