/* Driver Yönetimi (Web ve Mobil) */

package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverManager {
    private static WebDriver webDriver;
    private static AppiumDriver<MobileElement> appiumDriver;

    // WebDriver Başlatma (Selenium)
    public static WebDriver getWebDriver() {
        if (webDriver == null) {
            System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
            webDriver = new ChromeDriver();
            webDriver.manage().window().maximize();
        }
        return webDriver;
    }

    // AppiumDriver Başlatma (Mobil)
    public static AppiumDriver<MobileElement> getAppiumDriver() {
        if (appiumDriver == null) {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("platformName", "Android");
            caps.setCapability("deviceName", "emulator-5554");
            caps.setCapability("appPackage", "com.akakce.android");
            caps.setCapability("appActivity", "com.akakce.activities.MainActivity");
            try {
                appiumDriver = new AppiumDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return appiumDriver;
    }

    // Driverları Kapatma
    public static void closeWebDriver() {
        if (webDriver != null) {
            webDriver.quit();
            webDriver = null;
        }
    }

    public static void closeAppiumDriver() {
        if (appiumDriver != null) {
            appiumDriver.quit();
            appiumDriver = null;
        }
    }
}

/* Web Driver ve Appium Driver yönetimi tamamlandı. Sırada Page Object Model sınıflarını oluşturacağız. */
