package ru.tsystems.phoenix.qa.selenium.page;

import io.qameta.atlas.webdriver.AtlasWebElement;
import io.qameta.atlas.webdriver.WebPage;
import io.qameta.atlas.webdriver.extension.FindBy;
import io.qameta.atlas.webdriver.extension.Name;
import io.qameta.atlas.webdriver.extension.Selector;

public interface LoginPage extends WebPage {
    @FindBy(value = "h3", selector = Selector.CSS)
    AtlasWebElement title();

    @Name("Username field on Login page")
    @FindBy(value = "form#loginform input#UserName", selector = Selector.CSS)
    AtlasWebElement usernameInput();

    @FindBy(value = "form#loginform input[type=password]", selector = Selector.CSS)
    AtlasWebElement passwordInput();

    @FindBy(value = "form#loginform input[type=submit]", selector = Selector.CSS)
    AtlasWebElement loginButton();
}
