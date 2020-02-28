package ru.tsystems.phoenix.qa.selenium;

import io.qameta.atlas.core.Atlas;
import io.qameta.atlas.webdriver.WebDriverConfiguration;
import lombok.val;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import ru.tsystems.phoenix.qa.selenium.atlas.CheckMethodExtension;
import ru.tsystems.phoenix.qa.selenium.page.DashboardPage;
import ru.tsystems.phoenix.qa.selenium.page.LoginPage;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

public abstract class AbstractBaseSeleniumTest {
    public static ThreadLocal<WebDriver> webDriver = ThreadLocal.withInitial(() -> null);
    protected Atlas atlas;

    private static final URL HUB_URL;
    protected SoftAssert softAssert = new SoftAssert();

    // region test configuration

    static {
        try {
            HUB_URL = new URL("http://localhost:4444/wd/hub");
        } catch (MalformedURLException e) {
            throw new RuntimeException("Incorrect Selenium Hub URL", e);
        }
    }

    protected WebDriver getDriver() {
        return webDriver.get();
    }

    @BeforeMethod
    public void initWebDriver() {
        var chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized");
        webDriver.set(new RemoteWebDriver(HUB_URL, chromeOptions));
        atlas = new Atlas(new WebDriverConfiguration(getDriver()));
        atlas.extension(new CheckMethodExtension());
        getDriver().manage().timeouts().implicitlyWait(20, SECONDS);
        getDriver().manage().timeouts().pageLoadTimeout(60, SECONDS);
    }

    @AfterMethod
    public void closeWebDriver() {
        if (getDriver() != null) {
            getDriver().quit();
        }
        for (val errorMessage : CheckMethodExtension.errorsList.get()) {
            softAssert.assertTrue(false, errorMessage);
        }
        softAssert.assertAll();
    }

    protected void pause() {
        try {
            Thread.sleep(SECONDS.toMillis(5));
        } catch (InterruptedException ignored) {
        }
    }

    // endregion test configuration

    // region pages

    protected LoginPage onLoginPage() {
        return atlas.create(getDriver(), LoginPage.class);
    }

    protected DashboardPage onDashPage() {
        return atlas.create(getDriver(), DashboardPage.class);
    }

    // endregion pages
}
