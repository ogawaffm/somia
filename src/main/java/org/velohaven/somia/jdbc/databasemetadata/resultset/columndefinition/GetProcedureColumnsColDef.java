package org.velohaven.somia.jdbc.databasemetadata.resultset.columndefinition;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.sql.DatabaseMetaData;

import static java.sql.JDBCType.*;

import java.sql.SQLType;

/**
 * The column names, ordinal positions, types and nullability for the
 * {@link DatabaseMetaData#getProcedureColumns(String, String, String, String)}
 * method.
 */
@Accessors(fluent = true)
@Getter(onMethod_ = @Override)
@AllArgsConstructor
public enum GetProcedureColumnsColDef implements ColDefByEnum<GetProcedureColumnsColDef> {

    PROCEDURE_CAT(VARCHAR, true),
    PROCEDURE_SCHEM(VARCHAR, true),
    PROCEDURE_NAME(VARCHAR, false),
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
    COLUMN_DEF(VARCHAR, true),
    SQL_DATA_TYPE(INTEGER, true),
    SQL_DATETIME_SUB(INTEGER, true),
    CHAR_OCTET_LENGTH(INTEGER, true),
    ORDINAL_POSITION(INTEGER, false),
    IS_NULLABLE(VARCHAR, false),
    SPECIFIC_NAME(VARCHAR, false);

    final private SQLType sqlType;
    final private boolean isNullable;

}