package org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.velohaven.somia.jdbc.resultset.databasemetadata.entity.ProcedureColumnEntity;

import static org.velohaven.somia.jdbc.resultset.databasemetadata.columndefinition.GetProcedureColumnsColDef.*;

/**
 * A {@link KnownMetaDataReader} for reading {@link ProcedureColumnEntity}s from a ResultSet with the structure
 * returned by {@link java.sql.DatabaseMetaData#getProcedureColumns}.
 */
public class ProcedureColumnReader extends KnownMetaDataReader<ProcedureColumnEntity> {

    protected ProcedureColumnReader(final ResultSet resultSet, final boolean strict) {
        super(resultSet, ProcedureColumnEntity::new, strict);
    }

    @Override
    protected void transfer(ProcedureColumnEntity row) throws SQLException {
        row.procedureCatalog = readStringByIndex(PROCEDURE_CAT);
        row.procedureSchema = readStringByIndex(PROCEDURE_SCHEM);
        row.procedureName = readStringByIndex(PROCEDURE_NAME);
        row.columnName = readStringByIndex(COLUMN_NAME);
        row.columnType = readShortByIndex(COLUMN_TYPE);
        row.dataType = readIntegerByIndex(DATA_TYPE);
        row.typeName = readStringByIndex(TYPE_NAME);
        row.precision = readIntegerByIndex(PRECISION);
        row.length = readIntegerByIndex(LENGTH);
        row.scale = readShortByIndex(SCALE);
        row.numericalPrecisionRadix = readShortByIndex(RADIX);
        row.nullable = readShortByIndex(NULLABLE);
        row.remarks = readStringByIndex(REMARKS);
        row.columnDef = readStringByIndex(COLUMN_DEF);
        row.sqlDataType = readIntegerByIndex(SQL_DATA_TYPE);
        row.sqlDatetimeSub = readIntegerByIndex(SQL_DATETIME_SUB);
        row.charOctetLength = readIntegerByIndex(CHAR_OCTET_LENGTH);
        row.ordinalPosition = readIntegerByIndex(ORDINAL_POSITION);
        row.isNullable = readStringByIndex(IS_NULLABLE);
        row.specificName = readStringByIndex(SPECIFIC_NAME);
    }

}