package org.velohaven.somia.base;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public interface WithResource {

    String TEST_CLASS_NAME_SUFFIX = "_Test";

    /**
     * The basename is the name of the class without the suffix "_Test". It is used to determine the name of the
     * expected result file and the actual result file and the setup file. This method is used to get the basename.
     *
     * @return The basename of the class.
     */
    default String getBaseName() {
        String className = this.getClass().getSimpleName();
        return className.substring(0, className.length() - TEST_CLASS_NAME_SUFFIX.length());
    }

    /**
     * Get the relative path to the resource folder of the test. The resource folder is the folder the actual results
     * is written to and expected results and setup files are copied to by maven.
     *
     * @return The relative path to the resource folder.
     */
    private Path getRelativeResourceFolder() {
        // get the path directly below the "java" folder
        String relativeClassFilename = getClass().getCanonicalName().replace(".", "/");
        // shorten the name by removing the prefix
        relativeClassFilename = relativeClassFilename.substring("org.velohaven.somia.".length());
        // the folder is the parent of the class file
        Path resourceFolder = Path.of(relativeClassFilename).getParent();
        return Path.of(relativeClassFilename).getParent();
    }

    /**
     * Get the absolute path to the resource folder of the test. The resource folder is the folder the actual results
     * is written to and expected results is copied to by maven.
     * E.g. for the test class in my_Test in test/java/org.velohaven.somia.db.h2, the resource folder is
     * target/test-classes/db/h2
     *
     * @return The absolute path to the resource folder.
     */
    default Path getAbsoluteResourceFolder() {
        try {
            Path resourcePath = Paths.get(
                    Objects.requireNonNull(
                            this.getClass().getClassLoader().getResource("")
                    ).toURI()
            );
            return resourcePath.resolve(getRelativeResourceFolder());
        } catch (Exception exception) {
            throw new RuntimeException("Failed to get resource folder " + getRelativeResourceFolder(), exception);
        }
    }

}
