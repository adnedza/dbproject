# dbproject
Autorzy: Adrian Nędza, Igor Dzierwa, Konrad Makuch

Komenda potrzebna do uruchomienia kontenerów (należy ją wpisać w root folderze projektu):
"docker-compose up -d --build" - uruchamia kontenery w tle, buduje je zawsze przed uruchomieniem (zalecane przy zmianach w kodzie backendu, można pominąć '--build' jeśli zmian w kodzie nie było)

# Budowanie obrazu northwind-service: 
mvn clean package -DskipTests (spotify maven plugin)

# Swagger3: 
localhost:8080/swagger-ui/index.html

# Project Lombok:
Biblioteka Javy, która w znaczącym stopniu ułatwia definiowanie klas, szczególnie klas modelu, które powinny być zgodne ze standardem JavaBeans lub być klasami dla obiektów niemodyfikowalnych (immutable).\
Rezultat: Znaczące skrócenie kodu poprzez zastąpienie wszystkich getter'ów i setter'ów adnotacjami @Getter oraz @Setter.

Link: https://javastart.pl/baza-wiedzy/frameworki/project-lombok

Plugin do dodania do InteliJ: https://plugins.jetbrains.com/plugin/6317-lombok (dependency również dodane).

# Opis bazy danych:
Categories – kategorie oferowanych produktów.\
Products – informacja o oferowanych produktach.\
Suppliers – informacja o dostawcach.\
Shippers – informacja o spedytorach.\
Customers – informacja o klientach.\
Employees – informacja o pracownikach.\
Orders – zamówienia składane przez klientów.\
OrderDetails – szczegóły zamówień.\
Territories – terytoria/obszary/miasta.\
Region – Regiony.\
EmployeeTerritories – informacja o terytoriach/obszarach/miastach (obslugiwanych przez poszczególnych pracowników).\
CustomerDemographics – grupy klientów.\
CustomerCustomerDemo – przyporządkowanie klientów do grup.

# Tabele baz danych wraz z proponowaną formą i opisem

### Categories:

Tabela SQL:\
![Screenshot](images/category_table.png) 

Dokument NoSQL:
```
{
  "categoryName": "string",
  "description": "string",
  "id": "string",
  "picture": "string"
}
```
Dokument bazy NoSQL wygląda praktycznie tak samo jak tabela w bazie SQL.

### Products:

Tabela SQL:\
![Screenshot](images/products_table.png) 

Dokument NoSQL:

```
{
  "category": {
    "categoryName": "string",
    "description": "string",
    "id": "string",
    "picture": "string"
  },
  "discontinued": true,
  "id": "string",
  "productName": "string",
  "quantityPerUnit": 0,
  "reorderLevel": 0,
  "supplierID": "string",
  "unitPrice": 0,
  "unitsInOrder": 0,
  "unitsInStock": 0
}
```
W tym przypadku należało przełożyć relacje SQL na bazę NoSQL, co uzyskaliśmy poprzez zagnieżdżenie dokumentów - obiekty Supplier i Category są przekazywane w postaci ich numerów id do dokumentu Products.

Jeśli chodzi o kwestię dodawania produktów, a raczej dodawania obiektu Supplier i Category to preferowaną przez nas opcją jest przekazanie samych parametrów categoryName/companyName, po których możemy odszukać daną kategorię/dostawcę.

### Suppliers:

Tabela SQL:\
![Screenshot](images/suppliers_table.png)

Dokument NoSQL:
```
{
  "address": "string",
  "city": "string",
  "companyName": "string",
  "contactName": "string",
  "contactTitle": "string",
  "country": "string",
  "fax": "string",
  "homePage": "string",
  "id": "string",
  "phone": "string",
  "postalCode": "string",
  "region": "string"
}
``` 
Dokument bazy NoSQL wygląda praktycznie tak samo jak tabela w bazie SQL.

### Region:

Tabela SQL:\
![Screenshot](images/region_table.png)

Dokumet NoSQL:
```
{
  "id": "string",
  "regionDescription": "string"
}
``` 
Dokument bazy NoSQL wygląda praktycznie tak samo jak tabela w bazie SQL.


### Territories:

Tabela SQL:\
![Screenshot](images/territories_table.png)

Dokument NoSQL:
```
{
  "id": "string",
  "region": {
    "id": "string",
    "regionDescription": "string"
  },
  "territoryDescription": "string"
}
``` 
W tym przypadku należało przełożyć relacje SQL na bazę NoSQL, co uzyskaliśmy poprzez zagnieżdżenie dokumentów - dane z obiektu Region, w całości są przekazywane do dokumentu Territories.

Jeśli chodzi o kwestię dodawania obiektu Region, to preferowaną przez nas opcją jest przekazanie samego parametru: regionDescription, po których możemy odszukać dany region.


### EmployeeTerritories:

Tabela SQL:\
![Screenshot](images/employeeTerritories_table.png)

Dokument NoSQL:
```
{
    "id": "string",
    "territory": {
      "id": "string",
      "region": {
        "id": "string",
        "regionDescription": "string"
      },
      "territoryDescription": "string"
    }
  }
``` 
W tym przypadku należało przełożyć relacje SQL na bazę NoSQL, co uzyskaliśmy poprzez zagnieżdżenie dokumentów - obiekty Territory są przekazywane w całości do dokumentu EmployeeTerritories.

Jeśli chodzi o kwestię dodawania obiektu EmployeeTerritories do obiektu Employee, preferowaną przez nas opcją jest przekazanie samych parametrów:
employeeID/territoryDescription, po których możemy odszukać danego pracownika/terytorium.


### Employee:

Tabela SQL:\
![Screenshot](images/employees_table.png)

Dokument NoSQL:
```
{
  "address": "string",
  "birthDate": "2020-12-09T11:38:34.146Z",
  "city": "string",
  "country": "string",
  "employeeTerritories": [
    {
      "id": "string",
      "territory": {
        "id": "string",
        "region": {
          "id": "string",
          "regionDescription": "string"
        },
        "territoryDescription": "string"
      }
    }
  ],
  "extension": "string",
  "firstName": "string",
  "hireDate": "2020-12-09T11:38:34.147Z",
  "homePhone": "string",
  "id": "string",
  "lastName": "string",
  "notes": "string",
  "photo": "string",
  "photoPath": "string",
  "postalCode": "string",
  "region": "string",
  "reportsTo": "string",
  "title": "string",
  "titleOfCourtesy": "string"
}
``` 
W tym przypadku należało przełożyć relacje SQL na bazę NoSQL, co uzyskaliśmy poprzez zagnieżdżenie dokumentów - obiekty EmployeeTerritories są przekazywane w całości, w postaci listy, do dokumentu Employee.


### CustomerDemographics:

Tabela SQL:\
![Screenshot](images/CustomerDemographics_table.png)

Dokument NoSQL:
```
{
  "customerDesc": "string",
  "id": "string"
}
``` 
Dokument bazy NoSQL wygląda praktycznie tak samo jak tabela w bazie SQL.


### CustomerCustomerDemo:

Tabela SQL:\
![Screenshot](images/customerCustomerDemo_table.png)

Dokument NoSQL:
```
{
    "customerDemographic": {
      "customerDesc": "string",
      "id": "string"
    },
    "id": "string"
 }
``` 
W tym przypadku należało przełożyć relacje SQL na bazę NoSQL, co uzyskaliśmy poprzez zagnieżdżenie dokumentów - obiekty CustomerDemographic w całości są przekazywane do dokumentu CustomerCustomerDemo.

Jeśli chodzi o kwestię dodawania obiektu CustomerCustomerDemo do obiektu Customer, to preferowaną przez nas opcją jest przekazanie samych parametrów: customerID/customerDesc, po których możemy odszukać danego klienta/grupę klientów.


### Customers:

Tabela SQL:\
![Screenshot](images/customers_table.png)

Dokument NoSQL:
```
{
  "address": "string",
  "companyName": "string",
  "contactTitle": "string",
  "country": "string",
  "customerCustomerDemo": [
    {
      "customerDemographic": {
        "customerDesc": "string",
        "id": "string"
      },
      "id": "string"
    }
  ],
  "fax": "string",
  "id": "string",
  "phone": "string",
  "postalCode": "string",
  "region": "string"
}
``` 
W tym przypadku należało przełożyć relacje SQL na bazę NoSQL, co uzyskaliśmy poprzez zagnieżdżenie dokumentów - obiekty customerCustomerDemo są przekazywane w całości, w postaci listy, do dokumentu Customers.


### Shippers:

Tabela SQL:\
![Screenshot](images/shippers_table.png)

Dokument NoSQL:
```
{
  "companyName": "string",
  "id": "string",
  "phone": "string"
}
```
Dokument bazy NoSQL wygląda praktycznie tak samo jak tabela w bazie SQL.

### Orders:

Tabela SQL:\
![Screenshot](images/orders_table.png)

Dokument NoSQL:
```
{
    "customerID": "string",
    "employeeID": "string",
    "freight": "string",
    "id": "string",
    "orderDate": "2020-12-09T12:22:51.964Z",
    "orderDetails": [
      {
        "discount": 0,
        "id": "string",
        "productID": "string",
        "quantity": 0,
        "unitPrice": 0
      }
    ],
    "requiredDate": "2020-12-09T12:22:51.964Z",
    "shipCity": "string",
    "shipCountry": "string",
    "shipName": "string",
    "shipPostalCode": "string",
    "shipRegion": "string",
    "shippedDate": "2020-12-09T12:22:51.964Z",
    "shipperID": "string"
  }
```
W tym przypadku należało przełożyć relacje SQL na bazę NoSQL, co uzyskaliśmy poprzez zagnieżdżenie dokumentów - dane z obiektu Customer, Employee oraz Shipper
są przekazywane w postaci ich numerów id do dokumentu Orders, natomiast obiekty OrderDetails przekazywane są w całości, w postaci listy, do dokumentu Orders.

Jeśli chodzi o kwestię dodawania obiektów Customer, Employee oraz Shipper to preferowaną przez nas opcją jest przekazanie samych parametrów: companyName(Customer)/firstName+lastName(Employee)/companyName(Shipper), po których możemy wyszukać te obiekty.

### OrderDetails:

Tabela SQL:\
![Screenshot](images/orderDetails_table.png)

Dokument NoSQL:
```
{
    "discount": 0,
    "id": "string",
    "productID": "string",
    "quantity": 0,
    "unitPrice": 0
  }
```
W tym przypadku należało przełożyć relacje SQL na bazę NoSQL, co uzyskaliśmy poprzez zagnieżdżenie dokumentów - dane z obiektu Product w całości są przekazywane w postaci ich numerów id do dokumentu OrderDetails.

Jeśli chodzi o kwestię dodawania obiektów OrderDetails do obiektu Order, to preferowaną przez nas opcją jest przekazanie samych parametrów: orderID oraz body z polami discount, productName, quantity, unitPrice.
