package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pageObjects.HomePage;
import pageObjects.ProductsPage;
import pageObjects.CartPage;
import pageObjects.WebViewPage;
import retry.RetryAnalyzer;
import testBase.BaseClass;
import utilities.ScreenshotUtil;

import io.appium.java_client.android.AndroidDriver;

public class TC05_CompletePurchaseTest extends BaseClass {

    private static final Logger log = LogManager.getLogger(TC05_CompletePurchaseTest.class);

    @Test(groups = {"sanity", "regression"},
    		retryAnalyzer = RetryAnalyzer.class)
    public void completePurchaseTest() {

        log.info("===== CompletePurchaseTest START =====");

        try {

            // ---------------- HOME PAGE ----------------
            HomePage home = new HomePage(getDriver());
            home.enterName("Josena");
            home.selectFemale();
            home.clickLetsShop();
            ScreenshotUtil.takeScreenshot(getDriver(), "Home_AfterLetsShop");

            // ---------------- PRODUCTS PAGE ----------------
            ProductsPage products = new ProductsPage(getDriver());
            String productName = "Jordan 6 Rings";
            products.addProductToCart(productName);
            products.openCart();
            ScreenshotUtil.takeScreenshot(getDriver(), "Cart_Opened");

            // ---------------- CART PAGE ----------------
            CartPage cart = new CartPage(getDriver());
            Assert.assertEquals(cart.getProductName(), productName,
                    "❌ Product mismatch in cart");

            cart.acceptTermsAndProceed();
            ScreenshotUtil.takeScreenshot(getDriver(), "Cart_ProceedClicked");

            // ---------------- WEBVIEW ----------------
            WebViewPage webView = new WebViewPage(getDriver());

            log.info("Switching to WEBVIEW context");
            webView.waitAndSwitchToWebView();

            log.info("Searching term in WebView");
            webView.searchForTerm(getDriver(), "CloudBerry QA Bootcamp");
            ScreenshotUtil.takeScreenshot(getDriver(), "WebView_SearchDone");

            log.info("Pressing back to exit WebView");
            webView.pressBack();

            log.info("Switching back to NATIVE_APP context");
            webView.switchToNativeContext(getDriver());

            // Ensure app is active again
            ((AndroidDriver) getDriver())
            .activateApp("com.androidsample.generalstore");

            ScreenshotUtil.takeScreenshot(getDriver(), "App_Reactivated");

            // ---------------- VERIFY HOME PAGE ----------------
            home = new HomePage(getDriver()); // reinitialize to avoid stale elements

            log.debug("Fetching Home Page title after returning to Native context");
            String homePageTitle = home.getPageTitle();
            log.debug("Home Page Title: {}", homePageTitle);

            log.info("Validating Home Page Title");
            Assert.assertEquals(homePageTitle, "General Store");

            log.info("Assertion Passed: Successfully completed purchase flow");
            ScreenshotUtil.takeScreenshot(getDriver(), "Test_Passed");

            log.info("✅ CompletePurchaseTest PASSED");

        } catch (Exception e) {

            ScreenshotUtil.takeScreenshot(getDriver(), "CompletePurchaseTest_Failure");
            log.error("❌ CompletePurchaseTest FAILED", e);
            Assert.fail(e.getMessage());
        }

        log.info("===== CompletePurchaseTest END =====");
    }
}
