package org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.velohaven.somia.jdbc.resultset.databasemetadata.entity.CatalogEntity;

import static org.velohaven.somia.jdbc.resultset.databasemetadata.columndefinition.GetCatalogsColDef.*;

/**
 * A {@link KnownMetaDataReader} for reading {@link CatalogEntity}s from a ResultSet with the structure
 * returned by {@link java.sql.DatabaseMetaData#getCatalogs}.
 */
public class CatalogReader extends KnownMetaDataReader<CatalogEntity> {

    protected CatalogReader(final ResultSet resultSet, final boolean strict) {
        super(resultSet, CatalogEntity::new, strict);
    }

    @Override
    protected void transfer(CatalogEntity row) throws SQLException {
        row.tableCatalog = readStringByIndex(TABLE_CAT);
    }

}