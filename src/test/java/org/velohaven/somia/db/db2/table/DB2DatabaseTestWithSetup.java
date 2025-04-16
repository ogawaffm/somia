package org.velohaven.somia.db.db2.table;

import org.junit.jupiter.api.Assertions;
import org.velohaven.somia.db.DatabaseTestWithSetup;

import java.sql.Connection;
import java.sql.DriverManager;

abstract class DB2DatabaseTestWithSetup extends DatabaseTestWithSetup {

    static private Connection connection;
    static private final String CONNECT = "jdbc:db2://localhost:50000/testdb";

    static {
        try {
            Class.forName("com.ibm.db2.jcc.DB2Driver");
            connection = DriverManager.getConnection(CONNECT, "db2inst1", "password");
        } catch (Exception e) {
            Assertions.fail(e);
        }

    }

    @Override
    public Connection getConnection() {
        return connection;
    }

}
