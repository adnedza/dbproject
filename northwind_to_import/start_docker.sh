#!/bin/bash
docker build . --tag mongo:v1
docker run -d -p 27017:27017 --name mongo mongo:v1
#mongoimport --db northwind --file /northwind/categories.json
#mongoimport --db northwind --file /northwind/customers.json
#mongoimport --db northwind --file /northwind/employees.json
#mongoimport --db northwind --file /northwind/order_details.json
#mongoimport --db northwind --file /northwind/orders.json
#mongoimport --db northwind --file /northwind/products.json
#mongoimport --db northwind --file /northwind/shippers.json
#mongoimport --db northwind --file /northwind/suppliers.json
