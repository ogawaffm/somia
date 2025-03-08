package org.velohaven.somia.jdbc.databasemetadata.resultset.reader;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.velohaven.somia.jdbc.databasemetadata.resultset.entity.TableEntity;

import static org.velohaven.somia.jdbc.databasemetadata.resultset.columndefinition.GetTablesColDef.*;

/**
 * A {@link RowReader} for reading {@link TableEntity}s from a ResultSet with the structure
 * returned by {@link java.sql.DatabaseMetaData#getTables}.
 */
public class TableRowReader extends RowReader<TableEntity> {

    public TableRowReader(final ResultSet resultSet, boolean strict) {
        super(resultSet, strict);
    }

    public void read(TableEntity row) throws SQLException {
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