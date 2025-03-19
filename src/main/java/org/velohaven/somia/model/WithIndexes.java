package org.velohaven.somia.model;

import lombok.NonNull;

@SuppressWarnings("unused")
public interface WithIndexes<T> {

    Indexes indexes();

    T indexes(@NonNull Indexes indexes);

    default Index indexes(@NonNull String name) {
        return indexes().getByName(name);
    }

    default Index indexes(int index) {
        return indexes().getByIndex(index);
    }

}
