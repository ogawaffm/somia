package org.velohaven.somia.jdbc.databasemetadata.resultset.reader;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.velohaven.somia.jdbc.databasemetadata.resultset.entity.AttributeEntity;

import static org.velohaven.somia.jdbc.databasemetadata.resultset.columndefinition.GetAttributesColDef.*;

/**
 * A {@link RowReader} for reading {@link AttributeEntity}s from a ResultSet with the structure
 * returned by {@link java.sql.DatabaseMetaData#getAttributes}.
 */
public class AttributeRowReader extends RowReader<AttributeEntity> {

    public AttributeRowReader(final ResultSet resultSet, boolean strict) {
        super(resultSet, strict);
    }

    void read(AttributeEntity row) throws SQLException {
        row.tableCatalog = readStringByIndex(TABLE_CAT);
        row.tableSchema = readStringByIndex(TABLE_SCHEM);
        row.tableName = readStringByIndex(TABLE_NAME);
        row.attributeName = readStringByIndex(ATTR_NAME);
        row.dataType = readIntegerByIndex(DATA_TYPE);
        row.attributeTypeName = readStringByIndex(ATTR_TYPE_NAME);
        row.attributeSize = readIntegerByIndex(ATTR_SIZE);
        row.decimalDigits = readIntegerByIndex(DECIMAL_DIGITS);
        row.numericPrecisionRadix = readIntegerByIndex(NUM_PREC_RADIX);
        row.nullable = readIntegerByIndex(NULLABLE);
        row.remarks = readStringByIndex(REMARKS);
        row.attrDef = readStringByIndex(ATTR_DEF);
        row.sqlDataType = readIntegerByIndex(SQL_DATA_TYPE);
        row.sqlDatetimeSub = readIntegerByIndex(SQL_DATETIME_SUB);
        row.charOctetLength = readIntegerByIndex(CHAR_OCTET_LENGTH);
        row.ordinalPosition = readIntegerByIndex(ORDINAL_POSITION);
        row.isNullable = readStringByIndex(IS_NULLABLE);
        row.scopeCatalog = readStringByIndex(SCOPE_CATALOG);
        row.scopeSchema = readStringByIndex(SCOPE_SCHEMA);
        row.scopeTable = readStringByIndex(SCOPE_TABLE);
        row.sourceDataType = readShortByIndex(SOURCE_DATA_TYPE);
    }

}