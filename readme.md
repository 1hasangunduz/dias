# Hepsiburada UI Test Otomasyonu (Cucumber & Selenium)

Bu proje, Hepsiburada sitesinde en yüksek fiyatlı Apple ürününü sepete ekleme ve sepetteki fiyatın doğruluğunu kontrol etme senaryosunu Cucumber ve Selenium kullanarak otomatikleştirir.

## 🚀 Proje Teknolojileri
- **Java**
- **Selenium WebDriver**
- **Cucumber** (BDD)
- **JUnit**
- **Maven**
- **WebDriverManager**

---

## 📂 Proje Yapısı
```
├── src
│   ├── main
│   │   └── java
│   │       ├── pages
│   │       │   └── HomePage.java
│   │       └── utils
│   │           ├── DriverManager.java
│   │           └── Variables.java
│   ├── test
│   │   └── java
│   │       ├── runners
│   │       │   └── TestRunner.java
│   │       └── stepdefinitions
│   │           └── HepsiburadaStepDefs.java
│   └── resources
│       └── features
│           └── hepsiburada.feature
├── pom.xml
└── README.md
```

---

## 🛠️ Kurulum
1. **Projeyi Klonlayın:**
   ```bash
   git clone <repo-url>
   cd dias
   ```

2. **Maven Bağımlılıklarını Yükleyin:**
   ```bash
   mvn clean install
   ```

3. **Testi Çalıştırın:**
    - IntelliJ IDEA üzerinden `TestRunner.java` dosyasına sağ tıklayıp **Run** seçin.
    - Ya da terminalden şu komutu çalıştırın:
      ```bash
      mvn test
      ```

---

## 🔍 Kullanılan Kütüphaneler (pom.xml)
```xml
<dependencies>
    <!-- Cucumber -->
    <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-java</artifactId>
        <version>7.11.0</version>
    </dependency>
    <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-junit</artifactId>
        <version>7.11.0</version>
        <scope>test</scope>
    </dependency>

    <!-- JUnit -->
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.13.2</version>
        <scope>test</scope>
    </dependency>

    <!-- Selenium -->
    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>4.12.1</version>
    </dependency>

    <!-- WebDriver Manager -->
    <dependency>
        <groupId>io.github.bonigarcia</groupId>
        <artifactId>webdrivermanager</artifactId>
        <version>5.3.2</version>
    </dependency>
</dependencies>
```

---

## ✨ Özellikler
### 1️⃣ Feature Dosyası - `hepsiburada.feature`
```gherkin
Feature: Hepsiburada Sepet Testi

  Scenario: En yüksek fiyatlı Apple ürününü sepete ekleme
    Given kullanıcı Hepsiburada sitesine gider
    When Elektronik > Bilgisayar/Tablet > Tablet kategorisine girer
    And "Apple" filtresini seçer
    And "13,2 inç" filtresini seçer
    And en yüksek fiyatlı ürünü sepete ekler
    Then sepetteki fiyatın ürün fiyatı ile aynı olduğunu doğrular
```

### 2️⃣ Test Adımları - `HepsiburadaStepDefs.java`
```java
@Given("kullanıcı Hepsiburada sitesine gider")
public void kullanici_hepsiburada_sitesine_gider() {
    DriverManager.getWebDriver().get("https://www.hepsiburada.com/");
}

@When("Elektronik > Bilgisayar/Tablet > Tablet kategorisine girer")
public void elektronik_bilgisayar_tablet_tablet_kategorisine_girer() {
    homePage.selectElectronicsCategory();
}

@When("{string} filtresini seçer")
public void filtresini_secer(String filter) {
    homePage.selectProductSpecOnFilter(filter);
}

@When("en yüksek fiyatlı ürünü sepete ekler")
public void en_yuksek_fiyatli_urunu_sepete_ekler() {
    homePage.clickHighestPricedProduct();
    homePage.clickAddToCart();
}

@Then("sepetteki fiyatın ürün fiyatı ile aynı olduğunu doğrular")
public void sepetteki_fiyatin_urun_fiyati_ile_ayni_oldugunu_dogrular() {
    homePage.checkBasketPrice();
}
```

---

## 📊 Test Raporu
Test sonucu `target/cucumber-reports.html` dosyasında HTML formatında saklanır. Tarayıcıda açarak test raporunu görüntüleyebilirsiniz.

---

## 🔧 Karşılaşılan Sorunlar ve Çözümler

### 1. **CDP Uyarısı:**
```
WARNING: Unable to find version of CDP to use for 131.0.6778.265.
```
**Çözüm:**
- Selenium sürümü ve ChromeDriver uyumluluğunu kontrol edin.
- `selenium-devtools-v131` yerine uygun devtools sürümünü ekleyin.

### 2. **Undefined Step Exception:**
```
io.cucumber.junit.UndefinedStepException
```
**Çözüm:**
- Eksik step definition'ları tanımlayın.

---

## 👨‍💻 Katkıda Bulunma
1. Fork'layın 🍴
2. Yeni bir branch oluşturun (`feature/ozellik`)
3. Değişiklikleri commit'leyin ✅
4. Pull request gönderin 🔄

---

## 📞 İletişim
Proje hakkında soru ve önerileriniz için iletişime geçebilirsiniz.

---

## 📝 Lisans
Bu proje MIT Lisansı ile lisanslanmıştır.

