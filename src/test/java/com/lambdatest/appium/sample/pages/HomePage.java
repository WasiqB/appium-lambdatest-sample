package com.lambdatest.appium.sample.pages;

import static com.google.common.collect.ImmutableMap.of;
import static com.lambdatest.appium.sample.pages.Platform.ANDROID;
import static com.lambdatest.appium.sample.pages.Platform.IOS;
import static io.appium.java_client.MobileBy.AccessibilityId;
import static io.appium.java_client.MobileBy.iOSNsPredicateString;
import static org.openqa.selenium.By.id;

import java.util.Map;

import org.openqa.selenium.By;

public class HomePage {
    public Map<Platform, By> message () {
        return of (IOS, AccessibilityId ("Textbox"), ANDROID, id ("Textbox"));
    }

    public Map<Platform, By> notificationButton () {
        return of (IOS, AccessibilityId ("notification"), ANDROID, id ("notification"));
    }

    public Map<Platform, By> proverbialNotification () {
        return of (IOS, iOSNsPredicateString ("label BEGINSWITH \"PROVERBIAL\""), ANDROID, id ("android:id/title"));
    }

    public Map<Platform, By> textButton () {
        return of (IOS, iOSNsPredicateString ("label == \"Text\" AND type == \"XCUIElementTypeButton\""), ANDROID,
            id ("Text"));
    }
}
