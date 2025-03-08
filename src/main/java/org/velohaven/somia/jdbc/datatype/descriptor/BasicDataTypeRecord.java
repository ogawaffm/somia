package org.velohaven.somia.jdbc.datatype.descriptor;

public record BasicDataTypeRecord(

    String name,

    int typeNumber,

    String className,

    int precision,

    int scale,

    boolean isNullable,

    boolean isAutoIncrement,

    int numericPrecisionRadix,

    boolean isCaseSensitive,

    boolean isSearchable,

    boolean isSigned,

    boolean isCurrency,

    int columnDisplaySize,

    BasicDataTypeRecord baseType

) implements BasicDataTypeProps<BasicDataTypeRecord> {

    public static <T extends BasicDataTypeProps<T>> BasicDataTypeRecord of(T source) {
        return BasicDataTypePropsFactory.create(source, BasicDataTypeRecord::new);
    }

}
