package org.velohaven.somia.jdbc.databasemetadata.resultset.reader;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.velohaven.somia.jdbc.databasemetadata.resultset.entity.UDTEntity;

import static org.velohaven.somia.jdbc.databasemetadata.resultset.columndefinition.GetUDTsColDef.*;

/**
 * A {@link RowReader} for reading {@link UDTEntity}s from a ResultSet with the structure
 * returned by {@link java.sql.DatabaseMetaData#getUDTs}.
 */
public class UDTRowReader extends RowReader<UDTEntity> {

    public UDTRowReader(final ResultSet resultSet, boolean strict) {
        super(resultSet, strict);
    }

    void read(UDTEntity row) throws SQLException {
        row.tableCatalog = readStringByIndex(TABLE_CAT);
        row.tableSchema = readStringByIndex(TABLE_SCHEM);
        row.typeName = readStringByIndex(TYPE_NAME);
        row.className = readStringByIndex(CLASS_NAME);
        row.dataType = readIntegerByIndex(DATA_TYPE);
        row.remarks = readStringByIndex(REMARKS);
        row.baseType = readShortByIndex(BASE_TYPE);
    }

}