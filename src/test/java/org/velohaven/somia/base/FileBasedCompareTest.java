package org.velohaven.somia.base;

import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public interface FileBasedCompareTest extends CompareTest<String>, WithResource {

    String getResultFilenameExtension();

    /**
     * Get the expected result filename. This is used to compare the actual result with the expected result.
     * The expected result file is copied from the resource folder to the target resource folder by maven.
     *
     * @return expected result filename
     */
    default Path getExpectedResultFilename() {
        return getResultFilename("expected");
    }

    /**
     * Get the actual result filename. This is used to save the actual result to a file for later analysis.
     *
     * @return The actual result filename.
     */
    default Path getActualResultFilename() {
        return getResultFilename("actual");
    }

    /**
     * Get the expected result from the expected result file. This is used to compare the actual result with the
     * expected result.
     *
     * @return The expected result of the test.
     */
    default String getExpectedResult() {
        return readFromFile(getExpectedResultFilename());
    }

    /**
     * Delete the existing actual result file if it exists. This is used to delete the actual result file before
     * writing a new one to avoid mixing the old and new results if the test breaks before the actual result is written.
     */
    default void deleteExistingActualResultFile() {
        try {
            Files.deleteIfExists(getActualResultFilename());
        } catch (IOException ioException) {
            Assertions.fail(ioException);
        }
    }

    /**
     * Save the actual result to the actual result file. This is used to analyze the actual result in case of a test
     * failure.
     *
     * @param actualResult The actual result to save.
     */
    default void saveActualResultToFile(String actualResult) {
        saveToFile(getActualResultFilename(), actualResult);
    }

    /* ************************************************************************************************************* */
    /* ********************************************** private methods ********************************************** */
    /* ************************************************************************************************************* */

    private void saveToFile(Path filename, String content) {
        try {
            Files.writeString(filename, content, StandardCharsets.UTF_8);
        } catch (IOException ioException) {
            Assertions.fail(ioException);
        }
    }

    /**
     * Read the content of a file. This is used to read the expected result file and the actual result file.
     *
     * @param filename The name of the file to read.
     * @return The content of the file.
     */

    private String readFromFile(Path filename) {
        try {
            return Files.readString(filename, StandardCharsets.UTF_8);
        } catch (IOException ioException) {
            Assertions.fail(ioException);
        }
        // unreachable
        return null;
    }

    /**
     * Get the result filename. This is used to get the expected result filename and the actual result filename.
     *
     * @param type The type of the result file. E.g. "expected" or "actual".
     * @return The result filename.
     */
    private Path getResultFilename(String type) {
        String filename = getBaseName() + "." + type;
        if (!getResultFilenameExtension().isEmpty()) {
            filename += "." + getResultFilenameExtension();
        }
        return getAbsoluteResourceFolder().resolve(Path.of(filename));
    }

}
