package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.ProductsPage;
import retry.RetryAnalyzer;
import testBase.BaseClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TC03_LetsShopTest extends BaseClass {

    private static final Logger log = LogManager.getLogger(TC03_LetsShopTest.class);

    @Test(groups = {"sanity", "regression"},
    		retryAnalyzer = RetryAnalyzer.class)
    public void letsShopTest() {
        log.debug("===== LetsShopTest START =====");
        try {
            HomePage home = new HomePage(getDriver());
            home.enterName("Josena");
            home.selectFemale();
            home.clickLetsShop();

            ProductsPage products = new ProductsPage(getDriver());
            Assert.assertTrue(products.isProductsPageDisplayed(), "Products page not displayed");

            log.debug("LetsShopTest PASSED");
        } catch (Exception e) {
            log.error("LetsShopTest FAILED", e);
            Assert.fail(e.getMessage());
        }
        log.debug("===== LetsShopTest END =====");
    }
}
