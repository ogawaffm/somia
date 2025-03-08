package org.velohaven.somia.jdbc.databasemetadata.resultset.reader;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.velohaven.somia.jdbc.databasemetadata.resultset.entity.TypeInfoEntity;

import static org.velohaven.somia.jdbc.databasemetadata.resultset.columndefinition.GetTypeInfoColDef.*;

/**
 * A {@link RowReader} for reading {@link TypeInfoEntity}s from a ResultSet with the structure
 * returned by {@link java.sql.DatabaseMetaData#getTypeInfo}.
 */
public class TypeInfoRowReader extends RowReader<TypeInfoEntity> {

    public TypeInfoRowReader(final ResultSet resultSet, boolean strict) {
        super(resultSet, strict);
    }

    public void read(TypeInfoEntity row) throws SQLException {
        row.typeName = readStringByIndex(TYPE_NAME);
        row.dataType = readIntegerByIndex(DATA_TYPE);
        row.precision = readIntegerByIndex(PRECISION);
        row.literalPrefix = readStringByIndex(LITERAL_PREFIX);
        row.literalSuffix = readStringByIndex(LITERAL_SUFFIX);
        row.createParams = readStringByIndex(CREATE_PARAMS);
        row.nullable = readIntegerByIndex(NULLABLE);
        row.caseSensitive = readBooleanByIndex(CASE_SENSITIVE);
        row.searchable = readIntegerByIndex(SEARCHABLE);
        row.unsignedAttribute = readBooleanByIndex(UNSIGNED_ATTRIBUTE);
        row.fixedPrecisionScale = readBooleanByIndex(FIXED_PREC_SCALE);
        row.autoIncrement = readBooleanByIndex(AUTO_INCREMENT);
        row.localTypeName = readStringByIndex(LOCAL_TYPE_NAME);
        row.minimumScale = readIntegerByIndex(MINIMUM_SCALE);
        row.maximumScale = readIntegerByIndex(MAXIMUM_SCALE);
        row.sqlDataType = readIntegerByIndex(SQL_DATA_TYPE);
        row.sqlDatetimeSub = readIntegerByIndex(SQL_DATETIME_SUB);
        row.numericPrecisionRadix = readIntegerByIndex(NUM_PREC_RADIX);
    }

}