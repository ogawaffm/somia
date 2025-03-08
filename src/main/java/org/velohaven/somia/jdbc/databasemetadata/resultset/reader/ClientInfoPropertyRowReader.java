package org.velohaven.somia.jdbc.databasemetadata.resultset.reader;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.velohaven.somia.jdbc.databasemetadata.resultset.entity.ClientInfoPropertyEntity;

import static org.velohaven.somia.jdbc.databasemetadata.resultset.columndefinition.GetClientInfoPropertiesColDef.*;

/**
 * A {@link RowReader} for reading {@link ClientInfoPropertyEntity}s from a ResultSet with the structure
 * returned by {@link java.sql.DatabaseMetaData#getClientInfoProperties}.
 */
public class ClientInfoPropertyRowReader extends RowReader<ClientInfoPropertyEntity> {

    public ClientInfoPropertyRowReader(final ResultSet resultSet, boolean strict) {
        super(resultSet, strict);
    }

    void read(ClientInfoPropertyEntity row) throws SQLException {
        row.scope = readShortByIndex(SCOPE);
        row.name = readStringByIndex(NAME);
        row.maxLen = readIntegerByIndex(MAX_LEN);
        row.defaultValue = readStringByIndex(DEFAULT_VALUE);
        row.description = readStringByIndex(DESCRIPTION);
    }

}