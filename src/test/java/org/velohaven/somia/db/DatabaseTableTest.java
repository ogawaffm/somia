package org.velohaven.somia.db;

import org.junit.jupiter.api.Assertions;
import org.velohaven.somia.base.JsonFileCompareTest;
import org.velohaven.somia.model.ModelReader;
import org.velohaven.somia.model.Table;

import java.sql.SQLException;

/**
 * Interface for database table tests to test table structure found in the database.
 */
public interface DatabaseTableTest extends JsonFileCompareTest, WithConnection {

    default String getActualResult() {
        String actualResult;
        deleteExistingActualResultFile();
        Table table = null;
        try {
            table = new ModelReader().readTable(getConnection(), "", "TEST", getBaseName());
            if (table == null) {
                Assertions.fail("Table TEST." + getBaseName() + " not found");
            }
        } catch (SQLException e) {
            Assertions.fail(e);
        }
        actualResult = table.toJson();
        // save for traceability
        saveActualResultToFile(actualResult);
        return actualResult;
    }

}
