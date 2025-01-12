package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class ProductPageMobile {
    AppiumDriver driver;

    public ProductPageMobile(AppiumDriver driver) {
        this.driver = driver;
    }

    By sellerButton = By.id("btn_seller");

    public boolean isSellerButtonVisible() {
        return driver.findElement(sellerButton).isDisplayed();
    }
}
