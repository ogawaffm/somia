package org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.velohaven.somia.jdbc.resultset.databasemetadata.entity.PseudoColumnEntity;

import static org.velohaven.somia.jdbc.resultset.databasemetadata.columndefinition.GetPseudoColumnsColDef.*;

/**
 * A {@link KnownMetaDataReader} for reading {@link PseudoColumnEntity}s from a ResultSet with the structure
 * returned by {@link java.sql.DatabaseMetaData#getPseudoColumns}.
 */
public class PseudoColumnReader extends KnownMetaDataReader<PseudoColumnEntity> {

    protected PseudoColumnReader(final ResultSet resultSet, final boolean strict) {
        super(resultSet, PseudoColumnEntity::new, strict);
    }

    @Override
    protected void transfer(PseudoColumnEntity row) throws SQLException {
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