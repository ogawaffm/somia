package org.velohaven.somia.model;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * This class is a container for named items.
 * @param <T> The type of the NamedItemStore
 * @param <E> The type of the named NamedItems in the NamedItemStore
 */
@ToString(callSuper = true)
@SuppressWarnings({"unused", "UnusedReturnValue"})
@EqualsAndHashCode(callSuper = true)
public class NamedItemStore<T extends NamedItemStore<T, E>, E extends NamedItem<E>> extends NamedItemStoreView<T, E> {

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
        for (E namedItem : items) {
            namedItem.parent(parent);
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
     * Returns the NamedItem at the passed index
     * @param index 0-based index of the NamedItem
     * @return The NamedItem at the passed index
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
     * Prepends an NamedItem to this NamedItemStore
     * @param namedItem NamedItem to be prepended to this NamedItemStore
     * @return This
     */
    public T prepend(@NonNull E namedItem) {
        return insert(0, namedItem);
    }

    /**
     * Prepends clones of all NamedItems from the passed namedItemIterable to this NamedItemStore
     * @param namedItemIterable Iterable with NamedItems to prepend
     * @return This
     */
    public T prependAll(@NonNull Iterable<E> namedItemIterable) {
        return insertAll(0, namedItemIterable);
    }

    /**
     * Replaces a NamedItem in this NamedItemStore with a clone of the passed NamedItem. If the NamedItem is not
     * present in this NamedItemStore it is appended or prepended depending on the appendIfNotExists parameter.
     * @param item              NamedItem to replace in this NamedItemStore
     * @param appendIfNotExists True if the NamedItem should be appended if it is not present in this NamedItemStore,
     *                          False if the NamedItem should be prepended if it is not present in this NamedItemStore
     * @return This
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
     * Appends (a clone) of the passed NamedItem to this NamedItemStore
     * @param item NamedItem whose clone is added to this NamedItemStore
     */
    private void appendClone(@NonNull E item) {
        // add a clone with the NamedItemStore's parent as parent
        E clone = item.clone();
        item.parent(this.parent());
        items.add(clone);
    }

    /**
     * Adds (a clone) of the passed NamedItem to this NamedItemStore
     * @param namedItem NamedItem whose clone is added to this NamedItemStore
     * @return This
     */
    @SuppressWarnings("unchecked")
    public T append(@NonNull E namedItem) {
        appendClone(namedItem);
        return (T) this;
    }

    /**
     * Appends clones of all NamedItems from the passed namedItemIterable to this NamedItemStore
     * @param namedItemIterable with NamedItems to clone and to be appended to this NamedItemStore
     * @return This
     */
    @SuppressWarnings("unchecked")
    public T appendAll(@NonNull Iterable<E> namedItemIterable) {
        for (E namedItem : namedItemIterable) {
            appendClone(namedItem);
        }
        return (T) this;
    }

    /**
     * Inserts a NamedItem to this NamedItemStore at the passed index position
     * @param index    0-based index where to insert the NamedItem
     * @param namedItem NamedItem to insert
     * @return This
     */
    @SuppressWarnings("unchecked")
    public T insert(int index, @NonNull E namedItem) {
        insertAllCloned(index, List.of(namedItem));
        return (T) this;
    }

    /**
     * Inserts Clones of all NamedItemStore from the passed namedItemIterable to this NamedItemStore
     * @param startIndex        0-based start position where to insert the NamedItemStores
     * @param namedItemIterable Iterable with NamedItems to insert
     */
    private void insertAllCloned(int startIndex, @NonNull Iterable<E> namedItemIterable) {

        checkIndexExistsOrIsNext(startIndex);
        Iterator<E> iterator = namedItemIterable.iterator();

        // if the namedItemIterable is not empty
        if (iterator.hasNext()) {

            List<E> oldNamedItems = items;

            items = new ArrayList<>();

            /* ***** insert original elements from before the startIndex ***** */
            for (int i = 0; i < startIndex; i++) {
                items.add(oldNamedItems.get(i));
            }

            /* ***** insert elements of the namedItemIterable ***** */
            while (iterator.hasNext()) {
                E namedItem = iterator.next();
                appendClone(namedItem);
            }

            /* ***** insert original elements from after the startIndex ***** */
            for (int i = startIndex; i < oldNamedItems.size(); i++) {
                items.add(oldNamedItems.get(i));
            }

        }

    }

    /**
     * Inserts all NamedItems from the passed namedItemIterable to this NamedItemStore
     * @param startIndex        0-based start position where to insert the NamedItemStore
     * @param namedItemIterable Iterable with NamedItems to insert
     * @return This
     */
    @SuppressWarnings("unchecked")
    public T insertAll(int startIndex, @NonNull Iterable<E> namedItemIterable) {
        insertAllCloned(startIndex, namedItemIterable);
        return (T) this;
    }

    /**
     * Removes an NamedItem with the passed name from this NamedItemStore regardless if it is present or not.
     * @param name Name of the NamedItem to be removed from this NamedItemStore
     * @return The instance of the NamedItemStore.
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
     * Removes an NamedItem from the position of index. If there is no NamedItem at position index an
     * IndexOutOfBoundsException is thrown.
     * @param index 0-based index of the NamedItem to be removed from this NamedItemStore
     * @return This
     */
    @SuppressWarnings("unchecked")
    public T removeByIndex(int index) {
        checkIndexExists(index);
        // remove and set parent to null
        items.remove(index).parent(null);
        return (T) this;
    }

    /**
     * Removes all NamedItems from this NamedItemStore
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
     * Removes all NamedItems from this NamedItemStore
     * @param namedItemsToRemove NamedItemStore with NamedItems to be removed from this NamedItemStore
     */
    public void removeAll(NamedItemStore<T, E> namedItemsToRemove) {
        int index;
        for (E namedItem : namedItemsToRemove) {
            index = indexOf(namedItem);
            // Found?
            if (index != -1) {
                // remove and set parent to null
                items.remove(index).parent(null);
            }
        }
    }


    /**
     * Removes all NamedItems from this NamedItemStore
     */
    public void removeAll() {
        items.clear();
    }

    /**
     * Retains only the NamedItems in this NamedItemStore that are contained in the passed Iterable
     * @param nameIterable collection containing names to be retained in this NamedItemStore
     * @return This
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
