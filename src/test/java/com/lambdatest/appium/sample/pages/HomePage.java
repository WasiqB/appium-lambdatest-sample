package com.lambdatest.appium.sample.pages;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

public class HomePage {
    public Map<Platform, By> message () {
        return ImmutableMap.of (Platform.IOS, MobileBy.AccessibilityId ("Textbox"), Platform.ANDROID,
            By.id ("Textbox"));
    }

    public Map<Platform, By> notificationButton () {
        return ImmutableMap.of (Platform.IOS, MobileBy.AccessibilityId ("notification"), Platform.ANDROID,
            By.id ("notification"));
    }

    public Map<Platform, By> proverbialNotification () {
        return ImmutableMap.of (Platform.IOS, MobileBy.iOSNsPredicateString ("label BEGINSWITH \"PROVERBIAL\""),
            Platform.ANDROID, By.id ("android:id/title"));
    }

    public Map<Platform, By> textButton () {
        return ImmutableMap.of (Platform.IOS,
            MobileBy.iOSNsPredicateString ("label == \"Text\" AND type == \"XCUIElementTypeButton\""), Platform.ANDROID,
            By.id ("Text"));
    }
}
