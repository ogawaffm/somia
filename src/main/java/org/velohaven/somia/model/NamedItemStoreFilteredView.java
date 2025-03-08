package org.velohaven.somia.model;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * This class is a container for named artefacts.
 * @param <T> The type of the NamedArtefacts
 * @param <E> The type of the named artefacts in the NamedArtefacts
 */
@ToString(callSuper = true)
@SuppressWarnings("unused")
@EqualsAndHashCode(callSuper = true)
public class NamedItemStoreFilteredView<T extends NamedItemStoreView<T, E>, E extends NamedItem<E>>
    extends NamedItemStoreView<T, E> {

    /**
     * The artefacts backed by a LinkedHashMap using the lower-case name as key and which is keeping the
     * insertion order of the artefacts.
     */
    final private List<E> artefacts;

    Predicate<E> filterPredicate;

    NamedItemStoreFilteredView(@NonNull NamedItemStore namedItemStore, @NonNull Predicate<E> filterPredicate) {
        super.parent(namedItemStore);
        this.artefacts = namedItemStore.items;
        this.filterPredicate = filterPredicate;
    }

    @Override
    Object parent() {
        return ((Item) parent()).parent();
    }

    @Override
    void parent(Object parent) {
        throw new UnsupportedOperationException("parent() is not supported for " + getClass().getSimpleName());
    }


    @SuppressWarnings("unchecked")
    @Override
    T cloneFieldsFrom(@NonNull T source) {
        super.cloneFieldsFrom(source);
        if (source instanceof NamedItemStoreFilteredView) {
            filterPredicate = ((NamedItemStoreFilteredView<T, E>) source).filterPredicate;
        }
        return (T) this;
    }

    /**
     * Returns the number of named artefacts in this NamedArtefacts
     * @return Number of named artefacts in this NamedArtefacts
     */
    @Override
    public int size() {
        return (int) stream().count();
    }

    /**
     * Returns the Artefact at the passed index
     * @param index 0-based index of the Artefact
     * @return The Artefact at the passed index
     */
    public E getByIndex(int index) {
        return stream().skip(index).findFirst().orElseThrow(() -> newIndexOutOfBoundsException(index, size()));
    }

    @Override
    Stream<E> stream() {
        return artefacts.stream().filter(filterPredicate);
    }


}
