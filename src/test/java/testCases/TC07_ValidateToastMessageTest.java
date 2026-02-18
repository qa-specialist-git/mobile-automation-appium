package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pageObjects.HomePage;
import pageObjects.ProductsPage;
import pageObjects.ToastMessagePage;
import retry.RetryAnalyzer;
import testBase.BaseClass;

public class TC07_ValidateToastMessageTest extends BaseClass {

    private static final Logger log = LogManager.getLogger(TC07_ValidateToastMessageTest.class);

    @Test(groups = {"regression"},
    		retryAnalyzer = RetryAnalyzer.class)
    public void validateToastMessageTest() {

        log.debug("===== ValidateToastMessageTest START =====");

        try {
            // -------- Home Page --------
            HomePage home = new HomePage(getDriver());
            home.enterName(""); // leave name blank to trigger toast
            home.selectFemale();
            home.clickLetsShop();

            // -------- Capture Toast --------
            ToastMessagePage toast = new ToastMessagePage((io.appium.java_client.android.AndroidDriver) getDriver());
            String actualToast = toast.getToastMessage();

            log.debug("Captured toast message: {}", actualToast);

            Assert.assertEquals(actualToast, "Please enter your name", "Toast message did not match");

            log.debug("ValidateToastMessageTest PASSED");

        } catch (Exception e) {
            log.error("ValidateToastMessageTest FAILED", e);
            Assert.fail(e.getMessage());
        }

        log.debug("===== ValidateToastMessageTest END =====");
    }
}
