package org.velohaven.somia.db;

import java.sql.Connection;

public interface WithConnection {

    Connection getConnection();

}
