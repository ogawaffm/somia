package org.velohaven.somia.model;

import lombok.NonNull;

public interface WithTables<T> {

    @NonNull Tables tables();

    T tables(@NonNull Tables tables);

    default Table tables(@NonNull String tableName) {
        return tables().getByName(tableName);
    }

    default Table tables(int index) {
        return tables().getByIndex(index);
    }

}
