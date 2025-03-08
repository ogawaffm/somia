package org.velohaven.somia.jdbc.datatype;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.velohaven.somia.model.WithToJson;

@ToString(callSuper = true)
@EqualsAndHashCode
@Getter(onMethod = @__({@Override}))
@Accessors(fluent = true)
@AllArgsConstructor
public class SqlDataType<T extends SqlDataType<T>> implements DataType<T>, SpecificTypeTester, WithToJson {

    private final String name;
    private final int typeNumber;
    private final String className;
    private final int precision;
    private final int scale;
    private final boolean isNullable;
    private final boolean isAutoIncrement;
    private final int numericPrecisionRadix;

    private final boolean isCaseSensitive;
    private final boolean isSearchable;
    private final boolean isSigned;
    private final boolean isCurrency;
    private final int columnDisplaySize;

    private final T baseType;

    /* *********************************************** stores string *********************************************** */

    @Override
    public boolean isNationalCharString() {
        return is_NCHAR() || is_NVARCHAR() || is_LONGNVARCHAR() || is_NCLOB();
    }

    @Override
    public boolean isNonNationalCharString() {
        return is_CHAR() || is_VARCHAR() || is_LONGVARCHAR() || is_CLOB();
    }

    @Override
    public boolean isBinaryString() {
        return is_BINARY() || is_VARBINARY() || is_LONGVARBINARY() || is_BLOB();
    }

    @Override
    public boolean isVarLengthString() {
        return is_VARCHAR() || is_NVARCHAR()
            || is_LONGVARCHAR() || is_LONGNVARCHAR()
            || is_CLOB() || is_NCLOB()
            || is_VARBINARY() || is_LONGVARBINARY() || is_BLOB();
    }

    @Override
    public boolean isFixedLengthString() {
        return is_CHAR() || is_NCHAR() || is_BINARY();
    }


    /* ********************************************** stores numbers *********************************************** */
    @Override
    public boolean isExactNumber() {
        return isBinaryIntegerNumber() || isFixedPointNumber();
    }

    @Override
    public boolean isApproximateNumber() {
        return isFloatingPointNumber();
    }

    @Override
    public boolean isBinaryIntegerNumber() {
        return is_BIT() || is_TINYINT() || is_SMALLINT() || is_INTEGER() || is_BIGINT();
    }

    @Override
    public boolean isIntegralNumber() {
        return isFixedPointNumber() && (precision() == 0);
    }

    @Override
    public boolean isFixedPointNumber() {
        return is_DECIMAL() || is_NUMERIC() || isCurrency();
    }

    @Override
    public boolean isFloatingPointNumber() {
        return is_FLOAT() || is_REAL() || is_DOUBLE();
    }

    /* ************************************************* datetime ************************************************** */

    @Override
    public boolean storesDate() {
        return is_DATE() || is_TIMESTAMP() || is_TIMESTAMP_WITH_TIMEZONE();
    }

    @Override
    public boolean storesTime() {
        return is_TIME() || is_TIME_WITH_TIMEZONE() || is_TIMESTAMP() || is_TIMESTAMP_WITH_TIMEZONE();
    }

    @Override
    public boolean storesTimestamp() {
        return is_TIMESTAMP() || is_TIMESTAMP_WITH_TIMEZONE();
    }

    @Override
    public boolean storesTimezone() {
        return is_TIME_WITH_TIMEZONE() || is_TIMESTAMP_WITH_TIMEZONE();
    }

    /* *************************************************** size **************************************************** */

    @Override
    public boolean isLongSize() {
        return is_LONGVARCHAR() || is_LONGNVARCHAR() || is_LONGVARBINARY();
    }

    @Override
    public boolean isLob() {
        return is_CLOB() || is_NCLOB() || is_BLOB();
    }

    @Override
    public boolean supportsOptionalSize() {
        return is_CHAR() || is_NCHAR() || is_BINARY() || is_CLOB() || is_NCLOB() || is_BLOB();
    }

    @Override
    public boolean requiresSize() {
        return is_VARCHAR() || is_NVARCHAR() || is_VARBINARY();
    }

    @Override
    public int maxSize() {
        return Integer.MAX_VALUE;
    }

    @Override public int defaultSize() {
        if (is_CHAR() || is_NCHAR() || is_BINARY()) {
            return 1;
        } else if (is_CLOB() || is_NCLOB() || is_BLOB()) {
            return maxSize();
        } else {
            // there is no size, because the de
            return 0;
        }
    }

    @Override
    public boolean supportsOptionalPrecision() {
        return is_DECIMAL() || is_NUMERIC() || is_FLOAT() || storesTime();
    }

    @Override
    public boolean requiresPrecision() {
        // no standard sql type requires precision
        return false;
    }

    @Override public int maxPrecision() {
        if (is_FLOAT()) {
            return 53;
        } else if (is_DECIMAL() || is_NUMERIC()) {
            return 31;
        } else if (is_TIME() || is_TIME_WITH_TIMEZONE() || is_TIMESTAMP() || is_TIMESTAMP_WITH_TIMEZONE()) {
            return 6;
        }
        return 0;
    }

    @Override public int defaultPrecision() {
        if (is_FLOAT()) {
            return 53;
        } else if (is_DECIMAL() || is_NUMERIC()) {
            return 31;
        } else if (is_TIMESTAMP() || is_TIMESTAMP_WITH_TIMEZONE()) {
            return 6;
        }
        // including TIME/TIME WITH TIMEZONE and DECIMAL/NUMERIC
        return 0;
    }

    @Override
    public boolean supportsOptionalScale() {
        return is_DECIMAL() || is_NUMERIC();
    }

    @Override
    public boolean requiresScale() {
        // no standard sql type requires scale
        return false;
    }

    @Override public int maxScale() {
        return (is_DECIMAL() || is_NUMERIC()) ? 31 : 0;
    }

    /**
     * Returns the default scale for the type. This includes DECIMAL, NUMERIC. The default scale for DECIMAL and
     * NUMERIC is 0. For all other types the default scale is 0 because the SQL standard does not define a default
     * @return 0
     */
    @Override
    public int defaultScale() {
        return 0;
    }

    @Override
    public boolean isUserDefined() {
        return is_DISTINCT() || is_STRUCT();
    }

}
