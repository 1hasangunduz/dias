package pages;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;


public class HomePageMobile {
    AppiumDriver driver;

    public HomePageMobile(AppiumDriver driver) {
        this.driver = driver;
    }

    @AndroidFindBy(id = "btn_continue")
    WebElement continueWithoutLogin;

    @AndroidFindBy(id = "search_box")
    WebElement searchBox;

    @AndroidFindBy(id = "btn_filter")
    WebElement filterButton;


    public void continueWithoutLogin() {
        continueWithoutLogin.click();
    }

    public void searchProduct(String productName) {
        searchBox.sendKeys(productName);
    }

    public void clickFilterButton() {
        filterButton.click();
    }
}
