package ru.tsystems.phoenix.qa.selenium;

import org.openqa.selenium.By;

public class Locators {
    // login page
    public static final By USERNAME_INPUT_LOCATOR = By.cssSelector("form#loginform input#UserName");
    public static final By PASSWORD_INPUT_LOCATOR = By.cssSelector("form#loginform input[type=password]");
    public static final By LOGIN_BUTTON_LOCATOR = By.cssSelector("form#loginform input[type=submit]");

    // dashboard page
    public static final By MEGABYTE_TEXT_LOCATOR = By.cssSelector("div[data-name='pryaniky/virtcurrency'] span[data-bind*='mythanksCount']");

}
