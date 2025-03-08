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
public abstract class ComposedColumnRefs<T extends ComposedColumnRefs<T, C>, C extends ColumnRef<C>>
    extends NamedItem<T> implements WithColumns<T, C> {

    Columns<C> columns;

    ComposedColumnRefs() {
        super();
        columns = new Columns<>();
        columns.parent(this);
    }

    @Override
    public T columns(@NonNull Columns<C> columns) {
        this.columns = columns.cloneForParent(this);
        return (T) this;
    }

    @Override
    T cloneFieldsFrom(@NonNull T source) {
        super.cloneFieldsFrom(source);
        columns = source.columns().cloneForParent(this);

        return (T) this;
    }

    Table table() {
        return (Table) parent();
    }

}
