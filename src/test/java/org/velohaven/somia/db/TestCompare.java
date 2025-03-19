package org.velohaven.somia.db;

public interface TestCompare<T> {

    T getExpectedResult();
    T getActualResult();

    void compare();

}
