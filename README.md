# Podział koszyka
Zadanie w procesie rekrutacyjnym na staż w Ocado Technology.
## Zależności maven
Biblioteka do odczytu danych z pliku .json
```
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.15.2</version>
</dependency>
```
Biblioteki do testowania
```
<dependency>
    <groupId>org.testng</groupId>
    <artifactId>testng</artifactId>
    <version>RELEASE</version>
    <scope>test</scope>
</dependency>
```
```
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>RELEASE</version>
    <scope>test</scope>
</dependency>
```

## Działanie programu
### Wczytanie danych
Program z wykorzystaniem biblioteki jackson odczytuje dane 
korzystając z klasy ObjectMapper oraz konwertuje to na mapę.
W mapie znajdują się produkty (jako klucze) i listy dostaw tych produktów (jako wartości).
Mapa ta jest używana w celu znalezienia optymalnego podziału koszyka.

### Szukanie optymalnych sposobów dostawy
Program rekurencyjnie wywołuje funkcje splitItems(), która odnajduje najbardziej
optymalne rozwiązanie (z pomocą klasy BasketDelivery). W każdym wywołaniu sprawdzane są dwie opcje:
biorąc aktualny środek dostawy jako jedną z opcji lub pomijając go. Zwracany jest bardziej korzystny wynik
(jeżeli każdy produkt może być dostarczony przy zadanych sposobach dostawy).
Podział koszyka jest lepszy, jeżeli zawiera mniej opcji dostaw, a jeśli ten jest równy to lepszy koszyk ma więcej 
produktów w największej dostawie.

### Szukanie optymalnego rozwiązania
Mając już znaleziony najlepszy podział dostaw należy określić, który
produkt jest dostarczony przez który typ dostawy. W klasie BasketDelivery znajduje się
mapa deliveries, zawierająca używane dostawy (jako klucze) i lista produktów, które mogą być
dostarczone za pomocą tego sposobu dostawy (jako wartości). Wywoływana jest metoda getSortedDeliveries(),
która tworzy najlepsze grupy dostaw (możliwe jak najwięcej produktów za pomocą jednej dostawy).
Funkcja tworzy takie grupy do momentu, w którym każdy produkt jest pokryty przez jedną dostawę.