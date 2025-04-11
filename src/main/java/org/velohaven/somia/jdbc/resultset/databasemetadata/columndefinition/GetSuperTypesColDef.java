package org.velohaven.somia.jdbc.resultset.databasemetadata.columndefinition;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.sql.DatabaseMetaData;
import java.sql.SQLType;

import static java.sql.JDBCType.*;

/**
 * The column names, ordinal positions, types and nullability for the
 * {@link DatabaseMetaData#getSuperTypes(String, String, String)}
 * method.
 */
@Accessors(fluent = true)
@Getter(onMethod_ = @Override)
@AllArgsConstructor
public enum GetSuperTypesColDef implements ColDefByEnum<GetSuperTypesColDef> {

    TABLE_CAT(VARCHAR, true),
    TABLE_SCHEM(VARCHAR, true),
    TABLE_NAME(VARCHAR, false),
    SUPERTYPE_CAT(VARCHAR, true),
    SUPERTYPE_SCHEM(VARCHAR, true),
    SUPERTYPE_NAME(VARCHAR, false);

    private final SQLType sqlType;
    private final boolean isNullable;

}