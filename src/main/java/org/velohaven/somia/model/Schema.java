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
public class Schema extends Dictionary<Schema> {

    private String catalogName;

    public Schema catalogName(String catalogName) {
        this.catalogName = catalogName;
        return this;
    }

    @Override
    public String fullQuotedIdentifier() {
        return NameUtils.getFullQuotedIdentifier(catalogName, name());
    }

    Schema cloneFieldsFrom(@NonNull Schema source) {
        super.cloneFieldsFrom(source);
        catalogName = source.catalogName;
        return this;
    }

}
