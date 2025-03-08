package org.velohaven.somia.jdbc.databasemetadata.resultset.reader;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.velohaven.somia.jdbc.databasemetadata.resultset.entity.TableTypeEntity;

import static org.velohaven.somia.jdbc.databasemetadata.resultset.columndefinition.GetTableTypesColDef.*;

/**
 * A {@link RowReader} for reading {@link TableTypeEntity}s from a ResultSet with the structure
 * returned by {@link java.sql.DatabaseMetaData#getTableTypes}.
 */
public class TableTypeRowReader extends RowReader<TableTypeEntity> {

    public TableTypeRowReader(final ResultSet resultSet, boolean strict) {
        super(resultSet, strict);
    }

    void read(TableTypeEntity row) throws SQLException {
        row.tableType = readStringByIndex(TABLE_TYPE);
    }

}