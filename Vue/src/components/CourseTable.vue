<template>

    <div style="margin-left: -60%;margin-top: -1.5%;margin-bottom:1%">
        <el-input style="width: 200px;margin-top: 10px" placeholder="请输入课程名称" suffix-icon="search" v-model="this.courseName"></el-input>

        <el-input style="width: 200px;margin-left:10px;margin-top: 10px" placeholder="请输入课程学分" suffix-icon="search" v-model="this.grade"></el-input>
        <el-button type="primary" style="margin-left: 20px" @click="load" >搜索</el-button>
    </div>
    <el-button class="mt-4" style="width: 100px;margin-top: 10px;margin-left: 10px;margin-bottom: 10px" @click="this.clickCreate" type="primary">
        新增
        <i class="el-icon-circle-plus-outline"></i></el-button>

    <el-table :data="tableData" stripe style="width: 100%" max-height="700px" border>
        <el-table-column fixed prop="cid" label="cid" width="100" />
        <el-table-column prop="coursename" label="coursename" width="200" />
        <el-table-column prop="grade" label="grade" width="100" />
        <el-table-column prop="coursescore" label="coursescore" width="200" />
        <el-table-column prop="descriptions" label="descriptions" width="300" />
        <el-table-column fixed="right" label="Operations" min-width="200">
            <template #default="scope">
                <el-popover
                        placement="left"
                        title="修改"
                        :width="200"
                        trigger="click"
                        content="this is content, this is content, this is content"
                >
                    <div><b>coursename</b><el-input v-model="course.coursename"></el-input></div>
                    <div><b>grade</b><el-input v-model="course.grade"></el-input></div>
                    <div><b>coursescore</b><el-input v-model="course.coursescore"></el-input></div>
                    <div><b>descriptions</b><el-input v-model="course.descriptions"></el-input></div>
                    <el-button link type="primary" size="" @click="handleSave(scope.$index, scope.row)">保存</el-button>
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
        <span>新建课程信息</span>
        <div style="margin-top: 15px"><el-input v-model="course.cid" placeholder="cid"></el-input></div>
        <div style="margin-top: 15px"><el-input v-model="course.coursename" placeholder="coursename"></el-input></div>
        <div style="margin-top: 15px"><el-input v-model="course.grade" placeholder="grade"></el-input></div>
        <div style="margin-top: 15px"><el-input v-model="course.coursescore" placeholder="coursescore"></el-input></div>
        <div style="margin-top: 15px"><el-input v-model="course.descriptions" placeholder="descriptions"></el-input></div>
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
        name: "CourseTable",
        data(){
            return{
                grade:'',
                courseName:"",
                pageNum:1,
                total:0,
                dialogVisible:false,
                course:{
                    cid:"",
                    coursename:"",
                    grade:"",
                    coursescore:"",
                    descriptions:"",
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
                axios.delete("http://localhost:8081/course/delete/"+row.cid).then(
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
            load(){
                axios.get("http://localhost:8081/course/page?pageNum="+this.pageNum+"&pageSize=6&courseName="+this.courseName+"&grade="+this.grade).then(
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
                    coursename:row.coursename,
                    grade:row.grade,
                    coursescore:row.coursescore,
                    descriptions:row.descriptions,
                }

            },
            handleSave(){
                axios.post("http://localhost:8081/course/save",this.course).then(
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
                this.course={
                    cid:"",
                    coursename:"",
                    grade:"",
                    coursescore:"",
                    descriptions:"",
                }
                this.dialogVisible=true
            },
            handleCreate(){
                axios.post("http://localhost:8081/course/save",this.course).then(
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