package com.lambdatest.appium.sample;

import static io.appium.java_client.remote.MobileCapabilityType.APP;
import static io.appium.java_client.remote.MobileCapabilityType.DEVICE_NAME;
import static io.appium.java_client.remote.MobileCapabilityType.PLATFORM_VERSION;
import static java.lang.System.getenv;
import static java.text.MessageFormat.format;
import static org.openqa.selenium.remote.CapabilityType.PLATFORM_NAME;

import com.lambdatest.appium.sample.utils.Swipe;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;

public class BaseTest<D extends AppiumDriver<MobileElement>> {
    protected static final String        LT_KEY  = getenv ("LT_KEY");
    protected static final String        LT_USER = getenv ("LT_USER");
    protected static final String        URL     = "https://{0}:{1}@mobile-hub.lambdatest.com/wd/hub";
    protected              D             driver;
    protected              Swipe<D>      swipe;
    protected              WebDriverWait wait;

    @AfterTest
    public void tearDown (final ITestContext context) {
        final var status = (context.getFailedTests ()
            .size () > 0) ? "failed" : "passed";
        this.driver.executeScript (format ("lambda-status={0}", status));
        this.driver.quit ();
    }

    protected Capabilities getIOSOptions () {
        final DesiredCapabilities capabilities = new DesiredCapabilities ();
        capabilities.setCapability (PLATFORM_NAME, "iOS");
        capabilities.setCapability (PLATFORM_VERSION, "15");
        capabilities.setCapability (DEVICE_NAME, "iPhone 13 Pro");
        capabilities.setCapability (APP, getenv ("LT_APP_IOS"));
        capabilities.setCapability ("build", "TestNG iOS Sample Build");
        capabilities.setCapability ("name", "iOS Test Case");
        capabilities.setCapability ("console", true);
        capabilities.setCapability ("network", true);
        capabilities.setCapability ("visual", true);
        capabilities.setCapability ("video", true);
        capabilities.setCapability ("terminal", true);
        capabilities.setCapability ("isRealMobile", true);
        return capabilities;
    }
}
