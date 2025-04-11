package org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.velohaven.somia.jdbc.resultset.databasemetadata.entity.SuperTypeEntity;

import static org.velohaven.somia.jdbc.resultset.databasemetadata.columndefinition.GetSuperTypesColDef.*;

/**
 * A {@link KnownMetaDataReader} for reading {@link SuperTypeEntity}s from a ResultSet with the structure
 * returned by {@link java.sql.DatabaseMetaData#getSuperTypes}.
 */
public class SuperTypeReader extends KnownMetaDataReader<SuperTypeEntity> {

    protected SuperTypeReader(final ResultSet resultSet, final boolean strict) {
        super(resultSet, SuperTypeEntity::new, strict);
    }

    @Override
    protected void transfer(SuperTypeEntity row) throws SQLException {
        row.tableCatalog = readStringByIndex(TABLE_CAT);
        row.tableSchema = readStringByIndex(TABLE_SCHEM);
        row.tableName = readStringByIndex(TABLE_NAME);
        row.superTypeCatalog = readStringByIndex(SUPERTYPE_CAT);
        row.superTypeSchema = readStringByIndex(SUPERTYPE_SCHEM);
        row.superTypeName = readStringByIndex(SUPERTYPE_NAME);
    }

}