package org.velohaven.somia.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.Accessors;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(fluent = true)
@Data
public class View extends TableLikeObject<View> {

    private String sql;

    @Override public String typeName() {
        return "";
    }

    public View sql(String sql) {
        this.sql = sql;
        return this;
    }

    @Override
    View cloneFieldsFrom(@NonNull View source) {
        super.cloneFieldsFrom(source);
        sql = source.sql;
        return this;
    }

}
