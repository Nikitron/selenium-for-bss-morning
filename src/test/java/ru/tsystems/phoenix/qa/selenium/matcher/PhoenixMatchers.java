package ru.tsystems.phoenix.qa.selenium.matcher;

import org.hamcrest.Matcher;
import org.openqa.selenium.WebElement;

import static org.hamcrest.Matchers.is;

public class PhoenixMatchers {

    public static Matcher<WebElement> hasCss(String cssName, String expectedValue) {
        return new HasCssMatcher(cssName, is(expectedValue));
    }

    public static Matcher<WebElement> hasCss(String cssName, Matcher<String> matcher) {
        return new HasCssMatcher(cssName, matcher);
    }
}
