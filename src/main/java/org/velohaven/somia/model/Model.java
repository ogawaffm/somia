package org.velohaven.somia.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.LinkedHashSet;
import java.util.Set;

@SuppressWarnings("UnusedReturnValue")
@ToString(callSuper = true)
@EqualsAndHashCode()
@Accessors(fluent = true)
@Getter
public class Model implements WithToJson, WithCatalogs<Model> {

    private Catalogs catalogs;

    private Set<TypeInfo> typeInfos = new LinkedHashSet<>();

    Model() {
        catalogs = new Catalogs();
        catalogs.parent(this);
    }

    public Model typeInfos(Set<TypeInfo> typeInfos) {
        this.typeInfos = typeInfos;
        return this;
    }

    @Override
    public Model catalogs(@NonNull Catalogs catalogs) {
        this.catalogs = catalogs.cloneForParent(this);
        return this;
    }

}
