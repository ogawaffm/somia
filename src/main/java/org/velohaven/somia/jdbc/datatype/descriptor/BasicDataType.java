package org.velohaven.somia.jdbc.datatype.descriptor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Data
@AllArgsConstructor
public class BasicDataType implements BasicDataTypeProps<BasicDataType> {
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

    BasicDataType baseType;

    public static <T extends BasicDataTypeProps<T>> BasicDataType of(T source) {
        return BasicDataTypePropsFactory.create(source, BasicDataType::new);
    }

}
