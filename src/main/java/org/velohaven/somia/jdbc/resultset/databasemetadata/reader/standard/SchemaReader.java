package org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.velohaven.somia.jdbc.resultset.databasemetadata.entity.SchemaEntity;

import static org.velohaven.somia.jdbc.resultset.databasemetadata.columndefinition.GetSchemasColDef.*;

/**
 * A {@link KnownMetaDataReader} for reading {@link SchemaEntity}s from a ResultSet with the structure
 * returned by {@link java.sql.DatabaseMetaData#getSchemas}.
 */
public class SchemaReader extends KnownMetaDataReader<SchemaEntity> {

    protected SchemaReader(final ResultSet resultSet, final boolean strict) {
        super(resultSet, SchemaEntity::new, strict);
    }

    @Override
    protected void transfer(SchemaEntity row) throws SQLException {
        row.tableSchema = readStringByIndex(TABLE_SCHEM);
        row.tableCatalog = readStringByIndex(TABLE_CATALOG);
    }

}