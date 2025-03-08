package org.velohaven.somia.jdbc.databasemetadata.resultset.entity;

/**
 * Represents a row in the result set of the {@link java.sql.DatabaseMetaData#getBestRowIdentifier} method.
 */
public class BestRowIdentifierEntity {

    public Integer scope;
    public String columnName;
    public Integer dataType;
    public String typeName;
    public Integer columnSize;
    public Integer bufferLength;
    public Integer decimalDigits;
    public Integer pseudoColumn;

}