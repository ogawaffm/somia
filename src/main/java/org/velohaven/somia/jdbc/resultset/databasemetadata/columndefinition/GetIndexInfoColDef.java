package org.velohaven.somia.jdbc.resultset.databasemetadata.columndefinition;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.sql.DatabaseMetaData;
import java.sql.SQLType;

import static java.sql.JDBCType.*;

/**
 * The column names, ordinal positions, types and nullability for the
 * {@link DatabaseMetaData#getIndexInfo(String, String, String, boolean, boolean)}
 * method.
 */
@Accessors(fluent = true)
@Getter(onMethod_ = @Override)
@AllArgsConstructor
public enum GetIndexInfoColDef implements ColDefByEnum<GetIndexInfoColDef> {

    TABLE_CAT(VARCHAR, true),
    TABLE_SCHEM(VARCHAR, true),
    TABLE_NAME(VARCHAR, false),
    NON_UNIQUE(BOOLEAN, false),
    INDEX_QUALIFIER(VARCHAR, true),
    INDEX_NAME(VARCHAR, true),
    TYPE(SMALLINT, false),
    ORDINAL_POSITION(SMALLINT, true),
    COLUMN_NAME(VARCHAR, true),
    ASC_OR_DESC(VARCHAR, true),
    CARDINALITY(BIGINT, true),
    PAGES(BIGINT, false),
    FILTER_CONDITION(VARCHAR, true);

    final private SQLType sqlType;
    final private boolean isNullable;

}