package org.velohaven.somia.jdbc.resultset.databasemetadata.reader.oracle;

import lombok.NonNull;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.StandardMetadataReaderFactory;

import java.sql.ResultSet;

public class OracleMetadataReaderFactory extends StandardMetadataReaderFactory {

    @Override
    public org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.TableReader createTableRowReader(@NonNull final ResultSet resultSet, boolean strict) {
        return new OracleTableReader(resultSet, strict);
    }

}
