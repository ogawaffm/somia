package org.velohaven.somia.jdbc.databasemetadata.resultset.reader;

import org.velohaven.somia.jdbc.databasemetadata.resultset.entity.ExportedKeyColumnEntity;

import java.sql.ResultSet;

public class ExportedKeyColumnRowReader extends KeyReferenceColumnRowReader<ExportedKeyColumnEntity> {
    public ExportedKeyColumnRowReader(final ResultSet resultSet, final boolean strict) {
        super(resultSet, strict);
    }
}
