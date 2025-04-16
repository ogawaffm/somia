package org.velohaven.somia.jdbc.resultset.reader;

import lombok.NonNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public abstract class AbstractResultSetReader<T> {

    final protected ResultSet resultSet;
    private final Supplier<T> constructor;

    protected AbstractResultSetReader(@NonNull final ResultSet resultSet, @NonNull final Supplier<T> constructor) {
        this.resultSet = resultSet;
        this.constructor = constructor;
    }

    /**
     * Transfers the data from the ResultSet to the given row object. The position of the ResultSet is not changed.
     *
     * @param row the row object to transfer the data to
     * @throws SQLException if an error occurs while accessing the ResultSet
     */
    protected abstract void transfer(T row) throws SQLException;

    /**
     * Transfers the data from the ResultSet to the given row object. The position of the ResultSet is not changed.
     * If an error occurs while accessing the ResultSet, a SQLException is thrown with a message that includes the row
     * number.
     *
     * @param row the row object to transfer the data to
     * @throws SQLException a rdbms-independent exception wrapped around the causing SQLException
     */
    private void transferWithExceptionHandling(T row) throws SQLException {
        try {
            transfer(row);
        } catch (SQLException e) {
            throw createCouldNotAccessRowDataException(resultSet.getRow(), e);
        }
    }

    /**
     * Checks if the ResultSet is empty. The ResultSet is considered empty if it is positioned before the first row or
     * after the last row.
     *
     * @return true if the ResultSet is empty, false otherwise
     * @throws SQLException a rdbms-independent exception wrapped around the causing SQLException
     */

    private boolean isResultSetEmpty() throws SQLException {
        try {
            return !resultSet.isBeforeFirst();
        } catch (SQLException e) {
            throw new SQLException("Could not determine if the ResultSet is empty", e);
        }
    }

    /**
     * Determines and returns the current row number of the ResultSet. The row number is 1-based, meaning that the
     * first row is 1, the second row is 2, and so on. If the ResultSet is positioned before the first row or after the
     * last row, the row number is 0. If an error occurs while accessing the ResultSet, a SQLException is thrown.
     *
     * @return the current row number (1-based) or 0 if the ResultSet is empty
     * @throws SQLException a rdbms-independent exception wrapped around the causing SQLException
     */

    private int getRowWithExceptionHandling() throws SQLException {
        try {
            return resultSet.getRow();
        } catch (SQLException e) {
            throw new SQLException("Could not get current row number", e);
        }
    }

    /**
     * Reads a row from the ResultSet. The next() method is called to move the ResultSet to the next row.
     * If the ResultSet is already at the last row, null is returned otherwise the row object is returned.
     *
     * @return the row object or null if the ResultSet is at the last row
     * @throws SQLException a rdbms-independent exception wrapped around the causing SQLException
     */
    public T read() throws SQLException {
        int rowNumber = 0;
        try {
            if (!nextWithExceptionHandling()) {
                return null;
            } else {
                T row = constructor.get();
                rowNumber = getRowWithExceptionHandling();
                transferWithExceptionHandling(row);
                return row;
            }
        } catch (SQLException e) {
            throw createCouldNotReadRowException(rowNumber, e);
        }
    }

    /**
     * Checks if the given row number is valid. The row number is 1-based, meaning that the first row is 1, the
     * second row is 2, and so on. If the row number is 0, an SQLException is thrown. A negative row number is
     * considered valid and is used to address rows from the end of the ResultSet.
     *
     * @param rowNumber the row number to check
     * @throws SQLException a rdbms-independent exception
     */
    private void checkRowNumber(int rowNumber) throws SQLException {
        if (rowNumber == 0) {
            throw new SQLException("Row number must be greater than 0 or negative to address rows from the end");
        }
        if (rowNumber < 0 && resultSet.getType() == ResultSet.TYPE_FORWARD_ONLY) {
            throw new SQLException("Cannot address rows from the end of the ResultSet in TYPE_FORWARD_ONLY mode");
        }
    }

    /**
     * Creates a SQLException with a message that includes the row number. The row number is 1-based, meaning
     * that the first row is 1, the second row is 2, and so on. If the row number is negative, it is used to
     * address rows from the end of the ResultSet.
     *
     * @param rowNumber the row number
     * @param cause     the cause of the exception
     * @return a rdbms-independent exception wrapped around the causing SQLException
     */
    private SQLException createCouldNotReadRowException(int rowNumber, SQLException cause) {
        return new SQLException("Could not read row #" + rowNumber + " (1-based, negative to address from the end)", cause);
    }

    /**
     * Creates a SQLException with a message that includes the row number. The row number is 1-based, meaning
     * that the first row is 1, the second row is 2, and so on. If the row number is negative, it is used to
     * address rows from the end of the ResultSet.
     *
     * @param rowNumber the row number
     * @param cause     the cause of the exception
     * @return a rdbms-independent exception wrapped around the causing SQLException
     */
    private SQLException createCouldNotAccessRowDataException(int rowNumber, SQLException cause) {
        return new SQLException("Could not access data of row #" + rowNumber + " (1-based, negative to address from the end)", cause);
    }

    /**
     * Moves the ResultSet to the given row number. The row number is 1-based, meaning that the first row is 1,
     * the second row is 2, and so on. If the row number is negative, it is used to address rows from the end
     * of the ResultSet. If an error occurs while moving the ResultSet, a SQLException is thrown.
     *
     * @param rowNumber the row number to move to
     * @return true if the ResultSet is positioned at the given row number, false otherwise
     * @throws SQLException a rdbms-independent exception wrapped around the causing SQLException
     */
    private boolean absoluteWithExceptionHandling(int rowNumber) throws SQLException {
        try {
            return resultSet.absolute(rowNumber);
        } catch (SQLException e) {
            throw new SQLException("Could not move to row #" + rowNumber + " (1-based, negative to address from the end)", e);
        }
    }

    /**
     * Moves the ResultSet to the next row. If the ResultSet is already at the last row, false is returned.
     *
     * @return true if the ResultSet is positioned at the next row, false otherwise
     * @throws SQLException a rdbms-independent exception wrapped around the causing SQLException
     */
    private boolean nextWithExceptionHandling() throws SQLException {
        try {
            return resultSet.next();
        } catch (SQLException e) {
            throw new SQLException("Could not move to next row", e);
        }
    }

    private boolean moveForward(int numberOfRows) throws SQLException {
        if (numberOfRows < 0) {
            throw new SQLException("numberOfRows must be greater than or equal to 0");
        }
        for (int i = 0; i < numberOfRows; i++) {
            if (!nextWithExceptionHandling()) {
                return false;
            }
        }
        return true;
    }

    private boolean moveTo(int rowNumber) throws SQLException {
        // absolute() is not supported because the ResultSet is TYPE_FORWARD_ONLY?
        if (resultSet.getType() == ResultSet.TYPE_FORWARD_ONLY) {
            // TYPE_FORWARD_ONLY mode does neither support absolute() nor relative() or previous()
            // the only way to move to a specific row is to move forward
            if (rowNumber < resultSet.getRow()) {
                throw new SQLException("Cannot move backward to row #" + rowNumber + " in TYPE_FORWARD_ONLY mode");
            }
            // move to the current row by moving forward (and unfortunately fetching all rows in between)
            return moveForward(rowNumber - resultSet.getRow());
        } else {
            // absolute() should be supported
            // if the row number is negative, it is used to address rows from the end of the ResultSet
            if (rowNumber < 0) {
                // move to the last row, get the row count and check if the row number is out of bounds
                resultSet.last();
                int rowCount = resultSet.getRow();
                if (Math.abs(rowNumber) > rowCount) {
                    throw new SQLException("Row number " + rowNumber + " is out of bounds");
                }
                return resultSet.absolute(rowCount + rowNumber + 1);
            } else {
                return resultSet.absolute(rowNumber);
            }
        }
    }

    /**
     * Reads a number of rows from the ResultSet starting at the given row number. The ResultSet is positioned at the
     * given row number. The row number is 1-based, meaning that the first row is 1, the second row is 2, and so on.
     * If the ResultSet is positioned before the first row or after the last row, an empty list is returned.
     *
     * @param firstRowNumber the row number to start reading from
     * @param numberOfRows   the number of rows to read
     * @return a list of rows
     * @throws SQLException a rdbms-independent exception (wrapped around a causing SQLException)
     */
    public List<T> read(int firstRowNumber, int numberOfRows) throws SQLException {

        checkRowNumber(firstRowNumber);

        if (numberOfRows < 0) {
            throw new SQLException("numberOfRows must be greater than or equal to 0");
        }

        List<T> rows = new ArrayList<>();

        if (numberOfRows > 0) {

            int rowsRead = 0;
            int rowNo = resultSet.getRow();
            try {
                if (moveTo(firstRowNumber)) {
                    do {
                        T row = constructor.get();
                        transferWithExceptionHandling(row);
                        rows.add(row);
                        rowsRead++;
                    } while (rowsRead < numberOfRows && nextWithExceptionHandling());
                }
            } catch (SQLException e) {
                throw createCouldNotReadRowException(firstRowNumber + rowsRead, e);
            }
        }

        return rows;

    }

    /**
     * Reads a row from the ResultSet at the given row number, therefore the ResultSet is positioned at the row
     * number. The row number is 1-based, meaning that the first row is 1, the second row is 2, and so on.
     * If the ResultSet is positioned before the first row or after the last row, null is returned.
     * The current row number is not changed.
     *
     * @param rowNumber the row number to read
     * @return the row object or null if the ResultSet is at the last row
     * @throws SQLException a rdbms-independent exception wrapped around the causing SQLException
     */
    public T read(int rowNumber) throws SQLException {

        checkRowNumber(rowNumber);

        try {
            if (absoluteWithExceptionHandling(rowNumber)) {
                T row = constructor.get();
                transferWithExceptionHandling(row);
                return row;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw createCouldNotReadRowException(rowNumber, e);
        }
    }

    /**
     * Reads all rows from the ResultSet.
     *
     * @return a list of all rows
     * @throws SQLException a rdbms-independent exception wrapped around the causing SQLException
     */
    public List<T> readAll() throws SQLException {
        return read(1, Integer.MAX_VALUE);
    }

}