package org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard;

import org.velohaven.somia.jdbc.resultset.databasemetadata.columndefinition.ColDef;
import org.velohaven.somia.jdbc.resultset.reader.AbstractResultSetReader;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Supplier;

/**
 * Abstract class for reading rows from a ResultSet with known metadata from DatabaseMetadata.
 *
 * @param <T> the type of the object to be created from the row data
 */
public abstract class KnownMetaDataReader<T> extends AbstractResultSetReader<T> {

    /**
     * The strict mode flag. If true, the reader will throw an exception if a column is defined as NOT NULL and a null
     * value is read from it.
     */
    private final boolean strict;

    /**
     * Constructor for KnownMetaDataRowReader.
     *
     * @param resultSet   the ResultSet to read from
     * @param constructor a Supplier that provides a new instance of T
     * @param strict      whether to enable strict mode
     */
    protected KnownMetaDataReader(final ResultSet resultSet, final Supplier<T> constructor, final boolean strict) {
        super(resultSet, constructor);
        this.strict = strict;
    }

    /**
     * Creates a new SQLException indicating that a column could not be accessed.
     *
     * @param colDef the column definition
     * @param cause  the cause of the exception
     * @return a new SQLException indicating that a column could not be accessed
     */
    private SQLException createColAccessException(ColDef<?> colDef, SQLException cause) {
        return new SQLException(
                "Could not read from column " + colDef.name() + " using (1-based) column index " + colDef.ordinalPosition(),
                cause
        );
    }

    /**
     * Creates a new SQLException indicating that a null value was read from a column that is defined as NOT NULL.
     *
     * @param colDef the column definition
     * @return a new SQLException indicating that a null value was read from a column that is defined as NOT NULL
     */
    private SQLException createNullViolatedException(ColDef<?> colDef) {
        return new SQLException(
                "Read NULL from column "
                        + colDef.name()
                        + " which is defined NOT NULL using (1-based) column index "
                        + colDef.ordinalPosition()
        );
    }

    /**
     * Throws an exception if the value is null and the column is not nullable and strict mode is enabled. Otherwise,
     * returns the value.
     *
     * @param value            the value to check
     * @param columnDefinition the column definition
     * @param <V>              the type of the value
     * @return the value if it is not null or strict mode is disabled, otherwise null
     * @throws SQLException if the value is null and strict mode is enabled and the column is not nullable
     */
    <V> V checkResult(V value, ColDef<?> columnDefinition) throws SQLException {
        if (resultSet.wasNull()) {
            if (strict && !columnDefinition.isNullable()) {
                throw createNullViolatedException(columnDefinition);
            } else {
                return null;
            }
        } else {
            return value;
        }
    }

    /**
     * Reads a String column value from the result set by its index and checks if it is null.
     *
     * @param columnDefinition the column definition
     * @return the column value or null if the column is nullable and the value is null
     * @throws SQLException if an error occurs while reading the column value
     */

    protected String readStringByIndex(ColDef<?> columnDefinition) throws SQLException {
        try {
            String value = resultSet.getString(columnDefinition.ordinalPosition());
            return checkResult(value, columnDefinition);
        } catch (SQLException e) {
            throw createColAccessException(columnDefinition, e);
        }
    }

    /**
     * Reads a Short column value from the result set by its index and checks if it is null.
     *
     * @param columnDefinition the column definition
     * @return the column value or null if the column is nullable and the value is null
     * @throws SQLException if an error occurs while reading the column value
     */
    Short readShortByIndex(ColDef<?> columnDefinition) throws SQLException {
        try {
            short value = resultSet.getShort(columnDefinition.ordinalPosition());
            return checkResult(value, columnDefinition);
        } catch (SQLException e) {
            throw createColAccessException(columnDefinition, e);
        }
    }

    /**
     * Reads a String column value from the result set by its index and checks if it is null.
     *
     * @param columnDefinition the column definition
     * @return the column value or null if the column is nullable and the value is null
     * @throws SQLException if an error occurs while reading the column value
     */

    Integer readIntegerByIndex(ColDef<?> columnDefinition) throws SQLException {
        try {
            Integer value = resultSet.getInt(columnDefinition.ordinalPosition());
            return checkResult(value, columnDefinition);
        } catch (SQLException e) {
            throw createColAccessException(columnDefinition, e);
        }
    }

    /**
     * Reads a Long column value from the result set by its index and checks if it is null.
     *
     * @param columnDefinition the column definition
     * @return the column value or null if the column is nullable and the value is null
     * @throws SQLException if an error occurs while reading the column value
     */
    Long readLongByIndex(ColDef<?> columnDefinition) throws SQLException {
        try {
            Long value = resultSet.getLong(columnDefinition.ordinalPosition());
            return checkResult(value, columnDefinition);
        } catch (SQLException e) {
            throw createColAccessException(columnDefinition, e);
        }
    }

    /**
     * Reads a Boolean column value from the result set by its index and checks if it is null.
     *
     * @param columnDefinition the column definition
     * @return the column value or null if the column is nullable and the value is null
     * @throws SQLException if an error occurs while reading the column value
     */
    Boolean readBooleanByIndex(ColDef<?> columnDefinition) throws SQLException {
        try {
            Boolean value = resultSet.getBoolean(columnDefinition.ordinalPosition());
            return checkResult(value, columnDefinition);
        } catch (SQLException e) {
            throw createColAccessException(columnDefinition, e);
        }
    }

}