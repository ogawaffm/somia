package org.velohaven.somia.model.factory;

import lombok.NonNull;
import org.velohaven.somia.jdbc.resultset.databasemetadata.entity.PrimaryKeyColumnEntity;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.MetadataReaderFactory;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.RdbmsAwareReaderFactory;
import org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard.PrimaryKeyReader;
import org.velohaven.somia.model.ParentBinder;
import org.velohaven.somia.model.PrimaryKey;
import org.velohaven.somia.model.PrimaryKeyColumn;
import org.velohaven.somia.model.Table;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class PrimaryKeyFactory {

    /**
     * Reads the primary key of a table from the database.
     *
     * @param connection Connection to the database
     * @param table      Table to read the primary key for
     * @return PrimaryKey object with the primary key columns
     * @throws SQLException if the primary key could not be read
     */
    public PrimaryKey readPrimaryKey(@NonNull final Connection connection, @NonNull final Table table)
            throws SQLException {

        // Primary key columns come in the order of the column name, so we need to sort them by keySeq
        SortedMap<Integer, PrimaryKeyColumn> primaryKeyColumns = new TreeMap<>();

        PrimaryKey primaryKey = null;

        try (
                ResultSet primaryKeyColumnResultSet =
                        connection.getMetaData().getPrimaryKeys(table.catalogName(), table.schemaName(), table.name())
        ) {

            MetadataReaderFactory rowReaderFactory = new RdbmsAwareReaderFactory();
            PrimaryKeyReader primaryKeyRowReader = rowReaderFactory.createPrimaryKeyRowReader(primaryKeyColumnResultSet, true);

            PrimaryKeyColumnEntity primaryKeyEntity;

            PrimaryKeyColumn keyColumn;

            List<PrimaryKeyColumnEntity> primaryKeyColumnEntities = primaryKeyRowReader.readAll();

            for (PrimaryKeyColumnEntity primaryKeyColumnEntity : primaryKeyColumnEntities) {

                primaryKeyEntity = primaryKeyColumnEntity;

                if (primaryKeyEntity.keySeq == 1) {
                    primaryKey = new PrimaryKey();
                    primaryKey.name(primaryKeyEntity.primaryKeyName);
                }

                keyColumn = new PrimaryKeyColumn();
                ParentBinder.bind(primaryKey, keyColumn);
                keyColumn.name(primaryKeyEntity.columnName);

                primaryKeyColumns.put(primaryKeyEntity.keySeq, keyColumn);

            }

        } catch (SQLException exception) {
            throw new SQLException("Primary key of " + table.fullQuotedIdentifier() + " could not be read.", exception);
        }

        if (primaryKey != null) {
            primaryKey.columns().appendAll(primaryKeyColumns.values());
        }

        return primaryKey;

    }

}
