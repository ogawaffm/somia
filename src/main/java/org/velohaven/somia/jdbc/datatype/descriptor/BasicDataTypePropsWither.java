package org.velohaven.somia.jdbc.datatype.descriptor;

public interface BasicDataTypePropsWither<W> {

    W withPrecision(int precision);

    default W withSize(int size) {
        return withPrecision(size);
    }

}
