package org.velohaven.somia.db.db2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.velohaven.somia.db.JsonCompare;
import org.velohaven.somia.model.ModelReader;
import org.velohaven.somia.model.Table;

import java.sql.SQLException;

public abstract class DB2JsonBasedTest extends DB2TextBasedTest implements JsonCompare {

    @Override
    @Test
    public void compare() {
        JsonCompare.super.compare();
    }

    public String getActualResult() {
        Table table = null;
        try {
            table = new ModelReader().readTable(getConnection(), "", "TEST", getBaseName());
            if (table == null) {
                Assertions.fail("Table TEST." + getBaseName() + " not found");
            }
        } catch (SQLException e) {
            Assertions.fail(e);
        }
        return table.toJson();
    }

    @Override
    public String getResultFilenameExtension() {
        return "json";
    }

}
