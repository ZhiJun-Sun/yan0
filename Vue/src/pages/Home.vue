<template>
  
    <el-container style="min-height: 100vh">
  
      <el-aside :width="sideWidth + 'px'" style="box-shadow: 2px 0 6px rgb(0 21 41 / 35%);">
        <el-menu :default-openeds="['1', '3']" style="min-height: 100%; overflow-x: hidden"
                 background-color="rgb(48, 65, 86)"
                 text-color="#fff"
                 active-text-color="#ffd04b"

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

                        <el-menu-item index="3" @click="gradeShow">
                            <i class="el-icon-setting"></i>
                            <el-icon><document /></el-icon>
                            <span>成绩管理</span>
                        </el-menu-item>

                        <el-menu-item index="4" @click="gradeManage">
                            <i class="el-icon-setting"></i>
                            <el-icon><document /></el-icon>
                            <span>成绩列优</span>
                        </el-menu-item>

                        <el-menu-item index="5" @click="BarChart">
                            <i class="el-icon-setting"></i>
                            <el-icon><document /></el-icon>
                            <span>选课情况</span>
                        </el-menu-item>

        </el-menu>
      </el-aside>
  
      <el-container>
        <el-header class="header-bg" style="font-size: 12px; border-bottom: 1px solid #ccc; line-height: 60px; display: flex;margin-top: -5.5%;margin-bottom: 1px">
          <div style="flex: 1; font-size: 20px">
            
          </div>
            <span style="font-family: kaiti;font-size: large">孙智军</span>
        </el-header>
        <div class="background-image-container">
        <el-main>
          <router-view></router-view>
        </el-main>
        </div>
  
      </el-container>
    </el-container>
  </template>
  
  <script>
  import router from "@/router";
  export default {
    name: 'Home_',
    data() {
     
      return {
       
      }
    },
    methods: {
        studentManage(){
                router.push('/studentManage')
            },
            courseManage(){
                router.push('/courseManage')
            },
            gradeManage(){
                router.push('/gradeManage')
            },
            gradeShow(){
                router.push('/gradeShow')
            },
            BarChart(){
                router.push('/BarChart')
            },

    }
  }
  </script>
  
  <style>
  .headerBg {
    background: #eee!important;
  }
  .background-image-container {
    margin-top: 20px;
  width: 100%;
  height: 100%;
  background-image: url('https://ts1.cn.mm.bing.net/th/id/R-C.2779f59abecaef5c192755ddccdcc97a?rik=2znqXSPfc4cLog&riu=http%3a%2f%2fjjzx.know.edu.cn%2fueditor%2fjsp%2fupload%2fimage%2f20200306%2f1583462425553016840.jpg&ehk=CMlfDMFE%2b4xfZr8b7duWH2qaYhagy925jTMJwixXtEM%3d&risl=&pid=ImgRaw&r=0');
  background-size: cover;
  background-position: center;
  padding-top: 20px;

}


  </style>