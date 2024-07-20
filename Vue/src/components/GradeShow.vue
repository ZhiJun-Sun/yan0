<template>

    <div style="margin-left: -60%;margin-top: -1.5%;margin-bottom: 1%">
        <el-input style="width: 200px;margin-top: 10px" placeholder="请输入课程id" suffix-icon="search" v-model="this.cid"></el-input>
        <el-input style="width: 200px;margin-left:10px;margin-top: 10px" placeholder="请输入学生id" suffix-icon="search" v-model="this.sid"></el-input>
        <el-button style="margin-left: 20px" @click="load" type="primary">搜索</el-button>
    </div>
    <el-button class="mt-4" style="width: 100px;margin-top: 10px;margin-left: 10px;margin-bottom: 10px" @click="this.clickCreate" type="primary">
        新增
        <i class="el-icon-circle-plus-outline"></i></el-button>
    <el-table :data="tableData" stripe style="width: 100%" max-height="700px" border>
        <el-table-column fixed prop="sid" label="sid" width="200" />
        <el-table-column prop="cid" label="cid" width="200" />
        <el-table-column prop="score" label="score" width="200" />
<!--        <el-table-column prop="coursename" label="coursename" width="250" />-->
<!--        <el-table-column prop="name" label="name" width="250" />-->
        <el-table-column fixed="right" label="Operations" min-width="120">
            <template #default="scope">
                <el-popover
                        placement="left"
                        title="编辑"
                        :width="200"
                        trigger="click"
                        content="this is content, this is content, this is content"
                >
                    <div><b>sid</b><el-input v-model="temp.sid"></el-input></div>
                    <div><b>cid</b><el-input v-model="temp.cid"></el-input></div>
                    <div><b>score</b><el-input v-model="temp.score"></el-input></div>
                    <el-button link type="primary" size="small" @click="handleSave(scope.$index, scope.row)">保存</el-button>
                    <template #reference>
                        <el-button  type="success" size="" @click="handleEdit(scope.$index, scope.row)">
                            编辑
                        </el-button>
                    </template>
                </el-popover>
                <el-button  type="danger"  size="" @click="handleDelete(scope.$index, scope.row)">
                    删除
                </el-button>
            </template>
        </el-table-column>
    </el-table>

    <el-pagination
            @current-change="currentChange"
            :current-page="this.pageNum"
            :page-size="6"
            :pager-count="11"
            layout="total,prev, pager, next"
            :total="this.total"
            :size="'large'"
    />
    <el-dialog
            v-model="dialogVisible"
            title="ADD"
            width="500"
            :before-close="handleClose"
    >
        <span>新增成绩信息</span>
        <div style="margin-top: 15px"><el-input v-model="temp.sid" placeholder="sid"></el-input></div>
        <div style="margin-top: 15px"><el-input v-model="temp.cid" placeholder="cid"></el-input></div>
        <div style="margin-top: 15px"><el-input v-model="temp.score" placeholder="score"></el-input></div>
        <template #footer>
            <div class="dialog-footer">
                <el-button @click="dialogVisible = false">取消</el-button>
                <el-button type="primary" @click="handleCreate">
                    提交
                </el-button>
            </div>
        </template>
    </el-dialog>
</template>

<script>
    import { ref } from 'vue'
    import axios from "axios";
    export default {
        name: "GradeShow",
        data(){
            return{
                sid:'',
                cid:"",
                pageNum:1,
                total:0,
                dialogVisible:false,
                temp:{
                    sid:"",
                    cid:"",
                    score:""
                },
                tableData :ref([])
            }
        },
        created() {
            this.load()
        },
        methods:{
            handleDelete(index,row)
            {
                const res={
                    "sid":row.sid,
                    "cid":row.cid,
                    "score":row.score
                }
                axios.post("http://localhost:8081/grade/delete/",res).then(
                    resp=>{
                        if(resp.data)
                        {
                            this.$message.success("删除成功")
                        }else{
                            this.$message.error("删除失败")
                        }
                    }
                )
                this.load()
            },
            load(){
                axios.get("http://localhost:8081/grade/page?pageNum="+this.pageNum+"&pageSize=6&cid="+this.cid+"&sid="+this.sid).then(
                    resp=>{
                        this.tableData=resp.data.records
                        this.total=resp.data.total
                    }
                )
            },
            currentChange(val){
                this.pageNum=val
                this.load()
            },
            handleEdit(index,row){
                this.course={
                    cid:row.cid,
                    sid:row.sid,
                    score:row.score,
                }

            },
            handleSave(){
                axios.post("http://localhost:8081/grade/save",this.temp).then(
                    resp=>{
                        if(resp.data)
                        {
                            this.$message.success("修改成功")
                        }else{
                            this.$message.error("修改失败")
                        }
                    }
                )
                this.load()
            },
            clickCreate()
            {
                this.temp={
                    cid:"",
                    sid:"",
                    score: "",
                }
                this.dialogVisible=true
            },
            handleCreate(){
                axios.post("http://localhost:8081/grade/save",this.temp).then(
                    resp=>{
                        if(resp.data)
                        {
                            this.$message.success("添加成功")
                        }else{
                            this.$message.error("添加失败")
                        }
                    }
                )
                this.load()
                this.dialogVisible = false
            }
        }
    }
</script>

<style scoped>

</style>