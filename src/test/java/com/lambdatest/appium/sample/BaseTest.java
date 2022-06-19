package com.lambdatest.appium.sample;

import java.text.MessageFormat;

import com.lambdatest.appium.sample.utils.Swipe;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;

public class BaseTest<D extends AppiumDriver<MobileElement>> {
    protected static final String        LT_KEY  = System.getenv ("LT_KEY");
    protected static final String        LT_USER = System.getenv ("LT_USER");
    protected static final String        URL     = "https://{0}:{1}@mobile-hub.lambdatest.com/wd/hub";
    protected              D             driver;
    protected              Swipe<D>      swipe;
    protected              WebDriverWait wait;

    @AfterTest
    public void tearDown (final ITestContext context) {
        final var status = (context.getFailedTests ()
            .size () > 0) ? "failed" : "passed";
        this.driver.executeScript (MessageFormat.format ("lambda-status={0}", status));
        this.driver.quit ();
    }

    protected Capabilities getOptions (final String platform, final String deviceName, final String version,
        final String appKey) {
        final DesiredCapabilities capabilities = new DesiredCapabilities ();
        capabilities.setCapability (CapabilityType.PLATFORM_NAME, platform);
        capabilities.setCapability (MobileCapabilityType.PLATFORM_VERSION, version);
        capabilities.setCapability (MobileCapabilityType.DEVICE_NAME, deviceName);
        capabilities.setCapability (MobileCapabilityType.APP, System.getenv (appKey));
        capabilities.setCapability ("build", MessageFormat.format ("TestNG {0} Sample Build", platform));
        capabilities.setCapability ("name", MessageFormat.format ("{0} Test Case", platform));
        capabilities.setCapability ("console", true);
        capabilities.setCapability ("network", true);
        capabilities.setCapability ("visual", true);
        capabilities.setCapability ("video", true);
        capabilities.setCapability ("terminal", true);
        capabilities.setCapability ("isRealMobile", true);
        return capabilities;
    }
}
