package org.velohaven.somia.db.oracle;

import org.junit.jupiter.api.Assertions;
import org.velohaven.somia.db.DatabaseTestWithSetup;

import java.sql.Connection;
import java.sql.DriverManager;

abstract class OracleDatabaseTestWithSetup extends DatabaseTestWithSetup {

    static private Connection connection;
    static private final String CONNECT = "jdbc:oracle:thin:@localhost:1521/FREEPDB1?remarksReporting=true";

    static {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            connection = DriverManager.getConnection(CONNECT, "SYSTEM", "password");
        } catch (Exception e) {
            Assertions.fail(e);
        }

    }

    @Override
    public Connection getConnection() {
        return connection;
    }

}
