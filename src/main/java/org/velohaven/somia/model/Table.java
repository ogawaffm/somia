package org.velohaven.somia.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.Accessors;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(fluent = true)
@Getter
public class Table extends IndexableTableLikeObject<Table> {

    private String collation = "";

    private PrimaryKey primaryKey;

    private BestRowIdentifier bestRowIdentifier;

    @Override
    Table cloneFieldsFrom(@NonNull Table source) {
        super.cloneFieldsFrom(source);
        collation = source.collation;
        bestRowIdentifier = (source.bestRowIdentifier == null ? null : source.bestRowIdentifier.cloneForParent(this));
        primaryKey = (source.primaryKey == null ? null : source.primaryKey.cloneForParent(this));

        return this;
    }

    public Table collation(@NonNull String collation) {
        this.collation = collation;
        return this;
    }

    Table primaryKey(PrimaryKey primaryKey) {
        this.primaryKey = primaryKey;
        return this;
    }

    boolean hasPrimaryKey() {
        return primaryKey != null;
    }

    Table bestRowIdentifier(BestRowIdentifier bestRowIdentifier) {
        this.bestRowIdentifier = bestRowIdentifier;
        return this;
    }

    public boolean hasBestRowIdentifier() {
        return bestRowIdentifier != null;
    }

}
