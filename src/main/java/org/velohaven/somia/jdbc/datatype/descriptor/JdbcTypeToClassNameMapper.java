package org.velohaven.somia.jdbc.datatype.descriptor;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;


/**
 * Static mapping from JDBC type codes ({@link java.sql.Types}) to the corresponding Java class names as returned by
 * {@link Class#getName()}.
 * <p>
 * {@link java.sql.ResultSetMetaData#getColumnClassName(int)} specifies that it returns the fully qualified name of the
 * Java class, but this is ambiguous for certain types such as arrays. Most JDBC drivers use the JVM internal type name
 * format returned by {@link Class#getName()}, for example {@code "[B"} for a {@code byte[]} column, instead of the
 * source-level name {@code "byte[]"} reported by {@link Class#getCanonicalName()}.
 * <p>
 * This map aims to provide a consistent default mapping that aligns with the {@link Class#getName()} notation typically
 * used by JDBC drivers. For several JDBC types, the mapping intentionally uses the standard {@code java.sql} interfaces
 * (such as {@link java.sql.Blob}, {@link java.sql.Clob}, {@link java.sql.Array}, {@link java.sql.ResultSet}) rather than
 * concrete implementation classes. Individual JDBC drivers may return driver-specific implementation classes instead of
 * these interfaces, so this mapping should be understood as a canonical default rather than an exhaustive or
 * driver-specific list of possible return types.
 */
public class JdbcTypeToClassNameMapper {
    public static final Map<Integer, String> JDBC_TYPE_TO_CLASS_NAME_MAP = new HashMap<>();

    static {
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.ARRAY, java.sql.Array.class.getName());
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.BIGINT, Long.class.getName());
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.BINARY, byte[].class.getName());
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.BIT, Boolean.class.getName());
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.BLOB, java.sql.Blob.class.getName());
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.BOOLEAN, Boolean.class.getName());
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.CHAR, String.class.getName());
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.CLOB, java.sql.Clob.class.getName());
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.DATALINK, java.net.URL.class.getName());
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.DATE, java.sql.Date.class.getName());
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.DECIMAL, java.math.BigDecimal.class.getName());
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.DISTINCT, Object.class.getName());
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.DOUBLE, Double.class.getName());
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.FLOAT, Double.class.getName());
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.INTEGER, Integer.class.getName());
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.JAVA_OBJECT, Object.class.getName());
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.LONGNVARCHAR, String.class.getName());
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.LONGVARBINARY, byte[].class.getName());
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.LONGVARCHAR, String.class.getName());
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.NCHAR, String.class.getName());
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.NCLOB, java.sql.NClob.class.getName());
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.NULL, Object.class.getName());
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.NUMERIC, java.math.BigDecimal.class.getName());
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.NVARCHAR, String.class.getName());
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.OTHER, Object.class.getName());
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.REAL, Float.class.getName());
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.REF, java.sql.Ref.class.getName());
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.REF_CURSOR, java.sql.ResultSet.class.getName());
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.ROWID, java.sql.RowId.class.getName());
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.SMALLINT, Short.class.getName());
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.SQLXML, java.sql.SQLXML.class.getName());
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.STRUCT, java.sql.Struct.class.getName());
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.TIME, java.sql.Time.class.getName());
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.TIME_WITH_TIMEZONE, java.time.OffsetTime.class.getName());
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.TIMESTAMP, java.sql.Timestamp.class.getName());
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.TIMESTAMP_WITH_TIMEZONE, java.time.OffsetDateTime.class.getName());
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.TINYINT, Byte.class.getName());
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.VARBINARY, byte[].class.getName());
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.VARCHAR, String.class.getName());
    }
}