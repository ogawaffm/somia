package org.velohaven.somia.jdbc.resultset.databasemetadata.columndefinition;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

import static java.sql.JDBCType.*;

import java.sql.DatabaseMetaData;
import java.sql.SQLType;

/**
 * The column names, ordinal positions, types and nullability for the
 * {@link DatabaseMetaData#getProcedures(String, String, String)}
 * method.
 */
@Accessors(fluent = true)
@Getter(onMethod_ = @Override)
@AllArgsConstructor
public enum GetProceduresColDef implements ColDefByEnum<GetProceduresColDef> {

    PROCEDURE_CAT(VARCHAR, true),
    PROCEDURE_SCHEM(VARCHAR, true),
    PROCEDURE_NAME(VARCHAR, false),
    RESERVED_FOR_FUTURE_USE_4(NULL, true),
    RESERVED_FOR_FUTURE_USE_5(NULL, true),
    RESERVED_FOR_FUTURE_USE_6(NULL, true),
    REMARKS(VARCHAR, true),
    PROCEDURE_TYPE(SMALLINT, false),
    SPECIFIC_NAME(VARCHAR, false);

    final private SQLType sqlType;
    final private boolean isNullable;

}