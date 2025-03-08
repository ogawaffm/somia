package org.velohaven.somia.jdbc.databasemetadata.resultset.reader;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.velohaven.somia.jdbc.databasemetadata.resultset.entity.PseudoColumnEntity;

import static org.velohaven.somia.jdbc.databasemetadata.resultset.columndefinition.GetPseudoColumnsColDef.*;

/**
 * A {@link RowReader} for reading {@link PseudoColumnEntity}s from a ResultSet with the structure
 * returned by {@link java.sql.DatabaseMetaData#getPseudoColumns}.
 */
public class PseudoColumnRowReader extends RowReader<PseudoColumnEntity> {

    public PseudoColumnRowReader(final ResultSet resultSet, boolean strict) {
        super(resultSet, strict);
    }

    void read(PseudoColumnEntity row) throws SQLException {
        row.tableCatalog = readStringByIndex(TABLE_CAT);
        row.tableSchema = readStringByIndex(TABLE_SCHEM);
        row.tableName = readStringByIndex(TABLE_NAME);
        row.columnName = readStringByIndex(COLUMN_NAME);
        row.dataType = readIntegerByIndex(DATA_TYPE);
        row.columnSize = readIntegerByIndex(COLUMN_SIZE);
        row.decimalDigits = readIntegerByIndex(DECIMAL_DIGITS);
        row.numericPrecisionRadix = readIntegerByIndex(NUM_PREC_RADIX);
        row.columnUsage = readStringByIndex(COLUMN_USAGE);
        row.remarks = readStringByIndex(REMARKS);
        row.charOctetLength = readIntegerByIndex(CHAR_OCTET_LENGTH);
        row.isNullable = readStringByIndex(IS_NULLABLE);
    }

}