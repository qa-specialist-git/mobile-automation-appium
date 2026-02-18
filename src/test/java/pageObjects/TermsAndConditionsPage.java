package pageObjects;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
//import java.util.ImmutableMap;
import java.util.Collections;

public class TermsAndConditionsPage {

    private final AppiumDriver driver;

    // ---------- Locators ----------
    private final By termsButton = AppiumBy.id("com.androidsample.generalstore:id/termsButton");
    private final By popupTitle = AppiumBy.id("com.androidsample.generalstore:id/alertTitle");
    private final By okButton = AppiumBy.id("android:id/button1");

    // ---------- Constructor ----------
    public TermsAndConditionsPage(AppiumDriver driver) {
        this.driver = driver;
    }

    /** Long press to open Terms popup */
    public void openTermsPopup() {
        WebElement element = driver.findElement(termsButton);

        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
                java.util.Map.of(
                        "elementId", ((RemoteWebElement) element).getId(),
                        "duration", 2000
                ));
    }

    /** Wait and verify popup displayed */
    public boolean isPopupDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(popupTitle));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /** Get popup title text */
    public String getPopupTitle() {
        return driver.findElement(popupTitle).getText();
    }

    /** Close the popup safely */
    public void closePopup() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(okButton));
        btn.click();
    }
}
