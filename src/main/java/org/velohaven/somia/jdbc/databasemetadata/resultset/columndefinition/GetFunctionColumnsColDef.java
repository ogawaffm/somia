package org.velohaven.somia.jdbc.databasemetadata.resultset.columndefinition;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.sql.DatabaseMetaData;
import java.sql.SQLType;

import static java.sql.JDBCType.*;

/**
 * The column names, ordinal positions, types and nullability for the
 * {@link DatabaseMetaData#getFunctionColumns(String, String, String, String)}
 * method.
 */
@Accessors(fluent = true)
@Getter(onMethod_ = @Override)
@AllArgsConstructor
public enum GetFunctionColumnsColDef implements ColDefByEnum<GetFunctionColumnsColDef> {

    FUNCTION_CAT(VARCHAR, true),
    FUNCTION_SCHEM(VARCHAR, true),
    FUNCTION_NAME(VARCHAR, false),
    COLUMN_NAME(VARCHAR, false),
    COLUMN_TYPE(SMALLINT, false),
    DATA_TYPE(INTEGER, false),
    TYPE_NAME(VARCHAR, false),
    PRECISION(INTEGER, true),
    LENGTH(INTEGER, true),
    SCALE(SMALLINT, true),
    RADIX(SMALLINT, true),
    NULLABLE(SMALLINT, false),
    REMARKS(VARCHAR, true),
    CHAR_OCTET_LENGTH(INTEGER, true),
    ORDINAL_POSITION(INTEGER, false),
    IS_NULLABLE(VARCHAR, false),
    SPECIFIC_NAME(VARCHAR, false);

    final private SQLType sqlType;
    final private boolean isNullable;

}