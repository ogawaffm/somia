package org.velohaven.somia.jdbc.resultset.databasemetadata.entity;

/**
 * Represents a row in the result set of the {@link java.sql.DatabaseMetaData#getVersionColumns} method.
 */
public class VersionColumnEntity {

    public short scope;
    public String columnName;
    public int dataType;
    public String typeName;
    public int columnSize;
    public int bufferLength;
    public short decimalDigits;
    public short pseudoColumn;

}