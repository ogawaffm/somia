package org.velohaven.somia.jdbc.databasemetadata.resultset.columndefinition;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.sql.DatabaseMetaData;
import java.sql.SQLType;

import static java.sql.JDBCType.*;

/**
 * The column names, ordinal positions, types and nullability for the
 * {@link DatabaseMetaData#getUDTs(String, String, String, int[])}
 * method.
 */
@Accessors(fluent = true)
@Getter(onMethod_ = @Override)
@AllArgsConstructor
public enum GetUDTsColDef implements ColDefByEnum<GetUDTsColDef> {

    TABLE_CAT(VARCHAR, true),
    TABLE_SCHEM(VARCHAR, true),
    TYPE_NAME(VARCHAR, false),
    CLASS_NAME(VARCHAR, false),
    DATA_TYPE(INTEGER, false),
    REMARKS(VARCHAR, false),
    BASE_TYPE(SMALLINT, false);

    private final SQLType sqlType;
    private final boolean isNullable;

}