package com.lambdatest.appium.sample.android;

import static com.lambdatest.appium.sample.enums.Platform.ANDROID;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;
import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;

import com.lambdatest.appium.sample.BaseTest;
import com.lambdatest.appium.sample.enums.Environment;
import com.lambdatest.appium.sample.enums.Platform;
import com.lambdatest.appium.sample.pages.HomePage;
import com.lambdatest.appium.sample.utils.Swipe;
import io.appium.java_client.MobileElement;
import io.appium.java_client.Setting;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AndroidTest extends BaseTest<AndroidDriver<MobileElement>> {
    private static final Platform PLATFORM = ANDROID;
    private              HomePage homePage;

    @Parameters ({ "environment", "deviceName", "version", "app", "isAutomatic" })
    @BeforeTest
    public void setupDriver (final Environment environment, final String deviceName, final String version,
        final String app, final boolean isAutomatic) throws MalformedURLException {
        this.homePage = new HomePage ();
        startServer (environment, isAutomatic);
        System.out.println (getUrl (environment));
        this.driver = new AndroidDriver<> (getUrl (environment),
            getOptions (environment, "Android", deviceName, version, app));
        this.driver.setSetting (Setting.IGNORE_UNIMPORTANT_VIEWS, true);
        this.wait = new WebDriverWait (this.driver, 10);
        this.swipe = new Swipe<> (this.driver);
    }

    @Test
    public void testNotifications () {
        this.wait.until (elementToBeClickable (this.homePage.notificationButton ()
                .get (PLATFORM)))
            .click ();
        this.driver.openNotifications ();
        assertEquals (this.wait.until (visibilityOfElementLocated (this.homePage.proverbialNotification ()
                .get (PLATFORM)))
            .getText (), "Test Notification");
        this.driver.navigate ()
            .back ();
    }

    @Test
    public void testTextButton () {
        this.wait.until (elementToBeClickable (this.homePage.textButton ()
                .get (PLATFORM)))
            .click ();
        assertEquals (this.wait.until (visibilityOfElementLocated (this.homePage.message ()
                .get (PLATFORM)))
            .getText (), "Proverbial");
    }
}
