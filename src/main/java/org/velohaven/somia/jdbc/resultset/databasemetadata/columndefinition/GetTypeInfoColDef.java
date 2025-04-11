package org.velohaven.somia.jdbc.resultset.databasemetadata.columndefinition;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.sql.DatabaseMetaData;

import static java.sql.JDBCType.*;

import java.sql.SQLType;

/**
 * The column names, ordinal positions, types and nullability for the
 * {@link DatabaseMetaData#getTypeInfo()}
 * method.
 */
@Accessors(fluent = true)
@Getter(onMethod_ = @Override)
@AllArgsConstructor
public enum GetTypeInfoColDef implements ColDefByEnum<GetTypeInfoColDef> {

    TYPE_NAME(VARCHAR, false),
    DATA_TYPE(INTEGER, false),
    PRECISION(INTEGER, false),
    LITERAL_PREFIX(VARCHAR, true),
    LITERAL_SUFFIX(VARCHAR, true),
    CREATE_PARAMS(VARCHAR, true),
    NULLABLE(INTEGER, false),
    CASE_SENSITIVE(BOOLEAN, false),
    SEARCHABLE(SMALLINT, true),
    UNSIGNED_ATTRIBUTE(BOOLEAN, false),
    FIXED_PREC_SCALE(BOOLEAN, false),
    AUTO_INCREMENT(BOOLEAN, false),
    LOCAL_TYPE_NAME(VARCHAR, true),
    MINIMUM_SCALE(SMALLINT, true),
    MAXIMUM_SCALE(SMALLINT, true),
    SQL_DATA_TYPE(INTEGER, true),
    SQL_DATETIME_SUB(INTEGER, true),
    NUM_PREC_RADIX(INTEGER, true);

    final private SQLType sqlType;
    final private boolean isNullable;

}