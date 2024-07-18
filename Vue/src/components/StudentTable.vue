<template>

    <div style="margin-left: -77%;margin-top: -1.5%;margin-bottom: 1%">
        <el-input style="width: 200px;margin-top: 10px" placeholder="请输入姓名" suffix-icon="search"
                  v-model="this.studentName"></el-input>
        <el-button type="primary " style="margin-left: 20px" @click="load">搜索</el-button>
    </div>

        <el-button class="mt-4" style="width: 100px;margin-top: 10px;margin-left: 10px;margin-bottom: 10px" @click="this.clickCreate" type="primary">
            新增
            <i class="el-icon-circle-plus-outline"></i></el-button>


    <el-table :data="tableData" stripe style="width: 100%" max-height="700px" border>
        <el-table-column fixed prop="sid" label="sid" width="100"/>
        <el-table-column prop="name" label="name" width="100"/>
        <el-table-column prop="age" label="age" width="100"/>
        <el-table-column prop="sex" label="sex" width="100"/>
        <el-table-column prop="phone" label="phone" width="120"/>
        <el-table-column prop="address" label="address" width="150"/>
        <el-table-column prop="hometown" label="hometown" width="100"/>
        <el-table-column prop="xueyuan" label="xueyuan" width="100"/>
        <el-table-column prop="shuyuan" label="shuyuan" width="100"/>
        <el-table-column prop="grade" label="grade" width="100"/>
        <el-table-column fixed="right" label="Operations" min-width="120">
            <template #default="scope">
                <el-popover
                        placement="left"
                        title="编辑"
                        :width="250"
                        trigger="click"
                        content="this is content, this is content, this is content"
                >
                    <div><b>name</b>
                        <el-input v-model="student.name"></el-input>
                    </div>
                    <div><b>sex</b>
                        <el-input v-model="student.sex"></el-input>
                    </div>
                    <div><b>age</b>
                        <el-input v-model="student.age"></el-input>
                    </div>
                    <div><b>phone</b>
                        <el-input v-model="student.phone"></el-input>
                    </div>
                    <div><b>address</b>
                        <el-input v-model="student.address"></el-input>
                    </div>
                    <div><b>hometown</b>
                        <el-input v-model="student.hometown"></el-input>
                    </div>
                    <div><b>xueyuan</b>
                        <el-input v-model="student.xueyuan"></el-input>
                    </div>
                    <div><b>shuyuan</b>
                        <el-input v-model="student.shuyuan"></el-input>
                    </div>
                    <div><b>grade</b>
                        <el-input v-model="student.grade"></el-input>
                    </div>
                    <el-button type="primary" size="small" @click="handleSave(scope.$index, scope.row)">保存
                    </el-button>
                    <template #reference>
                        <el-button  type="success" size="" @click="handleEdit(scope.$index, scope.row)">
                            编辑
                            <i class="el-icon-edit"></i>
                        </el-button>
                    </template>
                </el-popover>
                <el-button  type="danger" size="" @click="handleDelete(scope.$index, scope.row)">
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
        <span>新建学生信息</span>
        <div style="margin-top: 15px">
            <el-input v-model="student.sid" placeholder="sid"></el-input>
        </div>
        <div style="margin-top: 15px">
            <el-input v-model="student.name" placeholder="name"></el-input>
        </div>
        <div style="margin-top: 15px">
            <el-input v-model="student.sex" placeholder="sex"></el-input>
        </div>
        <div style="margin-top: 15px">
            <el-input v-model="student.age" placeholder="age"></el-input>
        </div>
        <div style="margin-top: 15px">
            <el-input v-model="student.phone" placeholder="phone"></el-input>
        </div>
        <div style="margin-top: 15px">
            <el-input v-model="student.address" placeholder="address"></el-input>
        </div>
        <div style="margin-top: 15px">
            <el-input v-model="student.hometown" placeholder="hometown"></el-input>
        </div>
        <div style="margin-top: 15px">
            <el-input v-model="student.xueyuan" placeholder="xueyuan"></el-input>
        </div>
        <div style="margin-top: 15px">
            <el-input v-model="student.shuyuan" placeholder="shuyuan"></el-input>
        </div>
        <div style="margin-top: 15px">
            <el-input v-model="student.grade" placeholder="grade"></el-input>
        </div>
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
import {ref} from 'vue'
import axios from "axios";

export default {
    name: "StudentTable",
    data() {
        return {
            studentClass: '',
            studentName: "",
            pageNum: 1,
            total: 0,
            dialogVisible: false,
            student: {
                id: "",
                name: "",
                age: "",
                sex: "",
                phone: "",
                address: "",
                hometown: "",
                xueyuan: "",
                shuyuan: "",
                grade: "",
            },
            tableData: ref([])
        }
    },
    created() {
        this.load()
    },
    methods: {
        handleDelete(index, row) {
            axios.delete("http://localhost:8081/student/delete/" + row.sid).then(
                resp => {
                    if (resp.data) {
                        this.$message.success("修改成功")
                    } else {
                        this.$message.error("修改失败")
                    }
                }
            )
            this.load()
        },
        load() {
            /*fetch("http://localhost:8081/student/page?pageNum="+this.pageNum+"&pageSize=5&studentName="+this.studentName+"&studentClass="+this.studentClass).then(
                res=>res.json()).then(res=>{
                this.tableData=res.records
                this.total=res.total
            })*/
            axios.get("http://localhost:8081/student/page?pageNum=" + this.pageNum + "&pageSize=6&studentName=" + this.studentName + "&studentClass=" + this.studentClass).then(
                resp => {
                    this.tableData = resp.data.records
                    this.total = resp.data.total
                }
            )
        },
        currentChange(val) {
            this.pageNum = val
            this.load()
        },
        handleEdit(index, row) {
            this.student = {
                sid: row.sid,
                name: row.name,
                age: row.age,
                sex: row.sex,
                phone: row.phone,
                address: row.address,
                hometown: row.hometown,
                xueyuan: row.xueyuan,
                shuyuan: row.shuyuan,
                grade: row.grade
            }

        },
        handleSave() {
            axios.post("http://localhost:8081/student/save", this.student).then(
                resp => {
                    if (resp.data) {
                        this.$message.success("修改成功")
                    } else {
                        this.$message.error("修改失败")
                    }
                }
            )
            this.load()
        },
        clickCreate() {
            this.student = {
                sid: "",
                name: "",
                age: "",
                sex: "",
                phone: "",
                address: "",
                hometown: "",
                xueyuan: "",
                shuyuan: "",
                grade: "",
            }
            this.dialogVisible = true
        },
        handleCreate() {
            axios.post("http://localhost:8081/student/save", this.student).then(
                resp => {
                    if (resp.data) {
                        this.$message.success("添加成功")
                    } else {
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