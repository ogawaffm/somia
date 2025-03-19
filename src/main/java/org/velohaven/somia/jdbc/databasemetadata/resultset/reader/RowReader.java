package org.velohaven.somia.jdbc.databasemetadata.resultset.reader;

import org.velohaven.somia.jdbc.databasemetadata.resultset.columndefinition.ColDef;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public abstract class RowReader<T> {

    private final ResultSet resultSet;
    private final boolean strict;

    RowReader(ResultSet resultSet, boolean strict) {
        this.resultSet = resultSet;
        this.strict = strict;
    }

    private SQLException createColAccessException(ColDef<?> colDef, SQLException cause) {
        return new SQLException(
            "Could not read from column " + colDef.name() + " using (1-based) column index " + colDef.ordinalPosition(),
            cause
        );
    }

    private SQLException createNullViolatedException(ColDef<?> colDef) {
        return new SQLException(
            "Read NULL from column "
                + colDef.name()
                + " which is defined NOT NULL using (1-based) column index "
                + colDef.ordinalPosition()
        );
    }

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

    String readStringByIndex(ColDef<?> columnDefinition) throws SQLException {
        try {
            String value = resultSet.getString(columnDefinition.ordinalPosition());
            return checkResult(value, columnDefinition);
        } catch (SQLException e) {
            throw createColAccessException(columnDefinition, e);
        }
    }

    Short readShortByIndex(ColDef<?> columnDefinition) throws SQLException {
        try {
            short value = resultSet.getShort(columnDefinition.ordinalPosition());
            return checkResult(value, columnDefinition);
        } catch (SQLException e) {
            throw createColAccessException(columnDefinition, e);
        }
    }

    Integer readIntegerByIndex(ColDef<?> columnDefinition) throws SQLException {
        try {
            Integer value = resultSet.getInt(columnDefinition.ordinalPosition());
            return checkResult(value, columnDefinition);
        } catch (SQLException e) {
            throw createColAccessException(columnDefinition, e);
        }
    }

    Long readLongByIndex(ColDef<?> columnDefinition) throws SQLException {
        try {
            Long value = resultSet.getLong(columnDefinition.ordinalPosition());
            return checkResult(value, columnDefinition);
        } catch (SQLException e) {
            throw createColAccessException(columnDefinition, e);
        }
    }

    Boolean readBooleanByIndex(ColDef<?> columnDefinition) throws SQLException {
        try {
            Boolean value = resultSet.getBoolean(columnDefinition.ordinalPosition());
            return checkResult(value, columnDefinition);
        } catch (SQLException e) {
            throw createColAccessException(columnDefinition, e);
        }
    }

    abstract void read(T row) throws SQLException;

    public List<T> readAll(Supplier<T> constructor) throws SQLException {
        List<T> rows = new ArrayList<>();
        int rowNumber = 0;
        try {
            while (resultSet.next()) {
                rowNumber++;
                T row = constructor.get();
                read(row);
                rows.add(row);
            }
            return rows;
        } catch (SQLException e) {
            throw new SQLException("Could not read row #" + rowNumber + " (1-based)", e);
        }
    }

}