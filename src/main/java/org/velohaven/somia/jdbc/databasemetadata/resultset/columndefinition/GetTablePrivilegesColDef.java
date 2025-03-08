package org.velohaven.somia.jdbc.databasemetadata.resultset.columndefinition;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.sql.DatabaseMetaData;
import java.sql.SQLType;

import static java.sql.JDBCType.*;

/**
 * The column names, ordinal positions, types and nullability for the
 * {@link DatabaseMetaData#getTablePrivileges(String, String, String)}
 * method.
 */
@Accessors(fluent = true)
@Getter(onMethod_ = @Override)
@AllArgsConstructor
public enum GetTablePrivilegesColDef implements ColDefByEnum<GetTablePrivilegesColDef> {

    TABLE_CAT(VARCHAR, true),
    TABLE_SCHEM(VARCHAR, true),
    TABLE_NAME(VARCHAR, false),
    GRANTOR(VARCHAR, true),
    GRANTEE(VARCHAR, false),
    PRIVILEGE(VARCHAR, false),
    IS_GRANTABLE(VARCHAR, true);

    private final SQLType sqlType;
    private final boolean isNullable;

}