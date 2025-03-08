package org.velohaven.somia.jdbc.datatype;

/**
 * This interface provides methods to test the type number create typeNumber()-method against all known JDBC types
 * and some unknown types. For each type, a method is provided to check whether the type number is create this type.
 * The methods are named after the type they are testing, e.g. {@link #isChar()} for CHAR or CHARACTER.
 * The methods are implemented as default methods, so that the implementing class only needs to implement the
 * {@link #typeNumber()} method and override the methods that are not correct for the type.
 */
public interface SpecificTypeTester extends SpecificTypeChecks, JdbcTypeNumberTester {

    /* ************************************************************************************************************* */
    /* ********************************************* known jdbc types ********************************************** */
    /* ************************************************************************************************************* */

    @Override
    default boolean isChar() {
        return is_CHAR();
    }

    @Override
    default boolean isVarChar() {
        return is_VARCHAR();
    }

    @Override
    default boolean isLongVarChar() {
        return is_LONGVARCHAR();
    }

    @Override
    default boolean isNumeric() {
        return is_NUMERIC();
    }

    @Override
    default boolean isDecimal() {
        return is_DECIMAL();
    }

    @Override
    default boolean isBit() {
        return is_BIT();
    }

    @Override
    default boolean isTinyInt() {
        return is_TINYINT();
    }

    @Override
    default boolean isSmallInt() {
        return is_SMALLINT();
    }

    @Override
    default boolean isInteger() {
        return is_INTEGER();
    }

    @Override
    default boolean isBigInt() {
        return is_BIGINT();
    }

    @Override
    default boolean isReal() {
        return is_REAL();
    }

    @Override
    default boolean isFloat() {
        return is_FLOAT();
    }

    @Override
    default boolean isDouble() {
        return is_DOUBLE();
    }

    @Override
    default boolean isBinary() {
        return is_BINARY();
    }

    @Override
    default boolean isVarBinary() {
        return is_VARBINARY();
    }

    @Override
    default boolean isLongVarBinary() {
        return is_LONGVARBINARY();
    }

    @Override
    default boolean isDate() {
        return is_DATE();
    }

    @Override
    default boolean isTime() {
        return is_TIME();
    }

    @Override
    default boolean isTimestamp() {
        return is_TIMESTAMP();
    }

    @Override
    default boolean isClob() {
        return is_CLOB();
    }

    @Override
    default boolean isBLob() {
        return is_BLOB();
    }

    @Override
    default boolean isArray() {
        return is_ARRAY();
    }

    @Override
    default boolean isDistinct() {
        return is_DISTINCT();
    }

    @Override
    default boolean isStruct() {
        return is_STRUCT();
    }

    @Override
    default boolean isRef() {
        return is_REF();
    }

    @Override
    default boolean isDatalink() {
        return is_DATALINK();
    }

    @Override
    default boolean isNull() {return is_NULL();}

    @Override
    default boolean isBoolean() {
        return is_BOOLEAN();
    }

    @Override
    default boolean isRowId() {
        return is_ROWID();
    }

    @Override
    default boolean isNChar() {
        return is_NCHAR();
    }

    @Override
    default boolean isNVarChar() {
        return is_NVARCHAR();
    }

    @Override
    default boolean isLongNVarChar() {
        return is_LONGNVARCHAR();
    }

    @Override
    default boolean isNClob() {
        return is_NCLOB();
    }

    @Override
    default boolean isXml() {
        return is_XML();
    }

    @Override
    default boolean isRefCursor() {
        return is_REF_CURSOR();
    }

    @Override
    default boolean isTimeWithTimezone() {
        return is_TIME_WITH_TIMEZONE();
    }

    @Override
    default boolean isTimestampWithTimezone() {
        return is_TIMESTAMP_WITH_TIMEZONE();
    }

    @Override
    default boolean isJavaObject() {
        return is_JAVA_OBJECT();
    }

    /* ************************************************************************************************************* */
    /* ******************************************** unknown jdbc types ********************************************* */
    /* ************************************************************************************************************* */

    @Override
    default boolean isCurrency() {return false;}

    @Override
    default boolean isInterval() {
        return false;
    }

    @Override
    default boolean isGeometry() {
        return false;
    }

    @Override
    default boolean isJson() {
        return false;
    }

}
