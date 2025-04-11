package org.velohaven.somia.jdbc.resultset.databasemetadata.columndefinition;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.sql.DatabaseMetaData;
import java.sql.SQLType;

import static java.sql.JDBCType.*;

/**
 * The column names, ordinal positions, types and nullability for the
 * {@link DatabaseMetaData#getSchemas()}
 * method.
 */
@Accessors(fluent = true)
@Getter(onMethod_ = @Override)
@AllArgsConstructor
public enum GetSchemasColDef implements ColDefByEnum<GetSchemasColDef> {

    TABLE_SCHEM(VARCHAR, false),
    TABLE_CATALOG(VARCHAR, true);

    private final SQLType sqlType;
    private final boolean isNullable;

}