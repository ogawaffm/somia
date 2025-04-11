package org.velohaven.somia.jdbc.resultset.databasemetadata.columndefinition;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.sql.DatabaseMetaData;
import java.sql.SQLType;

import static java.sql.JDBCType.*;

/**
 * The column names, ordinal positions, types and nullability for the
 * {@link DatabaseMetaData#getColumns(String, String, String, String)}
 * method.
 */
@Accessors(fluent = true)
@Getter(onMethod_ = @Override)
@AllArgsConstructor
public enum GetColumnsColDef implements ColDefByEnum<GetColumnsColDef> {

    TABLE_CAT(VARCHAR, true),
    TABLE_SCHEM(VARCHAR, true),
    TABLE_NAME(VARCHAR, false),
    COLUMN_NAME(VARCHAR, false),
    DATA_TYPE(INTEGER, false),
    TYPE_NAME(VARCHAR, false),
    COLUMN_SIZE(INTEGER, false),
    BUFFER_LENGTH(INTEGER, true),
    DECIMAL_DIGITS(INTEGER, true),
    NUM_PREC_RADIX(INTEGER, true),
    NULLABLE(INTEGER, false),
    REMARKS(VARCHAR, true),
    COLUMN_DEF(VARCHAR, true),
    SQL_DATA_TYPE(INTEGER, true),
    SQL_DATETIME_SUB(INTEGER, true),
    CHAR_OCTET_LENGTH(INTEGER, true),
    ORDINAL_POSITION(INTEGER, false),
    IS_NULLABLE(VARCHAR, false),
    SCOPE_CATALOG(VARCHAR, true),
    SCOPE_SCHEMA(VARCHAR, true),
    SCOPE_TABLE(VARCHAR, true),
    SOURCE_DATA_TYPE(SMALLINT, true),
    IS_AUTOINCREMENT(VARCHAR, false),
    IS_GENERATEDCOLUMN(VARCHAR, false);

    final private SQLType sqlType;
    final private boolean isNullable;

}