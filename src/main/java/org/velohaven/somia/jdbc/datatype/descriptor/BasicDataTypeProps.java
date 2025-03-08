package org.velohaven.somia.jdbc.datatype.descriptor;

public interface BasicDataTypeProps<T extends BasicDataTypeProps<T>> {

    String name();

    int typeNumber();

    String className();

    int precision();

    default int size() {return precision();}

    int scale();

    boolean isNullable();

    boolean isAutoIncrement();

    int numericPrecisionRadix();

    boolean isCaseSensitive();

    boolean isSearchable();

    boolean isSigned();

    boolean isCurrency();

    int columnDisplaySize();

    default T baseType() {return null;}

}
