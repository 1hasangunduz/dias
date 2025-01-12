package mobile;


import io.appium.java_client.AppiumDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.HomePageMobile;
import pages.ProductPageMobile;
import utils.DriverManager;

import static org.junit.Assert.assertTrue;

public class AkakceMobileTest {
    AppiumDriver driver;
    HomePageMobile homePageMobile;
    ProductPageMobile productPageMobile;

    @Before
    public void setup() {
        driver = DriverManager.getAppiumDriver();
        homePageMobile = new HomePageMobile(driver);
        productPageMobile = new ProductPageMobile(driver);
    }

    @Test
    public void testSearchLaptopAndCheckSellerButton() {
        homePageMobile.continueWithoutLogin();
        homePageMobile.searchProduct("Laptop");
        homePageMobile.clickFilterButton();
        assertTrue(productPageMobile.isSellerButtonVisible());
    }

    @After
    public void tearDown() {
        DriverManager.closeAppiumDriver();
    }
}