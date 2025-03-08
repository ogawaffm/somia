package org.velohaven.somia.model;

public class ColumnRef<C extends ColumnRef<C>> extends NamedItem<C> {

    public Column baseColumn() {

        @SuppressWarnings("rawtypes")
        Key key = (Key) parent();

        if (key == null) {
            throw new IllegalStateException(getClass().getSimpleName() + " " + name() + " is unbound");
        }

        Table table = key.table();

        if (table == null) {
            throw new IllegalStateException(key.getClass().getSimpleName() + " " + name() + " is unbound");
        }

        return key.table().columns(name());

    }

}
