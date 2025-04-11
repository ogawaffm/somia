package org.velohaven.somia.jdbc.resultset.databasemetadata.columndefinition;

import java.sql.SQLType;

public interface ColDef<T extends ColDef<T>> extends Comparable<T> {

    String name();

    SQLType sqlType();

    /**
     * 1-based ordinal position
     *
     * @return 1-based ordinal position
     */
    int ordinalPosition();

    /**
     * @return column size
     */
    boolean isNullable();

    /**
     * Compares this object with the specified object for a deterministic order.  Returns a negative integer, zero,
     * or a positive integer as this object is less than, equal to, or greater than the specified object.
     *
     * @param other the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
     */
    @Override
    default int compareTo(T other) {
        int result = Integer.compare(this.ordinalPosition(), other.ordinalPosition());
        if (result == 0) {
            result = this.name().compareTo(other.name());
        }
        if (result == 0) {
            result = this.sqlType().getName().compareTo(other.sqlType().getName());
        }
        if (result == 0) {
            result = Boolean.compare(this.isNullable(), other.isNullable());
        }
        return result;
    }

}
