package org.velohaven.somia.db.db2;

import org.junit.jupiter.api.Assertions;
import org.velohaven.somia.db.TextBasedTest;

import java.sql.Connection;
import java.sql.DriverManager;

abstract class DB2TextBasedTest extends TextBasedTest {

    static private Connection connection;
    static private final String CONNECT = "jdbc:db2://localhost:50000/testdb";
    static private final String CONNECT2 = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";

    static {
        try {
            Class.forName("com.ibm.db2.jcc.DB2Driver");
            connection = DriverManager.getConnection(CONNECT, "db2inst1", "finarisfinaris");
        } catch (Exception e) {
            Assertions.fail(e);
        }

    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public String getRelativeResourceFolder() {
        return "db/db2";
    }
}
