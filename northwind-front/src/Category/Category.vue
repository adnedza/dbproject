<template>
    <div id="authors">
        <h3>
            Dodaj kategorię
        </h3>
<el-form>
    <el-row>
        <el-form ref="AddCategory" :rules="rules" :model="model" v-loading="loading">
            <el-form-item label="Nazwa kategorii" prop="categoryName">
                <el-input v-model="model.categoryName"></el-input>
            </el-form-item>
            <el-form-item label="Opis:" prop="description">
              <el-input type="textarea" v-model="model.description"></el-input>
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
                description: "",
                picture: "test",
            },
            rules:{
                categoryName: [
                    { required: true, message: "Pole wymagane", trigger: 'change' }
                    //{ required: true, type: 'email', message: "Pole wymagane", trigger: 'change' }
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
            var apiEndpoint = this.$api + "/api/category";
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
                        message: 'Poprawnie dodano kategorię.',
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
