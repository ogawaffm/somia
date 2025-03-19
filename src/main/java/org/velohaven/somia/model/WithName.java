package org.velohaven.somia.model;

public interface WithName {

    /**
     * Returns the name as it is stored in the database dictionary to identify the object in the database
     * @return name
     */
    String name();

    /**
     * Returns the name as it is stored in the database dictionary, but quoted to be safely used in SQL statements,
     * e.g. "my table"
     * @return quoted name
     */
    default String quotedIdentifier() {
        return "\"" + name().replace("\"", "\"\"") + "\"";
    }

    /**
     * Returns the full name, including the schema or catalog, if applicable. The names are quoted to be
     * safely used in SQL statements, e.g. "catalog", "schema"."my table"
     * @return full name including schema or catalog
     */
    default String fullQuotedIdentifier() {
        return quotedIdentifier();
    }

}
