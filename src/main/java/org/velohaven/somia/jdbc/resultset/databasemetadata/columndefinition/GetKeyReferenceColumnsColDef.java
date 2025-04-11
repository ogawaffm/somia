package org.velohaven.somia.jdbc.resultset.databasemetadata.columndefinition;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.sql.DatabaseMetaData;
import java.sql.SQLType;

import static java.sql.JDBCType.*;

/**
 * The column names, ordinal positions, types and nullability for the
 * {@link DatabaseMetaData#getCrossReference(String, String, String, String, String, String)}
 * {@link DatabaseMetaData#getImportedKeys(String, String, String)}
 * and
 * {@link DatabaseMetaData#getExportedKeys(String, String, String)}
 * methods.
 */
@Accessors(fluent = true)
@Getter(onMethod_ = @Override)
@AllArgsConstructor
public enum GetKeyReferenceColumnsColDef implements ColDefByEnum<GetKeyReferenceColumnsColDef> {

    PKTABLE_CAT(VARCHAR, true),
    PKTABLE_SCHEM(VARCHAR, true),
    PKTABLE_NAME(VARCHAR, false),
    PKCOLUMN_NAME(VARCHAR, false),

    FKTABLE_CAT(VARCHAR, true),
    FKTABLE_SCHEM(VARCHAR, true),
    FKTABLE_NAME(VARCHAR, false),
    FKCOLUMN_NAME(VARCHAR, false),


    KEY_SEQ(SMALLINT, false),

    UPDATE_RULE(SMALLINT, false),
    DELETE_RULE(SMALLINT, false),

    FK_NAME(VARCHAR, true),
    PK_NAME(VARCHAR, true),
    DEFERRABILITY(VARCHAR, true);

    final private SQLType sqlType;
    final private boolean isNullable;

}