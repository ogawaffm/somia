package org.velohaven.somia.db;

import org.junit.jupiter.api.Assertions;
import org.opentest4j.AssertionFailedError;
import org.velohaven.somia.model.JsonTester;

public interface JsonCompare extends TestCompare<String> {

    @Override
    default void compare() {
        String expected = getExpectedResult();
        String actual = getActualResult();

        try {

            if (expected == null && actual == null) {
                return;
            } else if (expected == null) {
                Assertions.fail(getClass().getSimpleName() + ": Expected result is null but actual is not");
            } else if (actual == null) {
                Assertions.fail(getClass().getSimpleName() + ": Actual result is null but expected is not");
            }

            if (expected.trim().isEmpty() && actual.trim().isEmpty()) {
                return;
            } else if (expected.trim().isEmpty()) {
                Assertions.fail(getClass().getSimpleName() + ": Expected result is empty but actual is not");
            } else if (actual.trim().isEmpty()) {
                Assertions.fail(getClass().getSimpleName() + ": Actual result is empty but expected is not");
            }
        } catch (AssertionFailedError assertionFailedError) {
            System.out.println("Actual result of " + getClass().getSimpleName() + ":");
            System.out.println(actual);
            throw assertionFailedError;
        }

        JsonTester tester = new JsonTester();
        tester.loadJson(expected);
        tester.assertEquals(actual);

    }

}
