- pom.xml dosyasında gerekli bağımlılıkları ekledik:

Selenium (UI Testleri)
Appium (Mobil Testler)
Rest-Assured (API Testleri)
Cucumber BDD (İsteğe bağlı)
JUnit (Test çalıştırma)


src
├── main
│   └── java
│       └── pages
│           └── HomePage.java
│       └── utils
│           └── DriverManager.java
│           └── Variables.java
├── test
│   └── java
│       └── stepdefinitions
│           └── HepsiburadaStepDefs.java
│       └── runners
│           └── TestRunner.java
│   └── resources
│       └── features
│           └── hepsiburada.feature
