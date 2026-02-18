package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import retry.RetryAnalyzer;
import testBase.BaseClass;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TC01_LaunchAppTest extends BaseClass {

    private static final Logger log = LogManager.getLogger(TC01_LaunchAppTest.class);

    @Test(groups = {"sanity", "regression"},
    		retryAnalyzer = RetryAnalyzer.class)
    public void launchAppTest() {
        log.debug("===== LaunchAppTest START =====");

        try {
            HomePage home = new HomePage(getDriver());
            Assert.assertTrue(home.isNameFieldDisplayed(), "Home page not displayed");

            log.debug("LaunchAppTest PASSED");

        } catch (Exception e) {
            log.error("LaunchAppTest FAILED", e);
            Assert.fail(e.getMessage());
        }

        log.debug("===== LaunchAppTest END =====");
    }
}
