package org.velohaven.somia.jdbc.databasemetadata.resultset.reader;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.velohaven.somia.jdbc.databasemetadata.resultset.entity.IndexInfoEntity;

import static org.velohaven.somia.jdbc.databasemetadata.resultset.columndefinition.GetIndexInfoColDef.*;

/**
 * A {@link RowReader} for reading {@link IndexInfoEntity}s from a ResultSet with the structure
 * returned by {@link java.sql.DatabaseMetaData#getIndexInfo}.
 */
public class IndexInfoRowReader extends RowReader<IndexInfoEntity> {

    public IndexInfoRowReader(final ResultSet resultSet, boolean strict) {
        super(resultSet, strict);
    }

    public void read(IndexInfoEntity row) throws SQLException {
        row.tableCatalog = readStringByIndex(TABLE_CAT);
        row.tableSchema = readStringByIndex(TABLE_SCHEM);
        row.tableName = readStringByIndex(TABLE_NAME);
        row.nonUnique = readBooleanByIndex(NON_UNIQUE);
        row.indexQualifier = readStringByIndex(INDEX_QUALIFIER);
        row.indexName = readStringByIndex(INDEX_NAME);
        row.type = readShortByIndex(TYPE);
        row.ordinalPosition = readIntegerByIndex(ORDINAL_POSITION);
        row.columnName = readStringByIndex(COLUMN_NAME);
        row.ascOrDesc = readStringByIndex(ASC_OR_DESC);
        row.cardinality = readLongByIndex(CARDINALITY);
        row.pages = readLongByIndex(PAGES);
        row.filterCondition = readStringByIndex(FILTER_CONDITION);
    }

}