package org.velohaven.somia.jdbc.databasemetadata.resultset.reader;

import org.velohaven.somia.jdbc.databasemetadata.resultset.entity.ImportedKeyColumnEntity;

import java.sql.ResultSet;

public class ImportedKeyColumnRowReader extends KeyReferenceColumnRowReader<ImportedKeyColumnEntity> {
    public ImportedKeyColumnRowReader(final ResultSet resultSet, final boolean strict) {
        super(resultSet, strict);
    }
}
