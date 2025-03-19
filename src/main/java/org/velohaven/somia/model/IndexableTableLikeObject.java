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
public abstract class IndexableTableLikeObject<T extends IndexableTableLikeObject<T>>
    extends TableLikeObject<T> implements WithIndexes<T> {

    private Indexes indexes;

    IndexableTableLikeObject() {
        super();
        indexes = new Indexes();
        indexes.parent(this);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T indexes(@NonNull Indexes indexes) {
        this.indexes = indexes.cloneForParent(this);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    @Override
    T cloneFieldsFrom(@NonNull T source) {
        super.cloneFieldsFrom(source);
        indexes = source.indexes().cloneForParent(this);
        return (T) this;
    }

}
