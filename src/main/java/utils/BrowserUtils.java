package utils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

public class BrowserUtils {

    private WebDriver driver;
    private String originalWindow;

    // Constructor: WebDriver'ı alır
    public BrowserUtils(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Yeni sekme açar ve o sekmeye geçiş yapar.
     */
    public void switchToNewTab() {
        // Mevcut sekmeyi kaydet
        originalWindow = driver.getWindowHandle();

        // Yeni sekme aç
        ((JavascriptExecutor) driver).executeScript("window.open();");

        // Tüm sekmeleri al
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());

        // Yeni sekmeye geç (son açılan sekme)
        driver.switchTo().window(tabs.get(tabs.size() - 1));
        System.out.println("Yeni sekmeye geçiş yapıldı.");
    }

    /**
     * Önceki (orijinal) sekmeye dönüş yapar.
     */
    public void switchToOriginalTab() {
        if (originalWindow != null) {
            driver.switchTo().window(originalWindow);
            System.out.println("Orijinal sekmeye geri dönüldü.");
        } else {
            System.out.println("Orijinal sekme bulunamadı.");
        }
    }
}
