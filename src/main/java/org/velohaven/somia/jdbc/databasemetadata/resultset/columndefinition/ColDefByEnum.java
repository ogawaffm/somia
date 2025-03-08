package org.velohaven.somia.jdbc.databasemetadata.resultset.columndefinition;

interface ColDefByEnum<T extends ColDefByEnum<T>> extends ColDef<T> {

    /**
     * 0-based ordinal
     * @return 0-based ordinal
     */
    int ordinal();

    default int ordinalPosition() {
        return ordinal() + 1;
    }

}
