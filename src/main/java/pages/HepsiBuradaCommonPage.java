package pages;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverManager;
import utils.Variables;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class HepsiBuradaCommonPage {

    WebDriver driver = DriverManager.getWebDriver();
    Actions actions = new Actions(driver);
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    public HepsiBuradaCommonPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[text()='Elektronik']")
    public WebElement electronicsCategoryHeader;

    @FindBy(xpath = "//a[text()='Bilgisayar/Tablet']")
    public WebElement electronicsCategorySubTitlePC;

    @FindBy(xpath = "(//a[text()='Tablet'])[1]")
    public WebElement tabletCategory;

    @FindBy(css = "[data-test-id='not_checked']")
    public List<WebElement> filterItems;

    @FindBy(css = "[data-test-id='addToCart']")
    public WebElement buttonAddToCart;

    @FindBy(xpath = "//button[text()='Sepete git']")
    public WebElement buttonGoToBasket;

    @FindBy(id = "basket_payedPrice")
    public WebElement textFieldBasketPrice;

    public void hoverElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));  // Elementi bekle
        actions.moveToElement(element).build().perform();
    }

    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getWebDriver();
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }


    public void selectElectronicsCategory() {
        try {
            hoverElement(electronicsCategoryHeader);
            hoverElement(electronicsCategorySubTitlePC);

            wait.until(ExpectedConditions.elementToBeClickable(tabletCategory));
            tabletCategory.click();
            System.out.println(driver.getCurrentUrl() + " - Clicked on Electronics > Tablet Category");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void selectProductSpecOnFilter(String productName) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getWebDriver(), Duration.ofSeconds(21));

        boolean isAppleSelected = false;

        while (!isAppleSelected) {

            for (WebElement item : filterItems) {
                try {
                    scrollToElement(item);
                    wait.until(ExpectedConditions.visibilityOf(item));

                    if (item.getText().equalsIgnoreCase(productName)) {
                        item.click();
                        System.out.println(item.getText() + " -> filtresi seçildi.");
                        isAppleSelected = true;
                        break;
                    }
                } catch (StaleElementReferenceException e) {
                    System.out.println("StaleElementReferenceException yakalandı. Öğeler yeniden alınıyor...");
                    break;
                }
            }

            // Eğer Apple bulunmazsa biraz bekle ve tekrar dene
            if (!isAppleSelected) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void clickHighestPricedProduct() {
        WebDriver driver = DriverManager.getWebDriver();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Tüm fiyatları topla
        List<WebElement> priceElements = driver.findElements(By.cssSelector("[data-test-id='price-current-price']"));

        WebElement highestPriceElement = null;
        double highestPrice = 0.0;

        for (WebElement priceElement : priceElements) {
            try {
                // Scroll yaparak öğeyi görünür yap
                scrollToElement(priceElement);
                wait.until(ExpectedConditions.visibilityOf(priceElement));

                // Fiyatı temizleyip sayıya çevir
                String priceText = priceElement.getText()
                        .replaceAll("[^0-9,\\.]", "")  // "TL" ve boşlukları kaldır
                        .replace(".", "")              // Binlik ayracı olan noktayı kaldır
                        .replace(",", ".")            // Ondalık virgülü noktaya çevir
                        .trim();

                double price = Double.parseDouble(priceText);

                // En yüksek fiyatı kontrol et
                if (price > highestPrice) {
                    highestPrice = price;
                    highestPriceElement = priceElement;
                }

            } catch (Exception e) {
                System.out.println("Fiyat alınırken hata: " + e.getMessage() + " - " + priceElement.getText());
            }
        }

        // En yüksek fiyatlı ürüne tıkla
        if (highestPriceElement != null) {
            scrollToElement(highestPriceElement);
            highestPriceElement.click();
            System.out.println("En yüksek fiyatlı ürün seçildi: ₺" + highestPrice);
            Variables.getInstance().setPrice(highestPrice);
        } else {
            System.out.println("Ürün bulunamadı!");
        }
    }

    String originalWindow = driver.getWindowHandle();


    public void clickAddToCart() {
        try {


            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());

            // Yeni sekmeye geç (eski sekmeden farklı olan)
            for (String tab : tabs) {
                if (!tab.equals(originalWindow)) {
                    driver.switchTo().window(tab);
                    System.out.println("Yeni sekmeye geçiş yapıldı.");
                    break;
                }
            }
            Thread.sleep(1000);
            scrollToElement(buttonAddToCart);
            buttonAddToCart.click();
            System.out.println(driver.getCurrentUrl() + " - Clicked on Add To Cart");
            buttonGoToBasket.click();
            Thread.sleep(1000);
            System.out.println(driver.getCurrentUrl() + " - Clicked on Basket");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkBasketPrice() {
        System.out.println(Variables.getInstance().getPrice());

        // Sepet fiyatını doğru formata çevir
        String basketPriceText = textFieldBasketPrice.getText()
                .replace(".", "")  // Binlik ayracı (.) kaldır
                .replace(",", "."); // Ondalık ayracı (,) noktaya çevir

        double basketPrice = Double.parseDouble(basketPriceText);

        System.out.println(basketPrice);

        Assert.assertEquals(Variables.getInstance().getPrice(), basketPrice, 0.01);
    }


}
