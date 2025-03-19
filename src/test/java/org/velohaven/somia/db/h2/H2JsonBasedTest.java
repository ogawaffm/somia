package org.velohaven.somia.db.h2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.velohaven.somia.db.JsonCompare;
import org.velohaven.somia.model.ModelReader;
import org.velohaven.somia.model.Table;

import java.sql.SQLException;

public abstract class H2JsonBasedTest extends H2TextBasedTest implements JsonCompare {

    @Override
    @Test
    public void compare() {
        JsonCompare.super.compare();
    }

    public String getActualResult() {
        Table table = null;
        try {
            table = new ModelReader().readTable(getConnection(), "TEST", null, getBaseName());
        } catch (SQLException e) {
            Assertions.fail(e);
        }
        return table.toJson();
    }

    @Override
    public String getExpectedResultFilenameExtension() {
        return "json";
    }

}
