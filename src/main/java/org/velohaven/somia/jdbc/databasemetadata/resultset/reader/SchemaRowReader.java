package org.velohaven.somia.jdbc.databasemetadata.resultset.reader;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.velohaven.somia.jdbc.databasemetadata.resultset.entity.SchemaEntity;

import static org.velohaven.somia.jdbc.databasemetadata.resultset.columndefinition.GetSchemasColDef.*;

/**
 * A {@link RowReader} for reading {@link SchemaEntity}s from a ResultSet with the structure
 * returned by {@link java.sql.DatabaseMetaData#getSchemas}.
 */
public class SchemaRowReader extends RowReader<SchemaEntity> {

    public SchemaRowReader(final ResultSet resultSet, boolean strict) {
        super(resultSet, strict);
    }

    public void read(SchemaEntity row) throws SQLException {
        row.tableSchema = readStringByIndex(TABLE_SCHEM);
        row.tableCatalog = readStringByIndex(TABLE_CATALOG);
    }

}