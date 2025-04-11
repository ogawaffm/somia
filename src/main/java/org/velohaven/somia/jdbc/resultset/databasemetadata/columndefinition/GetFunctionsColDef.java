package org.velohaven.somia.jdbc.resultset.databasemetadata.columndefinition;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.sql.DatabaseMetaData;
import java.sql.SQLType;

import static java.sql.JDBCType.*;

/**
 * The column names, ordinal positions, types and nullability for the
 * {@link DatabaseMetaData#getFunctions(String, String, String)}
 * method.
 */
@Accessors(fluent = true)
@Getter(onMethod_ = @Override)
@AllArgsConstructor
public enum GetFunctionsColDef implements ColDefByEnum<GetFunctionsColDef> {

    FUNCTION_CAT(VARCHAR, true),
    FUNCTION_SCHEM(VARCHAR, true),
    FUNCTION_NAME(VARCHAR, false),
    REMARKS(VARCHAR, true),
    FUNCTION_TYPE(SMALLINT, false),
    SPECIFIC_NAME(VARCHAR, false);

    final private SQLType sqlType;
    final private boolean isNullable;

}
