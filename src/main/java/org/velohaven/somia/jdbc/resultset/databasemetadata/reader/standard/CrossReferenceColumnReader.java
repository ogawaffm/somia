package org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard;

import org.velohaven.somia.jdbc.resultset.databasemetadata.entity.CrossReferenceColumnEntity;

import java.sql.ResultSet;

public class CrossReferenceColumnReader extends KeyReferenceColumnReader<CrossReferenceColumnEntity> {

    protected CrossReferenceColumnReader(final ResultSet resultSet, final boolean strict) {
        super(resultSet, CrossReferenceColumnEntity::new, strict);
    }

}
