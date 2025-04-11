package org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.velohaven.somia.jdbc.resultset.databasemetadata.entity.SuperTableEntity;

import static org.velohaven.somia.jdbc.resultset.databasemetadata.columndefinition.GetSuperTablesColDef.*;

/**
 * A {@link KnownMetaDataReader} for reading {@link SuperTableEntity}s from a ResultSet with the structure
 * returned by {@link java.sql.DatabaseMetaData#getSuperTables}.
 */
public class SuperTableReader extends KnownMetaDataReader<SuperTableEntity> {

    protected SuperTableReader(final ResultSet resultSet, final boolean strict) {
        super(resultSet, SuperTableEntity::new, strict);
    }

    @Override
    protected void transfer(SuperTableEntity row) throws SQLException {
        row.tableCatalog = readStringByIndex(TABLE_CAT);
        row.tableSchema = readStringByIndex(TABLE_SCHEM);
        row.tableName = readStringByIndex(TABLE_NAME);
        row.superTableName = readStringByIndex(SUPERTABLE_NAME);
    }

}