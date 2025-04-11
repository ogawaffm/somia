package org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.velohaven.somia.jdbc.resultset.databasemetadata.entity.ProcedureEntity;

import static org.velohaven.somia.jdbc.resultset.databasemetadata.columndefinition.GetProceduresColDef.*;

/**
 * A {@link KnownMetaDataReader} for reading {@link ProcedureEntity}s from a ResultSet with the structure
 * returned by {@link java.sql.DatabaseMetaData#getProcedures}.
 */
public class ProcedureReader extends KnownMetaDataReader<ProcedureEntity> {

    protected ProcedureReader(final ResultSet resultSet, final boolean strict) {
        super(resultSet, ProcedureEntity::new, strict);
    }

    @Override
    protected void transfer(ProcedureEntity row) throws SQLException {
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