<template>

    <div class="common-layout">
        <el-container style="min-height: 100vh">
            <el-aside :width="sideWidth + 'px'" style="box-shadow: 2px 0 6px rgb(0 21 41 / 35%);">
                <el-menu :default-openeds="['1', '3']" style="min-height: 100%; overflow-x: hidden"
                         background-color="#FFFFCC"
                         text-color="#663300"
                         active-text-color="coral"
                >
                    <div style="height: 60px; line-height: 60px; text-align:center">
                        <img src="../assets/hohai.jpg" alt="" style="width: 20px;position: relative;top: 5px;margin-right: 5px">
                        <b style="color: #42b983">学生管理系统</b>
                    </div>

                    <el-menu-item index="1" @click="studentManage">
                            <i class="el-icon-message"></i>
                        <el-icon><location /></el-icon>
                            <span>学生管理</span>
                        </el-menu-item>
                        <el-menu-item index="2" @click="courseManage">
                            <i class="el-icon-menu"></i>
                            <el-icon><camera/></el-icon>
                            <span>课程管理</span>
                        </el-menu-item>
                        <el-menu-item index="3" @click="gradeManage">
                            <i class="el-icon-setting"></i>
                            <el-icon><document /></el-icon>
                            <span>成绩管理</span>
                        </el-menu-item>

                        <el-menu-item index="4" @click="BarManage">
                            <i class="el-icon-setting"></i>
                            <el-icon><document /></el-icon>
                            <span>选课情况</span>
                        </el-menu-item>

                    </el-menu>
                </el-aside>
            <el-container>
                <el-header>
                    <div style="margin-top: -4%">
                        <el-menu
                                :default-active="activeIndex"
                                class="el-menu-demo"
                                mode="horizontal"
                                :ellipsis="false"
                        >

                            <div class="flex-grow" />
                            <el-menu-item index="1">
                                孙智军
                                <el-icon><User /></el-icon>

                            </el-menu-item>

                        </el-menu>
                    </div>
                </el-header>
                <el-main style="margin-top: -2%">
                    <router-view></router-view>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
    import router from "@/router";
    export default {
        name: "Home_",
        components: {},
        data(){
           return{

            }
        },
        methods:{
            studentManage(){
                router.push('/studentManage')
            },
            courseManage(){
                router.push('/courseManage')
            },
            gradeManage(){
                router.push('/gradeManage')
            },
            BarManage(){
                router.push('/BarChart')
            },
        }
    }
</script>

<style scoped>
    .flex-grow {
        flex-grow: 1;
    }
</style>