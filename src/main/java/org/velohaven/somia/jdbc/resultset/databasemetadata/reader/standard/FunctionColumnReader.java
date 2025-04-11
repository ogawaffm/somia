package org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.velohaven.somia.jdbc.resultset.databasemetadata.entity.FunctionColumnEntity;

import static org.velohaven.somia.jdbc.resultset.databasemetadata.columndefinition.GetFunctionColumnsColDef.*;

/**
 * A {@link KnownMetaDataReader} for reading {@link FunctionColumnEntity}s from a ResultSet with the structure
 * returned by {@link java.sql.DatabaseMetaData#getFunctionColumns}.
 */
public class FunctionColumnReader extends KnownMetaDataReader<FunctionColumnEntity> {

    protected FunctionColumnReader(final ResultSet resultSet, final boolean strict) {
        super(resultSet, FunctionColumnEntity::new, strict);
    }

    @Override
    protected void transfer(FunctionColumnEntity row) throws SQLException {
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