package org.velohaven.somia.jdbc.databasemetadata.resultset.reader;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.velohaven.somia.jdbc.databasemetadata.resultset.entity.FunctionColumnEntity;

import static org.velohaven.somia.jdbc.databasemetadata.resultset.columndefinition.GetFunctionColumnsColDef.*;

/**
 * A {@link RowReader} for reading {@link FunctionColumnEntity}s from a ResultSet with the structure
 * returned by {@link java.sql.DatabaseMetaData#getFunctionColumns}.
 */
public class FunctionColumnRowReader extends RowReader<FunctionColumnEntity> {

    public FunctionColumnRowReader(final ResultSet resultSet, boolean strict) {
        super(resultSet, strict);
    }

    void read(FunctionColumnEntity row) throws SQLException {
        row.functionCatalog = readStringByIndex(FUNCTION_CAT);
        row.functionSchema = readStringByIndex(FUNCTION_SCHEM);
        row.functionName = readStringByIndex(FUNCTION_NAME);
        row.columnName = readStringByIndex(COLUMN_NAME);
        row.columnType = readShortByIndex(COLUMN_TYPE);
        row.dataType = readIntegerByIndex(DATA_TYPE);
        row.typeName = readStringByIndex(TYPE_NAME);
        row.precision = readIntegerByIndex(PRECISION);
        row.length = readIntegerByIndex(LENGTH);
        row.scale = readShortByIndex(SCALE);
        row.numericPrecisionRadix = readShortByIndex(RADIX);
        row.nullable = readShortByIndex(NULLABLE);
        row.remarks = readStringByIndex(REMARKS);
        row.charOctetLength = readIntegerByIndex(CHAR_OCTET_LENGTH);
        row.ordinalPosition = readIntegerByIndex(ORDINAL_POSITION);
        row.isNullable = readStringByIndex(IS_NULLABLE);
        row.specificName = readStringByIndex(SPECIFIC_NAME);
    }

}