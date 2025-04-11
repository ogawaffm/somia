package org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.velohaven.somia.jdbc.resultset.databasemetadata.entity.ColumnPrivilegeEntity;

import static org.velohaven.somia.jdbc.resultset.databasemetadata.columndefinition.GetColumnPrivilegesColDef.*;

/**
 * A {@link KnownMetaDataReader} for reading {@link ColumnPrivilegeEntity}s from a ResultSet with the structure
 * returned by {@link java.sql.DatabaseMetaData#getColumnPrivileges}.
 */
public class ColumnPrivilegeReader extends KnownMetaDataReader<ColumnPrivilegeEntity> {

    protected ColumnPrivilegeReader(final ResultSet resultSet, final boolean strict) {
        super(resultSet, ColumnPrivilegeEntity::new, strict);
    }

    @Override
    protected void transfer(ColumnPrivilegeEntity row) throws SQLException {
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