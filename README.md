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
![Screenshot](images/category_table.JPG) 

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
![Screenshot](images/products_table.JPG) 

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
  "supplier": {
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
  },
  "unitPrice": 0,
  "unitsInOrder": 0,
  "unitsInStock": 0
}
```
W tym przypadku należało przełożyć relacje SQL na bazę NoSQL, co uzyskaliśmy poprzez zagnieżdżenie dokumentów - dane z obiektu Supplier jak i z obiektu Category w całości są przekazywane do dokumentu Products.

Jeśli chodzi o kwestię dodawania produktów, a raczej dodawania obiektu Supplier i Category to preferowaną przez nas opcją jest przekazanie samych parametrów categoryName/companyName, po których możemy odszukać daną kategorię/dostawcę.

### Suppliers:

Tabela SQL:\
![Screenshot](images/suppliers_table.JPG)

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
    "employee": {
      "id": "string",
      "lastName": "string",
      "firstName": "string",
      "title": "string",
      "titleOfCourtesy": "string",
      "birthDate": "2020-12-04T08:35:03.328Z",
      "hireDate": "2020-12-04T08:35:03.328Z",
      "address": "string",
      "city": "string",
      "region": "string",
      "postalCode": "string",
      "country": "string",
      "homePhone": "string",
      "extension": "string",
      "photo": "string",
      "notes": "string",
      "reportsTo": "string",
      "photoPath": "string"
    },
    "territory": {
      "id": "string",
      "territoryDescription": "string",
      "region": {
        "id": "string",
        "regionDescription": "string"
      }
    }
  }
``` 
W tym przypadku należało przełożyć relacje SQL na bazę NoSQL, co uzyskaliśmy poprzez zagnieżdżenie dokumentów - dane z obiektu Territory jak i z obiektu Employee, w całości są przekazywane do dokumentu EmployeeTerritories.

Jeśli chodzi o kwestię dodawania obiektu Employee i Territory, to preferowaną przez nas opcją jest przekazanie samych parametrów:
firstName,lastName/territoryDescription, po których możemy odszukać danego pracownika/terytorium.



### Employee:

Tabela SQL:\
![Screenshot](images/employees_table.png)

Dokument NoSQL:
```
{
  "address": "string",
  "birthDate": "2020-12-04T08:35:03.328Z",
  "city": "string",
  "country": "string",
  "extension": "string",
  "firstName": "string",
  "hireDate": "2020-12-04T08:35:03.328Z",
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
Dokument bazy NoSQL wygląda praktycznie tak samo jak tabela w bazie SQL.


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
    "id": "string",
    "customer": {
      "id": "string",
      "companyName": "string",
      "contactTitle": "string",
      "address": "string",
      "postalCode": "string",
      "country": "string",
      "phone": "string",
      "fax": "string",
      "region": "string"
    },
    "customerDemographic": {
      "id": "string",
      "customerDesc": "string"
    }
  }
``` 
W tym przypadku należało przełożyć relacje SQL na bazę NoSQL, co uzyskaliśmy poprzez zagnieżdżenie dokumentów - dane z obiektu Customer jak i z obiektu CustomerDemographic, w całości są przekazywane do dokumentu CustomerCustomerDemo.

Jeśli chodzi o kwestię dodawania obiektu Customer i CustomerDemographic to preferowaną przez nas opcją jest przekazanie samych parametrów: companyName/customerDesc, po których możemy odszukać danego klienta/grupę klientów.


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
  "fax": "string",
  "id": "string",
  "phone": "string",
  "postalCode": "string",
  "region": "string"
}
``` 
Dokument bazy NoSQL wygląda praktycznie tak samo jak tabela w bazie SQL.

