package org.velohaven.somia.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * Base class for all artefacts. An artefact is a database artefact like a table, a column, a constraint, etc.
 * @param <T> the type of the artefact
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(fluent = true)
@Getter
public class NamedItem<T extends NamedItem<T>> extends Item<T> implements WithName, WithToJson {

    private String name = "";

    @SuppressWarnings("unchecked")
    public T name(@NonNull String name) {
        this.name = name;
        return (T) this;
    }

    /**
     * Copies the fields from the source object to this object by direct access to the fields. Access via setters must
     * be avoided because they may trigger change notifications.
     * @param source the source object to copy the fields from
     * @return This instance
     */
    @SuppressWarnings("unchecked")
    @Override
    T cloneFieldsFrom(@NonNull T source) {
        super.cloneFieldsFrom(source);
        this.name = source.name();
        return (T) this;
    }

}
