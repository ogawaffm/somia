package org.velohaven.somia.jdbc.datatype.descriptor;

import org.velohaven.somia.jdbc.datatype.DataType;

public record DataTypePropsRecord<T extends DataType<T>>(
    String name,
    int typeNumber,
    String className,
    int precision,
    int scale,
    boolean isNullable,
    boolean isAutoIncrement,
    int numericPrecisionRadix,

    boolean isCaseSensitive,
    boolean isSearchable,
    boolean isSigned,
    boolean isCurrency,
    int columnDisplaySize,

    boolean isString,
    boolean isCharString,
    boolean isNationalCharString,
    boolean isNonNationalCharString,
    boolean isBinaryString,
    boolean isVarLengthString,
    boolean isFixedLengthString,

    boolean isNumber,
    boolean isExactNumber,
    boolean isApproximateNumber,
    boolean isBinaryIntegerNumber,
    boolean isIntegralNumber,
    boolean isFixedPointNumber,
    boolean isFloatingPointNumber,

    boolean isDatetime,
    boolean storesDate,
    boolean storesTime,
    boolean storesTimestamp,
    boolean storesTimezone,
    boolean storesLocalTime,

    boolean isLongSize,
    boolean isLob,

    boolean supportsSize,
    boolean supportsOptionalSize,
    boolean requiresSize,
    int maxSize,
    int defaultSize,

    boolean supportsPrecision,
    boolean supportsOptionalPrecision,
    boolean requiresPrecision,
    int maxPrecision,
    int defaultPrecision,

    boolean supportsScale,
    boolean supportsOptionalScale,
    boolean requiresScale,
    int maxScale,
    int defaultScale,

    boolean isUserDefined,

    T baseType

) implements DataType<T> {
}
