package org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.velohaven.somia.jdbc.resultset.databasemetadata.entity.TablePrivilegeEntity;

import static org.velohaven.somia.jdbc.resultset.databasemetadata.columndefinition.GetTablePrivilegesColDef.*;

/**
 * A {@link KnownMetaDataReader} for reading {@link TablePrivilegeEntity}s from a ResultSet with the structure
 * returned by {@link java.sql.DatabaseMetaData#getTablePrivileges}.
 */
public class TablePrivilegeReader extends KnownMetaDataReader<TablePrivilegeEntity> {

    protected TablePrivilegeReader(final ResultSet resultSet, final boolean strict) {
        super(resultSet, TablePrivilegeEntity::new, strict);
    }

    @Override
    protected void transfer(TablePrivilegeEntity row) throws SQLException {
        row.tableCatalog = readStringByIndex(TABLE_CAT);
        row.tableSchema = readStringByIndex(TABLE_SCHEM);
        row.tableName = readStringByIndex(TABLE_NAME);
        row.grantor = readStringByIndex(GRANTOR);
        row.grantee = readStringByIndex(GRANTEE);
        row.privilege = readStringByIndex(PRIVILEGE);
        row.isGrantable = readStringByIndex(IS_GRANTABLE);
    }

}