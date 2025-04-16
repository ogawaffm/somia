package org.velohaven.somia.jdbc.resultset.databasemetadata.entity;

/**
 * Represents a row in the result set of the {@link java.sql.DatabaseMetaData#getClientInfoProperties} method.
 */
public class ClientInfoPropertyEntity {

    public Short scope;
    public String name;
    public Integer maxLen;
    public String defaultValue;
    public String description;

}