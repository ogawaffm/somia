package org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.velohaven.somia.jdbc.resultset.databasemetadata.entity.ClientInfoPropertyEntity;

import static org.velohaven.somia.jdbc.resultset.databasemetadata.columndefinition.GetClientInfoPropertiesColDef.*;

/**
 * A {@link KnownMetaDataReader} for reading {@link ClientInfoPropertyEntity}s from a ResultSet with the structure
 * returned by {@link java.sql.DatabaseMetaData#getClientInfoProperties}.
 */
public class ClientInfoPropertyReader extends KnownMetaDataReader<ClientInfoPropertyEntity> {

    protected ClientInfoPropertyReader(final ResultSet resultSet, final boolean strict) {
        super(resultSet, ClientInfoPropertyEntity::new, strict);
    }

    @Override
    protected void transfer(ClientInfoPropertyEntity row) throws SQLException {
        row.scope = readShortByIndex(SCOPE);
        row.name = readStringByIndex(NAME);
        row.maxLen = readIntegerByIndex(MAX_LEN);
        row.defaultValue = readStringByIndex(DEFAULT_VALUE);
        row.description = readStringByIndex(DESCRIPTION);
    }

}