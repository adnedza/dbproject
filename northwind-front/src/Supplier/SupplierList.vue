<template>
    <div class="">
        <h3>
            Lista suppliers
        </h3>
        <el-table
                :data="categoryData"
                style="width: 100%"
                id="categoryList"
                empty-text="Brak wyników"
        >
            <el-table-column prop ="id" label="id"></el-table-column>
            <el-table-column prop ="companyName" label="companyName"></el-table-column>
        </el-table>
    </div>
</template>

<script>
    export default {
        name: "CategoryList",
        data() {
            return {
                categoryData: [],
            }
        },
        methods: {
            loadCategory: function () {
                this.$axios.get(this.$api + "/api/suppliers", {})
                    .then(resp => {
                        this.categoryData = resp.data;
                        console.log(this.categoryData);
                    })
                    .catch(resp => {
                        console.log("error", resp);
                    })
            },
        },
        mounted() {
            this.loadCategory()
        }
    }
</script>

<style>
</style>