<template>

    <div style="margin-left: -60%;margin-top: -1.5%;margin-bottom: 1%">
        <el-input style="width: 200px;margin-top: 10px" placeholder="请输入课程名称" suffix-icon="search"
            v-model="this.courseName"></el-input>
        <el-button style="margin-left: 20px" @click="load" type="primary">确定</el-button>
    </div>

    <el-table :data="tableData" stripe style="width: 100%" max-height="700px" border>
        <el-table-column fixed prop="sid" label="sid" style="width: 100%" />
        <el-table-column prop="cid" label="cid" style="width: 100%" />
        <el-table-column prop="score" label="score" style="width: 100%" />
        <el-table-column prop="courseName" label="courseName" style="width: 100%" />
        <el-table-column prop="name" label="name" style="width: 100%" />

    </el-table>

    <!-- <el-pagination @current-change="currentChange" :current-page="this.pageNum" :page-size="10" :pager-count="11"
        layout="total,prev, pager, next" :total="this.total" :size="'large'" /> -->
    <el-dialog v-model="dialogVisible" title="ADD" width="500" :before-close="handleClose">
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
    name: "GradeTable",
    data() {
        return {
            sid: '',
            cid: "",
            name: '',
            courseName: '',
            pageNum: 1,
            total: 0,
            dialogVisible: false,
            temp: {
                sid: "",
                cid: "",
                score: ""
            },
            tableData: ref([])
        }
    },
    created() {
        this.load()
    },
    methods: {
        handleDelete(index, row) {
            const res = {
                "sid": row.sid,
                "cid": row.cid,
                "score": row.score
            }
            axios.post("http://localhost:8081/grade/delete/", res).then(
                resp => {
                    if (resp.data) {
                        this.$message.success("删除成功")
                    } else {
                        this.$message.error("删除失败")
                    }
                }
            )
            this.load()
        },
        load() {
            axios.get("http://localhost:8081/grade/page?pageNum=" + this.pageNum + "&pageSize=10&cname=" + this.courseName).then(
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
            this.course = {
                cid: row.cid,
                sid: row.sid,
                score: row.score,
            }

        },
        handleSave() {
            axios.post("http://localhost:8081/grade/save", this.temp).then(
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
            this.temp = {
                cid: "",
                sid: "",
                score: "",
            }
            this.dialogVisible = true
        },
        handleCreate() {
            axios.post("http://localhost:8081/grade/save", this.temp).then(
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

<style scoped></style>