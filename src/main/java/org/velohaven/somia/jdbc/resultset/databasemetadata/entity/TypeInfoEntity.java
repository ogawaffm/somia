package org.velohaven.somia.jdbc.resultset.databasemetadata.entity;

/**
 * Represents a row in the result set of the {@link java.sql.DatabaseMetaData#getTypeInfo} method.
 */
public class TypeInfoEntity {

    public String typeName;
    public Integer dataType;
    public Integer precision;
    public String literalPrefix;
    public String literalSuffix;
    public String createParams;
    public Integer nullable;
    public Boolean caseSensitive;
    public Integer searchable;
    public Boolean unsignedAttribute;
    public Boolean fixedPrecisionScale;
    public Boolean autoIncrement;
    public String localTypeName;
    public Integer minimumScale;
    public Integer maximumScale;
    public Integer sqlDataType;
    public Integer sqlDatetimeSub;
    public Integer numericPrecisionRadix;

}
