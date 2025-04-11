package org.velohaven.somia.jdbc.resultset.databasemetadata.columndefinition;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.sql.DatabaseMetaData;
import java.sql.SQLType;

import static java.sql.JDBCType.*;

/**
 * The column names, ordinal positions, types and nullability for the
 * {@link DatabaseMetaData#getBestRowIdentifier(String, String, String, int, boolean)} method.
 * method.
 */
@Accessors(fluent = true)
@Getter(onMethod_ = @Override)
@AllArgsConstructor
public enum GetBestRowIdentifierColDef implements ColDefByEnum<GetBestRowIdentifierColDef> {

    SCOPE(SMALLINT, true),
    COLUMN_NAME(VARCHAR, false),
    DATA_TYPE(INTEGER, false),
    TYPE_NAME(VARCHAR, false),
    COLUMN_SIZE(INTEGER, false),
    BUFFER_LENGTH(INTEGER, true),
    DECIMAL_DIGITS(SMALLINT, true),
    PSEUDO_COLUMN(SMALLINT, true);

    final private SQLType sqlType;
    final private boolean isNullable;

}