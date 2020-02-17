package ru.tsystems.phoenix.qa.selenium.page;

import io.qameta.atlas.webdriver.AtlasWebElement;
import io.qameta.atlas.webdriver.WebPage;
import io.qameta.atlas.webdriver.extension.FindBy;
import io.qameta.atlas.webdriver.extension.Selector;

public interface DashboardPage extends WebPage {
    @FindBy(value = "div[data-name='pryaniky/virtcurrency'] span[data-bind*='mythanksCount']", selector = Selector.CSS)
    AtlasWebElement megabyteText();
}
