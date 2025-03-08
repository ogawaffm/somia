package org.velohaven.somia.jdbc.databasemetadata.resultset.reader;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.velohaven.somia.jdbc.databasemetadata.resultset.entity.KeyReferenceColumnEntity;

import static org.velohaven.somia.jdbc.databasemetadata.resultset.columndefinition.GetKeyReferenceColumnsColDef.*;

/**
 * A {@link RowReader} for reading {@link KeyReferenceColumnEntity}s from a ResultSet with the structure
 * returned by {@link java.sql.DatabaseMetaData#getImportedKeys},
 * {@link java.sql.DatabaseMetaData#getExportedKeys} and
 * {@link java.sql.DatabaseMetaData#getCrossReference}.
 */
public abstract class KeyReferenceColumnRowReader<T extends KeyReferenceColumnEntity> extends RowReader<T> {

    public KeyReferenceColumnRowReader(final ResultSet resultSet, boolean strict) {
        super(resultSet, strict);
    }

    void read(KeyReferenceColumnEntity row) throws SQLException {

        row.primaryKeyTableCatalog = readStringByIndex(PKTABLE_CAT);
        row.primaryKeyTableSchema = readStringByIndex(PKTABLE_SCHEM);
        row.primaryKeyTableName = readStringByIndex(PKTABLE_NAME);
        row.primaryKeyColumnName = readStringByIndex(PKCOLUMN_NAME);

        row.foreignKeyTableCatalog = readStringByIndex(FKTABLE_CAT);
        row.foreignKeyTableSchema = readStringByIndex(FKTABLE_SCHEM);
        row.foreignKeyTableName = readStringByIndex(FKTABLE_NAME);
        row.foreignKeyColumnName = readStringByIndex(FKCOLUMN_NAME);

        row.keySeq = readShortByIndex(KEY_SEQ);

        row.updateRule = readShortByIndex(UPDATE_RULE);
        row.deleteRule = readShortByIndex(DELETE_RULE);

        row.foreignKeyName = readStringByIndex(FK_NAME);
        row.primaryKeyName = readStringByIndex(PK_NAME);
        row.deferrability = readStringByIndex(DEFERRABILITY);

    }

}
