package org.velohaven.somia.jdbc.datatype.descriptor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.With;
import lombok.experimental.Accessors;

import java.sql.SQLType;

@Accessors(fluent = true)
@Getter
@With
@AllArgsConstructor
public class BasicDataTypeWither implements BasicDataTypeProps<BasicDataTypeWither>,
    BasicDataTypePropsWither<BasicDataTypeWither> {
    String name;
    int typeNumber;
    String className;
    int precision;
    int scale;
    boolean isNullable;
    boolean isAutoIncrement;
    int numericPrecisionRadix;

    boolean isCaseSensitive;
    boolean isSearchable;
    boolean isSigned;
    boolean isCurrency;
    int columnDisplaySize;

    BasicDataTypeWither baseType;

    public static BasicDataTypeWither create() {
        return BasicDataTypePropsFactory.create(BasicDataTypeWither::new);
    }

    public static BasicDataTypeWither of(SQLType sqlType) {
        return create().withTypeNumber(sqlType.getVendorTypeNumber()).withName(sqlType.getName());
    }

    public static <T extends BasicDataTypeProps<T>> BasicDataTypeWither of(T source) {
        return BasicDataTypePropsFactory.create(source, BasicDataTypeWither::new);
    }

}
