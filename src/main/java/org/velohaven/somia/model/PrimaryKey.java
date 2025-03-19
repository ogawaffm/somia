package org.velohaven.somia.model;

public class PrimaryKey extends Key<PrimaryKey, PrimaryKeyColumn> {

    public PrimaryKey() {
        super();
        isUnique(true);
    }

    @Override
    public PrimaryKey isUnique(boolean isUnique) {
        if (!isUnique) {
            throw new IllegalArgumentException("Primary key must be unique");
        }
        super.isUnique(true);
        return this;
    }

}
