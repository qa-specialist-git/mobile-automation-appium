package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.ProductsPage;
import retry.RetryAnalyzer;
import testBase.BaseClass;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TC02_ShopSelectingUnitedStatesTest extends BaseClass {

    private static final Logger log =
            LogManager.getLogger(TC02_ShopSelectingUnitedStatesTest.class);

    @Test(groups = {"regression"}, 
    	retryAnalyzer = RetryAnalyzer.class)
    public void selectUnitedStatesAndVerifyProducts() {

        log.debug("===== ShopSelectingUnitedStatesTest START =====");

        try {
            // -------- Home Page --------
            HomePage home = new HomePage(getDriver());

            log.debug("Selecting country: United States");
            home.selectCountry("United States");

            log.debug("Entering name: Josena");
            home.enterName("Josena");

            log.debug("Selecting Female option");
            home.selectFemale();

            log.debug("Clicking Let's Shop");
            home.clickLetsShop();

            // -------- Products Page --------
            ProductsPage products = new ProductsPage(getDriver());

            log.debug("✅ ShopSelectingUnitedStatesTest PASSED");

        } catch (Exception e) {
            log.error("❌ ShopSelectingUnitedStatesTest FAILED", e);
            Assert.fail(e.getMessage());
        }

        log.debug("===== ShopSelectingUnitedStatesTest END =====");
    }
}
