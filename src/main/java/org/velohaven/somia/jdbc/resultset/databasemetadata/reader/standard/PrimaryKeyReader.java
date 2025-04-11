package org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.velohaven.somia.jdbc.resultset.databasemetadata.entity.PrimaryKeyColumnEntity;

import static org.velohaven.somia.jdbc.resultset.databasemetadata.columndefinition.GetPrimaryKeyColDef.*;

/**
 * A {@link KnownMetaDataReader} for reading {@link PrimaryKeyColumnEntity}s from a ResultSet with the structure
 * returned by {@link java.sql.DatabaseMetaData#getPrimaryKeys}.
 */
public class PrimaryKeyReader extends KnownMetaDataReader<PrimaryKeyColumnEntity> {

    protected PrimaryKeyReader(final ResultSet resultSet, final boolean strict) {
        super(resultSet, PrimaryKeyColumnEntity::new, strict);
    }

    @Override
    protected void transfer(PrimaryKeyColumnEntity row) throws SQLException {
        row.tableCatalog = readStringByIndex(TABLE_CAT);
        row.tableSchema = readStringByIndex(TABLE_SCHEM);
        row.tableName = readStringByIndex(TABLE_NAME);
        row.columnName = readStringByIndex(COLUMN_NAME);
        row.keySeq = readIntegerByIndex(KEY_SEQ);
        row.primaryKeyName = readStringByIndex(PK_NAME);
    }

}