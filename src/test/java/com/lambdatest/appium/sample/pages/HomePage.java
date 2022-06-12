package com.lambdatest.appium.sample.pages;

import static com.google.common.collect.ImmutableMap.of;
import static io.appium.java_client.MobileBy.AccessibilityId;
import static io.appium.java_client.MobileBy.iOSNsPredicateString;

import java.util.Map;

import org.openqa.selenium.By;

public class HomePage {
    public Map<String, By> message () {
        return of ("iOS", AccessibilityId ("Textbox"));
    }

    public Map<String, By> notificationButton () {
        return of ("iOS", AccessibilityId ("notification"));
    }

    public Map<String, By> proverbialNotification () {
        return of ("iOS", iOSNsPredicateString ("label BEGINSWITH \"PROVERBIAL\""));
    }

    public Map<String, By> textButton () {
        return of ("iOS", iOSNsPredicateString ("label == \"Text\" AND type == \"XCUIElementTypeButton\""));
    }
}
