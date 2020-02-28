package ru.tsystems.phoenix.qa.selenium.page;

import io.qameta.atlas.webdriver.WebPage;
import io.qameta.atlas.webdriver.extension.FindBy;
import io.qameta.atlas.webdriver.extension.Name;
import io.qameta.atlas.webdriver.extension.Selector;
import ru.tsystems.phoenix.qa.selenium.atlas.PhoenixElement;

public interface LoginPage extends WebPage {
    @FindBy(value = "h3", selector = Selector.CSS)
    PhoenixElement title();

    @Name("Username field on Login page")
    @FindBy(value = "form#loginform input#UserName", selector = Selector.CSS)
    PhoenixElement usernameInput();

    @FindBy(value = "form#loginform input[type=password]", selector = Selector.CSS)
    PhoenixElement passwordInput();

    @FindBy(value = "form#loginform input[type=submit]", selector = Selector.CSS)
    PhoenixElement loginButton();
}
