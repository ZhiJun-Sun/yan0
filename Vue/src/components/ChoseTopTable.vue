<template>

    <div style="margin-left: -60%;margin-top: -1.5%;margin-bottom: 1%">
        <el-input style="width: 200px;margin-top: 10px" placeholder="请输入课程id" suffix-icon="search" v-model="this.cid"></el-input>
        <el-button style="margin-left: 20px" @click="load" type="primary">确定</el-button>
    </div>
    <el-table :data="tableData" stripe style="width: 100%" max-height="700px" border>
        <el-table-column fixed prop="sid" label="sid" width="200" />
        <el-table-column prop="name" label="name" width="200" />
        <el-table-column prop="coursename" label="coursename" width="200" />
        <el-table-column prop="score" label="score" width="200" />
<!--        <el-table-column prop="coursename" label="coursename" width="250" />-->
<!--        <el-table-column prop="name" label="name" width="250" />-->
      
    </el-table>

    <el-pagination
            @current-change="currentChange"
            :current-page="this.pageNum"
            :page-size="10"
            :pager-count="11"
            layout="total,prev, pager, next"
            :total="this.total"
            :size="'large'"
    />
 
</template>

<script>
    import { ref } from 'vue'
    import axios from "axios";
    export default {
        name: "ChoseTopTable",
        data(){
            return{
                sid:'',
                cid:'',
                name:'',
                coursename:'',
                pageNum:1,
                total:0,
                tableData :ref([])
            }
        },
        created() {
            this.load()
        },
        methods:{
            load(){
                axios.get("http://localhost:8081//page?pageNum="+this.pageNum+"&pageSize=6&cid="+this.cid+"&sid="+this.sid).then(
                    resp=>{
                        this.tableData=resp.data.records
                        this.total=resp.data.total
                    }
                )
            },

        }
    }
</script>

<style scoped>

</style>