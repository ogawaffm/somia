package org.velohaven.somia.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.Accessors;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(fluent = true)
@Getter
public class Catalog extends Dictionary<Catalog> implements WithSchemas<Catalog> {

    private Schemas schemas;

    public Catalog() {
        super();
        schemas = new Schemas();
        schemas.parent(this);
    }

    @Override
    public Catalog schemas(@NonNull final Schemas schemas) {
        this.schemas = schemas.cloneForParent(this);
        return this;
    }

    @Override
    public String fullQuotedIdentifier() {
        return NameUtils.getFullQuotedIdentifier(name());
    }

    @Override
    Catalog cloneFieldsFrom(@NonNull Catalog source) {
        super.cloneFieldsFrom(source);
        schemas = source.schemas.cloneForParent(this);
        return this;
    }
}
