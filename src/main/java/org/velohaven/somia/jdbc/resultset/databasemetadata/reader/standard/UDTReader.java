package org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.velohaven.somia.jdbc.resultset.databasemetadata.entity.UDTEntity;

import static org.velohaven.somia.jdbc.resultset.databasemetadata.columndefinition.GetUDTsColDef.*;

/**
 * A {@link KnownMetaDataReader} for reading {@link UDTEntity}s from a ResultSet with the structure
 * returned by {@link java.sql.DatabaseMetaData#getUDTs}.
 */
public class UDTReader extends KnownMetaDataReader<UDTEntity> {

    protected UDTReader(final ResultSet resultSet, final boolean strict) {
        super(resultSet, UDTEntity::new, strict);
    }

    @Override
    protected void transfer(UDTEntity row) throws SQLException {
        row.tableCatalog = readStringByIndex(TABLE_CAT);
        row.tableSchema = readStringByIndex(TABLE_SCHEM);
        row.typeName = readStringByIndex(TYPE_NAME);
        row.className = readStringByIndex(CLASS_NAME);
        row.dataType = readIntegerByIndex(DATA_TYPE);
        row.remarks = readStringByIndex(REMARKS);
        row.baseType = readShortByIndex(BASE_TYPE);
    }

}