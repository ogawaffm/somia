package org.velohaven.somia.db.h2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.velohaven.somia.db.JsonCompare;
import org.velohaven.somia.model.ModelReader;
import org.velohaven.somia.model.Table;

import java.sql.SQLException;

public abstract class H2JsonBasedTest extends H2TextBasedTest implements JsonCompare {

    private String actualResult = null;

    @Override
    @Test
    public void compare() {
        JsonCompare.super.compare();
    }

    public String getActualResult() {
        if (actualResult == null) {
            Table table = null;
            try {
                table = new ModelReader().readTable(getConnection(), null, "TEST", getBaseName());
                if (table == null) {
                    Assertions.fail("Table TEST." + getBaseName() + " not found");
                }
            } catch (SQLException e) {
                Assertions.fail(e);
            }
            actualResult = table.toJson();
            // save for traceability
            saveResult(getActualResult(), actualResult);
        }
        return actualResult;
    }

    @Override
    public String getResultFilenameExtension() {
        return "json";
    }

}
