package org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.velohaven.somia.jdbc.resultset.databasemetadata.entity.BestRowIdentifierEntity;

import static org.velohaven.somia.jdbc.resultset.databasemetadata.columndefinition.GetBestRowIdentifierColDef.*;

/**
 * A {@link KnownMetaDataReader} for reading {@link BestRowIdentifierEntity}s from a ResultSet with the structure
 * returned by {@link java.sql.DatabaseMetaData#getBestRowIdentifier}.
 */
public class BestIdentifierReader extends KnownMetaDataReader<BestRowIdentifierEntity> {

    protected BestIdentifierReader(final ResultSet resultSet, final boolean strict) {
        super(resultSet, BestRowIdentifierEntity::new, strict);
    }

    @Override
    protected void transfer(BestRowIdentifierEntity row) throws SQLException {
        row.scope = readIntegerByIndex(SCOPE);
        row.columnName = readStringByIndex(COLUMN_NAME);
        row.dataType = readIntegerByIndex(DATA_TYPE);
        row.typeName = readStringByIndex(TYPE_NAME);
        row.columnSize = readIntegerByIndex(COLUMN_SIZE);
        row.bufferLength = readIntegerByIndex(BUFFER_LENGTH);
        row.decimalDigits = readIntegerByIndex(DECIMAL_DIGITS);
        row.pseudoColumn = readIntegerByIndex(PSEUDO_COLUMN);
    }

}