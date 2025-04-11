package org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.velohaven.somia.jdbc.resultset.databasemetadata.entity.VersionColumnEntity;

import static org.velohaven.somia.jdbc.resultset.databasemetadata.columndefinition.GetVersionColumnsColDef.*;

/**
 * A {@link KnownMetaDataReader} for reading {@link VersionColumnEntity}s from a ResultSet with the structure
 * returned by {@link java.sql.DatabaseMetaData#getVersionColumns}.
 */
public class VersionColumnReader extends KnownMetaDataReader<VersionColumnEntity> {

    protected VersionColumnReader(final ResultSet resultSet, final boolean strict) {
        super(resultSet, VersionColumnEntity::new, strict);
    }

    @Override
    protected void transfer(VersionColumnEntity row) throws SQLException {
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