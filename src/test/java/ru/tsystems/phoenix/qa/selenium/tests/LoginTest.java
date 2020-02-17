package ru.tsystems.phoenix.qa.selenium.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import ru.tsystems.phoenix.qa.selenium.AbstractBaseSeleniumTest;
import ru.tsystems.phoenix.qa.selenium.Locators;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class LoginTest extends AbstractBaseSeleniumTest {
    public static final String START_PAGE_URL = "https://intra.t-systems.ru/Dash";
    public static final String USERNAME = System.getenv("TEST_USERNAME");
    public static final String PASSWORD = System.getenv("TEST_PASSWORD");

    @Test
    public void testLogin() {
        webDriver.get(START_PAGE_URL);
        var title = webDriver.findElement(By.cssSelector("h3")).getText();
        assertThat("Intra Login page is not open", title, is("Добро пожаловать в Intra"));

        var userNameInput = webDriver.findElement(Locators.USERNAME_INPUT_LOCATOR);
        userNameInput.sendKeys(USERNAME);

        var passwordInput = webDriver.findElement(Locators.PASSWORD_INPUT_LOCATOR);
        passwordInput.sendKeys(PASSWORD);

        var loginButton = webDriver.findElement(Locators.LOGIN_BUTTON_LOCATOR);
        loginButton.click();

        var currentUrl = webDriver.getCurrentUrl();
        assertThat("Redirect or login is not working properly", currentUrl, is(START_PAGE_URL));

        //"div[data-name='pryaniky/virtcurrency'] span[data-bind*='mythanksCount']"
        var wait = new WebDriverWait(webDriver, SECONDS.toSeconds(60), SECONDS.toMillis(1));
        WebElement megabyteTextBlock = wait.until(presenceOfElementLocated(Locators.MEGABYTE_TEXT_LOCATOR));
        var megabyte = Integer.parseInt(megabyteTextBlock.getText());
        assertThat(megabyte, is(greaterThan(100)));

    }
}
