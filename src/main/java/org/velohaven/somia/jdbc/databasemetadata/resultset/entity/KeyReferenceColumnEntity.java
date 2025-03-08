package org.velohaven.somia.jdbc.databasemetadata.resultset.entity;

/**
 * Represents a row in the result set of the {@link java.sql.DatabaseMetaData#getCrossReference},
 * {@link java.sql.DatabaseMetaData#getImportedKeys}, and {@link java.sql.DatabaseMetaData#getExportedKeys} methods.
 */
public class KeyReferenceColumnEntity {

    public String primaryKeyTableCatalog;
    public String primaryKeyTableSchema;
    public String primaryKeyTableName;
    public String primaryKeyColumnName;

    public String foreignKeyTableCatalog;
    public String foreignKeyTableSchema;
    public String foreignKeyTableName;
    public String foreignKeyColumnName;

    public Short keySeq;

    public Short updateRule;
    public Short deleteRule;

    public String foreignKeyName;
    public String primaryKeyName;
    public String deferrability;

}