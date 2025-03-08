package org.velohaven.somia.jdbc.databasemetadata.resultset.columndefinition;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.sql.DatabaseMetaData;
import java.sql.SQLType;

import static java.sql.JDBCType.*;

/**
 * The column names, ordinal positions, types and nullability for the
 * {@link DatabaseMetaData#getTableTypes()}
 * method.
 */
@Accessors(fluent = true)
@Getter(onMethod_ = @Override)
@AllArgsConstructor
public enum GetTableTypesColDef implements ColDefByEnum<GetTableTypesColDef> {

    // can be from different domains, e.g. Sap: TRANSP, VIEW, INTTAB, APPEND, POOL
    TABLE_TYPE(VARCHAR, false);

    private final SQLType sqlType;
    private final boolean isNullable;

}