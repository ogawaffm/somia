package org.velohaven.somia.model;

/**
 * As the parent property of the Item class is not public and can only be set from within the same package of an Item,
 * this ParentBinder class is used to set the parent property of an Item to compose structures of items.
 */
public class ParentBinder {

    public static void bind(Item<?> item, Object parent) {
        item.parent(parent);
    }

}
