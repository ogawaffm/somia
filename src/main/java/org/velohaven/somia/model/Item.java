package org.velohaven.somia.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

import java.lang.reflect.Constructor;

@EqualsAndHashCode
public class Item<T extends Item<T>> implements Cloneable {

    public Item() {
    }

    public Item(@NonNull Object parent) {
        parent(parent);
    }

    /**
     * The parent object of this object or null if this object has no parent by definition or if the parent is not
     * bound yet.
     */
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Object parent = null;

    void parent(Object parent) {
        this.parent = parent;
    }

    Object parent() {
        return parent;
    }

    @SuppressWarnings({"unchecked", "MethodDoesntCallSuperMethod"})
    @Override
    public T clone() {
        T clone = createNewInstance((T) this);
        return clone.cloneFieldsFrom((T) this);
    }

    T cloneForParent(Object parent) {
        T clone = clone();
        clone.parent(parent);
        return clone;
    }

    /**
     * Copies the fields from the source object to this object by direct access to the fields. Access via setters must
     * be avoided because they may trigger change notifications.
     * @param source the source object to copy the fields from
     * @return This instance
     */
    @SuppressWarnings("unchecked")
    T cloneFieldsFrom(@NonNull T source) {
        return (T) this;
    }


    @SuppressWarnings("unchecked")
    private static <T> T createNewInstance(@NonNull T instance) {
        try {
            Class<?> clazz = instance.getClass();
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            return (T) constructor.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create a new instance of" + instance.getClass().getName(), e);
        }
    }


}
