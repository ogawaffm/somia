package org.velohaven.somia.db.h2;

import org.junit.jupiter.api.Assertions;
import org.velohaven.somia.db.DatabaseTestWithSetup;

import java.sql.Connection;
import java.sql.DriverManager;

abstract class H2DatabaseTestWithSetup extends DatabaseTestWithSetup {

    static private Connection connection;
    static private final String CONNECT = "jdbc:h2:tcp://localhost:9092/~\\test";
    static private final String CONNECT2 = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";

    static {
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(CONNECT, "sa", "");
        } catch (Exception e) {
            Assertions.fail(e);
        }

    }

    @Override
    public Connection getConnection() {
        return connection;
    }

}
