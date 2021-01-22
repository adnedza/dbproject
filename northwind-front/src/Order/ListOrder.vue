<template>
    <div class="">
        <h3>
            Lista zamówień
        </h3>
       <el-input
            placeholder="Wyszukaj"
            v-model="inputText"
            clearable>
        </el-input>
        <el-table
                :data="tableData"
                style="width: 100%"
                id="booksList"
                empty-text="Brak wyników"
        >
            <el-table-column prop="id" label="Identyfikator"></el-table-column>
            <el-table-column prop="orderDate" label="Data złożenia"></el-table-column>

            <el-table-column
                    fixed="right"
                    label="Akcje"
                    width="120">
                <template slot-scope="scope">
                    <div class="buttons" v-if="!order">
                        <router-link tag="el-button" class="myClass" id="button" :to="'/order/view/' + scope.row.id">Szczegóły</router-link>
                    </div>
                </template>
            </el-table-column>
        </el-table>

        <el-dialog
                title="Potwierdzenie"
                :visible.sync="dialogVisible"
                width="60%"
                :before-close="handleClose">
            <span>Czy na pewno chcesz usunąć książkę: {{toDeleteId}}?</span>
            <span slot="footer" class="dialog-footer">
            <el-button @click="dialogVisible = false">Anuluj</el-button>
            <el-button type="primary" @click="handleConfirmDelete">Potwierdzam</el-button>
        </span>
        </el-dialog>
    </div>
</template>
<script>
    export default {
        name: 'prodlist',
      props: ['order'],
        data() {
            return {
                test: 'test',
                loading: true,
                tableData: [],
                inputText: '',
                dialogVisible: false,
                toDeleteId: -1,
            }
        },
        methods: {
            loadProds: function () {
                this.loading = true;

                this.$axios.get(this.$api + "/api/orders", {})
                    .then(resp => {
                        this.tableData = resp.data;
                        console.log(this.tableData);
                        this.loading = false
                    })
                    .catch(resp => {
                        console.log("error", resp);
                        this.loading = false
                    })
            },
            handleDelete(variable) {
                console.log(variable);
                this.toDeleteId = variable;
                this.dialogVisible = true;
            },
            handleAdd(variable) {
              this.$emit("addproduct", variable);
            },
            handleEdit(id){
                //redirect na stronę /authors/id - przekierowanie do komponentu Authors poprzez Router ustawiony w main.js
               // this.$router.push("/product/" + id);

            },
            handleClose(){

            },
            handleConfirmDelete() {
                this.dialogVisible = false;

                this.$axios.delete(this.$api + "/api/product/" + this.toDeleteId)
                    .then(resp => {
                        console.log(resp);
                        this.$message({
                            message: 'Poprawnie usunięto product.',
                            type: 'success'
                        });
                        this.loadProds();  //zeby odswiezyla sie lista
                    })
                    .catch(error => {
                        console.log(error);
                        this.$message({
                            message: 'Błąd przy usuwaniu.',
                            type: 'error'
                        });
                    });
            },
            //tego jeszcze nie ma
            // handleEdit(id) {
            //     //redirect na stronę /authors/id - przekierowanie do komponentu Authors poprzez Router ustawiony w main.js
            //     this.$router.push("/authors/" + id);
            // }
        },
        computed: {
            // filteredTableData: function(){
            //     return this.tableData.filter((book) => {
            //         if (book.productName.toLowerCase().match(this.inputText.toLowerCase())) {
            //             return book.productName;
            //         }
            //     });
            // }
        },
         mounted() {
             this.loadProds()
        }
    }
</script>
<style lang="scss">
    #booksList {
        .buttons {
            button {
                display: block;
                width: 100%;
                margin: 5px auto;
            }
        }
    }
</style>