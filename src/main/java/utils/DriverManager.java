package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class DriverManager {
    private static WebDriver webDriver;
    private static AppiumDriver driver;
    private static final String PLATFORM = System.getProperty("platform", "Android");

    public static WebDriver getWebDriver() {
        if (webDriver == null) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--disable-notifications");  // Bildirimleri kapatma
            options.addArguments("--start-maximized");        // Tarayıcıyı büyütme

            webDriver = new ChromeDriver(options);
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Varsayılan bekleme
            webDriver.manage().window().maximize();
        }
        return webDriver;
    }

    public static void closeWebDriver() {
        if (webDriver != null) {
            try {
                webDriver.quit();
                webDriver = null;
            } catch (Exception e) {
                System.out.println("WebDriver zaten kapalı ya da sonlandırılmış.");
            }
        }
    }

    // AppiumDriver Başlatma (Android ve iOS)
    public static AppiumDriver getAppiumDriver() {
        if (driver == null) {
            switch (PLATFORM) {
                case "Android" -> driver = createAndroidDriver();
                case "iOS" -> driver = createIOSDriver();
                default -> throw new UnsupportedOperationException("Desteklenmeyen platform: " + PLATFORM);
            }
        }
        return driver;
    }

    private static AppiumDriver createAndroidDriver() {
        UiAutomator2Options options = new UiAutomator2Options()
                .setDeviceName("emulator-5554")
                .setPlatformVersion("12.0")
                .setApp(getAppPath("android"))
                .setAppPackage("com.akakce.android")
                .setAppActivity("com.akakce.activities.MainActivity")
                .setNoReset(true)
                .autoGrantPermissions();
        try {
            return new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private static AppiumDriver createIOSDriver() {
        XCUITestOptions options = new XCUITestOptions()
                .setDeviceName("iPhone 12")
                .setPlatformVersion("15.0")
                .setApp(getAppPath("ios"))
                .setNoReset(true)
                .autoAcceptAlerts();
        try {
            return new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getAppPath(String platform) {
        String basePath = "src/test/resources/apps/";
        return new File(basePath + (platform.equals("android") ? "app-debug.apk" : "app-debug.ipa")).getAbsolutePath();
    }

    public static void closeAppiumDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
