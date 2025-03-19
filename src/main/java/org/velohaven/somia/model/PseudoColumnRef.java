package org.velohaven.somia.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.Accessors;

import static java.sql.DatabaseMetaData.bestRowNotPseudo;

@SuppressWarnings("UnusedReturnValue")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(fluent = true)
public class PseudoColumnRef<C extends PseudoColumnRef<C>> extends ColumnRef<C> {

    @Getter
    int pseudoColumn;

    Column pseudoColumnBaseColumn;

    @SuppressWarnings("unchecked")
    public C pseudoColumn(int pseudoColumn) {
        this.pseudoColumn = pseudoColumn;
        return (C) this;
    }

    @SuppressWarnings("unchecked")
    public C pseudoColumnBaseColumn(@NonNull Column pseudoColumnBaseColumn) {
        this.pseudoColumnBaseColumn = pseudoColumnBaseColumn;
        return (C) this;
    }

    public boolean isPseudo() {
        return pseudoColumn != bestRowNotPseudo;
    }

    @Override
    @SuppressWarnings("unchecked")
    C cloneFieldsFrom(@NonNull C source) {
        super.cloneFieldsFrom(source);
        pseudoColumn = source.pseudoColumn();
        pseudoColumnBaseColumn = (source.pseudoColumnBaseColumn == null
            ? null
            : source.pseudoColumnBaseColumn.cloneForParent(this));

        return (C) this;

    }

    @Override
    public Column baseColumn() {
        if (isPseudo()) {
            return pseudoColumnBaseColumn;
        } else {
            return super.baseColumn();
        }
    }

}
