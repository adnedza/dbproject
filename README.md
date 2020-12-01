# dbproject
Authors: Adrian Nędza, Igor Dzierwa, Konrad Makuch

Komenda potrzebna do uruchomienia kontenerów (należy ją wpisać w root folderze projektu):
"docker-compose up -d --build" - uruchamia kontenery w tle, buduje je zawsze przed uruchomieniem (zalecane przy zmianach w kodzie backendu, można pominąć '--build' jeśli zmian w kodzie nie było)

# Budowanie obrazu northwind-service: 
mvn clean package -DskipTests (spotify maven plugin)

# Swagger3: 
localhost:8080/swagger-ui/index.html

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

