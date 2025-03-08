package org.velohaven.somia.jdbc.databasemetadata.resultset.columndefinition;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.sql.DatabaseMetaData;

import static java.sql.JDBCType.*;

import java.sql.SQLType;

/**
 * The column names, ordinal positions, types and nullability for the
 * {@link DatabaseMetaData#getTables(String, String, String, String[])}
 * method.
 */
@Accessors(fluent = true)
@Getter(onMethod_ = @Override)
@AllArgsConstructor
public enum GetTablesColDef implements ColDefByEnum<GetTablesColDef> {

    TABLE_CAT(VARCHAR, true),
    TABLE_SCHEM(VARCHAR, true),
    TABLE_NAME(VARCHAR, false),
    TABLE_TYPE(VARCHAR, false),
    REMARKS(VARCHAR, true),
    TYPE_CAT(VARCHAR, true),
    TYPE_SCHEM(VARCHAR, true),
    TYPE_NAME(VARCHAR, true),
    SELF_REFERENCING_COL_NAME(VARCHAR, true),
    REF_GENERATION(VARCHAR, true);

    final private SQLType sqlType;
    final private boolean isNullable;

}