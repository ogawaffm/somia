package org.velohaven.somia.base;

public interface CompareTest<T> {

    T getExpectedResult();

    T getActualResult();

    void compare();

}
