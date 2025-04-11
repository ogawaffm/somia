package org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.velohaven.somia.jdbc.resultset.databasemetadata.entity.FunctionEntity;

import static org.velohaven.somia.jdbc.resultset.databasemetadata.columndefinition.GetFunctionsColDef.*;

/**
 * A {@link KnownMetaDataReader} for reading {@link FunctionEntity}s from a ResultSet with the structure
 * returned by {@link java.sql.DatabaseMetaData#getFunctions}.
 */
public class FunctionReader extends KnownMetaDataReader<FunctionEntity> {

    protected FunctionReader(final ResultSet resultSet, boolean strict) {
        super(resultSet, FunctionEntity::new, strict);
    }

    @Override
    protected void transfer(FunctionEntity row) throws SQLException {
        row.functionCatalog = readStringByIndex(FUNCTION_CAT);
        row.functionSchema = readStringByIndex(FUNCTION_SCHEM);
        row.functionName = readStringByIndex(FUNCTION_NAME);
        row.remarks = readStringByIndex(REMARKS);
        row.functionType = readShortByIndex(FUNCTION_TYPE);
        row.specificName = readStringByIndex(SPECIFIC_NAME);
    }

}