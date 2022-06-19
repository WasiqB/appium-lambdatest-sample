package com.lambdatest.appium.sample.ios;

import static com.lambdatest.appium.sample.pages.Platform.IOS;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;

import com.lambdatest.appium.sample.BaseTest;
import com.lambdatest.appium.sample.pages.HomePage;
import com.lambdatest.appium.sample.pages.Platform;
import com.lambdatest.appium.sample.utils.Swipe;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class IOSTest extends BaseTest<IOSDriver<MobileElement>> {
    private static final Platform PLATFORM = IOS;
    private              HomePage homePage;

    @BeforeTest
    public void setupDriver () throws MalformedURLException {
        this.homePage = new HomePage ();
        this.driver = new IOSDriver<> (new URL (MessageFormat.format (BaseTest.URL, BaseTest.LT_USER, BaseTest.LT_KEY)),
            getOptions ("iOS", "iPhone 13 Pro", "15", "LT_APP_IOS"));
        this.wait = new WebDriverWait (this.driver, 10);
        this.swipe = new Swipe<> (this.driver);
    }

    @Test
    public void testNotifications () {
        this.wait.until (elementToBeClickable (this.homePage.notificationButton ()
                .get (PLATFORM)))
            .click ();
        this.swipe.down ();
        Assert.assertTrue (this.wait.until (visibilityOfElementLocated (this.homePage.proverbialNotification ()
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
        Assert.assertEquals (this.wait.until (visibilityOfElementLocated (this.homePage.message ()
                .get (PLATFORM)))
            .getText (), "Proverbial");
    }
}
