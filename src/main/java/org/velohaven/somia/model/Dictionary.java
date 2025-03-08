package org.velohaven.somia.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.Accessors;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(fluent = true)
public class Dictionary<T extends Dictionary<T>> extends NamedItem<T> implements WithComment, WithTables<T> {

    @Getter(onMethod_ = {@Override})
    private String comment = "";
    @Getter
    private UserDefinedType udt;

    @Getter
    private Tables tables;

    Dictionary() {
        super();
        tables = new Tables();
        tables.parent(this);
    }

    @Override
    public T tables(Tables tables) {
        this.tables = tables;
        return (T) this;
    }

    @Override
    T cloneFieldsFrom(@NonNull T source) {
        super.cloneFieldsFrom(source);
        this.comment = source.comment();
        udt = source.udt() == null ? null : source.udt().cloneForParent(this);
        tables = source.tables().clone();
        return (T) this;
    }

    public T comment(String comment) {
        this.comment = comment;
        return (T) this;
    }

}
