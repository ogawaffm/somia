package org.velohaven.somia.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.Accessors;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(fluent = true)
public abstract class TableLikeObject<T extends TableLikeObject<T>> extends DatabaseObject<T>
    implements WithColumns<T, Column> {

    @Getter
    private Columns<Column> columns;

    TableLikeObject() {
        super();
        columns = new Columns<Column>();
        columns.parent(this);
    }

    @Override
    public T columns(@NonNull Columns<Column> columns) {
        this.columns = columns.cloneForParent(this);
        return (T) this;
    }


    @SuppressWarnings("unchecked")
    @Override
    T cloneFieldsFrom(@NonNull T source) {
        super.cloneFieldsFrom(source);
        columns = source.columns().clone();
        columns.parent(this);
        return (T) this;
    }

}
