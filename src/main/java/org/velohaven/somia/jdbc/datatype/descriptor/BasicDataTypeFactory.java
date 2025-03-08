package org.velohaven.somia.jdbc.datatype.descriptor;

import java.util.HashMap;
import java.util.Map;

import static java.sql.Types.*;
import static org.velohaven.somia.jdbc.datatype.SqlDataTypeInfo.*;

public class BasicDataTypeFactory {

    public static final Map<Integer, BasicDataType> DEFAULT_BASIC_DATA_TYPE = new HashMap<>();

    static private final int SMALL_SIZE = 255;
    static private final int LONG_SIZE = 65535;
    static private final int LOB_SIZE = 2147483647;

    static private final int MAX_DECIMAL_PRECISION = 38;

    static private final int SEPARATOR_WIDTH = 1;  // between DATE and TIME " ", TIME and time zone "+" or fraction "."
    static private final int DATE_WIDTH = 10;      // yyyy-MM-dd or dd.MM.yyyy
    static private final int TIME_WIDTH = 8;       // HH:mm:ss
    static private final int TIMEZONE_WIDTH = 5;   // HH:mm
    static private final int TIME_WITH_TIMEZONE_WIDTH = TIME_WIDTH + SEPARATOR_WIDTH + TIMEZONE_WIDTH;
    static private final int TIMESTAMP_WIDTH = DATE_WIDTH + SEPARATOR_WIDTH + TIME_WIDTH;
    static private final int TIMESTAMP_WITH_TIMEZONE_WIDTH = TIMESTAMP_WIDTH + SEPARATOR_WIDTH + TIMEZONE_WIDTH;
    static private final int MAX_FRAC_WIDTH = 9;  // 9 digits

    static private final int MAX_FRAC_TIME_WIDTH = TIME_WIDTH + SEPARATOR_WIDTH + MAX_FRAC_WIDTH;
    static private final int MAX_FRAC_TIME_WITH_TIMEZONE_WIDTH = TIME_WITH_TIMEZONE_WIDTH
        + SEPARATOR_WIDTH
        + MAX_FRAC_WIDTH;
    static private final int MAX_FRAC_TIMESTAMP_WIDTH = TIMESTAMP_WIDTH + SEPARATOR_WIDTH + MAX_FRAC_WIDTH;
    static private final int MAX_FRAC_TIMESTAMP_WITH_TIMEZONE_WIDTH = TIMESTAMP_WITH_TIMEZONE_WIDTH
        + SEPARATOR_WIDTH
        + MAX_FRAC_WIDTH;

    static {
        DEFAULT_BASIC_DATA_TYPE.put(ARRAY,
            new BasicDataType("ARRAY", ARRAY, "java.sql.Array", 0, 0, true, false, 0, false, true, false, false, 0,
                null));
        DEFAULT_BASIC_DATA_TYPE.put(BIGINT,
            new BasicDataType("BIGINT", BIGINT, "java.lang.Long", 19, 0, true, false, 2, false, true, true, false, 20,
                null));
        DEFAULT_BASIC_DATA_TYPE.put(BINARY,
            new BasicDataType("BINARY", BINARY, "byte[]", SMALL_SIZE, 0, true, false, 0, false, true, false, false, 510,
                null));
        DEFAULT_BASIC_DATA_TYPE.put(BIT,
            new BasicDataType("BIT", BIT, "java.lang.Boolean", 1, 0, true, false, 2, false, true, false, false, 1,
                null));
        DEFAULT_BASIC_DATA_TYPE.put(BLOB,
            new BasicDataType("BLOB", BLOB, "java.sql.Blob", LONG_SIZE, 0, true, false, 0, false, false, false, false,
                LONG_SIZE, null));
        DEFAULT_BASIC_DATA_TYPE.put(BOOLEAN,
            new BasicDataType("BOOLEAN", BOOLEAN, "java.lang.Boolean", 1, 0, true, false, 0, false, true, false, false,
                5, null));
        DEFAULT_BASIC_DATA_TYPE.put(CHAR,
            new BasicDataType("CHAR", CHAR, "java.lang.String", SMALL_SIZE, 0, true, false, 0, false, true, false,
                false, SMALL_SIZE, null));
        DEFAULT_BASIC_DATA_TYPE.put(CLOB,
            new BasicDataType("CLOB", CLOB, "java.sql.Clob", LOB_SIZE, 0, true, false, 0, false, false, false, false,
                LOB_SIZE, null));
        DEFAULT_BASIC_DATA_TYPE.put(DATALINK,
            new BasicDataType("DATALINK", DATALINK, "java.net.URL", 0, 0, true, false, 0, false, true, false, false, 0,
                null));
        DEFAULT_BASIC_DATA_TYPE.put(DATE,
            new BasicDataType("DATE", DATE, "java.sql.Date", DATE_WIDTH, 0, true, false, 0, false, true, false, false,
                DATE_WIDTH, null));
        DEFAULT_BASIC_DATA_TYPE.put(DECIMAL,
            new BasicDataType("DECIMAL", DECIMAL, "java.math.BigDecimal", MAX_DECIMAL_PRECISION, 0, true, false, 10,
                false, true, true, false, MAX_DECIMAL_PRECISION, null));
        DEFAULT_BASIC_DATA_TYPE.put(DISTINCT,
            new BasicDataType("DISTINCT", DISTINCT, "java.lang.Object", 0, 0, true, false, 0, false, true, false, false,
                0, null));
        DEFAULT_BASIC_DATA_TYPE.put(DOUBLE,
            new BasicDataType("DOUBLE", DOUBLE, "java.lang.Double", 17, 0, true, false, 2, false, true, true, false, 24,
                null));
        DEFAULT_BASIC_DATA_TYPE.put(FLOAT,
            new BasicDataType("FLOAT", FLOAT, "java.lang.Double", 17, 0, true, false, 2, false, true, true, false, 24,
                null));
        DEFAULT_BASIC_DATA_TYPE.put(INTEGER,
            new BasicDataType("INTEGER", INTEGER, "java.lang.Integer", 10, 0, true, false, 2, false, true, true, false,
                11, null));
        DEFAULT_BASIC_DATA_TYPE.put(JAVA_OBJECT,
            new BasicDataType("JAVA OBJECT", JAVA_OBJECT, "java.lang.Object", LOB_SIZE, 0, true, false, 0, false, false,
                false, false, LOB_SIZE, null));
        DEFAULT_BASIC_DATA_TYPE.put(LONGNVARCHAR,
            new BasicDataType("LONG NVARCHAR", LONGNVARCHAR, "java.lang.String", LONG_SIZE, 0, true, false, 0, false,
                true, false, false, LONG_SIZE, null));
        DEFAULT_BASIC_DATA_TYPE.put(LONGVARBINARY,
            new BasicDataType("LONG VARBINARY", LONGVARBINARY, "byte[]", LONG_SIZE, 0, true, false, 0, false, true,
                false, false, LONG_SIZE, null));
        DEFAULT_BASIC_DATA_TYPE.put(LONGVARCHAR,
            new BasicDataType("LONG VARCHAR", LONGVARCHAR, "java.lang.String", LONG_SIZE, 0, true, false, 0, false,
                true, false, false, LONG_SIZE, null));
        DEFAULT_BASIC_DATA_TYPE.put(NCHAR,
            new BasicDataType("NCHAR", NCHAR, "java.lang.String", SMALL_SIZE, 0, true, false, 0, false, true, false,
                false, SMALL_SIZE, null));
        DEFAULT_BASIC_DATA_TYPE.put(NCLOB,
            new BasicDataType("NCLOB", NCLOB, "java.sql.NClob", LOB_SIZE, 0, true, false, 0, false, true, false, false,
                LOB_SIZE, null));
        DEFAULT_BASIC_DATA_TYPE.put(NULL,
            new BasicDataType("NULL", NULL, "null", 0, 0, true, false, 0, false, true, false, false, 4, null));
        DEFAULT_BASIC_DATA_TYPE.put(NUMERIC,
            new BasicDataType("NUMERIC", NUMERIC, "java.math.BigDecimal", MAX_DECIMAL_PRECISION, 0, true, false, 10,
                false, true, true, false, MAX_DECIMAL_PRECISION, null));
        DEFAULT_BASIC_DATA_TYPE.put(NVARCHAR,
            new BasicDataType("NVARCHAR", NVARCHAR, "java.lang.String", SMALL_SIZE, 0, true, false, 0, false, true,
                false, false, SMALL_SIZE, null));
        DEFAULT_BASIC_DATA_TYPE.put(OTHER,
            new BasicDataType("OTHER", OTHER, "java.lang.Object", 0, 0, true, false, 0, false, true, false, false, 0,
                null));
        DEFAULT_BASIC_DATA_TYPE.put(REAL,
            new BasicDataType("REAL", REAL, "java.lang.Float", 17, 0, true, false, 2, false, true, true, false, 24,
                null));
        DEFAULT_BASIC_DATA_TYPE.put(REF,
            new BasicDataType("REF", REF, "java.sql.Ref", 0, 0, true, false, 0, false, false, false, false, 0, null));
        DEFAULT_BASIC_DATA_TYPE.put(REF_CURSOR,
            new BasicDataType("REF_CURSOR", REF_CURSOR, "java.sql.Ref", 0, 0, true, false, 0, false, false, false,
                false, 0, null));
        DEFAULT_BASIC_DATA_TYPE.put(ROWID,
            new BasicDataType("ROWID", ROWID, "java.sql.RowId", 0, 0, true, false, 0, false, true, false, false, 0,
                null));
        DEFAULT_BASIC_DATA_TYPE.put(SMALLINT,
            new BasicDataType("SMALLINT", SMALLINT, "java.lang.Short", 5, 0, true, false, 2, false, true, true, false,
                6, null));
        DEFAULT_BASIC_DATA_TYPE.put(SQLXML,
            new BasicDataType("SQLXML", SQLXML, "java.sql.SQLXML", LOB_SIZE, 0, true, false, 0, false, false, false,
                false, LOB_SIZE, null));
        DEFAULT_BASIC_DATA_TYPE.put(STRUCT,
            new BasicDataType("STRUCT", STRUCT, "java.sql.Struct", 0, 0, true, false, 0, false, true, false, false, 0,
                null));
        DEFAULT_BASIC_DATA_TYPE.put(TIME,
            new BasicDataType("TIME", TIME, "java.sql.Time", MAX_FRAC_TIME_WIDTH, MAX_FRAC_WIDTH, true, false, 0, false,
                true, false, false, 18, null));
        DEFAULT_BASIC_DATA_TYPE.put(TIME_WITH_TIMEZONE,
            new BasicDataType("TIME WITH TIMEZONE", TIME_WITH_TIMEZONE, "java.time.OffsetTime",
                MAX_FRAC_TIME_WITH_TIMEZONE_WIDTH, MAX_FRAC_WIDTH, true, false, 0, false, true, false, false,
                MAX_FRAC_TIME_WITH_TIMEZONE_WIDTH, null));
        DEFAULT_BASIC_DATA_TYPE.put(TIMESTAMP,
            new BasicDataType("TIMESTAMP", TIMESTAMP, "java.sql.Timestamp", MAX_FRAC_TIMESTAMP_WIDTH, MAX_FRAC_WIDTH,
                true, false, 0, false, true, false, false, MAX_FRAC_TIMESTAMP_WIDTH, null));
        DEFAULT_BASIC_DATA_TYPE.put(TIMESTAMP_WITH_TIMEZONE,
            new BasicDataType("TIMESTAMP WITH TIMEZONE", TIMESTAMP_WITH_TIMEZONE, "java.time.OffsetDateTime",
                MAX_FRAC_TIMESTAMP_WITH_TIMEZONE_WIDTH, MAX_FRAC_WIDTH, true, false, 0, false, true, false, false,
                MAX_FRAC_TIMESTAMP_WITH_TIMEZONE_WIDTH, null));
        DEFAULT_BASIC_DATA_TYPE.put(TINYINT,
            new BasicDataType("TINYINT", TINYINT, "java.lang.Byte", 3, 0, true, false, 2, false, true, true, false, 4,
                null));
        DEFAULT_BASIC_DATA_TYPE.put(VARBINARY,
            new BasicDataType("VARBINARY", VARBINARY, "byte[]", SMALL_SIZE, 0, true, false, 0, false, true, false,
                false, 510, null));
        DEFAULT_BASIC_DATA_TYPE.put(VARCHAR,
            new BasicDataType("VARCHAR", VARCHAR, "java.lang.String", SMALL_SIZE, 0, true, false, 0, false, true, false,
                false, SMALL_SIZE, null));
    }

    public static BasicDataType of(int typeNumber) {

        BasicDataType dataType;

        if (!DEFAULT_BASIC_DATA_TYPE.containsKey(typeNumber)) {
            dataType = DEFAULT_BASIC_DATA_TYPE.get(OTHER);
            dataType.typeNumber = typeNumber;
        } else {
            dataType = DEFAULT_BASIC_DATA_TYPE.get(typeNumber);
        }

        return dataType;

    }

    public static BasicDataType of(int typeNumber, int sizeOrPrecisionOrScale) {

        BasicDataType dataType = of(typeNumber);

        // sizeOrPrecisionOrScale is size?
        if (isStringType(typeNumber)) {
            dataType.precision = sizeOrPrecisionOrScale;
            if (isBinaryStringType(typeNumber)) {
                if (sizeOrPrecisionOrScale < LOB_SIZE / 2) {
                    dataType.columnDisplaySize = sizeOrPrecisionOrScale * 2;
                } else {
                    dataType.columnDisplaySize = LOB_SIZE;
                }
            } else {
                dataType.columnDisplaySize = sizeOrPrecisionOrScale;
            }
        } else if (isFixedPointNumberType(typeNumber)) {
            dataType.precision = sizeOrPrecisionOrScale;
            dataType.scale = 0;
            dataType.columnDisplaySize = sizeOrPrecisionOrScale;
            // is sizeOrPrecisionOrScale fraction scale?
        } else if (isDatetimeType(typeNumber)) {
            int fractionWidth = 0;
            if (sizeOrPrecisionOrScale > 0) {
                // add 1 for fraction separator (dot)
                fractionWidth = sizeOrPrecisionOrScale + 1;
            }
            if (typeNumber == TIME) {
                dataType.precision = TIME_WIDTH + fractionWidth;
            } else if (typeNumber == TIME_WITH_TIMEZONE) {
                dataType.precision = TIME_WITH_TIMEZONE_WIDTH + fractionWidth;
            } else if (typeNumber == DATE) {
                // does not have fraction, but it was set
                dataType.precision = DATE_WIDTH + fractionWidth;
            } else if (typeNumber == TIMESTAMP) {
                dataType.precision = TIMESTAMP_WIDTH + fractionWidth;
            } else if (typeNumber == TIMESTAMP_WITH_TIMEZONE) {
                dataType.precision = TIMESTAMP_WITH_TIMEZONE_WIDTH + fractionWidth;
            }
            dataType.scale = sizeOrPrecisionOrScale;
            dataType.columnDisplaySize = dataType.precision;
        } else {
            dataType.precision = sizeOrPrecisionOrScale;
        }

        return dataType;
    }

    public static BasicDataType of(int typeNumber, int sizeOrPrecision, int scale) {

        BasicDataType dataType = of(typeNumber);

        if (isFixedPointNumberType(typeNumber)) {
            dataType.precision = sizeOrPrecision;
            dataType.scale = scale;
            dataType.columnDisplaySize = sizeOrPrecision + (scale > 0 ? scale + 1 : 0);
        } else {
            // what ever it is, set precision and scale
            dataType.precision = sizeOrPrecision;
            dataType.scale = scale;
        }

        return dataType;
    }
}
