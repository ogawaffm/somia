package org.velohaven.somia.jdbc.resultset.databasemetadata.entity;

/**
 * Represents a row in the result set of the {@link java.sql.DatabaseMetaData#getProcedures} method.
 */
public class ProcedureEntity {

    public String procedureCatalog;
    public String procedureSchema;
    public String procedureName;
    public String reservedForFutureUse4;
    public String reservedForFutureUse5;
    public String reservedForFutureUse6;
    public String remarks;
    public Short procedureType;
    public String specificName;

}