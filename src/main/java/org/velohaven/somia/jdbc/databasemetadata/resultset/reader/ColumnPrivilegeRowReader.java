package org.velohaven.somia.jdbc.databasemetadata.resultset.reader;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.velohaven.somia.jdbc.databasemetadata.resultset.entity.ColumnPrivilegeEntity;

import static org.velohaven.somia.jdbc.databasemetadata.resultset.columndefinition.GetColumnPrivilegesColDef.*;

/**
 * A {@link RowReader} for reading {@link ColumnPrivilegeEntity}s from a ResultSet with the structure
 * returned by {@link java.sql.DatabaseMetaData#getColumnPrivileges}.
 */
public class ColumnPrivilegeRowReader extends RowReader<ColumnPrivilegeEntity> {

    public ColumnPrivilegeRowReader(final ResultSet resultSet, boolean strict) {
        super(resultSet, strict);
    }

    void read(ColumnPrivilegeEntity row) throws SQLException {
        row.tableCatalog = readStringByIndex(TABLE_CAT);
        row.tableSchema = readStringByIndex(TABLE_SCHEM);
        row.tableName = readStringByIndex(TABLE_NAME);
        row.columnName = readStringByIndex(COLUMN_NAME);
        row.grantor = readStringByIndex(GRANTOR);
        row.grantee = readStringByIndex(GRANTEE);
        row.privilege = readStringByIndex(PRIVILEGE);
        row.isGrantable = readStringByIndex(IS_GRANTABLE);
    }

}