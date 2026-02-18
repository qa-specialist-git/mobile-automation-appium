package utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import testBase.BaseClass;

public class TestListener implements ITestListener {

    private static ExtentReports extent = ExtentReportManager.getExtentReports();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
        test.get().log(Status.INFO, "Test started: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().log(Status.FAIL, result.getThrowable());

        try {
            if (BaseClass.isDriverInitialized()) {
                String screenshotPath = captureScreenshot(result.getMethod().getMethodName());
                test.get().addScreenCaptureFromPath(screenshotPath);
            } else {
                test.get().log(Status.WARNING, "Driver not initialized, skipping screenshot");
            }
        } catch (Exception e) {
            test.get().log(Status.WARNING, "Screenshot failed: " + e.getMessage());
        }
    }
    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onStart(ITestContext context) {
        // nothing
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    // ---------------- Screenshot Utility ----------------
    private String captureScreenshot(String testName) throws IOException {
        // ✅ Use safe driver check again
        if (!BaseClass.isDriverInitialized()) {
            throw new IllegalStateException("Driver not initialized, cannot take screenshot");
        }

        File src = ((TakesScreenshot) BaseClass.getDriver()).getScreenshotAs(OutputType.FILE);
        String dest = "test-output/screenshots/" + testName + "_" + System.currentTimeMillis() + ".png";
        File target = new File(dest);
        target.getParentFile().mkdirs(); // ensure directories exist
        Files.copy(src.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
        return dest;
    }
}
