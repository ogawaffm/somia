package org.velohaven.somia.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.velohaven.somia.jdbc.datatype.SqlDataType;
import org.velohaven.somia.jdbc.datatype.descriptor.BasicDataTypeFactory;
import org.velohaven.somia.jdbc.datatype.descriptor.BasicDataTypeProps;
import org.velohaven.somia.jdbc.datatype.descriptor.BasicDataTypePropsFactory;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class JdbcDataType extends SqlDataType<JdbcDataType> {

    public JdbcDataType(final String name, final int typeNumber, final String className, final int precision,
        final int scale, final boolean isNullable,
        final boolean isAutoIncrement, final int numericPrecisionRadix, final boolean isCaseSensitive,
        final boolean isSearchable, final boolean isSigned,
        final boolean isCurrency, final int columnDisplaySize, final JdbcDataType baseType) {
        super(name, typeNumber, className, precision, scale, isNullable, isAutoIncrement, numericPrecisionRadix,
            isCaseSensitive, isSearchable, isSigned, isCurrency, columnDisplaySize, baseType);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static JdbcDataType of(BasicDataTypeProps basicDataType) {
        return BasicDataTypePropsFactory.create(basicDataType, JdbcDataType::new);
    }

    public static JdbcDataType of(int typeNumber) {
        return of(BasicDataTypeFactory.of(typeNumber));
    }

    public static JdbcDataType of(int typeNumber, int sizeOrPrecisionOrScale) {
        return of(BasicDataTypeFactory.of(typeNumber, sizeOrPrecisionOrScale));
    }

    public static JdbcDataType of(int typeNumber, int precision, int scale) {
        return of(BasicDataTypeFactory.of(typeNumber, precision, scale));
    }

}
