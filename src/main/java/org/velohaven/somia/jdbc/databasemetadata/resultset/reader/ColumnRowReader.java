package org.velohaven.somia.jdbc.databasemetadata.resultset.reader;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.velohaven.somia.jdbc.databasemetadata.resultset.entity.ColumnEntity;

import static org.velohaven.somia.jdbc.databasemetadata.resultset.columndefinition.GetColumnsColDef.*;

public class ColumnRowReader extends RowReader<ColumnEntity> {

    public ColumnRowReader(final ResultSet resultSet, boolean strict) {
        super(resultSet, strict);
    }

    public void read(ColumnEntity row) throws SQLException {
        row.tableCatalog = readStringByIndex(TABLE_CAT);
        row.tableSchema = readStringByIndex(TABLE_SCHEM);
        row.tableName = readStringByIndex(TABLE_NAME);
        row.columnName = readStringByIndex(COLUMN_NAME);
        row.dataType = readIntegerByIndex(DATA_TYPE);
        row.typeName = readStringByIndex(TYPE_NAME);
        row.columnSize = readIntegerByIndex(COLUMN_SIZE);
        row.bufferLength = readIntegerByIndex(BUFFER_LENGTH);
        row.decimalDigits = readIntegerByIndex(DECIMAL_DIGITS);
        row.numericPrecisionRadix = readIntegerByIndex(NUM_PREC_RADIX);
        row.nullable = readIntegerByIndex(NULLABLE);
        row.remarks = readStringByIndex(REMARKS);
        row.columnDef = readStringByIndex(COLUMN_DEF);
        row.sqlDataType = readIntegerByIndex(SQL_DATA_TYPE);
        row.sqlDatetimeSub = readIntegerByIndex(SQL_DATETIME_SUB);
        row.charOctetLength = readIntegerByIndex(CHAR_OCTET_LENGTH);
        row.ordinalPosition = readIntegerByIndex(ORDINAL_POSITION);
        row.isNullable = readStringByIndex(IS_NULLABLE);
        row.scopeCatalog = readStringByIndex(SCOPE_CATALOG);
        row.scopeSchema = readStringByIndex(SCOPE_SCHEMA);
        row.scopeTable = readStringByIndex(SCOPE_TABLE);
        row.sourceDataType = readShortByIndex(SOURCE_DATA_TYPE);
        row.isAutoIncrement = readStringByIndex(IS_AUTOINCREMENT);
        row.isGeneratedColumn = readStringByIndex(IS_GENERATEDCOLUMN);
    }

}
