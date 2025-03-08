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
 * @param <T> The type of the NamedArtefacts
 * @param <E> The type of the named artefacts in the NamedArtefacts
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
     * Returns the number of named artefacts in this NamedArtefacts
     * @return Number of named artefacts in this NamedArtefacts
     */
    public int size() {
        return items.size();
    }

    /**
     * Returns whether this NamedArtefacts contains no named artefacts.
     * @return True if this NamedArtefacts contains no named artefacts, else false.
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns whether an artefact with the passed index exists
     * @param index 0-based index of the artefact
     * @return True if an artefact with the passed index exists, else false
     */
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean indexExists(int index) {
        return index >= 0 && index < size();
    }

    /**
     * Returns the index of the Artefact with the passed name
     * @param name Name of the Artefact
     * @return 0-based index of the Artefact with the passed name
     */
    public int indexOf(@NonNull String name) {
        return indexOf(artefact -> artefact.name().equals(name));
    }

    public int indexOf(@NonNull E artefact) {
        return indexOf(artefact::equals);
    }

    int indexOf(@NonNull Predicate<NamedItem<?>> elementPredicate) {
        int matchIndex = -1;
        Iterator<E> iterator = this.stream().iterator();

        while (iterator.hasNext()) {
            E artefact = iterator.next();
            matchIndex++;
            if (elementPredicate.test(artefact)) {
                return matchIndex;
            }
        }
        return -1;
    }

    /**
     * Returns a List of the names in original case of the artefacts in this Artefacts
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
     * Returns the Artefact at the passed index
     * @param index 0-based index of the Artefact
     * @return The Artefact at the passed index
     */
    public E getByIndex(int index) {
        if (!indexExists(index)) {
            throw newIndexOutOfBoundsException(index, size());
        }
        return items.get(index);
    }

    /**
     * Returns the Artefact with the passed name or null if no such Artefact exists
     * @param name Name of the Artefact to return
     * @return The Artefact with the passed name or null if no such Artefact exists
     */
    public E getByName(@NonNull String name) {
        return stream().filter(artefact -> artefact.name().equals(name)).findFirst()
            .orElseThrow(() -> newNoSuchElement(name));
    }

    /**
     * Returns whether this Artefacts contains the passed Artefact. The comparison is done by the equals method.
     * @param artefact Artefact to be checked for containment in this Artefacts
     * @return True if this Artefacts contains the passed Artefact, else false.
     */
    public boolean contains(@NonNull E artefact) {
        return indexOf(artefact) != -1;
    }

    /**
     * Returns whether this Artefacts containsName an Artefact with the passed name
     * @param name Name of the Artefact to be checked for containment in this Artefacts
     * @return True if this Artefacts containsName an Artefact with the passed name, else false.
     */
    public boolean containsName(@NonNull String name) {
        return indexOf(name) != -1;
    }

    /**
     * Returns whether this Artefacts contains all artefacts with the names of the passed Iterable
     * @param nameIterable Iterable with names to be checked for containment in this Artefacts
     * @return True if all elements of nameIterable are contained, else false
     */
    public boolean containsAllNames(@NonNull Iterable<String> nameIterable) {
        for (String name : nameIterable) {
            if (!containsName(name)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns an iterator over the artefacts in this Artefacts
     * @return Iterator over the artefacts
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
