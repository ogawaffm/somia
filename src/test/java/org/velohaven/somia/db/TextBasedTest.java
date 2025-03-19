package org.velohaven.somia.db;

import org.junit.jupiter.api.Assertions;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.util.Objects;

public abstract class TextBasedTest implements TestCompare<String> {

    static private final String CLASS_NAME_SUFFIX = "_Test";

    public TextBasedTest() {
        super();
        String className = this.getClass().getSimpleName();
        if (!className.endsWith(CLASS_NAME_SUFFIX)) {
            throw new IllegalArgumentException("Class name must end with " + CLASS_NAME_SUFFIX);
        }
        setUp();
    }

    private void setUp() {
        // Are there any setup scripts to execute?
        if (Files.exists(Path.of(getSetUpResourcePath()))) {
            // Yes, there are
            // Is it a directory?
            if (Files.isDirectory(Path.of(getSetUpResourcePath()))) {
                // execute all files in the directory in the order of their names
                SqlExecutor.executeAllSqlScripts(getConnection(), getSetUpResourcePath());
            } else {
                // just a single file
                SqlExecutor.executeSqlScript(getConnection(), getSetUpResourcePath());
            }
        }
    }

    abstract public Connection getConnection();

    abstract public String getResourceFolder();

    public String getBaseName() {
        String className = this.getClass().getSimpleName();
        return className.substring(0, className.length() - CLASS_NAME_SUFFIX.length());
    }

    public String getSetUpResourcePath() {
        return getResourceFolder() + "/" + getBaseName() + ".sql";
    }

    abstract public String getExpectedResultFilenameExtension();

    public String getExpectedResultFilename() {
        String filename = getResourceFolder() + "/" + getBaseName() + ".expected";
        if (!getExpectedResultFilenameExtension().isEmpty()) {
            filename += "." + getExpectedResultFilenameExtension();
        }
        return filename;
    }

    public String getExpectedResult() {
        Path expectedResultPath = null;
        try {
            expectedResultPath = Path.of(
                    Objects.requireNonNull(
                            getClass().getClassLoader().getResource(getExpectedResultFilename())
                    ).toURI()
            );
            return Files.readString(expectedResultPath, StandardCharsets.UTF_8);
        } catch (Exception e) {
            Assertions.fail("Failed to read expected result file " + expectedResultPath, e);
        }
        // unreachable
        return null;
    }
}
