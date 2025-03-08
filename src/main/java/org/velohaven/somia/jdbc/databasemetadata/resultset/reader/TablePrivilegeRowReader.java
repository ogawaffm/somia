package org.velohaven.somia.jdbc.databasemetadata.resultset.reader;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.velohaven.somia.jdbc.databasemetadata.resultset.entity.TablePrivilegeEntity;

import static org.velohaven.somia.jdbc.databasemetadata.resultset.columndefinition.GetTablePrivilegesColDef.*;

/**
 * A {@link RowReader} for reading {@link TablePrivilegeEntity}s from a ResultSet with the structure
 * returned by {@link java.sql.DatabaseMetaData#getTablePrivileges}.
 */
public class TablePrivilegeRowReader extends RowReader<TablePrivilegeEntity> {

    public TablePrivilegeRowReader(final ResultSet resultSet, boolean strict) {
        super(resultSet, strict);
    }

    void read(TablePrivilegeEntity row) throws SQLException {
        row.tableCatalog = readStringByIndex(TABLE_CAT);
        row.tableSchema = readStringByIndex(TABLE_SCHEM);
        row.tableName = readStringByIndex(TABLE_NAME);
        row.grantor = readStringByIndex(GRANTOR);
        row.grantee = readStringByIndex(GRANTEE);
        row.privilege = readStringByIndex(PRIVILEGE);
        row.isGrantable = readStringByIndex(IS_GRANTABLE);
    }

}