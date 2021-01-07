<template>
    <div id="bookInfo">
        <el-container>
            <el-header><b>OrderID:</b></el-header>
            <el-main>{{this.tableData.id}}</el-main>
            <el-header><b>CustomerCompanyName:</b></el-header>
            <el-main :key="'comp'+counter">{{this.tableData.fullCustomer.companyName}}</el-main>
            <el-header><b>EmployeeName:</b></el-header>
            <el-main :key="'emp'+counter">{{this.tableData.fullEmployee.firstName}} {{this.tableData.fullEmployee.lastName}}</el-main>
          <el-header><b>Shipper:</b></el-header>
          <el-main :key="'ship'+counter">{{this.tableData.fullShipper.companyName}}</el-main>
          <el-card class="box-card"  :key="'ordsss'+counter">
            <div slot="header" class="clearfix">
              <span>Produkty w zamówieniu</span>
            </div>
            <div v-for="o in tableData.orderDetails" class="text item">
              {{'Produkt: ' + o.fullData.productName + ', ilość: ' + o.quantity }}


            </div>
          </el-card>
        </el-container>
    </div>
</template>

<script>
    export default {
        name: "SingleOrderView",

        data() {
            return {
                orderId: this.$route.params.id,
                tableData: [],
                counter: 0,
            }
        },
        methods: {
            loadOrder: function () {
                this.$axios.get(this.$api + "/api/order/" + this.orderId, {})
                .then(resp => {
                    this.tableData = resp.data;
                    console.log(this.tableData)

                    this.loadProds(this.tableData.orderDetails);
                    this.loadCustomer(this.tableData.customerID);
                    this.loadEmployee(this.tableData.employeeID);
                    this.loadShipper(this.tableData.shipperID);


                    console.log(this.tableData);
                  this.counter++;



                }).catch(resp => {
                    console.log("error", resp);
                })
            },
            loadProds(prods){
              for (let i = 0; i < prods.length; i++) {
                 this.$axios.get(this.$api + "/api/product/id/" +prods[i].productID, {})
                    .then(resp => {
                      console.log(resp);
                      prods[i].fullData = resp.data
                      this.counter++;
                    }).catch(resp => {
                  console.log("error", resp);
                })

              }

            },
          loadShipper(id){
            this.$axios.get(this.$api + "/api/shipper/id/" +id, {})
                .then(resp => {
                  console.log(resp);
                  this.tableData.fullShipper = resp.data;
                  this.counter++;
                }).catch(resp => {
              console.log("error", resp);
            })
          },
          loadCustomer(id){
              this.$axios.get(this.$api + "/api/customer/id/" +id, {})
                  .then(resp => {
                    console.log(resp);
                    this.tableData.fullCustomer = resp.data;
                    this.counter++;
                  }).catch(resp => {
                console.log("error", resp);
              })
          },
          loadEmployee(id){
            this.$axios.get(this.$api + "/api/employee/id/" +id, {})
                .then(resp => {
                  console.log(resp);
                  this.tableData.fullEmployee = resp.data;
                  this.counter++;
                }).catch(resp => {
              console.log("error", resp);
            })
          }
        },
        mounted() {
            this.loadOrder();
        }
    }
</script>

<style lang="scss">
    #bookInfo{
        text-align:center;
        margin:auto;
    }
    .el-header {
        background-color: #e0e4e9;
        color: #333;
        text-align: center;
        line-height: 60px;
    }
    img {
        max-width: 400px;
        align-content: center;
    }
</style>