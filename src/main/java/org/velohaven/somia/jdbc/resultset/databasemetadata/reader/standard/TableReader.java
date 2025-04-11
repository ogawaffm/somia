package org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.velohaven.somia.jdbc.resultset.databasemetadata.entity.TableEntity;

import static org.velohaven.somia.jdbc.resultset.databasemetadata.columndefinition.GetTablesColDef.*;

/**
 * A {@link KnownMetaDataReader} for reading {@link TableEntity}s from a ResultSet with the structure
 * returned by {@link java.sql.DatabaseMetaData#getTables}.
 */
public class TableReader extends KnownMetaDataReader<TableEntity> {

    protected TableReader(final ResultSet resultSet, final boolean strict) {
        super(resultSet, TableEntity::new, strict);
    }

    @Override
    protected void transfer(TableEntity row) throws SQLException {
        row.tableCatalog = readStringByIndex(TABLE_CAT);
        row.tableSchema = readStringByIndex(TABLE_SCHEM);
        row.tableName = readStringByIndex(TABLE_NAME);
        row.tableType = readStringByIndex(TABLE_TYPE);
        row.remarks = readStringByIndex(REMARKS);
        row.typeCatalog = readStringByIndex(TYPE_CAT);
        row.typeSchema = readStringByIndex(TYPE_SCHEM);
        row.typeName = readStringByIndex(TYPE_NAME);
        row.selfReferencingColName = readStringByIndex(SELF_REFERENCING_COL_NAME);
        row.refGeneration = readStringByIndex(REF_GENERATION);
    }

}