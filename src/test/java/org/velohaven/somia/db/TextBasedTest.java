package org.velohaven.somia.db;

import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        String s = getSetUpResourcePath();
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

    private String getAbsoluteResourceFolder() {
        try {
            Path resourcePath = Paths.get(
                    Objects.requireNonNull(
                            TextBasedTest.class.getClassLoader().getResource(getRelativeResourceFolder())
                    ).toURI()
            );
            return resourcePath.toAbsolutePath().toString();
        } catch (Exception exception) {
            throw new RuntimeException("Failed to get resource folder", exception);
        }
    }

    abstract public String getRelativeResourceFolder();

    public String getBaseName() {
        String className = this.getClass().getSimpleName();
        return className.substring(0, className.length() - CLASS_NAME_SUFFIX.length());
    }

    public String getSetUpResourcePath() {
        return getAbsoluteResourceFolder() + "/" + getBaseName() + ".sql";
    }

    abstract public String getResultFilenameExtension();

    private String getResultFilename(String type) {
        String filename = getAbsoluteResourceFolder() + "/" + getBaseName() + "." + type;
        if (!getResultFilenameExtension().isEmpty()) {
            filename += "." + getResultFilenameExtension();
        }
        return filename;
    }

    public String getExpectedResultFilename() {
        return getResultFilename("expected");
    }

    public String getActualResultFilename() {
        return getResultFilename("actual");
    }

    public String getActualResult() {
        return readResult(getActualResultFilename());
    }

    public String getExpectedResult() {
        return readResult(getExpectedResultFilename());
    }


    public String readResult(String filename) {
        try {
            return Files.readString(Path.of(filename), StandardCharsets.UTF_8);
        } catch (IOException ioException) {
            Assertions.fail(ioException);
        }
        // unreachable
        return null;
    }

    public void saveResult(String filename, String result) {
        try {
            Files.writeString(Path.of(filename), result, StandardCharsets.UTF_8);
        } catch (IOException ioException) {
            Assertions.fail(ioException);
        }
    }

}
