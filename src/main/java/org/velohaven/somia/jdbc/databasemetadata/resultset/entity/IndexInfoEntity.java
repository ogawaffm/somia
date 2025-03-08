package org.velohaven.somia.jdbc.databasemetadata.resultset.entity;

/**
 * Represents a row in the result set of the {@link java.sql.DatabaseMetaData#getIndexInfo} method.
 */
public class IndexInfoEntity {

    public String tableCatalog;
    public String tableSchema;
    public String tableName;
    public Boolean nonUnique;
    public String indexQualifier;
    public String indexName;
    public Short type;
    public Integer ordinalPosition;
    public String columnName;
    public String ascOrDesc;
    public Long cardinality;
    public Long pages;
    public String filterCondition;

}