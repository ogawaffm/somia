package org.velohaven.somia.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.Accessors;

@SuppressWarnings("UnusedReturnValue")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(fluent = true)
@Getter
public class IndexColumn extends ColumnRef<IndexColumn> {

    /**
     * The order of the column in the index ("ASC", "DESC" or "" if not applicable)
     */
    private String order = "";
    private boolean isIncluded = false;


    public IndexColumn order(@NonNull String order) {
        this.order = order;
        return this;
    }

    public IndexColumn isIncluded(@NonNull Boolean isUnique) {
        this.isIncluded = isUnique;
        return this;
    }

    @Override
    IndexColumn cloneFieldsFrom(@NonNull IndexColumn source) {
        super.cloneFieldsFrom(source);
        order = source.order;
        isIncluded = source.isIncluded;

        return this;

    }

}
