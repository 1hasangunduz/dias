package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HepsiBuradaCommonPage;
import utils.DriverManager;

public class HepsiburadaStepDefs {

    HepsiBuradaCommonPage hepsiBuradaCommonPage = new HepsiBuradaCommonPage();

    @Given("kullanıcı Hepsiburada sitesine gider")
    public void kullanici_hepsiburada_sitesine_gider() {
        DriverManager.getWebDriver().get("https://www.hepsiburada.com/");
        System.out.println("Hepsiburada sitesine gidildi.");
    }

    @When("Elektronik > Bilgisayar\\/Tablet > Tablet kategorisine girer")
    public void elektronik_bilgisayar_tablet_tablet_kategorisine_girer() {
        hepsiBuradaCommonPage.selectElectronicsCategory();
    }

    @When("{string} filtresini seçer")
    public void filtresini_secer(String filter) {
        hepsiBuradaCommonPage.selectProductSpecOnFilter(filter);
    }

    @When("en yüksek fiyatlı ürünü sepete ekler")
    public void en_yuksek_fiyatli_urunu_sepete_ekler() {
        hepsiBuradaCommonPage.clickHighestPricedProduct();
        hepsiBuradaCommonPage.clickAddToCart();
    }

    @Then("sepetteki fiyatın ürün fiyatı ile aynı olduğunu doğrular")
    public void sepetteki_fiyatin_urun_fiyati_ile_ayni_oldugunu_dogrular() {
        hepsiBuradaCommonPage.checkBasketPrice();
    }
}
