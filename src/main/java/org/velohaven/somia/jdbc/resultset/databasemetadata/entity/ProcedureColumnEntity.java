package org.velohaven.somia.jdbc.resultset.databasemetadata.entity;

/**
 * Represents a row in the result set of the {@link java.sql.DatabaseMetaData#getProcedureColumns} method.
 */
public class ProcedureColumnEntity {

    public String procedureCatalog;
    public String procedureSchema;
    public String procedureName;
    public String columnName;
    public Short columnType;
    public Integer dataType;
    public String typeName;
    public Integer precision;
    public Integer length;
    public Short scale;
    public Short numericalPrecisionRadix;
    public Short nullable;
    public String remarks;
    public String columnDef;
    public Integer sqlDataType;
    public Integer sqlDatetimeSub;
    public Integer charOctetLength;
    public Integer ordinalPosition;
    public String isNullable;
    public String specificName;

}