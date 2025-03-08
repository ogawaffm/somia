package org.velohaven.somia.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ModelFactory {

    public static class Api {
        public String call(Object object) {
            return "";
        }

        public Byte newByte(Number number) {
            return number.byteValue();
        }

        public Byte newByte(String numberString) {
            return Byte.parseByte(numberString);
        }

        public Short newShort(Number number) {
            return number.shortValue();
        }

        public Short newShort(String numberString) {
            return Short.parseShort(numberString);
        }

        public Integer newInteger(Number number) {
            return number.intValue();
        }

        public Integer newInteger(String numberString) {
            return Integer.parseInt(numberString);
        }

        public Long newLong(Number number) {
            return number.longValue();
        }

        public Long newLong(String numberString) {
            return Long.parseLong(numberString);
        }

        public Float newFloat(Number number) {
            return number.floatValue();
        }

        public Double newDouble(Number number) {
            return number.doubleValue();
        }

        public Double newDouble(String numberString) {
            return Double.parseDouble(numberString);
        }

        public BigDecimal newBigDecimal(Number number) {
            return new BigDecimal(number.toString());
        }

        public BigDecimal newBigDecimal(String numberString) {
            return new BigDecimal(numberString);
        }

        public BigInteger newBigInteger(Number number) {
            return new BigInteger(number.toString());
        }

        public BigInteger newBigInteger(String numberString) {
            return new BigInteger(numberString);
        }


    }

    public Api createApi() {
        return new Api();
    }

    public Connection getSqlServerConnection() throws SQLException {
        final String JDBC_URL = "jdbc:sqlserver://localhost:1433;instanceName=BITS-DSK002\\\\SQLEXPRESS01;database=test;encrypt=true;integratedSecurity=true;trustServerCertificate=false;";
        final String JDBC_USER = "admin";
        final String JDBC_PASSWORD = "finaris";
        return DriverManager.getConnection(JDBC_URL);
    }

    public Connection getH2Connection() throws SQLException {
        final String JDBC_URL = "jdbc:h2:tcp:localhost/~/testdb";
        final String JDBC_USER = "sa";
        final String JDBC_PASSWORD = "";
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }

    public Connection getDB2Connection() throws SQLException {
        final String JDBC_URL = "jdbc:db2://localhost:50000/testdb";
        final String JDBC_USER = "DB2INST1";
        final String JDBC_PASSWORD = "admin";
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }

    public Connection getConnection() throws SQLException {
        return getH2Connection();
    }


    public Map<String, Table> loadTables(String catalogName, String schemaName, String tableName) throws SQLException {
        return new ModelReader().readTables(
            getConnection(),
            catalogName,
            schemaName,
            tableName,
            Integer.MAX_VALUE
        );
    }

    public List<BestRowIdentifierColumn> test(Table table) throws SQLException {
        return new ModelReader().readBestRowIdentifier(getConnection(), table);
    }

    public Catalog newCatalog() {
        return new Catalog();
    }

    public Model loadModel() throws SQLException {
        return new ModelReader().readModel(getConnection(), Integer.MAX_VALUE);
    }

    public Schemas loadSchemas(String catalogName, String schemaName) throws SQLException {
        return new ModelReader().readSchemas(
            getConnection(),
            catalogName,
            schemaName,
            Integer.MAX_VALUE
        );
    }

    public Table newTable() {
        return new Table();
    }

    public Column newTableColumn() {
        return new Column();
    }

    public Index newIndex() {
        return new Index();
    }

    public IndexColumn newIndexColumn() {
        return new IndexColumn();
    }

    public PrimaryKey newPrimaryKey() {
        return new PrimaryKey();
    }

}
