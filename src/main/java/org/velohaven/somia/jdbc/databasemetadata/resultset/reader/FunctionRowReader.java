package org.velohaven.somia.jdbc.databasemetadata.resultset.reader;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.velohaven.somia.jdbc.databasemetadata.resultset.entity.FunctionEntity;

import static org.velohaven.somia.jdbc.databasemetadata.resultset.columndefinition.GetFunctionsColDef.*;

/**
 * A {@link RowReader} for reading {@link FunctionEntity}s from a ResultSet with the structure
 * returned by {@link java.sql.DatabaseMetaData#getFunctions}.
 */
public class FunctionRowReader extends RowReader<FunctionEntity> {

    public FunctionRowReader(final ResultSet resultSet, boolean strict) {
        super(resultSet, strict);
    }

    void read(FunctionEntity row) throws SQLException {
        row.functionCatalog = readStringByIndex(FUNCTION_CAT);
        row.functionSchema = readStringByIndex(FUNCTION_SCHEM);
        row.functionName = readStringByIndex(FUNCTION_NAME);
        row.remarks = readStringByIndex(REMARKS);
        row.functionType = readShortByIndex(FUNCTION_TYPE);
        row.specificName = readStringByIndex(SPECIFIC_NAME);
    }

}