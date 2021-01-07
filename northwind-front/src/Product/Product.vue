<template>
    <div id="authors">

        <h3>
            Dodaj Product
        </h3>

<el-form>
    <el-row>
        <el-form ref="AddBook" :rules="rules" :model="model" v-loading="loading">
            <el-form-item label="Nazwa produktu" prop="productName">
                <el-input v-model="model.productName"></el-input>
            </el-form-item>
            <el-form-item label="Stan magazynowy:" prop="unitsInStock">
                <el-input type="text" v-model="model.unitsInStock" ></el-input>
            </el-form-item>
            <el-form-item label="Opis:" prop="description">
                <el-input type="textarea" v-model="model.description"></el-input>
            </el-form-item>
            <el-form-item label="Kategoria produktu:" prop="categoryID">
                <el-select v-loading="categorySelectLoading" v-model="model.categoryID" placeholder="Zacznij wpisywać nazwę kategorii" :no-match-text="'Brak wyników'" filterable>
                    <el-option
                            v-for="item in categories"
                            :key="item.category"
                            :label="item.categoryName"
                            :value="item.id">
                    </el-option>
                </el-select>
            </el-form-item>

          <el-form-item label="Supplier:" prop="supplierID">
            <el-select v-loading="categorySelectLoading" v-model="model.supplierID" placeholder="Zacznij wpisywać supplier" :no-match-text="'Brak wyników'" filterable>
              <el-option
                  v-for="item in suppliers"
                  :key="item.id"
                  :label="item.companyName"
                  :value="item.id">
              </el-option>
            </el-select>
          </el-form-item>
            <el-button type="success" @click="handleSubmit">Zapisz!</el-button>
        </el-form>
    </el-row>
</el-form>
    </div>
</template>
<script>
export default {
    name: 'authors',
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
            componentName: 'AddBook',
            mydata: [],
            loading: false,
            authorsSelectLoading: false,
            categorySelectLoading: false,
            editingId: false,
            suppliers: [],
            categories: [],
            model:{
              productName: "",
                length: "",
                author: "",
              unitsInStock:"",
                stockCount: "",
                description: "",
                categoryID: "",
              supplierID: "",
                imageUrl: "",
                filename: "",
                picture_name: "",
            },
            rules:{
              productName: [
                    { required: true, message: "Pole wymagane", trigger: 'change' }
                    //{ required: true, type: 'email', message: "Pole wymagane", trigger: 'change' }
                ],
                length: [
                    { required: true, validator: validateNumber,  message: "Wpisz poprawna dlugosc", trigger: 'change' }
                ],
              unitsInStock: [
                    { required: true, validator: validateNumber,  message: "Wpisz poprawny stan magazynowy", trigger: 'change' }
                ],
                author: [
                    { required: true,  message: "Pole wymagane", trigger: 'change' }
                ],
              categoryID: [
                    { required: true,  message: "Pole wymagane", trigger: 'change' }
                ],
              supplierID: [
                { required: true,  message: "Pole wymagane", trigger: 'change' }
              ],
                description: [
                    { required: true,  message: "Pole wymagane", trigger: 'change' }
                ],
            }
        }
    },
    methods: {
        handleSubmit: function(){
            console.log(this.$refs);
            if(!this.valid(this.componentName))
                return false;

            this.loading = true;
            this.model.stockCount = parseInt(this.model.stockCount);
            var formData = this.prepareFormDataFromModel(this.model);
            var apiEndpoint = this.$api + "/api/product";
            var method = "POST";


            this.$axios({
                method: method,
                url: apiEndpoint,
                data: this.model,
                options: {headers: {'Content-Type': 'application/x-www-form-urlencoded'}}
                }).then(resp => {
                    console.log(resp)
                    this.loading = false;
                    if(method == "PUT"){
                        this.$message({
                            message: 'Poprawnie zmodyfikowano książke.',
                            type: 'success'
                        });
                        return;
                    }
                    this.$message({
                        message: 'Poprawnie dodano książkę.',
                        type: 'success'
                    });
                })
                .catch(error => {
                    console.log(error)
                    this.loading = false;
                    this.$message({
                        message: 'Error przy modyfikacji książki.',
                        type: 'error'
                    });
                })
        },
        loadCategories: function(){
            this.authorsSelectLoading = true;
            this.$axios
                .get(this.$api + "/api/categories/",
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

      loadSuppliers: function(){
        this.authorsSelectLoading = true;
        this.$axios
            .get(this.$api + "/api/suppliers/",
                {headers: {'Content-Type': 'application/x-www-form-urlencoded'}})
            .then(resp => {
              console.log(resp);
              console.log(resp.data, this.model);
              this.suppliers = resp.data;
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

        this.loadSuppliers();
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