package com.lambdatest.appium.sample.android;

import static com.lambdatest.appium.sample.pages.Platform.ANDROID;
import static java.text.MessageFormat.format;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;
import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;

import com.lambdatest.appium.sample.BaseTest;
import com.lambdatest.appium.sample.pages.HomePage;
import com.lambdatest.appium.sample.pages.Platform;
import com.lambdatest.appium.sample.utils.Swipe;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AndroidTest extends BaseTest<AndroidDriver<MobileElement>> {
    private static final Platform PLATFORM = ANDROID;
    private              HomePage homePage;

    @BeforeTest
    public void setupDriver () throws MalformedURLException {
        this.homePage = new HomePage ();
        this.driver = new AndroidDriver<> (new URL (format (BaseTest.URL, BaseTest.LT_USER, BaseTest.LT_KEY)),
            getOptions ("Android", "Galaxy S10", "10", "LT_APP_ANDROID"));
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
