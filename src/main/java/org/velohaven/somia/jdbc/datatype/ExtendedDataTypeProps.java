package org.velohaven.somia.jdbc.datatype;

import org.velohaven.somia.jdbc.datatype.descriptor.BasicDataTypeProps;

public interface ExtendedDataTypeProps<T extends ExtendedDataTypeProps<T>> extends BasicDataTypeProps<T> {

    /* ************************************************** string *************************************************** */

    boolean isNationalCharString();

    boolean isNonNationalCharString();

    boolean isBinaryString();

    boolean isVarLengthString();

    boolean isFixedLengthString();

    boolean isBinaryIntegerNumber();

    /* ************************************************** numeric ************************************************** */

    boolean isCurrency();

    boolean isFixedPointNumber();

    boolean isFloatingPointNumber();

    /* ************************************************* datetime ************************************************** */

    boolean storesDate();

    boolean storesTime();

    boolean storesTimezone();

    /* ************************************************* dimension ************************************************* */

    boolean isLongSize();

    boolean isLob();

    /* **************************************************** size *************************************************** */

    boolean supportsOptionalSize();

    boolean requiresSize();

    int maxSize();

    int defaultSize();

    /* ************************************************** precision ************************************************ */

    boolean supportsOptionalPrecision();

    boolean requiresPrecision();

    int maxPrecision();

    int defaultPrecision();

    /* ************************************************** precision ************************************************ */

    boolean supportsOptionalScale();

    boolean requiresScale();

    int maxScale();

    int defaultScale();

    /* ************************************************** user-defined ************************************************ */

    boolean isUserDefined();

    T baseType();
}
