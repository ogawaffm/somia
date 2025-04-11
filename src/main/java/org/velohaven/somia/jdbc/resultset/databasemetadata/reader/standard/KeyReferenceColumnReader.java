package org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Supplier;

import org.velohaven.somia.jdbc.resultset.databasemetadata.entity.KeyReferenceColumnEntity;

import static org.velohaven.somia.jdbc.resultset.databasemetadata.columndefinition.GetKeyReferenceColumnsColDef.*;

/**
 * A {@link KnownMetaDataReader} for reading {@link KeyReferenceColumnEntity}s from a ResultSet with the structure
 * returned by {@link java.sql.DatabaseMetaData#getImportedKeys},
 * {@link java.sql.DatabaseMetaData#getExportedKeys} and
 * {@link java.sql.DatabaseMetaData#getCrossReference}.
 */
public abstract class KeyReferenceColumnReader<T extends KeyReferenceColumnEntity> extends KnownMetaDataReader<T> {

    protected KeyReferenceColumnReader(final ResultSet resultSet, final Supplier<T> constructor, final boolean strict) {
        super(resultSet, constructor, strict);
    }

    @Override
    protected void transfer(KeyReferenceColumnEntity row) throws SQLException {

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
