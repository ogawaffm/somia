package org.velohaven.somia.jdbc.datatype.descriptor;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

public class JdbcTypeToClassNameMapper {
    public static final Map<Integer, String> JDBC_TYPE_TO_CLASS_NAME_MAP = new HashMap<>();

    static {
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.ARRAY, "java.sql.Array");
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.BIGINT, "java.lang.Long");
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.BINARY, "byte[]");
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.BIT, "java.lang.Boolean");
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.BLOB, "java.sql.Blob");
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.BOOLEAN, "java.lang.Boolean");
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.CHAR, "java.lang.String");
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.CLOB, "java.sql.Clob");
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.DATALINK, "java.net.URL");
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.DATE, "java.sql.Date");
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.DECIMAL, "java.math.BigDecimal");
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.DISTINCT, "java.lang.Object");
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.DOUBLE, "java.lang.Double");
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.FLOAT, "java.lang.Double");
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.INTEGER, "java.lang.Integer");
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.JAVA_OBJECT, "java.lang.Object");
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.LONGNVARCHAR, "java.lang.String");
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.LONGVARBINARY, "byte[]");
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.LONGVARCHAR, "java.lang.String");
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.NCHAR, "java.lang.String");
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.NCLOB, "java.sql.NClob");
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.NULL, "java.lang.Object");
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.NUMERIC, "java.math.BigDecimal");
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.NVARCHAR, "java.lang.String");
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.OTHER, "java.lang.Object");
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.REAL, "java.lang.Float");
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.REF, "java.sql.Ref");
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.REF_CURSOR, "java.sql.Ref");
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.ROWID, "java.sql.RowId");
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.SMALLINT, "java.lang.Short");
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.SQLXML, "java.sql.SQLXML");
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.STRUCT, "java.sql.Struct");
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.TIME, "java.sql.Time");
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.TIME_WITH_TIMEZONE, "java.time.OffsetTime");
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.TIMESTAMP, "java.sql.Timestamp");
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.TIMESTAMP_WITH_TIMEZONE, "java.time.OffsetDateTime");
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.TINYINT, "java.lang.Byte");
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.VARBINARY, "byte[]");
        JDBC_TYPE_TO_CLASS_NAME_MAP.put(Types.VARCHAR, "java.lang.String");
    }
}