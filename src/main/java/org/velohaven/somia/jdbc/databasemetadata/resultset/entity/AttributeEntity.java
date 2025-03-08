package org.velohaven.somia.jdbc.databasemetadata.resultset.entity;

/**
 * Represents a row in the result set of the {@link java.sql.DatabaseMetaData#getAttributes} method.
 */
public class AttributeEntity {

    public String tableCatalog;
    public String tableSchema;
    public String tableName;
    public String attributeName;
    public Integer dataType;
    public String attributeTypeName;
    public Integer attributeSize;
    public Integer decimalDigits;
    public Integer numericPrecisionRadix;
    public Integer nullable;
    public String remarks;
    public String attrDef;
    public Integer sqlDataType;
    public Integer sqlDatetimeSub;
    public Integer charOctetLength;
    public Integer ordinalPosition;
    public String isNullable;
    public String scopeCatalog;
    public String scopeSchema;
    public String scopeTable;
    public Short sourceDataType;

}