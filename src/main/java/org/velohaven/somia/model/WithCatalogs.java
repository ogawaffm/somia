package org.velohaven.somia.model;

import lombok.NonNull;

public interface WithCatalogs<T> {

    Catalogs catalogs();

    T catalogs(@NonNull Catalogs catalogs);

    default Catalog catalogs(String catalogName) {
        return catalogs().getByName(catalogName);
    }

    default Catalog catalogs(int index) {
        return catalogs().getByIndex(index);
    }

}
