package pageObjects;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductsPage {

    private final AppiumDriver driver;

    private final By toolbarTitle = AppiumBy.id("com.androidsample.generalstore:id/toolbar_title");
    private final By productNameLocator = AppiumBy.id("com.androidsample.generalstore:id/productName");
    private final By addToCartLocator = AppiumBy.id("com.androidsample.generalstore:id/productAddCart");
    private final By cartBtn = AppiumBy.id("com.androidsample.generalstore:id/appbar_btn_cart");

    public ProductsPage(AppiumDriver driver) {
        this.driver = driver;
    }

    // --- Scroll and add product to cart ---
    public void addProductToCart(String productName) {
        // Scroll to the product
        driver.findElement(AppiumBy.androidUIAutomator(
            "new UiScrollable(new UiSelector().scrollable(true))" +
            ".scrollIntoView(new UiSelector().text(\"" + productName + "\"));"));

        // Get all visible products
        List<WebElement> products = driver.findElements(productNameLocator);

        // Click the corresponding Add button
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getText().equalsIgnoreCase(productName)) {
                driver.findElements(addToCartLocator).get(i).click();
                break; // Stop after adding the correct product
            }
        }
    }

    // --- Open the cart ---
    public void openCart() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(
                AppiumBy.id("com.androidsample.generalstore:id/appbar_btn_cart")))
            .click();

        // Wait for cart page
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.id("com.androidsample.generalstore:id/productName")));
    }


    // --- Get page title ---
    public String getProductsTitle() {
        return driver.findElement(toolbarTitle).getText();
    }

    // --- Check if Products Page is displayed ---
    public boolean isProductsPageDisplayed() {
        return !driver.findElements(cartBtn).isEmpty();
    }
}
