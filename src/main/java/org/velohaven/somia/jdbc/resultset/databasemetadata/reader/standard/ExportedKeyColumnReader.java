package org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard;

import org.velohaven.somia.jdbc.resultset.databasemetadata.entity.ExportedKeyColumnEntity;

import java.sql.ResultSet;

public class ExportedKeyColumnReader extends KeyReferenceColumnReader<ExportedKeyColumnEntity> {

    protected ExportedKeyColumnReader(final ResultSet resultSet, final boolean strict) {
        super(resultSet, ExportedKeyColumnEntity::new, strict);
    }
}
