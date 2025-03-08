package org.velohaven.somia.jdbc.databasemetadata.resultset.columndefinition;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.sql.DatabaseMetaData;
import java.sql.SQLType;

import static java.sql.JDBCType.*;

/**
 * The column names, ordinal positions, types and nullability for the
 * {@link DatabaseMetaData#getPseudoColumns(String, String, String, String)}
 * method.
 */
@Accessors(fluent = true)
@Getter(onMethod_ = @Override)
@AllArgsConstructor
public enum GetPseudoColumnsColDef implements ColDefByEnum<GetPseudoColumnsColDef> {

    TABLE_CAT(VARCHAR, true),
    TABLE_SCHEM(VARCHAR, true),
    TABLE_NAME(VARCHAR, false),
    COLUMN_NAME(VARCHAR, false),
    DATA_TYPE(INTEGER, false),
    COLUMN_SIZE(INTEGER, false),
    DECIMAL_DIGITS(INTEGER, true),
    NUM_PREC_RADIX(INTEGER, true),
    COLUMN_USAGE(VARCHAR, true),
    REMARKS(VARCHAR, true),
    CHAR_OCTET_LENGTH(INTEGER, true),
    IS_NULLABLE(VARCHAR, false);

    private final SQLType sqlType;
    private final boolean isNullable;

}