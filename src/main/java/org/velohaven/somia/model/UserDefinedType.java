package org.velohaven.somia.model;

import static java.sql.Types.NULL;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.Accessors;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(fluent = true)
@Getter
public class UserDefinedType extends DatabaseObject<UserDefinedType> implements WithDataType {

    String className = "";

    private JdbcDataType type = JdbcDataType.of(NULL);

    public UserDefinedType className(@NonNull String className) {
        this.className = className;
        return this;
    }

    public UserDefinedType type(@NonNull JdbcDataType type) {
        this.type = type;
        return this;
    }

    @Override
    UserDefinedType cloneFieldsFrom(@NonNull UserDefinedType source) {
        super.cloneFieldsFrom(source);
        type(JdbcDataType.of(source.type()));
        return this;
    }

}
