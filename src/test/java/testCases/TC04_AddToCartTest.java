package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.ProductsPage;
import pageObjects.CartPage;
import retry.RetryAnalyzer;
import testBase.BaseClass;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TC04_AddToCartTest extends BaseClass {

    private static final Logger log = LogManager.getLogger(TC04_AddToCartTest.class);

    @Test(groups = {"sanity", "regression", "datadriven"},
    		retryAnalyzer = RetryAnalyzer.class)
    public void addToCartTest() {
        log.debug("===== AddToCartTest START =====");

        try {
            // ---------- Home Page ----------
            HomePage home = new HomePage(getDriver());
            home.enterName("Josena");
            home.selectFemale();
            home.clickLetsShop();

            // ---------- Products Page ----------
            ProductsPage products = new ProductsPage(getDriver());
            String productName = "Jordan 6 Rings";

            log.debug("Adding product to cart: {}", productName);
            products.addProductToCart(productName);

            // ---------- Cart Page ----------
            products.openCart();
            CartPage cart = new CartPage(getDriver());

            Assert.assertEquals(
                    cart.getProductName(),
                    productName,
                    "❌ Product not added to cart"
            );

            log.debug("✅ AddToCartTest PASSED");

        } catch (Exception e) {
            log.error("❌ AddToCartTest FAILED", e);
            Assert.fail(e.getMessage());
        }

        log.debug("===== AddToCartTest END =====");
    }
}
