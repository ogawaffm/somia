package org.velohaven.somia.jdbc.databasemetadata.resultset.columndefinition;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.sql.DatabaseMetaData;
import java.sql.SQLType;

import static java.sql.JDBCType.*;

/**
 * The column names, ordinal positions, types and nullability for the
 * {@link DatabaseMetaData#getClientInfoProperties()}
 * method.
 */
@Accessors(fluent = true)
@Getter(onMethod_ = @Override)
@AllArgsConstructor
public enum GetClientInfoPropertiesColDef implements ColDefByEnum<GetClientInfoPropertiesColDef> {

    SCOPE(SMALLINT, true),
    NAME(VARCHAR, false),
    MAX_LEN(INTEGER, false),
    DEFAULT_VALUE(VARCHAR, false),
    DESCRIPTION(VARCHAR, false);

    final private SQLType sqlType;
    final private boolean isNullable;

}