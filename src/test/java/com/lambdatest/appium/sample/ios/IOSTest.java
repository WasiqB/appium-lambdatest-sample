package com.lambdatest.appium.sample.ios;

import static java.text.MessageFormat.format;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;

import com.lambdatest.appium.sample.BaseTest;
import com.lambdatest.appium.sample.pages.HomePage;
import com.lambdatest.appium.sample.utils.Swipe;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class IOSTest extends BaseTest<IOSDriver<MobileElement>> {
    private static final String   PLATFORM = "iOS";
    private              HomePage homePage;

    @BeforeTest
    public void setupDriver () throws MalformedURLException {
        this.homePage = new HomePage ();
        this.driver = new IOSDriver<> (new URL (format (BaseTest.URL, BaseTest.LT_USER, BaseTest.LT_KEY)),
            getIOSOptions ());
        this.wait = new WebDriverWait (this.driver, 10);
        this.swipe = new Swipe<> (this.driver);
    }

    @Test
    public void testNotifications () {
        this.wait.until (elementToBeClickable (this.homePage.notificationButton ()
                .get (PLATFORM)))
            .click ();
        this.swipe.down ();
        assertTrue (this.wait.until (visibilityOfElementLocated (this.homePage.proverbialNotification ()
                .get (PLATFORM)))
            .getText ()
            .contains ("Test Notification, Please enjoy this notification"));
        this.swipe.up ();
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
