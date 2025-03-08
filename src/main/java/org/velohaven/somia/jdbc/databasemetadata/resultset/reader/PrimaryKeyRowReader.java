package org.velohaven.somia.jdbc.databasemetadata.resultset.reader;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.velohaven.somia.jdbc.databasemetadata.resultset.entity.PrimaryKeyColumnEntity;

import static org.velohaven.somia.jdbc.databasemetadata.resultset.columndefinition.GetPrimaryKeyColDef.*;

/**
 * A {@link RowReader} for reading {@link PrimaryKeyColumnEntity}s from a ResultSet with the structure
 * returned by {@link java.sql.DatabaseMetaData#getPrimaryKeys}.
 */
public class PrimaryKeyRowReader extends RowReader<PrimaryKeyColumnEntity> {

    public PrimaryKeyRowReader(final ResultSet resultSet, boolean strict) {
        super(resultSet, strict);
    }

    public void read(PrimaryKeyColumnEntity row) throws SQLException {
        row.tableCatalog = readStringByIndex(TABLE_CAT);
        row.tableSchema = readStringByIndex(TABLE_SCHEM);
        row.tableName = readStringByIndex(TABLE_NAME);
        row.columnName = readStringByIndex(COLUMN_NAME);
        row.keySeq = readIntegerByIndex(KEY_SEQ);
        row.primaryKeyName = readStringByIndex(PK_NAME);
    }

}