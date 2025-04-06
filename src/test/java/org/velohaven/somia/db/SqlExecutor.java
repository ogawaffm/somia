package org.velohaven.somia.db;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class SqlExecutor {

    /**
     * Builds a list of script file names in the given directory, ordered by their natural order.
     * The ordering is important to ensure that the scripts are executed in the correct order, which can be achieved by
     * naming the scripts in a way that the natural order is the correct order (e.g. prefixed by "001_...", "002_...").
     * The directory is expected to be in the resources' folder.
     *
     * @param scriptDirectoryName The directory name in the resources' folder.
     * @return A list of script file names.
     */
    private static List<String> getOrderedScriptFileNames(String scriptDirectoryName) {
        try (Stream<Path> paths = Files.list(
                Paths.get(Objects.requireNonNull(SqlExecutor.class.getClassLoader().getResource(scriptDirectoryName)).toURI()))
        ) {
            return paths.filter(path ->
                            path.toString().endsWith(".sql"))
                    .sorted(Comparator.naturalOrder())
                    .map(path -> scriptDirectoryName + "/" + path.getFileName())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void executeAllSqlScripts(Connection connection, String scriptDirectoryName) {
        List<String> sqlFiles = getOrderedScriptFileNames(scriptDirectoryName);
        for (String script : sqlFiles) {
            executeSqlScript(connection, script);
        }
    }

    static public String[] getSqlStatements(String sqlBatch) {
        return sqlBatch.split(";");
    }

    @SuppressWarnings("SqlSourceToSinkFlow")
    static public void executeSql(Connection connection, String singleStatement) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(singleStatement);
        }
    }

    static public void executeSqlBatch(Connection connection, String batch) {
        String[] statements = getSqlStatements(batch);
        String statement = "";
        try {
            for (int statementIndex = 0; statementIndex < statements.length; statementIndex++) {

                statement = statements[statementIndex];
                if (statement.isEmpty()) {
                    log.debug("Skipping execution of empty statement");
                    continue;
                }
                if (statements.length > 1) {
                    log.debug("Executing {}/{}:\n{}\n", statementIndex, statements.length, statement);
                } else {
                    log.debug("Executing:\n{}\n", statement);
                }
                executeSql(connection, statement);
            }
        } catch (SQLException e) {
            log.error("Error executing:\n{}\n{}", statement, e);
            throw new RuntimeException(e);
        }
    }

    static public void executeSqlScript(Connection connection, String scriptFilename) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                Files.newInputStream(Path.of(scriptFilename)), StandardCharsets.UTF_8))) {
            String sql = reader.lines().collect(Collectors.joining("\n"));
            executeSqlBatch(connection, sql);
        } catch (Exception e) {
            throw new RuntimeException("Error executing script: " + scriptFilename + "\n" + e, e);
        }
    }
}