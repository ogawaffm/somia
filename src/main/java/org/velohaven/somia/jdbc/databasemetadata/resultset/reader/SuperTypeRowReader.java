package org.velohaven.somia.jdbc.databasemetadata.resultset.reader;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.velohaven.somia.jdbc.databasemetadata.resultset.entity.SuperTypeEntity;

import static org.velohaven.somia.jdbc.databasemetadata.resultset.columndefinition.GetSuperTypesColDef.*;

/**
 * A {@link RowReader} for reading {@link SuperTypeEntity}s from a ResultSet with the structure
 * returned by {@link java.sql.DatabaseMetaData#getSuperTypes}.
 */
public class SuperTypeRowReader extends RowReader<SuperTypeEntity> {

    public SuperTypeRowReader(final ResultSet resultSet, boolean strict) {
        super(resultSet, strict);
    }

    void read(SuperTypeEntity row) throws SQLException {
        row.tableCatalog = readStringByIndex(TABLE_CAT);
        row.tableSchema = readStringByIndex(TABLE_SCHEM);
        row.tableName = readStringByIndex(TABLE_NAME);
        row.superTypeCatalog = readStringByIndex(SUPERTYPE_CAT);
        row.superTypeSchema = readStringByIndex(SUPERTYPE_SCHEM);
        row.superTypeName = readStringByIndex(SUPERTYPE_NAME);
    }

}