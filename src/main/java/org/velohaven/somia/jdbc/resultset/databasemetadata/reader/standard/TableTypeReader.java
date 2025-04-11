package org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.velohaven.somia.jdbc.resultset.databasemetadata.entity.TableTypeEntity;

import static org.velohaven.somia.jdbc.resultset.databasemetadata.columndefinition.GetTableTypesColDef.*;

/**
 * A {@link KnownMetaDataReader} for reading {@link TableTypeEntity}s from a ResultSet with the structure
 * returned by {@link java.sql.DatabaseMetaData#getTableTypes}.
 */
public class TableTypeReader extends KnownMetaDataReader<TableTypeEntity> {

    protected TableTypeReader(final ResultSet resultSet, final boolean strict) {
        super(resultSet, TableTypeEntity::new, strict);
    }

    @Override
    protected void transfer(TableTypeEntity row) throws SQLException {
        row.tableType = readStringByIndex(TABLE_TYPE);
    }

}