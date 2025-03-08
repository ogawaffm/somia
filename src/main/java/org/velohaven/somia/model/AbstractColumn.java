package org.velohaven.somia.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.Accessors;

import static java.sql.Types.NULL;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(fluent = true)
@Getter
public abstract class AbstractColumn<T extends AbstractColumn<T>> extends NamedItem<T>
    implements WithDataType, WithComment {

    private JdbcDataType type = JdbcDataType.of(NULL);
    private String comment = "";

    @SuppressWarnings("unchecked")
    public T type(@NonNull JdbcDataType type) {
        this.type = type;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T comment(@NonNull String comment) {
        this.comment = comment;
        return (T) this;
    }


    @SuppressWarnings("unchecked")
    @Override
    T cloneFieldsFrom(@NonNull T source) {
        super.cloneFieldsFrom(source);
        type(JdbcDataType.of(source.type()));
        comment = source.comment();
        return (T) this;
    }

}
