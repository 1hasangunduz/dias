package ui;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.HepsiBuradaCommonPage;
import utils.DriverManager;

import static org.junit.Assert.assertEquals;

public class HepsiburadaUITest {

    private HepsiBuradaCommonPage hepsiBuradaCommonPage;

    @Before
    public void setUp() {
        DriverManager.getWebDriver().get("https://www.hepsiburada.com/");
        hepsiBuradaCommonPage = new HepsiBuradaCommonPage();  // Eksik olan HomePage nesnesi başlatıldı
    }
    @Test
    public void testAddHighestPriceProductToCart() throws InterruptedException {
        hepsiBuradaCommonPage.selectElectronicsCategory();
        hepsiBuradaCommonPage.selectProductSpecOnFilter("Apple");
        hepsiBuradaCommonPage.selectProductSpecOnFilter("13,2 inç");
        hepsiBuradaCommonPage.clickHighestPricedProduct();
        hepsiBuradaCommonPage.clickAddToCart();
        hepsiBuradaCommonPage.checkBasketPrice();

        Thread.sleep(100000);

    }

    @After
    public void tearDown() {
        DriverManager.closeWebDriver();
    }
}