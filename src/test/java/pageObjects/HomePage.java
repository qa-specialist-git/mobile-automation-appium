package pageObjects;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private final AppiumDriver driver;

    // ---------- Constructor ----------
    public HomePage(AppiumDriver driver) {
        this.driver = driver;
    }

    // ---------- Locators ----------
    private final By nameField = AppiumBy.id("com.androidsample.generalstore:id/nameField");
    private final By femaleRadio = AppiumBy.id("com.androidsample.generalstore:id/radioFemale");
    private final By maleRadio = AppiumBy.id("com.androidsample.generalstore:id/radioMale");
    private final By letsShopBtn = AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop");
    private final By countryDropdown = AppiumBy.id("com.androidsample.generalstore:id/spinnerCountry");
    private final By pageTitle = AppiumBy.id("com.androidsample.generalstore:id/toolbar_title");

    // ---------- Actions ----------
    public void enterName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    public void selectFemale() {
        driver.findElement(femaleRadio).click();
    }

    public void selectMale() {
        driver.findElement(maleRadio).click();
    }

    public void clickLetsShop() {
        driver.findElement(letsShopBtn).click();
    }

    public void selectCountry(String countryName) {
        driver.findElement(countryDropdown).click();
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                ".scrollTextIntoView(\"" + countryName + "\")"
        )).click();
    }

    public boolean isNameFieldDisplayed() {
        return driver.findElement(nameField).isDisplayed();
    }

    public boolean waitForHomePage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(nameField)
        ).isDisplayed();
    }

    // ---------- NEW METHOD ----------
    public String getPageTitle() {
        return driver.findElement(pageTitle).getText();
    }
}
