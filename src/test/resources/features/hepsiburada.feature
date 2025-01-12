Feature: Hepsiburada Sepet Testi

  Scenario: En yüksek fiyatlı Apple ürününü sepete ekleme
    Given kullanıcı Hepsiburada sitesine gider
    When Elektronik > Bilgisayar/Tablet > Tablet kategorisine girer
    And "Apple" filtresini seçer
    And "13,2 inç" filtresini seçer
    And en yüksek fiyatlı ürünü sepete ekler
    Then sepetteki fiyatın ürün fiyatı ile aynı olduğunu doğrular
