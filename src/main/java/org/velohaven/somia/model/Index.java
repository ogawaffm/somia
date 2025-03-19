package org.velohaven.somia.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.Accessors;

@SuppressWarnings({"UnusedReturnValue", "unused"})
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(fluent = true)
@Getter
public class Index extends Key<Index, IndexColumn> implements WithComment {

    private String comment = "";

    private String qualifierName = "";
    private boolean isUnique;
    private boolean isClustered;
    private boolean isHashed;
    private boolean isStatistic;
    private String whereClause = "";

    /**
     * Number of unique values in the index or in case of a statistic the number of rows in the table.
     */

    private long cardinality;
    /**
     * Number of pages used by the index or in case of a statistic the number of pages used by the table.
     */
    private long pages;

    @Override
    Index cloneFieldsFrom(@NonNull Index source) {
        super.cloneFieldsFrom(source);
        comment = source.comment;
        qualifierName = source.qualifierName;
        isUnique = source.isUnique;
        isClustered = source.isClustered;
        isHashed = source.isHashed;
        isStatistic = source.isStatistic;
        whereClause = source.whereClause;
        cardinality = source.cardinality;
        pages = source.pages;

        return this;
    }

    public Table table() {
        return (Table) parent();
    }

    public Index comment(@NonNull String comment) {
        this.comment = comment;
        return this;
    }

    public Index qualifierName(@NonNull String qualifierName) {
        this.qualifierName = qualifierName;
        return this;
    }

    public Index isUnique(boolean isUnique) {
        this.isUnique = isUnique;
        return this;
    }

    public Index isClustered(boolean isClustered) {
        this.isClustered = isClustered;
        if (isClustered) {
            isHashed = false;
            isStatistic = false;
        }
        return this;
    }

    public Index isHashed(boolean isHashed) {
        this.isHashed = isHashed;
        if (isHashed) {
            isClustered = false;
            isStatistic = false;
        }
        return this;
    }

    public Index isStatistic(boolean isStatistic) {
        this.isStatistic = isStatistic;
        if (isStatistic) {
            isClustered = false;
            isHashed = false;
        }
        return this;
    }

    public Index whereClause(@NonNull String whereClause) {
        this.whereClause = whereClause;
        return this;
    }

    public Index cardinality(long cardinality) {
        this.cardinality = cardinality;
        return this;
    }

    public Index pages(long pages) {
        this.pages = pages;
        return this;
    }

    public NamedItemStoreFilteredView<?, IndexColumn> nonKeyColumns() {
        return columns().createView(IndexColumn::isIncluded);
    }

    public IndexColumn nonKeyColumns(int index) {
        return nonKeyColumns().getByIndex(index);
    }

    public IndexColumn nonKeyColumns(String name) {
        return nonKeyColumns().getByName(name);
    }

    public NamedItemStoreFilteredView<?, IndexColumn> keyColumns() {
        return columns().createView(column -> !column.isIncluded());
    }

    public IndexColumn keyColumns(String name) {
        return keyColumns().getByName(name);
    }

    public IndexColumn keyColumns(int index) {
        return keyColumns().getByIndex(index);
    }

}
