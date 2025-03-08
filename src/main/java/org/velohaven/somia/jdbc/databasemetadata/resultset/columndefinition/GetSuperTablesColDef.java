package org.velohaven.somia.jdbc.databasemetadata.resultset.columndefinition;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.sql.DatabaseMetaData;
import java.sql.SQLType;

import static java.sql.JDBCType.*;

/**
 * The column names, ordinal positions, types and nullability for the
 * {@link DatabaseMetaData#getSuperTables(String, String, String)}
 * method.
 */
@Accessors(fluent = true)
@Getter(onMethod_ = @Override)
@AllArgsConstructor
public enum GetSuperTablesColDef implements ColDefByEnum<GetSuperTablesColDef> {

    TABLE_CAT(VARCHAR, true),
    TABLE_SCHEM(VARCHAR, true),
    TABLE_NAME(VARCHAR, false),
    SUPERTABLE_NAME(VARCHAR, false);

    private final SQLType sqlType;
    private final boolean isNullable;

}