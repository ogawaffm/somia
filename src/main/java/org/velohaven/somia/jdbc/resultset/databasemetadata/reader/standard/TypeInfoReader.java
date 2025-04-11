package org.velohaven.somia.jdbc.resultset.databasemetadata.reader.standard;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.velohaven.somia.jdbc.resultset.databasemetadata.entity.TypeInfoEntity;

import static org.velohaven.somia.jdbc.resultset.databasemetadata.columndefinition.GetTypeInfoColDef.*;

/**
 * A {@link KnownMetaDataReader} for reading {@link TypeInfoEntity}s from a ResultSet with the structure
 * returned by {@link java.sql.DatabaseMetaData#getTypeInfo}.
 */
public class TypeInfoReader extends KnownMetaDataReader<TypeInfoEntity> {

    protected TypeInfoReader(final ResultSet resultSet, final boolean strict) {
        super(resultSet, TypeInfoEntity::new, strict);
    }

    @Override
    protected void transfer(TypeInfoEntity row) throws SQLException {
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