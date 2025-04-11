package org.velohaven.somia.jdbc.resultset.reader;

import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.KnownMetaDataReader;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A {@link KnownMetaDataReader} for reading rows from a ResultSet into an array of Objects.
 */
public class RowToArrayReader extends AbstractResultSetReader<Object[]> {

    final private static class ObjectArrayFactory implements java.util.function.Supplier<Object[]> {

        private final int arrayLength;

        private ObjectArrayFactory(final ResultSet resultSet) {
            try {
                this.arrayLength = resultSet.getMetaData().getColumnCount();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public Object[] get() {
            return new Object[arrayLength];
        }
    }

    protected RowToArrayReader(final ResultSet resultSet) {
        super(resultSet, new ObjectArrayFactory(resultSet));
    }

    @Override
    protected void transfer(Object[] row) throws SQLException {
        for (int i = 0; i < row.length; i++) {
            row[i] = resultSet.getObject(i + 1);
        }
    }

}