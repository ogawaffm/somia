package org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard;

import org.velohaven.somia.jdbc.resultset.databasemetadata.entity.ImportedKeyColumnEntity;

import java.sql.ResultSet;

public class ImportedKeyColumnReader extends KeyReferenceColumnReader<ImportedKeyColumnEntity> {

    protected ImportedKeyColumnReader(final ResultSet resultSet, final boolean strict) {
        super(resultSet, ImportedKeyColumnEntity::new, strict);
    }
}
