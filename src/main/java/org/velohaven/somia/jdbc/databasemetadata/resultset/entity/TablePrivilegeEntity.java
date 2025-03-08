package org.velohaven.somia.jdbc.databasemetadata.resultset.entity;

/**
 * Represents a row in the result set of the {@link java.sql.DatabaseMetaData#getTablePrivileges} method.
 */
public class TablePrivilegeEntity {

    public String tableCatalog;
    public String tableSchema;
    public String tableName;
    public String grantor;
    public String grantee;
    public String privilege;
    public String isGrantable;

}