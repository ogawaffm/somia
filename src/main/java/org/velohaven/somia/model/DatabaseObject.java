package org.velohaven.somia.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * Base class for all database objects. A database object is an object that can exist alone in a database. It can be a
 * table, a view, an index, a sequence, etc. A column is not a database object because it cannot exist alone. It is
 * part of a table. A database object tracks its last change.
 * @param <T>
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(fluent = true)
@Getter
public abstract class DatabaseObject<T extends DatabaseObject<T>> extends NamedItem<T>
    implements DatabaseObjectIdentity, WithComment {

    @Getter(onMethod_ = {@Override})
    private String catalogName = "";
    @Getter(onMethod_ = {@Override})
    private String schemaName = "";
    @Getter
    private String typeName = "";

    @Getter
    private String comment = "";

    @SuppressWarnings("unchecked")
    public T catalogName(@NonNull String catalogName) {
        this.catalogName = catalogName;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T schemaName(@NonNull String schemaName) {
        this.schemaName = schemaName;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T typeName(@NonNull String typeName) {
        this.typeName = typeName;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T comment(@NonNull String comment) {
        this.comment = comment;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    @Override
    T cloneFieldsFrom(@NonNull T source) {
        super.cloneFieldsFrom(source);
        catalogName = source.catalogName();
        schemaName = source.schemaName();
        typeName = source.typeName();
        comment = source.comment();
        return (T) this;
    }

}
