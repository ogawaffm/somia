package org.velohaven.somia.model;

public interface WithTables<T> {

    Tables tables();

    T tables(Tables tables);

    default Table tables(String tableName) {
        return tables().getByName(tableName);
    }

    default Table tables(int index) {
        return tables().getByIndex(index);
    }

}
