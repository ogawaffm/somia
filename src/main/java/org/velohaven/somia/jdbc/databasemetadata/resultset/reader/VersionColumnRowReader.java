package org.velohaven.somia.jdbc.databasemetadata.resultset.reader;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.velohaven.somia.jdbc.databasemetadata.resultset.entity.VersionColumnEntity;

import static org.velohaven.somia.jdbc.databasemetadata.resultset.columndefinition.GetVersionColumnsColDef.*;

/**
 * A {@link RowReader} for reading {@link VersionColumnEntity}s from a ResultSet with the structure
 * returned by {@link java.sql.DatabaseMetaData#getVersionColumns}.
 */
public class VersionColumnRowReader extends RowReader<VersionColumnEntity> {

    public VersionColumnRowReader(final ResultSet resultSet, boolean strict) {
        super(resultSet, strict);
    }

    void read(VersionColumnEntity row) throws SQLException {
        row.scope = readShortByIndex(SCOPE);
        row.columnName = readStringByIndex(COLUMN_NAME);
        row.dataType = readIntegerByIndex(DATA_TYPE);
        row.typeName = readStringByIndex(TYPE_NAME);
        row.columnSize = readIntegerByIndex(COLUMN_SIZE);
        row.bufferLength = readIntegerByIndex(BUFFER_LENGTH);
        row.decimalDigits = readShortByIndex(DECIMAL_DIGITS);
        row.pseudoColumn = readShortByIndex(PSEUDO_COLUMN);
    }

}