package org.velohaven.somia.jdbc.databasemetadata.resultset.reader;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.velohaven.somia.jdbc.databasemetadata.resultset.entity.CatalogEntity;

import static org.velohaven.somia.jdbc.databasemetadata.resultset.columndefinition.GetCatalogsColDef.*;

/**
 * A {@link RowReader} for reading {@link CatalogEntity}s from a ResultSet with the structure
 * returned by {@link java.sql.DatabaseMetaData#getCatalogs}.
 */
public class CatalogRowReader extends RowReader<CatalogEntity> {

    public CatalogRowReader(final ResultSet resultSet, boolean strict) {
        super(resultSet, strict);
    }

    public void read(CatalogEntity row) throws SQLException {
        row.tableCatalog = readStringByIndex(TABLE_CAT);
    }

}