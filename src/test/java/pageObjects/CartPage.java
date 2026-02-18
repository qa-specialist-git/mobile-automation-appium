package pageObjects;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.ScreenshotUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;

public class CartPage {

    private final AppiumDriver driver;
    private static final Logger log = LogManager.getLogger(CartPage.class);

    // Product title in cart
    private final By productNameLocator =
            AppiumBy.id("com.androidsample.generalstore:id/productName");

    // ✅ FIXED CHECKBOX ID (new APK version uses "checkbox")
    private final By termsCheckbox =
            AppiumBy.className("android.widget.CheckBox");

    private final By proceedBtn =
            AppiumBy.id("com.androidsample.generalstore:id/btnProceed");

    public CartPage(AppiumDriver driver) {
        this.driver = driver;
    }

    // ------------------ GET PRODUCT NAME ------------------
    public String getProductName() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        log.info("Waiting for product name to be visible in cart");

        WebElement product =
                wait.until(ExpectedConditions.visibilityOfElementLocated(productNameLocator));

        return product.getText();
    }

    // ------------------ ACCEPT TERMS + PROCEED ------------------
    public void acceptTermsAndProceed() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {

            log.info("Waiting for Cart page to load");
            wait.until(ExpectedConditions.visibilityOfElementLocated(productNameLocator));

            log.info("Waiting for Terms checkbox");
            WebElement checkbox =
                    wait.until(ExpectedConditions.visibilityOfElementLocated(termsCheckbox));

            if (!checkbox.isSelected()) {
                log.info("Clicking Terms checkbox");
                checkbox.click();
            }

            ScreenshotUtil.takeScreenshot(driver, "Terms_Checked");

            log.info("Waiting for Proceed button");
            WebElement proceed =
                    wait.until(ExpectedConditions.elementToBeClickable(proceedBtn));

            log.info("Clicking Proceed button");
            proceed.click();

            ScreenshotUtil.takeScreenshot(driver, "Proceed_Clicked");

        } catch (Exception e) {

            ScreenshotUtil.takeScreenshot(driver, "CartPage_Error");
            log.error("❌ Failed in acceptTermsAndProceed()", e);
            throw e;
        }
    }
}
