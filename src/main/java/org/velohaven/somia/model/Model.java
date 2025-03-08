package org.velohaven.somia.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.LinkedHashSet;
import java.util.Set;

@ToString(callSuper = true)
@EqualsAndHashCode()
@Accessors(fluent = true)
@Getter
public class Model implements WithToJson, WithCatalogs {

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
    public Model catalogs(Catalogs catalogs) {
        this.catalogs = catalogs.cloneForParent(this);
        return this;
    }

}
