package org.velohaven.somia.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.Accessors;

@SuppressWarnings("UnusedReturnValue")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(fluent = true)
@Getter
public abstract class Key<K extends Key<K, C>, C extends ColumnRef<C>> extends ComposedColumnRefs<K, C> {

    Columns<C> columns;
    boolean isUnique;

    Key() {
        super();
        columns = new Columns<>();
        columns.parent(this);
    }

    @Override
    @SuppressWarnings("unchecked")
    K cloneFieldsFrom(@NonNull K source) {
        super.cloneFieldsFrom(source);
        isUnique = source.isUnique;
        this.columns = source.columns.cloneForParent(this);
        return (K) this;
    }

    @SuppressWarnings("unchecked")
    public K isUnique(boolean isUnique) {
        this.isUnique = isUnique;
        return (K) this;
    }

}
