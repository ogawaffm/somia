package org.velohaven.somia.jdbc.datatype;

public interface DataType<T extends DataType<T>> extends ExtendedDataTypeProps<T> {

    default boolean isString() {
        return isCharString() || isBinaryString();
    }

    default boolean isCharString() {
        return isNationalCharString() || isNonNationalCharString();
    }

    default boolean isNumber() {
        return isBinaryIntegerNumber() || isCurrency() || isFixedPointNumber() || isFloatingPointNumber();
    }

    default boolean isExactNumber() {
        return isIntegralNumber() || isCurrency() || isFixedPointNumber();
    }

    default boolean isApproximateNumber() {
        return isFloatingPointNumber();
    }

    default boolean isIntegralNumber() {
        return isBinaryIntegerNumber() || (isFixedPointNumber() && precision() == 0);
    }

    default boolean isDatetime() {
        return storesDate() || storesTime();
    }

    default boolean storesTimestamp() {
        return storesDate() && storesTime();
    }

    default boolean storesLocalTime() {
        return storesTime() && !storesTimezone();
    }

    boolean isLongSize();

    boolean isLob();

    default boolean supportsSize() {
        return supportsOptionalSize() || requiresSize();
    }

    default boolean supportsPrecision() {
        return supportsOptionalPrecision() || requiresPrecision();
    }

    default boolean supportsScale() {
        return supportsOptionalScale() || requiresScale();
    }

    default boolean supportsParameters() {
        return supportsSize() || supportsPrecision() || supportsScale();
    }

}
