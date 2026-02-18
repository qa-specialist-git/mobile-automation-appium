package testBase;

import java.io.File;
import java.net.URI;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class BaseClass {

    private static final Logger log = LogManager.getLogger(BaseClass.class);
    private static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

    // ---------- Driver Getter ----------
    public static AppiumDriver getDriver() {
        return driver.get();
    }

    // ---------- Check if driver initialized ----------
    public static boolean isDriverInitialized() {
        return driver.get() != null;
    }

    // ---------- BeforeMethod: Initialize Driver ----------
    @BeforeMethod
    @org.testng.annotations.Parameters({ "deviceName", "udid", "systemPort" })
    public void openApp(String deviceName, String udid, String systemPort) throws Exception {
        try {

            String appPath = "C:/Users/ACER/eclipse-workspace/MobileV01/src/test/resources/General-Store.apk";
            String serverURL = "http://127.0.0.1:4723";
            String chromeDriverPath = "C:\\Users\\ACER\\eclipse-workspace\\MobileV01\\src\\test\\resources\\chromedriver-win64\\chromedriver.exe";

            File apkFile = new File(appPath);
            if (!apkFile.exists())
                throw new RuntimeException("APK not found: " + appPath);

            UiAutomator2Options options = new UiAutomator2Options();

            options.setDeviceName(deviceName);
            options.setUdid(udid);
            options.setSystemPort(Integer.parseInt(systemPort)); // VERY IMPORTANT
            options.setApp(appPath);
            options.setAutomationName("UiAutomator2");
            options.setChromedriverExecutable(chromeDriverPath);
            options.setCapability("autoGrantPermissions", true);
            options.setCapability("disableWindowAnimation", true);

            driver.set(new AndroidDriver(
                    new URI(serverURL).toURL(), options));

            getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            log.info("✅ Driver initialized successfully on device: " + deviceName);

        } catch (Exception e) {
            log.error("❌ Driver initialization failed", e);
            throw e;
        }
    }

    // ---------- AfterMethod: Quit Driver ----------
    @AfterMethod(alwaysRun = true)
    public void closeApp() {
        try {
            if (getDriver() != null) {
                getDriver().quit();
                log.info("Driver quit successfully");
            }
        } catch (Exception e) {
            log.error("Error during teardown", e);
        } finally {
            driver.remove();
        }
    }
}
