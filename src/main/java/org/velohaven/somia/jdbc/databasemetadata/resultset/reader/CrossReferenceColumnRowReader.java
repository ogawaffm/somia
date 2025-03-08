package org.velohaven.somia.jdbc.databasemetadata.resultset.reader;

import org.velohaven.somia.jdbc.databasemetadata.resultset.entity.CrossReferenceColumnEntity;

import java.sql.ResultSet;

public class CrossReferenceColumnRowReader extends KeyReferenceColumnRowReader<CrossReferenceColumnEntity> {
    public CrossReferenceColumnRowReader(final ResultSet resultSet, final boolean strict) {
        super(resultSet, strict);
    }
}
