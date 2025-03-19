package org.velohaven.somia.model;

import lombok.NonNull;

@SuppressWarnings("unused")
public interface WithSchemas<T> {

    Schemas schemas();

    T schemas(@NonNull Schemas schemas);

    default Schema schemas(@NonNull String schemaName) {
        return schemas().getByName(schemaName);
    }

    default Schema schemas(int index) {
        return schemas().getByIndex(index);
    }

}
