package org.velohaven.somia.jdbc.databasemetadata.resultset.reader;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.velohaven.somia.jdbc.databasemetadata.resultset.entity.ProcedureEntity;

import static org.velohaven.somia.jdbc.databasemetadata.resultset.columndefinition.GetProceduresColDef.*;

/**
 * A {@link RowReader} for reading {@link ProcedureEntity}s from a ResultSet with the structure
 * returned by {@link java.sql.DatabaseMetaData#getProcedures}.
 */
public class ProcedureRowReader extends RowReader<ProcedureEntity> {

    public ProcedureRowReader(final ResultSet resultSet, boolean strict) {
        super(resultSet, strict);
    }

    void read(ProcedureEntity row) throws SQLException {
        row.procedureCatalog = readStringByIndex(PROCEDURE_CAT);
        row.procedureSchema = readStringByIndex(PROCEDURE_SCHEM);
        row.procedureName = readStringByIndex(PROCEDURE_NAME);
        row.reservedForFutureUse4 = readStringByIndex(RESERVED_FOR_FUTURE_USE_4);
        row.reservedForFutureUse5 = readStringByIndex(RESERVED_FOR_FUTURE_USE_5);
        row.reservedForFutureUse6 = readStringByIndex(RESERVED_FOR_FUTURE_USE_6);
        row.remarks = readStringByIndex(REMARKS);
        row.procedureType = readShortByIndex(PROCEDURE_TYPE);
        row.specificName = readStringByIndex(SPECIFIC_NAME);
    }

}