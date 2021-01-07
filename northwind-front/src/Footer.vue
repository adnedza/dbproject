<template>
    <div id="foot_proj">
        <el-row  type="flex" class="row-bg" justify="space-around">
            <el-col>
                <i class="el-icon-info"></i>Northwind - Bazy Danych<br>
                &copy; 2021<br><br>
                <span> <i class="el-icon-user-solid"></i>Authors:</span>

                <div>Igor Dzierwa</div>
                <div>Adrian Nędza</div>
                <div>Konrad Makuch</div>

            </el-col>
        </el-row>
    </div>

</template>
<script>
    export default {
        name: 'footer_component'
    }
</script>
<style lang="scss">
    #foot_proj{
        padding-top: 60px;
        text-align: center;
    }
</style>