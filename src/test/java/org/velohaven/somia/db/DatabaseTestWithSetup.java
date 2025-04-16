package org.velohaven.somia.db;

import org.velohaven.somia.base.WithResource;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * This class is used to set up the database for tests that require a specific setup.
 */
public abstract class DatabaseTestWithSetup implements WithResource, WithConnection {

    protected DatabaseTestWithSetup() {
        setUp();
    }

    void setUp() {
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

    private String getSetUpResourcePath() {
        return getAbsoluteResourceFolder() + "/" + getBaseName() + ".sql";
    }

}
