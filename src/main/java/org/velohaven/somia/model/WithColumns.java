package org.velohaven.somia.model;

import lombok.NonNull;

public interface WithColumns<T, C extends NamedItem<C>> {

    Columns<C> columns();

    T columns(@NonNull Columns<C> columns);

    default C columns(int index) {
        return columns().getByIndex(index);
    }

    default C columns(@NonNull String name) {
        return columns().getByName(name);
    }

}
