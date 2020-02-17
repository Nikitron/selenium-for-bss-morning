package ru.tsystems.phoenix.qa.selenium.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import ru.tsystems.phoenix.qa.selenium.AbstractBaseSeleniumTest;
import ru.tsystems.phoenix.qa.selenium.Locators;
import ru.tsystems.phoenix.qa.selenium.page.BadLoginPage;
import ru.yandex.qatools.htmlelements.matchers.WebElementMatchers;

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

        var title = onLoginPage().title().getText();
        assertThat("Intra Login page is not open", title, is("Добро пожаловать в Intra"));

        onLoginPage().usernameInput()
                .should("Username is nof found on Login Page", WebElementMatchers.exists())
                .sendKeys(USERNAME);
        onLoginPage().passwordInput().should(WebElementMatchers.exists()).sendKeys(PASSWORD);

        onLoginPage().loginButton().should(WebElementMatchers.isEnabled()).click();

        var currentUrl = webDriver.getCurrentUrl();
        assertThat("Redirect or login is not working properly", currentUrl, is(START_PAGE_URL));

        var megabyteText = onDashPage().megabyteText().waitUntil(WebElementMatchers.exists()).getText();

        var megabyte = Integer.parseInt(megabyteText);
        assertThat(megabyte, is(greaterThan(100)));

    }
    @Test
    public void testLoginWithPage() {
        webDriver.get(START_PAGE_URL);

        var loginPage = PageFactory.initElements(webDriver, BadLoginPage.class);
        var title = loginPage.getTitleTextBlock().getText();
        assertThat("Intra Login page is not open", title, is("Добро пожаловать в Intra"));

        loginPage.login(USERNAME, PASSWORD);

        pause();

        var currentUrl = webDriver.getCurrentUrl();
        assertThat("Redirect or login is not working properly", currentUrl, is(START_PAGE_URL));

        //"div[data-name='pryaniky/virtcurrency'] span[data-bind*='mythanksCount']"
        var wait = new WebDriverWait(webDriver, SECONDS.toSeconds(60), SECONDS.toMillis(1));
        WebElement megabyteTextBlock = wait.until(presenceOfElementLocated(Locators.MEGABYTE_TEXT_LOCATOR));
        var megabyte = Integer.parseInt(megabyteTextBlock.getText());
        assertThat(megabyte, is(greaterThan(100)));
    }
}
