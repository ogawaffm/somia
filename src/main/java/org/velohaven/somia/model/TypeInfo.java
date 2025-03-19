package org.velohaven.somia.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

@SuppressWarnings({"UnusedReturnValue", "unused"})
@ToString(callSuper = true)
@EqualsAndHashCode()
@Accessors(fluent = true)
@Getter
public class TypeInfo implements WithToJson {

    private String name;
    private int dataType;
    private String localTypeName;

    public String literalPrefix;
    public String literalSuffix;
    private String createParams;

    private boolean isNullable;
    private boolean isCaseSensitive;
    private boolean isSearchable;
    private boolean isUnsignedAttribute;
    private boolean isAutoIncrement;

    public int precision;
    private int numericPrecisionRadix;

    private int minimumScale;
    private int maximumScale;
    private boolean isFixedPrecisionScale;

    public int sqlDataType;
    public int sqlDatetimeSub;

    public TypeInfo name(String name) {
        this.name = name;
        return this;
    }

    public TypeInfo dataType(int dataType) {
        this.dataType = dataType;
        return this;
    }

    public TypeInfo localTypeName(String localTypeName) {
        this.localTypeName = localTypeName;
        return this;
    }

    public TypeInfo literalPrefix(String literalPrefix) {
        this.literalPrefix = literalPrefix;
        return this;
    }

    public TypeInfo literalSuffix(String literalSuffix) {
        this.literalSuffix = literalSuffix;
        return this;
    }

    public TypeInfo createParams(String createParams) {
        this.createParams = createParams;
        return this;
    }

    public TypeInfo isNullable(boolean isNullable) {
        this.isNullable = isNullable;
        return this;
    }

    public TypeInfo isCaseSensitive(boolean isCaseSensitive) {
        this.isCaseSensitive = isCaseSensitive;
        return this;
    }

    public TypeInfo isSearchable(boolean isSearchable) {
        this.isSearchable = isSearchable;
        return this;
    }

    public TypeInfo isUnsignedAttribute(boolean isUnsignedAttribute) {
        this.isUnsignedAttribute = isUnsignedAttribute;
        return this;
    }

    public TypeInfo isAutoIncrement(boolean isAutoIncrement) {
        this.isAutoIncrement = isAutoIncrement;
        return this;
    }

    public TypeInfo precision(int precision) {
        this.precision = precision;
        return this;
    }

    public TypeInfo numericPrecisionRadix(int numericPrecisionRadix) {
        this.numericPrecisionRadix = numericPrecisionRadix;
        return this;
    }

    public TypeInfo minimumScale(int minimumScale) {
        this.minimumScale = minimumScale;
        return this;
    }

    public TypeInfo maximumScale(int maximumScale) {
        this.maximumScale = maximumScale;
        return this;
    }

    public TypeInfo isFixedPrecisionScale(boolean fixedPrecisionScale) {
        this.isFixedPrecisionScale = fixedPrecisionScale;
        return this;
    }

    public TypeInfo sqlDataType(int sqlDataType) {
        this.sqlDataType = sqlDataType;
        return this;
    }

    public TypeInfo sqlDatetimeSub(int sqlDatetimeSub) {
        this.sqlDatetimeSub = sqlDatetimeSub;
        return this;
    }


}
