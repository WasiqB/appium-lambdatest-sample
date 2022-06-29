package com.lambdatest.appium.sample.utils;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;

@SuppressWarnings ("rawtypes")
public class Swipe<D extends AppiumDriver<MobileElement>> {
    private final D driver;

    public Swipe (final D driver) {
        this.driver = driver;
    }

    public void down () {
        final Dimension screenDimension = this.driver.manage ()
            .window ()
            .getSize ();
        final PointOption startPoint = point (screenDimension.getWidth () / 2, 10);
        final PointOption endPoint = point (screenDimension.getWidth () / 2, screenDimension.getHeight () / 2);
        perform (startPoint, endPoint);
    }

    public void up () {
        final Dimension screenDimension = this.driver.manage ()
            .window ()
            .getSize ();
        final PointOption startPoint = point (screenDimension.getWidth () / 2, screenDimension.getHeight () - 10);
        final PointOption endPoint = point (screenDimension.getWidth () / 2, 10);
        perform (startPoint, endPoint);
    }

    private void perform (final PointOption startPoint, final PointOption endPoint) {
        final TouchAction action = new TouchAction (this.driver);
        action.press (startPoint)
            .waitAction (waitOptions (ofMillis (200)))
            .moveTo (endPoint)
            .release ()
            .perform ();
    }
}
