package testCases;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import pageObjects.TermsAndConditionsPage;
import retry.RetryAnalyzer;

public class TC06_TermsAndConditionsTest {

    @Test(groups = {"regression"},
    		retryAnalyzer = RetryAnalyzer.class)
    public void verifyTermsAndConditions() throws MalformedURLException, URISyntaxException, InterruptedException {

        // ---------- Setup ----------
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("emulator-5554");
        options.setApp("C://Users//ACER//eclipse-workspace//AppiumV01//src//test//java//resources//General-Store.apk");

        AndroidDriver driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        try {
            // ---------- Enter Name ----------
            driver.findElement(AppiumBy.androidUIAutomator(
                    "new UiSelector().resourceId(\"com.androidsample.generalstore:id/nameField\")"))
                    .sendKeys("Josena");

            // ---------- Select Gender ----------
            driver.findElement(AppiumBy.androidUIAutomator(
                    "new UiSelector().resourceId(\"com.androidsample.generalstore:id/radioFemale\")"))
                    .click();

            // ---------- Click Let's Shop ----------
            driver.findElement(AppiumBy.androidUIAutomator(
                    "new UiSelector().resourceId(\"com.androidsample.generalstore:id/btnLetsShop\")"))
                    .click();

            // ---------- Scroll & Add Product ----------
            driver.findElement(AppiumBy.androidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true))" +
                            ".scrollTextIntoView(\"Jordan 6 Rings\")"));

            int productCount = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();

            for (int i = 0; i < productCount; i++) {
                String productName = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
                if (productName.equalsIgnoreCase("Jordan 6 Rings")) {
                    driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
                    break;
                }
            }

            // ---------- Open Cart ----------
            driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
            Thread.sleep(1000);

            // ---------- Terms & Conditions ----------
            TermsAndConditionsPage terms = new TermsAndConditionsPage(driver);
            terms.openTermsPopup();

            Assert.assertTrue(terms.isPopupDisplayed(), "❌ Terms popup not displayed");

            String title = terms.getPopupTitle();
            System.out.println("Popup Title: " + title);
            Assert.assertEquals(title, "Terms Of Conditions");

            // ---------- Close Popup ----------
            terms.closePopup();

            System.out.println("✅ Terms popup verified and closed successfully");

        } finally {
            driver.quit();
        }
    }
}
