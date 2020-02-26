package ru.tsystems.phoenix.qa.selenium.allure;

import io.qameta.allure.Attachment;
import io.qameta.allure.listener.StepLifecycleListener;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ru.tsystems.phoenix.qa.selenium.AbstractBaseSeleniumTest;

public class StepLogger implements StepLifecycleListener {
    @Override
    public void beforeStepStop(io.qameta.allure.model.StepResult result) {
        byte[] bytes = ((TakesScreenshot) AbstractBaseSeleniumTest.webDriver.get()).getScreenshotAs(OutputType.BYTES);
        saveScreenshot(bytes);
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshot(byte[] screenShot) {
        return screenShot;
    }
}
