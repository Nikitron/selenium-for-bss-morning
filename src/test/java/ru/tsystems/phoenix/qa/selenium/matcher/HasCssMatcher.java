package ru.tsystems.phoenix.qa.selenium.matcher;

import lombok.val;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.openqa.selenium.WebElement;

public class HasCssMatcher extends TypeSafeMatcher<WebElement> {
    private String cssName;
    private Matcher matcher;

    public HasCssMatcher(String cssName, Matcher<String> matcher) {
        this.cssName = cssName;
        this.matcher = matcher;
    }

    @Override
    protected boolean matchesSafely(WebElement element) {
        val cssValue = element.getCssValue(cssName);
        return matcher.matches(cssValue);
    }

    public void describeTo(Description description) {
        description.appendText("css ").
                appendValue(cssName).
                appendText(" ").
                appendDescriptionOf(matcher);
    }

    @Override
    protected void describeMismatchSafely(WebElement item, Description mismatchDescription) {
        mismatchDescription.appendText("css ").
                appendValue(cssName).
                appendText(" of element ").
                appendValue(item).
                appendText(" was ").
                appendValue(item.getAttribute(cssName));
    }
}
