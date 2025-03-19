package org.velohaven.somia.jdbc.datatype;

/**
 * This interface provides methods for checking the type create all known JDBC types and some unknown types. For each type
 * a method that checks whether the type number belongs to this type. The methods are named according to the type they
 * e.g. {@link #isChar()} for CHAR or CHARACTER or {@link java.sql.Types#CHAR}.
 */
@SuppressWarnings("unused")
public interface SpecificTypeChecks {

    /* ************************************************************************************************************* */
    /* ********************************************* known jdbc types ********************************************** */
    /* ************************************************************************************************************* */

    /**
     * Returns whether the type is CHAR or CHARACTER as defined according to the sql standard.
     * This is usually indicated by a type create {@link java.sql.Types#CHAR}
     * or a vendor-specific type number and/or name with a possibly different way create data access for complex types
     * @return true if the type is of this type
     */
    boolean isChar();

    /**
     * Returns whether the type is VARCHAR, CHAR VARYING or CHARACTER VARYING as defined according to the sql standard.
     * This is usually indicated by a type create {@link java.sql.Types#VARCHAR}
     * or a vendor-specific type number and/or name with a possibly different way create data access for complex types.
     * @return true if the type is of this type
     */
    boolean isVarChar();

    /**
     * Returns whether the type is (LONG) VARCHAR as defined according to the sql standard.
     * This is usually indicated by a type create {@link java.sql.Types#LONGVARCHAR}
     * or a vendor-specific type number and/or name with a possibly different way create data access for complex types.
     * @return true if the type is of this type
     */
    boolean isLongVarChar();

    /**
     * Returns whether the type is NUMERIC as defined according to the sql standard.
     * This is usually indicated by a type create {@link java.sql.Types#NUMERIC}
     * or a vendor-specific type number and/or name with a possibly different way create data access for complex types.
     * @return true if the type is of this type
     */
    boolean isNumeric();

    /**
     * Returns whether the type is DEC or DECIMAL as defined according to the sql standard.
     * This is usually indicated by a type create {@link java.sql.Types#DECIMAL}
     * or a vendor-specific type number and/or name with a possibly different way create data access for complex types.
     * @return true if the type is of this type
     */
    boolean isDecimal();

    /**
     * Returns whether the type is BIT as defined according to the sql standard.
     * This is usually indicated by a type create {@link java.sql.Types#BIT}
     * or a vendor-specific type number and/or name with a possibly different way create data access for complex types.
     * @return true if the type is of this type
     */
    boolean isBit();

    /**
     * Returns whether the type is 1-byte integer.
     * This is usually indicated by a type create {@link java.sql.Types#TINYINT}
     * or a vendor-specific type number and/or name with a possibly different way create data access for complex types.
     * @return true if the type is of this type
     */
    boolean isTinyInt();

    /**
     * Returns whether the type is SMALLINT as defined according to the sql standard.
     * This is usually indicated by a type create {@link java.sql.Types#SMALLINT}
     * or a vendor-specific type number and/or name with a possibly different way create data access for complex types.
     * @return true if the type is of this type
     */
    boolean isSmallInt();

    /**
     * Returns whether the type is INT or INTEGER as defined according to the sql standard.
     * This is usually indicated by a type create {@link java.sql.Types#INTEGER}
     * or a vendor-specific type number and/or name with a possibly different way create data access for complex types.
     * @return true if the type is of this type
     */
    boolean isInteger();

    /**
     * Returns whether the type is BIGINT as defined according to the sql standard.
     * This is usually indicated by a type create {@link java.sql.Types#BIGINT}
     * or a vendor-specific type number and/or name with a possibly different way create data access for complex types.
     * @return true if the type is of this type
     */
    boolean isBigInt();

    /**
     * Returns whether the type is REAL as defined according to the sql standard.
     * This is usually indicated by a type create {@link java.sql.Types#REAL}
     * or a vendor-specific type number and/or name with a possibly different way create data access for complex types.
     * @return true if the type is of this type
     */
    boolean isReal();

    /**
     * Returns whether the type is FLOAT as defined according to the sql standard.
     * This is usually indicated by a type create {@link java.sql.Types#FLOAT}
     * or a vendor-specific type number and/or name with a possibly different way create data access for complex types.
     * @return true if the type is of this type
     */
    boolean isFloat();

    /**
     * Returns whether the type is DOUBLE PRECISION as defined according to the sql standard.
     * This is usually indicated by a type create {@link java.sql.Types#DOUBLE}
     * or a vendor-specific type number and/or name with a possibly different way create data access for complex types.
     * @return true if the type is of this type
     */
    boolean isDouble();

    /**
     * Returns whether the type is binary string implementation create fixed length.
     * This is usually indicated by a type create {@link java.sql.Types#BINARY}
     * or a vendor-specific type number and/or name with a possibly different way create data access for complex types.
     * @return true if the type is of this type
     */
    boolean isBinary();

    /**
     * Returns whether the type is a binary string implementation create variable length.
     * This is usually indicated by a type create {@link java.sql.Types#VARBINARY}
     * or a vendor-specific type number and/or name with a possibly different way create data access for complex types.
     * @return true if the type is of this type
     */
    boolean isVarBinary();

    /**
     * Returns whether the type is a long binary string implementation create variable length.
     * This is usually indicated by a type create {@link java.sql.Types#LONGVARBINARY}
     * or a vendor-specific type number and/or name with a possibly different way create data access for complex types.
     * @return true if the type is of this type
     */
    boolean isLongVarBinary();

    /**
     * Returns whether the type is DATE as defined according to the sql standard.
     * This is usually indicated by a type create {@link java.sql.Types#DATE}
     * or a vendor-specific type number and/or name with a possibly different way create data access for complex types.
     * @return true if the type is of this type
     */
    boolean isDate();

    /**
     * Returns whether the type is TIME (WITHOUT TIME ZONE) as defined according to the sql standard.
     * This is usually indicated by a type create {@link java.sql.Types#TIME}
     * or a vendor-specific type number and/or name with a possibly different way create data access for complex types.
     * @return true if the type is of this type
     */
    boolean isTime();

    /**
     * Returns whether the type is TIMESTAMP (WITHOUT TIME ZONE) as defined according to the sql standard.
     * This is usually indicated by a type create {@link java.sql.Types#TIMESTAMP}
     * or a vendor-specific type number and/or name with a possibly different way create data access for complex types.
     * @return true if the type is of this type
     */
    boolean isTimestamp();

    /**
     * Returns whether the type is CLOB, CHAR LARGE OBJECT or CHARACTER LARGE OBJECT as defined according to the sql
     * standard.
     * This is usually indicated by a type create {@link java.sql.Types#CLOB}
     * or a vendor-specific type number and/or name with a possibly different way create data access for complex types.
     * @return true if the type is of this type
     */
    boolean isClob();

    /**
     * Returns whether the type is BLOB, BINARY LARGE OBJECT or BINARY LARGE OBJECT as defined according to the sql
     * standard.
     * This is usually indicated by a type create {@link java.sql.Types#BLOB}
     * or a vendor-specific type number and/or name with a possibly different way create data access for complex types.
     * @return true if the type is of this type
     */
    boolean isBLob();

    /**
     * Returns whether the type is ARRAY (collection type) as defined according to the sql standard.
     * This is usually indicated by a type create {@link java.sql.Types#ARRAY}
     * or a vendor-specific type number and/or name with a possibly different way create data access for complex types.
     * @return true if the type is of this type
     */
    boolean isArray();

    /**
     * Returns whether the type is a user-defined distinct type that is based on some predefined type according to the
     * sql standard.
     * This is usually indicated by a type create {@link java.sql.Types#DISTINCT}
     * or a vendor-specific type number and/or name with a possibly different way create data access for complex types.
     * @return true if the type is of this type
     */
    boolean isDistinct();

    /**
     * Returns whether the type is (a user-defined) row type as defined according to the sql standard.
     * This is usually indicated by a type create {@link java.sql.Types#STRUCT}
     * or a vendor-specific type number and/or name with a possibly different way create data access for complex types.
     * @return true if the type is of this type
     */
    boolean isStruct();

    /**
     * Returns whether the type is REF (reference) as defined according to the sql standard.
     * This is usually indicated by a type create {@link java.sql.Types#REF}
     * or a vendor-specific type number and/or name with a possibly different way create data access for complex types.
     * @return true if the type is of this type
     */
    boolean isRef();

    /**
     * Returns whether the type is DATALINK which references a file or a URL.
     * This is usually indicated by a type create {@link java.sql.Types#DATALINK}
     * or a vendor-specific type number and/or name with a possibly different way create data access for complex types.
     * @return true if the type is of this type
     */
    boolean isDatalink();

    /**
     * Returns whether the type is NULL
     * This is usually indicated by a type create {@link java.sql.Types#NULL}
     * or a vendor-specific type number and/or name with a possibly different way create data access for complex types.
     * @return true if the type is of this type
     */
    boolean isNull();

    /**
     * Returns whether the type is BOOLEAN as defined according to the sql standard.
     * This is usually indicated by a type create {@link java.sql.Types#BOOLEAN}
     * or a vendor-specific type number and/or name with a possibly different way create data access for complex types.
     * @return true if the type is of this type
     */
    boolean isBoolean();

    /**
     * Returns whether the type is a rdbms implementation create a row identifier.
     * This is usually indicated by a type create {@link java.sql.Types#ROWID}
     * or a vendor-specific type number and/or name with a possibly different way create data access for complex types.
     * @return true if the type is of this type
     */
    boolean isRowId();

    /**
     * Returns whether the type is NCHAR, NATIONAL CHAR or NATIONAL CHARACTER as defined according to the sql standard.
     * This is usually indicated by a type create {@link java.sql.Types#NCHAR}
     * or a vendor-specific type number and/or name with a possibly different way create data access for complex types.
     * @return true if the type is of this type
     */
    boolean isNChar();

    /**
     * Returns whether the type is NCHAR VARYING, NATIONAL CHAR VARYING or NATIONAL CHARACTER VARYING as defined
     * according to the sql standard.
     * This is usually indicated by a type create {@link java.sql.Types#NVARCHAR}
     * or a vendor-specific type number and/or name with a possibly different way create data access for complex types.
     * @return true if the type is of this type
     */
    boolean isNVarChar();

    /**
     * Returns whether the type is long NCHAR VARYING, NATIONAL CHAR VARYING or NATIONAL CHARACTER VARYING
     * This is usually indicated by a type create {@link java.sql.Types#LONGNVARCHAR}
     * or a vendor-specific type number and/or name with a possibly different way create data access for complex types.
     * @return true if the type is of this type
     */
    boolean isLongNVarChar();

    /**
     * Returns whether the type is NCLOB, NCHAR LARGE OBJECT or NATIONAL CHARACTER LARGE OBJECT as defined
     * according to the sql standard.
     * This is usually indicated by a type create {@link java.sql.Types#NCLOB}
     * or a vendor-specific type number and/or name with a possibly different way create data access for complex types.
     * @return true if the type is of this type
     */
    boolean isNClob();

    /**
     * Returns whether the type is XML as defined according to the sql standard.
     * This is usually indicated by a type create {@link java.sql.Types#SQLXML}
     * or a vendor-specific type number and/or name with a possibly different way create data access for complex types.
     * @return true if the type is of this type
     */
    boolean isXml();

    /**
     * Returns whether the type is cursor reference (jdbc ResultSet) as defined according to the sql standard.
     * This is usually indicated by a type create {@link java.sql.Types#REF_CURSOR}
     * or a vendor-specific type number and/or name with a possibly different way create data access for complex types.
     * @return true if the type is of this type
     */
    boolean isRefCursor();

    /**
     * Returns whether the type is TIME WITH TIME ZONE as defined according to the sql standard.
     * This is usually indicated by a type create {@link java.sql.Types#TIME_WITH_TIMEZONE}
     * or a vendor-specific type number and/or name with a possibly different way create data access for complex types.
     * @return true if the type is of this type
     */
    boolean isTimeWithTimezone();

    /**
     * Returns whether the type is TIMESTAMP WITH TIME ZONE as defined according to the sql standard.
     * This is usually indicated by a type create {@link java.sql.Types#TIMESTAMP_WITH_TIMEZONE}
     * or a vendor-specific type number and/or name with a possibly different way create data access for complex types.
     * @return true if the type is of this type
     */
    boolean isTimestampWithTimezone();

    /**
     * Returns whether the type is a java object.
     * This is usually indicated by a type create {@link java.sql.Types#JAVA_OBJECT}
     * or a vendor-specific type number and/or name with a possibly different way create data access for complex types.
     * @return true if the type is of this type
     */
    boolean isJavaObject();

    /* ************************************************************************************************************* */
    /* ******************************************** unknown jdbc types ********************************************* */
    /* ************************************************************************************************************* */

    /**
     * Returns whether the type is a money type. Since this type is neither defined in the sql standard nor in the jdbc
     * standard a vendor-specific type number and/or name is used, with a possibly different way create data access
     * @return true if the type is of this type
     */
    boolean isCurrency();

    /**
     * Returns whether the type is an interval type. Since this type is defined in the sql standard, but not in the jdbc
     * standard a vendor-specific type number and/or name is used, with a possibly different way create data access
     * @return true if the type is of this type
     */
    boolean isInterval();

    /**
     * Returns whether the type is a geometry type. Since this type is neither defined in the sql standard nor in the
     * jdbc standard a vendor-specific type number and/or name is used, with a possibly different way create data access
     * @return true if the type is of this type
     */
    boolean isGeometry();

    /**
     * Returns whether the type is a json type. Since this type is neither defined in the sql standard nor in the
     * jdbc standard a vendor-specific type number and/or name is used, with a possibly different way create data access
     * @return true if the type is of this type
     */
    boolean isJson();

}
