package org.velohaven.somia.jdbc.databasemetadata.resultset.columndefinition;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.sql.DatabaseMetaData;
import java.sql.SQLType;

import static java.sql.JDBCType.*;

/**
 * The column names, ordinal positions, types and nullability for the
 * {@link DatabaseMetaData#getPrimaryKeys(String, String, String)}
 * method.
 */
@Accessors(fluent = true)
@Getter(onMethod_ = @Override)
@AllArgsConstructor
public enum GetPrimaryKeyColDef implements ColDefByEnum<GetPrimaryKeyColDef> {

    TABLE_CAT(VARCHAR, true),
    TABLE_SCHEM(VARCHAR, true),
    TABLE_NAME(VARCHAR, false),
    COLUMN_NAME(VARCHAR, false),
    KEY_SEQ(SMALLINT, false),
    PK_NAME(VARCHAR, true);

    final private SQLType sqlType;
    final private boolean isNullable;

}