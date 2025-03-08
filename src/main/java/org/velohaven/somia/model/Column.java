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
public class Column extends AbstractColumn<Column> implements WithCollation {

    private String collation = "";
    private String defaultDefinition = "";

    public Column collation(@NonNull String collation) {
        this.collation = collation;
        return this;
    }

    public Column defaultDefinition(@NonNull String defaultDefinition) {
        this.defaultDefinition = defaultDefinition;
        return this;
    }

    public boolean hasDefaultDefinition() {
        return !defaultDefinition.isEmpty();
    }

    @Override
    Column cloneFieldsFrom(@NonNull Column source) {
        super.cloneFieldsFrom(source);
        collation = source.collation;
        defaultDefinition = source.defaultDefinition;
        return this;
    }

}

