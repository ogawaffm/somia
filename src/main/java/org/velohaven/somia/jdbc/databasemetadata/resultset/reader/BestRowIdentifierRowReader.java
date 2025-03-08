package org.velohaven.somia.jdbc.databasemetadata.resultset.reader;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.velohaven.somia.jdbc.databasemetadata.resultset.entity.BestRowIdentifierEntity;

import static org.velohaven.somia.jdbc.databasemetadata.resultset.columndefinition.GetBestRowIdentifierColDef.*;

/**
 * A {@link RowReader} for reading {@link BestRowIdentifierEntity}s from a ResultSet with the structure
 * returned by {@link java.sql.DatabaseMetaData#getBestRowIdentifier}.
 */
public class BestRowIdentifierRowReader extends RowReader<BestRowIdentifierEntity> {

    public BestRowIdentifierRowReader(final ResultSet resultSet, boolean strict) {
        super(resultSet, strict);
    }

    public void read(BestRowIdentifierEntity row) throws SQLException {
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