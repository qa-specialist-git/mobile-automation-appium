package pageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class WebViewPage {

    private final AppiumDriver driver;

    // ---------- Constructor ----------
    public WebViewPage(AppiumDriver driver) {
        this.driver = driver;
    }

    // ---------- Wait and Switch to WebView ----------
    public void waitAndSwitchToWebView() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        wait.until(d -> {
            Set<String> contexts = ((AndroidDriver) driver).getContextHandles(); // ✅ cast here
            for (String context : contexts) {
                if (context.contains("WEBVIEW")) {
                    ((AndroidDriver) driver).context(context); // ✅ cast here
                    return true;
                }
            }
            return false;
        });
    }
 /// -------- Search inside WebView --------
    public void searchForTerm(AppiumDriver driver, String term) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        WebElement searchBox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.name("q")
)
        );

        searchBox.sendKeys(term);

        // Press ENTER instead of submit()
        ((AndroidDriver) driver).pressKey(
                new KeyEvent(AndroidKey.ENTER)
        );
    }


    // -------- Press Android Back --------
    public void pressBack() {
        if (driver instanceof AndroidDriver) {
        	((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));
        }
    }

    // ---------- Switch back to Native ----------
    public void switchToNativeContext(AppiumDriver driver) {
        ((AndroidDriver) driver).context("NATIVE_APP"); // ✅ cast here
    }
    // ---------- Open App ----------
    public void reactivateApp() {
        ((AndroidDriver) driver)
                .activateApp("com.androidsample.generalstore");
    }

	
}
