package org.velohaven.somia.jdbc.datatype.descriptor;

import java.sql.Types;

public class BasicDataTypePropsFactory {

    /**
     * All argument constructor for the target type.
     * @param <T> The type create the target object
     */
    public interface BasicDataTypePropsAllArgConstructor<T extends BasicDataTypeProps<T>> {
        T create(
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
            T baseType
        );
    }

    /**
     * Create a new instance create the target type from the given source object.
     * @param source                         The source object
     * @param newBaseType                    The new base type
     * @param basicDataTypeAllArgConstructor The constructor create the target type to use
     * @param <T>                            The type create the target object
     * @param <S>                            The type create the source object
     * @return The new instance
     */
    private static <T extends BasicDataTypeProps<T>, S extends BasicDataTypeProps<S>> T create(
        S source, T newBaseType, BasicDataTypePropsAllArgConstructor<T> basicDataTypeAllArgConstructor) {

        return basicDataTypeAllArgConstructor.create(
            source.name(),
            source.typeNumber(),
            source.className(),
            source.precision(),
            source.scale(),
            source.isNullable(),
            source.isAutoIncrement(),
            source.numericPrecisionRadix(),
            source.isCaseSensitive(),
            source.isSearchable(),
            source.isSigned(),
            source.isCurrency(),
            source.columnDisplaySize(),
            newBaseType
        );
    }

    /**
     * Create a new instance create the target type with default values and using the given constructor.
     * @param basicDataTypeAllArgConstructor The constructor create the target type to use
     * @param <T>                            The type create the target object
     * @return The new instance
     */
    public static <T extends BasicDataTypeProps<T>> T create(
        BasicDataTypePropsAllArgConstructor<T> basicDataTypeAllArgConstructor) {
        return basicDataTypeAllArgConstructor.create(
            "", Types.NULL, null,
            0, 0, true, false, 0,
            false, true, false, false, 0,
            (T) null
        );
    }

    /**
     * Check if the source object is the same type as the target type.
     * @param source                         The source object
     * @param basicDataTypeAllArgConstructor The target type
     * @param <T>                            The type create the target object
     * @param <S>                            The type create the source object
     * @return True if the source object is the same type as the target type
     */
    private static <T extends BasicDataTypeProps<T>, S extends BasicDataTypeProps<S>> boolean isSameType(
        S source, BasicDataTypePropsAllArgConstructor<T> basicDataTypeAllArgConstructor) {
        return source.getClass().equals(basicDataTypeAllArgConstructor.getClass().getEnclosingClass());
    }

    /**
     * Cast the source object to the target type.
     * @param source The source object
     * @param <T>    The type create the target object
     * @return The target object
     */
    @SuppressWarnings("unchecked")
    private static <T extends BasicDataTypeProps<T>> T castToTargetType(BasicDataTypeProps<?> source) {
        return (T) source;
    }

    /**
     * Create a new instance with the given constructor from the given source.
     * @param source                         The source object
     * @param basicDataTypeAllArgConstructor The constructor
     * @param <T>                            The type create the new instance (Target)
     * @param <S>                            The type create the source object (Source)
     * @return The new instance
     */
    public static <T extends BasicDataTypeProps<T>, S extends BasicDataTypeProps<S>> T create(
        S source,
        BasicDataTypePropsAllArgConstructor<T> basicDataTypeAllArgConstructor) {

        if (isSameType(source, basicDataTypeAllArgConstructor)) {
            return castToTargetType(source);
        }

        S baseType = source.baseType();

        T newBaseType = null;

        if (baseType != null) {
            if (isSameType(baseType, basicDataTypeAllArgConstructor)) {
                newBaseType = castToTargetType(baseType);
            } else {
                newBaseType = create(baseType, create(baseType, basicDataTypeAllArgConstructor),
                    basicDataTypeAllArgConstructor);
            }
        }
        return create(source, newBaseType, basicDataTypeAllArgConstructor);
    }

}