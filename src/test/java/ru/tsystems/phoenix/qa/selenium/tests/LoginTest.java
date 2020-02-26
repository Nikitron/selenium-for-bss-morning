package ru.tsystems.phoenix.qa.selenium.tests;

import io.qameta.allure.Step;
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
    public void testLoginWithPage() {
        openDashboardPage();

        var loginPage = PageFactory.initElements(getDriver(), BadLoginPage.class);
        var title = loginPage.getTitleTextBlock().getText();
        assertThat("Intra Login page is not open", title, is("Добро пожаловать в Intra"));

        loginPage.login(USERNAME, PASSWORD);

        pause();

        checkRedirectToDasboardPage();

        //"div[data-name='pryaniky/virtcurrency'] span[data-bind*='mythanksCount']"
        var wait = new WebDriverWait(getDriver(), SECONDS.toSeconds(60), SECONDS.toMillis(1));
        WebElement megabyteTextBlock = wait.until(presenceOfElementLocated(Locators.MEGABYTE_TEXT_LOCATOR));
        var megabyte = Integer.parseInt(megabyteTextBlock.getText());
        assertThat(megabyte, is(greaterThan(100)));
    }

    @Test
    public void testLoginZZZZZZZZZZZZZZZZZZZ() {
        openDashboardPage();

        pause();
        checkLoginPageIsOpen();

        fillLoginForm(USERNAME);
        clickOnLoginButton();

        checkRedirectToDasboardPage();

        checkMegabyteCountIsGreatThan(100);

    }

    @Step("dkjfkdjfkjdlf")
    private void checkMegabyteCountIsGreatThan(int minimalExpected) {
        var megabyteText = onDashPage().megabyteText().waitUntil(WebElementMatchers.exists()).getText();
        var megabyte = Integer.parseInt(megabyteText);
        assertThat(megabyte, is(greaterThan(minimalExpected)));
    }

    @Step
    private void checkRedirectToDasboardPage() {
        var currentUrl = getDriver().getCurrentUrl();
        assertThat("Redirect or login is not working properly", currentUrl, is(START_PAGE_URL));
    }

    @Step
    private void clickOnLoginButton() {
        onLoginPage().loginButton().should(WebElementMatchers.isEnabled()).click();
    }

    @Step("Fill login form with username = {username} and some password")
    private void fillLoginForm(String username) {
        onLoginPage().usernameInput().waitUntil(WebElementMatchers.exists()).sendKeys(username);
        onLoginPage().passwordInput().should(WebElementMatchers.exists()).sendKeys(PASSWORD);
    }

    @Step
    private void checkLoginPageIsOpen() {
        var title = onLoginPage().title().getText();
        assertThat("Intra Login page is not open", title, is("Добро пожаловать в Intra"));
    }

    @Step
    private void openDashboardPage() {
        getDriver().get(START_PAGE_URL);
    }
}
