package org.velohaven.somia.jdbc.datatype;

/**
 * This interface provides methods to test the type number create typeNumber()-method against all known JDBC types.
 * For each type, a method is provided to check whether the type number is create this type.
 */
public interface JdbcTypeNumberTester {

    int typeNumber();

    /* ************************************************************************************************************* */
    /* *********************************************** string types ************************************************ */
    /* ************************************************************************************************************* */

    /**
     * Returns whether the type number is {@link java.sql.Types#CHAR}.
     * @return true if the type is of this type
     */
    default boolean is_CHAR() {
        return typeNumber() == java.sql.Types.CHAR;
    }

    /**
     * Returns whether the type number is {@link java.sql.Types#VARCHAR}.
     * @return true if the type is of this type
     */
    default boolean is_VARCHAR() {
        return typeNumber() == java.sql.Types.VARCHAR;
    }

    /**
     * Returns whether the type number is {@link java.sql.Types#LONGVARCHAR}.
     * @return true if the type is of this type
     */
    default boolean is_LONGVARCHAR() {
        return typeNumber() == java.sql.Types.LONGVARCHAR;
    }

    /**
     * Returns whether the type number is {@link java.sql.Types#CLOB}.
     * @return true if the type is of this type
     */
    default boolean is_CLOB() {
        return typeNumber() == java.sql.Types.CLOB;
    }

    /**
     * Returns whether the type number is {@link java.sql.Types#NCHAR}.
     * @return true if the type is of this type
     */
    default boolean is_NCHAR() {
        return typeNumber() == java.sql.Types.NCHAR;
    }

    /**
     * Returns whether the type number is {@link java.sql.Types#NVARCHAR}.
     * @return true if the type is of this type
     */
    default boolean is_NVARCHAR() {
        return typeNumber() == java.sql.Types.NVARCHAR;
    }

    /**
     * Returns whether the type number is {@link java.sql.Types#LONGNVARCHAR}.
     * @return true if the type is of this type
     */
    default boolean is_LONGNVARCHAR() {
        return typeNumber() == java.sql.Types.LONGNVARCHAR;
    }

    /**
     * Returns whether the type number is {@link java.sql.Types#NCLOB}.
     * @return true if the type is of this type
     */
    default boolean is_NCLOB() {
        return typeNumber() == java.sql.Types.NCLOB;
    }

    /**
     * Returns whether the type number is {@link java.sql.Types#BINARY}.
     * @return true if the type is of this type
     */
    default boolean is_BINARY() {
        return typeNumber() == java.sql.Types.BINARY;
    }

    /**
     * Returns whether the type number is {@link java.sql.Types#VARBINARY}.
     * @return true if the type is of this type
     */
    default boolean is_VARBINARY() {
        return typeNumber() == java.sql.Types.VARBINARY;
    }

    /**
     * Returns whether the type number is {@link java.sql.Types#LONGVARBINARY}.
     * @return true if the type is of this type
     */
    default boolean is_LONGVARBINARY() {
        return typeNumber() == java.sql.Types.LONGVARBINARY;
    }

    /**
     * Returns whether the type number is {@link java.sql.Types#BLOB}.
     * @return true if the type is of this type
     */
    default boolean is_BLOB() {
        return typeNumber() == java.sql.Types.BLOB;
    }

    /* ************************************************************************************************************* */
    /* *********************************************** numeric types *********************************************** */
    /* ************************************************************************************************************* */

    /**
     * Returns whether the type number is {@link java.sql.Types#NUMERIC}.
     * @return true if the type is of this type
     */
    default boolean is_NUMERIC() {
        return typeNumber() == java.sql.Types.NUMERIC;
    }

    /**
     * Returns whether the type number is {@link java.sql.Types#DECIMAL}.
     * @return true if the type is of this type
     */
    default boolean is_DECIMAL() {
        return typeNumber() == java.sql.Types.DECIMAL;
    }

    /**
     * Returns whether the type number is {@link java.sql.Types#BIT}.
     * @return true if the type is of this type
     */
    default boolean is_BIT() {
        return typeNumber() == java.sql.Types.BIT;
    }

    /**
     * Returns whether the type number is {@link java.sql.Types#TINYINT}.
     * @return true if the type is of this type
     */
    default boolean is_TINYINT() {
        return typeNumber() == java.sql.Types.TINYINT;
    }

    /**
     * Returns whether the type number is {@link java.sql.Types#SMALLINT}.
     * @return true if the type is of this type
     */
    default boolean is_SMALLINT() {
        return typeNumber() == java.sql.Types.SMALLINT;
    }

    /**
     * Returns whether the type number is {@link java.sql.Types#INTEGER}.
     * @return true if the type is of this type
     */
    default boolean is_INTEGER() {
        return typeNumber() == java.sql.Types.INTEGER;
    }

    /**
     * Returns whether the type number is {@link java.sql.Types#BIGINT}.
     * @return true if the type is of this type
     */
    default boolean is_BIGINT() {
        return typeNumber() == java.sql.Types.BIGINT;
    }

    /**
     * Returns whether the type number is {@link java.sql.Types#REAL}.
     * @return true if the type is of this type
     */
    default boolean is_REAL() {
        return typeNumber() == java.sql.Types.REAL;
    }

    /**
     * Returns whether the type number is {@link java.sql.Types#FLOAT}.
     * @return true if the type is of this type
     */
    default boolean is_FLOAT() {
        return typeNumber() == java.sql.Types.FLOAT;
    }

    /**
     * Returns whether the type number is {@link java.sql.Types#DOUBLE}.
     * @return true if the type is of this type
     */
    default boolean is_DOUBLE() {
        return typeNumber() == java.sql.Types.DOUBLE;
    }

    /* ************************************************************************************************************* */
    /* *********************************************** datetime types ********************************************** */
    /* ************************************************************************************************************* */

    /**
     * Returns whether the type number is {@link java.sql.Types#DATE}.
     * @return true if the type is of this type
     */
    default boolean is_DATE() {
        return typeNumber() == java.sql.Types.DATE;
    }

    /**
     * Returns whether the type number is {@link java.sql.Types#TIME}.
     * @return true if the type is of this type
     */
    default boolean is_TIME() {
        return typeNumber() == java.sql.Types.TIME;
    }

    /**
     * Returns whether the type number is {@link java.sql.Types#TIME_WITH_TIMEZONE}.
     * @return true if the type is of this type
     */
    default boolean is_TIME_WITH_TIMEZONE() {
        return typeNumber() == java.sql.Types.TIME_WITH_TIMEZONE;
    }

    /**
     * Returns whether the type number is {@link java.sql.Types#TIMESTAMP}.
     * @return true if the type is of this type
     */
    default boolean is_TIMESTAMP() {
        return typeNumber() == java.sql.Types.TIMESTAMP;
    }

    /**
     * Returns whether the type number is {@link java.sql.Types#TIMESTAMP_WITH_TIMEZONE}.
     * @return true if the type is of this type
     */
    default boolean is_TIMESTAMP_WITH_TIMEZONE() {
        return typeNumber() == java.sql.Types.TIMESTAMP_WITH_TIMEZONE;
    }

    /* ************************************************************************************************************* */
    /* ********************************************* user-defined types ******************************************** */
    /* ************************************************************************************************************* */

    /**
     * Returns whether the type number is {@link java.sql.Types#DISTINCT}.
     * @return true if the type is of this type
     */
    default boolean is_DISTINCT() {
        return typeNumber() == java.sql.Types.DISTINCT;
    }

    /**
     * Returns whether the type number is {@link java.sql.Types#STRUCT}.
     * @return true if the type is of this type
     */
    default boolean is_STRUCT() {
        return typeNumber() == java.sql.Types.STRUCT;
    }

    /* ************************************************************************************************************* */
    /* ************************************************ other types ************************************************ */
    /* ************************************************************************************************************* */

    /**
     * Returns whether the type number is {@link java.sql.Types#NULL}.
     * @return true if the type is of this type
     */
    default boolean is_NULL() {
        return typeNumber() == java.sql.Types.NULL;
    }

    /**
     * Returns whether the type number is {@link java.sql.Types#BOOLEAN}.
     * @return true if the type is of this type
     */
    default boolean is_BOOLEAN() {
        return typeNumber() == java.sql.Types.BOOLEAN;
    }

    /**
     * Returns whether the type number is {@link java.sql.Types#ROWID}.
     * @return true if the type is of this type
     */
    default boolean is_ROWID() {
        return typeNumber() == java.sql.Types.ROWID;
    }

    /**
     * Returns whether the type number is {@link java.sql.Types#ARRAY}.
     * @return true if the type is of this type
     */
    default boolean is_ARRAY() {
        return typeNumber() == java.sql.Types.ARRAY;
    }

    /**
     * Returns whether the type number is {@link java.sql.Types#JAVA_OBJECT}.
     * @return true if the type is of this type
     */
    default boolean is_JAVA_OBJECT() {
        return typeNumber() == java.sql.Types.JAVA_OBJECT;
    }

    /**
     * Returns whether the type number is {@link java.sql.Types#SQLXML}.
     * @return true if the type is of this type
     */
    default boolean is_XML() {
        return typeNumber() == java.sql.Types.SQLXML;
    }

    /**
     * Returns whether the type number is {@link java.sql.Types#DATALINK}.
     * @return true if the type is of this type
     */
    default boolean is_DATALINK() {
        return typeNumber() == java.sql.Types.DATALINK;
    }

    /**
     * Returns whether the type number is {@link java.sql.Types#REF}.
     * @return true if the type is of this type
     */
    default boolean is_REF() {
        return typeNumber() == java.sql.Types.REF;
    }

    /**
     * Returns whether the type number is {@link java.sql.Types#REF_CURSOR}.
     * @return true if the type is of this type
     */
    default boolean is_REF_CURSOR() {
        return typeNumber() == java.sql.Types.REF_CURSOR;
    }

}
