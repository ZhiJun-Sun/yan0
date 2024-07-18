import { createRouter,createWebHistory} from 'vue-router'
import Home from '../pages/Home'
import StudentTable from "@/components/StudentTable";
import GradeTable from "@/components/GradeTable";
import CourseTable from "@/components/CourseTable";
import BarChart from "@/components/BarChart";
import ChoseTopTable from "@/components/ChoseTopTable"
const routes=[
    {
        path:'/',
        component:Home,
        children:[
            {
                path:'',
                component:StudentTable
            },
            {
                path:'studentManage',
                component:StudentTable
            },
            {
                path:'gradeManage',
                component: GradeTable
            },
            {
                path:'courseManage',
                component: CourseTable
            },
            {
                path:'BarChart',
                component: BarChart
            },
            {
                path:'ChoseTopTable',
                component: ChoseTopTable
            }
        ]
    },
]

export default createRouter({
    history:createWebHistory(),
    routes:routes
})