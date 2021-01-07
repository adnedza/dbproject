<template>
  <div id="authors">
    <h3>
      Złóż zamówienie
    </h3>
<!--    <pre>-->
<!--      {-->
<!--  "customerCompanyName": "string",-->
<!--  "employeeFirstName": "string",-->
<!--  "employeeLastName": "string",-->
<!--  "freight": "string",-->
<!--  "orderDate": "2021-01-07T15:21:56.414Z",-->
<!--  "orderDetails": [-->
<!--    {-->
<!--      "discount": 0,-->
<!--      "productName": "string",-->
<!--      "quantity": 0,-->
<!--      "unitPrice": 0-->
<!--    }-->
<!--  ],-->
<!--  "requiredDate": "2021-01-07T15:21:56.414Z",-->
<!--  "shipCity": "string",-->
<!--  "shipCountry": "string",-->
<!--  "shipName": "string",-->
<!--  "shipPostalCode": "string",-->
<!--  "shipRegion": "string",-->
<!--  "shippedDate": "2021-01-07T15:21:56.414Z",-->
<!--  "shipperCompanyName": "string"-->
<!--}-->
<!--    </pre>-->

    <el-form>
      <el-row>
        <el-form ref="PlaceOrder" :rules="rules" :model="model" v-loading="loading">

          <el-form-item label="Customer name:" prop="customerCompanyName">
            <el-select v-loading="authorsSelectLoading" v-model="model.customerCompanyName" placeholder="Zacznij wpisywać customer" :no-match-text="'Brak wyników'" filterable>
              <el-option
                  v-for="item in customers"
                  :key="item.id"
                  :label="item.companyName"
                  :value="item.companyName">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="Employee name:" prop="employees">
            <el-select v-loading="authorsSelectLoading" v-model="model.employees" placeholder="Zacznij wpisywać employee" :no-match-text="'Brak wyników'" filterable>
              <el-option
                  v-for="item in employees"
                  :key="item.id"
                  :label="item.firstName + ' ' + item.lastName"
                  :value="item.id">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="Shipper:" prop="shipperCompanyName">
            <el-select v-loading="authorsSelectLoading" v-model="model.shipperCompanyName" placeholder="Zacznij wpisywać shipper" :no-match-text="'Brak wyników'" filterable>
              <el-option
                  v-for="item in shippers"
                  :key="item.id"
                  :label="item.companyName"
                  :value="item.companyName">
              </el-option>
            </el-select>
          </el-form-item>

          <prodlist :order="true" v-on:addproduct="handleAddProduct"></prodlist>

          <el-card class="box-card" v-if="selectedproducts.length > 0">
            <div slot="header" class="clearfix">
              <span>Produkty w zamówieniu</span>
              <el-button style="float: right; padding: 3px 0" type="text" @click="handleDeleteFromOrderAll()">Usuń wszystkie</el-button>
            </div>
            <div v-for="o in selectedproducts" :key="counter" class="text item">
              {{'Produkt: ' + o.productName + ', ilość: ' + o.quantity }}
              <el-button @click="handleDeleteFromOrder(o.id)" size="small" type="danger">Usuń z zamówienia</el-button>

            </div>
          </el-card>

          <el-button type="success" @click="handleSubmit">Zapisz!</el-button>
        </el-form>
      </el-row>
    </el-form>
  </div>
</template>
<script>
import Prodlist from "../Product/ProductList.vue";
export default {
  name: 'PlaceOrder',
  components: {Prodlist},
  props: ['edit'],
  data(){
    const validateNumber = (r, value, callback)  => {
      console.log(r, value, callback);
      if(/^[0-9]*$/gm.test(value) && value.toString().length > 0){
        callback();
      } else {
        callback(new Error('Contains letter or too short or loo long'));
      }
    };

    return {
      componentName: 'PlaceOrder',
      mydata: [],
      loading: false,
      authorsSelectLoading: false,
      categorySelectLoading: false,
      editingId: false,
      customers: [],
      employees: [],
      shippers: [],
      categories: [],
      selectedproducts: [],
      counter: 0,
      model:{
        name: "",
        length: "",
        author: "",
        stockCount: "",
        description: "",
        shipperCompanyName: "",
        category: "",
        imageUrl: "",
        filename: "",
        picture_name: "",
        customerCompanyName: '',
      },
      rules:{
        name: [
          { required: true, message: "Pole wymagane", trigger: 'change' }
          //{ required: true, type: 'email', message: "Pole wymagane", trigger: 'change' }
        ],
        length: [
          { required: true, validator: validateNumber,  message: "Wpisz poprawna dlugosc", trigger: 'change' }
        ],
        stockCount: [
          { required: true, validator: validateNumber,  message: "Wpisz poprawny stan magazynowy", trigger: 'change' }
        ],
        employees: [
          { required: true,  message: "Pole wymagane", trigger: 'change' }
        ],
        shipperCompanyName: [
          { required: true,  message: "Pole wymagane", trigger: 'change' }
        ],
        customerCompanyName: [
          { required: true,  message: "Pole wymagane", trigger: 'change' }
        ],
        category: [
          { required: true,  message: "Pole wymagane", trigger: 'change' }
        ],
        description: [
          { required: true,  message: "Pole wymagane", trigger: 'change' }
        ],
      }
    }
  },
  methods: {
    handlePhotoUploadSuccess: function(data, file){
      console.log(data);
      this.model.imageUrl = URL.createObjectURL(file.raw);
      this.model.picture_name = "Author Picture";
      this.model.filename = data.name;
    },
    handlePhotoUploadError: function(error, vv){
      this.$message({message: error, type: "error"})
    },
    handleSubmit: function(){

      if(!this.valid(this.componentName))
        return false;

      this.loading = true;
      this.model.stockCount = parseInt(this.model.stockCount);
      var formData = this.prepareFormDataFromModel(this.model);


      console.log(this.model);
      var emp = this.employees.filter(v=> {
        console.log(v.id, this.model.employees);
        return v.id == this.model.employees;
      })
      this.model.employeeFirstName = emp[0].firstName;
      this.model.employeeLastName = emp[0].lastName;


      this.model.orderDetails = [];
      for (let i = 0; i < this.selectedproducts.length; i++) {
        var obj = {};
        obj.productName = this.selectedproducts[i].productName;
        obj.quantity = this.selectedproducts[i].quantity;
        obj.unitPrice = this.selectedproducts[i].unitPrice;
        this.model.orderDetails.push(obj)
      }
      console.log(this.model);

      var apiEndpoint = this.$api + "/api/order";
      var method = "POST";

      this.$axios({
        method: method,
        url: apiEndpoint,
        data: this.model,
        options: {headers: {'Content-Type': 'application/json'}}
      }).then(resp => {
        console.log(resp)
        this.loading = false;
        this.$message({
          message: 'Poprawnie dodano order.',
          type: 'success'
        });
      })
          .catch(error => {
            console.log(error)
            this.loading = false;
            this.$message({
              message: 'Error przy dodawaniu ordera',
              type: 'error'
            });
          })
    },
    loadCustomers: function(){
      this.authorsSelectLoading = true;
      this.$axios
          .get(this.$api + "/api/customers/",
              {headers: {'Content-Type': 'application/x-www-form-urlencoded'}})
          .then(resp => {
            console.log(resp);
            console.log(resp.data, this.model);
            this.customers = resp.data;
            this.authorsSelectLoading = false;
          })
          .catch(error => {
            console.log(error);
            this.authorsSelectLoading = false;
          })
    },

    loadEmployees: function(){
      this.authorsSelectLoading = true;
      this.$axios
          .get(this.$api + "/api/employees/",
              {headers: {'Content-Type': 'application/x-www-form-urlencoded'}})
          .then(resp => {
            console.log(resp);
            console.log(resp.data, this.model);
            this.employees = resp.data;
            this.authorsSelectLoading = false;
          })
          .catch(error => {
            console.log(error);
            this.authorsSelectLoading = false;
          })
    },

    loadShippers: function(){
      this.authorsSelectLoading = true;
      this.$axios
          .get(this.$api + "/api/shippers/",
              {headers: {'Content-Type': 'application/x-www-form-urlencoded'}})
          .then(resp => {
            console.log(resp);
            console.log(resp.data, this.model);
            this.shippers = resp.data;
            this.authorsSelectLoading = false;
          })
          .catch(error => {
            console.log(error);
            this.authorsSelectLoading = false;
          })
    },
    loadCategories: function(){
      this.authorsSelectLoading = true;
      this.$axios
          .get(this.$api + "/rest/categories/",
              {headers: {'Content-Type': 'application/x-www-form-urlencoded'}})
          .then(resp => {
            console.log(resp);
            console.log(resp.data, this.model);
            this.categories = resp.data;
            this.categorySelectLoading = false;
          })
          .catch(error => {
            console.log(error);
            this.categorySelectLoading = false;
          })
    },
    loadEntity: function(id){
      this.$axios
          .get(this.$api + "/rest/books/" + id,
              {headers: {'Content-Type': 'application/x-www-form-urlencoded'}})
          .then(resp => {
            console.log(resp);
            console.log(resp.data, this.model);
            this.model.name = resp.data.name;
            this.model.length = resp.data.length;
            this.model.filename = "";
            this.model.author = parseInt(resp.data.author);
            this.model.stockCount = resp.data.stockCount;
            this.model.category = resp.data.category;
            this.model.imageUrl = this.$api + "/resources/gfx/"+resp.data.cover_photo.url;
            this.model.description = resp.data.description;
            this.loading = false;
          })
          .catch(error => {
            console.log(error)
            this.loading = false;
          })
    },
    handleAddProduct(prod){
      console.log("addprod", prod);
      var exist = this.selectedproducts.filter(v=>{
        return v.productName == prod.productName
      })

      console.log(exist)

      if(exist.length == 0) {
        prod.quantity = 1;
        this.selectedproducts.push(prod);
      }
      else
        exist[0].quantity++;

      this.counter++;
      console.log(this.selectedproducts);

    },
    handleDeleteFromOrderAll(){
      this.selectedproducts = [];
    },
    handleDeleteFromOrder(id){
      this.selectedproducts = this.selectedproducts.filter(v=>{
        v.id != id;
      })
    },
    validate() {
      return new Promise((resolve, reject) => {
        this.$refs.Application.validate((valid) => {
          this.$emit('on-validate', valid, this.model)
          resolve(valid);
        });
      })
    },
  },
  mounted: function() {
    console.log("PROP", this.edit);

    if(this.edit) {  // property przekazane w VueRouter - patrz main.js
      this.editingId = this.$route.params.id;
      this.loadEntity(this.editingId);
    }

    this.loadCustomers();
    this.loadEmployees();
    this.loadShippers();
    this.loadCategories();

  },


  // watch: {
  //     $route(to, from) {
  //
  //     }
  // }

}
</script>
<style lang="scss">
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>