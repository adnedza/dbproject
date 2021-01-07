<template>
    <div id="authors">
        <h3>
            Dodaj supplier
        </h3>
<el-form>
    <el-row>
        <el-form ref="AddCategory" :rules="rules" :model="model" v-loading="loading">
            <el-form-item label="Adres" prop="address">
                <el-input v-model="model.address"></el-input>
            </el-form-item>
            <el-form-item label="Miasto" prop="city">
              <el-input v-model="model.city"></el-input>
            </el-form-item>
          <el-form-item label="Kod pocztowy" prop="postalCode">
            <el-input v-model="model.postalCode"></el-input>
          </el-form-item>

          <el-form-item label="Nazwa firmy" prop="companyName">
            <el-input v-model="model.companyName"></el-input>
          </el-form-item>
          <el-form-item label="Contact name" prop="contactName">
            <el-input v-model="model.contactName"></el-input>
          </el-form-item>
          <el-form-item label="country" prop="country">
            <el-input v-model="model.country"></el-input>
          </el-form-item>
          <el-form-item label="fax" prop="fax">
            <el-input v-model="model.fax"></el-input>
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
            componentName: 'AddCategory',
            mydata: [],
            loading: false,
            authorsSelectLoading: false,
            categorySelectLoading: false,
            editingId: false,
            authors: [],
            categories: [],
            model:{
              name: "",
              address: "",
              city:"",
              companyName: "",
              contactName: "",
              country: "",
              fax: "",
              postalCode: "",
            },
            rules:{
              name: [
                    { required: true, message: "Pole wymagane", trigger: 'change' }
                    //{ required: true, type: 'email', message: "Pole wymagane", trigger: 'change' }
                ],
              address: [
                    { required: true,  message: "Pole wymagane", trigger: 'change' }
                ],
              city: [
                { required: true,  message: "Pole wymagane", trigger: 'change' }
              ],
              companyName: [
                { required: true,  message: "Pole wymagane", trigger: 'change' }
              ],
              contactName: [
                { required: true,  message: "Pole wymagane", trigger: 'change' }
              ],
              country: [
                { required: true,  message: "Pole wymagane", trigger: 'change' }
              ],
              fax: [
                { required: true,  message: "Pole wymagane", trigger: 'change' }
              ],
              postalCode: [
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
            var apiEndpoint = this.$api + "/api/supplier";
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
                        message: 'Poprawnie dodano supplier.',
                        type: 'success'
                    });
                })
                .catch(error => {
                    console.log(error)
                    this.loading = false;
                    this.$message({
                        message: 'Error przy dodawaniu supplier.',
                        type: 'error'
                    });
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
    },

}
</script>
