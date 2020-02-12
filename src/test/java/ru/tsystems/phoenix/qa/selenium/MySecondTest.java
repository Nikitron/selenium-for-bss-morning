package ru.tsystems.phoenix.qa.selenium;

import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class MySecondTest {
    @Test
    public void testDivisionForInt() {
        int x = 11;
        int result = x / 3;
        assertThat("Div is not working properly", result, is(4));
    }

    @Test
    public void testDivisionForFloat() {
        float x = 11;
        float result = x / 3;
        assertThat("Div is not working properly", result, allOf(greaterThan(3.666f), lessThan(3.667f)));
    }
}
