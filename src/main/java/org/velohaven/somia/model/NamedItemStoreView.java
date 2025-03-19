package org.velohaven.somia.model;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class is a container for named items. Items put to this container are cloned before being added.
 * The items are accessible by index or by name. The items are iterable.
 * If the store is cloned, the items are cloned as well.
 * @param <T> The type of the NamedItemStoreView
 * @param <E> The type of the named NamedItems in the NamedItemStoreView
 */

@SuppressWarnings("unused")
public abstract class NamedItemStoreView<T extends NamedItemStoreView<T, E>, E extends NamedItem<E>>
    extends Item<T> implements Iterable<E>, WithToJson {

    List<E> items = new ArrayList<>();

    IndexOutOfBoundsException newIndexOutOfBoundsException(int index, int size) {
        throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    NoSuchElementException newNoSuchElement(String name) {
        return new NoSuchElementException("No item with name: " + name);
    }

    public NamedItemStoreView() {
        // unbounded
        this(null);
    }

    public NamedItemStoreView(Object parent) {
        super.parent(parent);
    }

    @SuppressWarnings("unchecked")
    @Override
    T cloneFieldsFrom(@NonNull T source) {
        super.cloneFieldsFrom(source);
        items = new ArrayList<>();
        // TODO make direct by get by index
        for (String key : source.allNames()) {
            E clone = source.getByName(key).clone();
            items.add(clone);
        }
        return (T) this;
    }

    /**
     * Returns the number of NamedItems in this NamedItemStoreView
     * @return Number of NamedItems in this NamedItemStoreView
     */
    public int size() {
        return items.size();
    }

    /**
     * Returns whether this NamedItemStoreView contains no NamedItem.
     * @return True if this NamedItemStoreView contains no NamedItem, else false.
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns whether a NamedItem with the passed index exists
     * @param index 0-based index of the NamedItem
     * @return True if a NamedItem with the passed index exists, else false
     */
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean indexExists(int index) {
        return index >= 0 && index < size();
    }

    /**
     * Returns the index of the NamedItem with the passed name
     * @param name Name of the NamedItem
     * @return 0-based index of the NamedItem with the passed name
     */
    public int indexOf(@NonNull String name) {
        return indexOf(namedItem -> namedItem.name().equals(name));
    }

    public int indexOf(@NonNull E namedItem) {
        return indexOf(namedItem::equals);
    }

    int indexOf(@NonNull Predicate<NamedItem<?>> elementPredicate) {
        int matchIndex = -1;
        Iterator<E> iterator = this.stream().iterator();

        while (iterator.hasNext()) {
            E namedItem = iterator.next();
            matchIndex++;
            if (elementPredicate.test(namedItem)) {
                return matchIndex;
            }
        }
        return -1;
    }

    /**
     * Returns a List of the names in original case of the NamedItem in this NamedItemStoreView
     * @return List of the names
     */
    public List<String> allNames() {
        return stream().map(NamedItem::name).collect(Collectors.toCollection(ArrayList::new));
    }

    public E getFirst() {
        return getByIndex(0);
    }

    public E getLast() {
        return getByIndex(size() - 1);
    }

    /**
     * Returns the NamedItem at the passed index
     * @param index 0-based index of the NamedItem
     * @return The NamedItem at the passed index
     */
    public E getByIndex(int index) {
        if (!indexExists(index)) {
            throw newIndexOutOfBoundsException(index, size());
        }
        return items.get(index);
    }

    /**
     * Returns the NamedItem with the passed name or null if no such NamedItem exists
     * @param name Name of the NamedItem to return
     * @return The NamedItem with the passed name or null if no such NamedItem exists
     */
    public E getByName(@NonNull String name) {
        return stream().filter(namedItem -> namedItem.name().equals(name)).findFirst()
            .orElseThrow(() -> newNoSuchElement(name));
    }

    /**
     * Returns whether this NamedItemStoreView contains the passed NamedItem. The comparison is done by the equals
     * method.
     * @param namedItem NamedItem to be checked for containment in this NamedItemStoreView
     * @return True if this NamedItemStoreView contains the passed NamedItem, else false.
     */
    public boolean contains(@NonNull E namedItem) {
        return indexOf(namedItem) != -1;
    }

    /**
     * Returns whether this NamedItemStoreView contains a NamedItem with the passed name
     * @param name Name of the NamedItem to be checked for containment in this NamedItemStoreView
     * @return True if this NamedItemStoreView contains a NamedItem with the passed name, else false.
     */
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean containsName(@NonNull String name) {
        return indexOf(name) != -1;
    }

    /**
     * Returns whether this NamedItemStoreView contains all NamedItems with the names of the passed Iterable
     * @param namedItemIterable Iterable with names to be checked for containment in this NamedItemStoreView
     * @return True if all elements of namedItemIterable are contained, else false
     */
    public boolean containsAllNames(@NonNull Iterable<String> namedItemIterable) {
        for (String name : namedItemIterable) {
            if (!containsName(name)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns an iterator over the NamedItems in this NamedItemStoreView
     * @return Iterator over the NamedItems
     */
    @SuppressWarnings("NullableProblems")
    @Override
    public Iterator<E> iterator() {
        return stream().iterator();
    }

    Stream<E> stream() {
        return items.stream();
    }

}
