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
public class BestRowIdentifierColumn extends PseudoColumnRef<BestRowIdentifierColumn> implements WithToJson {

    private int scope;

    @Override
    BestRowIdentifierColumn cloneFieldsFrom(@NonNull BestRowIdentifierColumn source) {
        super.cloneFieldsFrom(source);
        scope = source.scope;

        return this;

    }

    BestRowIdentifierColumn scope(int scope) {
        this.scope = scope;
        return this;
    }

}
