package pageObjects;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ToastMessagePage {

    private final AndroidDriver driver;

    public ToastMessagePage(AndroidDriver driver) {
        if (driver == null) {
            throw new RuntimeException("Driver not initialized! Check BaseClass @BeforeMethod.");
        }
        this.driver = driver;
    }

    /**
     * Capture the toast message text.
     * @return toast text
     */
    public String getToastMessage() {
        // Toast messages in Android are under "android.widget.Toast"
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement toastElement = wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.xpath("//android.widget.Toast[1]")));

        return toastElement.getText();
    }
}

