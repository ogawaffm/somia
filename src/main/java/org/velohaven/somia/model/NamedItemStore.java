package org.velohaven.somia.model;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

import java.util.*;
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
public class NamedItemStore<T extends NamedItemStore<T, E>, E extends NamedItem<E>>
    extends NamedItemStoreView<T, E> {

    public NamedItemStore() {
        // unbounded
        this(null);
    }

    NamedItemStore(Object parent) {
        parent(parent);
    }

    @Override
    void parent(Object parent) {
        super.parent(parent);
        for (E artefact : items) {
            artefact.parent(parent);
        }
    }

    private boolean indexIsNext(int index) {
        return index == size();
    }

    private void checkIndexExists(int index) {
        if (!indexExists(index)) {
            throw newIndexOutOfBoundsException(index, size());
        }
    }

    private void checkIndexExistsOrIsNext(int index) {
        if (!indexExists(index) && !indexIsNext(index)) {
            throw new IndexOutOfBoundsException("Index is neither the next new index nor does it exist: "
                + "Index= " + index + ", Size= " + size()
            );
        }
    }

    /**
     * Returns the number of named artefacts in this NamedArtefacts
     * @return Number of named artefacts in this NamedArtefacts
     */
    @Override
    public int size() {
        return items.size();
    }

    /**
     * Returns the Artefact at the passed index
     * @param index 0-based index of the Artefact
     * @return The Artefact at the passed index
     */
    public E getByIndex(int index) {
        checkIndexExists(index);
        return items.get(index);
    }

    public Stream<E> stream() {
        return items.stream();
    }

    NamedItemStoreFilteredView<?, E> createView(@NonNull Predicate<E> filter) {
        return new NamedItemStoreFilteredView<T, E>(this, filter);
    }

    /**
     * Prepends an Artefact to this Artefacts
     * @param artefact Artefact to be prepended to this Artefacts
     * @return This artefacts
     */
    public T prepend(@NonNull E artefact) {
        return insert(0, artefact);
    }

    /**
     * Prepends clones of all artefacts from the passed artefactIterable to this Artefacts
     * @param artefactIterable Iterable with artefacts to prepend
     * @return This artefacts
     */
    public T prependAll(@NonNull Iterable<E> artefactIterable) {
        return insertAll(0, artefactIterable);
    }

    /**
     * Replaces a NamedItem in this NamedItemStore with a clone of the passed Artefact. If the Artefact is not
     * present in this Artefacts it is appended or prepended depending on the appendIfNotExists parameter.
     * @param item              Artefact to replace in this Artefacts
     * @param appendIfNotExists True if the Artefact should be appended if it is not present in this Artefacts,
     *                          False if the Artefact should be prepended if it is not present in this Artefacts
     * @return This artefacts
     */
    @SuppressWarnings("unchecked")
    public T replace(@NonNull E item, boolean appendIfNotExists) {

        int index = indexOf(item.name());

        if (index != -1) {
            items.set(index, item.cloneForParent(parent()));
            // Was the item a real replacement and not just a no-op because the item was the same?
        } else {
            if (appendIfNotExists) {
                append(item);
            } else {
                prepend(item);
            }
        }

        return (T) this;
    }

    /**
     * Appends (a clone) of the passed Artefact to this Artefacts
     * @param item Artefact whose clone is added to this Artefacts
     */
    private void appendClone(@NonNull E item) {
        // add a clone with the NamedItemStore's parent as parent
        E clone = item.clone();
        item.parent(this.parent());
        items.add(clone);
    }

    /**
     * Adds (a clone) of the passed Artefact to this Artefacts
     * @param artefact Artefact whose clone is added to this Artefacts
     * @return This artefacts
     */
    @SuppressWarnings("unchecked")
    public T append(@NonNull E artefact) {
        appendClone(artefact);
        return (T) this;
    }

    /**
     * Appends clones of all artefacts from the passed artefactsIterable to this Artefacts
     * @param artefactIterable with artefacts to clone and to be appended to this Artefacts
     * @return This artefacts
     */
    @SuppressWarnings("unchecked")
    public T appendAll(@NonNull Iterable<E> artefactIterable) {
        for (E artefact : artefactIterable) {
            appendClone(artefact);
        }
        return (T) this;
    }

    /**
     * Inserts a Artefact to this Artefacts at the passed index position
     * @param index    0-based index where to insert the artefact
     * @param artefact Artefact to insert
     * @return This artefacts
     */
    @SuppressWarnings("unchecked")
    public T insert(int index, @NonNull E artefact) {
        insertAllCloned(index, List.of(artefact));
        return (T) this;
    }

    /**
     * Inserts Clones of all Artefacts from the passed artefactsIterable to this Artefacts
     * @param startIndex        0-based start position where to insert the artefacts
     * @param artefactsIterable Iterable with artefacts to insert
     */
    @SuppressWarnings({"MismatchedQueryAndUpdateOfCollection"})
    private void insertAllCloned(int startIndex, @NonNull Iterable<E> artefactsIterable) {

        checkIndexExistsOrIsNext(startIndex);
        Iterator<E> iterator = artefactsIterable.iterator();

        // if the artefactsIterable is not empty
        if (iterator.hasNext()) {

            List<E> oldArtefacts = items;

            items = new ArrayList<>();

            /* ***** insert original elements from before the startIndex ***** */
            for (int i = 0; i < startIndex; i++) {
                items.add(oldArtefacts.get(i));
            }

            /* ***** insert elements of the artefactsIterable ***** */
            while (iterator.hasNext()) {
                E artefact = iterator.next();
                appendClone(artefact);
            }

            /* ***** insert original elements from after the startIndex ***** */
            for (int i = startIndex; i < oldArtefacts.size(); i++) {
                items.add(oldArtefacts.get(i));
            }

        }

    }

    /**
     * Inserts all Artefacts from the passed artefactsIterable to this Artefacts
     * @param startIndex        0-based start position where to insert the artefacts
     * @param artefactsIterable Iterable with artefacts to insert
     * @return This artefacts
     */
    @SuppressWarnings("unchecked")
    public T insertAll(int startIndex, @NonNull Iterable<E> artefactsIterable) {
        insertAllCloned(startIndex, artefactsIterable);
        return (T) this;
    }

    /**
     * Removes an Artefact with the passed name from this Artefacts regardless if it is present or not.
     * @param name Name of the Artefact to be removed from this Artefacts
     * @return The instance of the artefacts.
     */
    @SuppressWarnings("unchecked")
    public T removeByName(@NonNull String name) {
        int index = indexOf(name);
        if (index != -1) {
            // remove and set parent to null
            items.remove(index).parent(null);
        }
        return (T) this;
    }

    /**
     * Removes an Artefact from the position of index. If there is no artefact at position index an
     * IndexOutOfBoundsException is thrown.
     * @param index 0-based index of the Artifact to be removed from this Artefacts
     * @return This artefacts
     */
    @SuppressWarnings("unchecked")
    public T removeByIndex(int index) {
        checkIndexExists(index);
        // remove and set parent to null
        items.remove(index).parent(null);
        return (T) this;
    }

    /**
     * Removes all artefacts from this Artefacts
     */
    public void removeAllByName(Iterator<String> nameIterator) {
        int index;

        while (nameIterator.hasNext()) {
            index = indexOf(nameIterator.next());
            if (index != -1) {
                // remove and set parent to null
                items.remove(index).parent(null);
            }
        }
    }

    /**
     * Removes all artefacts from this Artefacts
     * @param artefactsToRemove Artefacts with artefacts to be removed from this Artefacts
     */
    public void removeAll(NamedItemStore<T, E> artefactsToRemove) {
        int index;
        for (E artefact : artefactsToRemove) {
            index = indexOf(artefact);
            // Found?
            if (index != -1) {
                // remove and set parent to null
                items.remove(index).parent(null);
            }
        }
    }


    /**
     * Removes all artefacts from this Artefacts
     */
    public void removeAll() {
        items.clear();
    }

    /**
     * Retains only the artefacts in this Artefacts that are contained in the passed Iterable
     * @param nameIterable collection containing names to be retained in this artefacts
     * @return This artefacts
     */
    @SuppressWarnings("unchecked")
    public T retainAllByName(@NonNull Iterable<String> nameIterable) {

        Iterator<String> nameIterator = nameIterable.iterator();
        Set<Integer> indexesToRetain = new HashSet<>();

        int index;
        while (nameIterator.hasNext()) {
            String name = nameIterator.next();
            index = indexOf(name);
            if (index != -1) {
                indexesToRetain.add(index);
            }
        }

        for (int i = size() - 1; i >= 0; i--) {
            if (!indexesToRetain.contains(i)) {
                // remove and set parent to null
                items.remove(i).parent(null);
            }
        }

        return (T) this;
    }

}
