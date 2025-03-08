package org.velohaven.somia.jdbc.databasemetadata.resultset.reader;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.velohaven.somia.jdbc.databasemetadata.resultset.entity.SuperTableEntity;

import static org.velohaven.somia.jdbc.databasemetadata.resultset.columndefinition.GetSuperTablesColDef.*;

/**
 * A {@link RowReader} for reading {@link SuperTableEntity}s from a ResultSet with the structure
 * returned by {@link java.sql.DatabaseMetaData#getSuperTables}.
 */
public class SuperTableRowReader extends RowReader<SuperTableEntity> {

    public SuperTableRowReader(final ResultSet resultSet, boolean strict) {
        super(resultSet, strict);
    }

    void read(SuperTableEntity row) throws SQLException {
        row.tableCatalog = readStringByIndex(TABLE_CAT);
        row.tableSchema = readStringByIndex(TABLE_SCHEM);
        row.tableName = readStringByIndex(TABLE_NAME);
        row.superTableName = readStringByIndex(SUPERTABLE_NAME);
    }

}