<template>
  <div id="main" style="width: 100%; height: 600px"></div>
</template>



<script>
import * as echarts from "echarts";

import axios from "axios";
export default {
  name: "BarChart",
  data() {
    return {
      xData: [],
      yData: []
    }
  },
  methods(){
    // forward()
    //         {
    //             var chartDom = document.getElementById('main');
    //             this.pageNum++
    //             this.start=(this.pageNum-1)*this.pageSize
    //             let index=0
    //             for(let i=this.start;i<this.pageSize+this.start;i++,index++)
    //             {
    //                 this.resX[index]=this.arrayX[i]
    //                 this.resY[index]=this.arrayY[i]
    //             }
    //             var myChart = echarts.init(chartDom);
    //             this.option.xAxis.data=this.resX
    //             this.option.series[0].data=this.resY
    //             myChart.setOption(this.option);
    //         },
    //         backward()
    //         {
    //             if(this.start<=0)
    //             {
    //                 ElMessage.error('不能再后退啦')
    //             }else
    //             {
    //                 var chartDom = document.getElementById('main');
    //                 this.pageNum--
    //                 this.start=(this.pageNum-1)*this.pageSize
    //                 let index=0
    //                 for(let i=this.start;i<this.pageSize+this.start;i++,index++)
    //                 {
    //                     this.resX[index]=this.arrayX[i]
    //                     this.resY[index]=this.arrayY[i]
    //                 }
    //                 var myChart = echarts.init(chartDom);
    //                 this.option.xAxis.data=this.resX
    //                 this.option.series[0].data=this.resY
    //                 myChart.setOption(this.option);
    //             }

    //         }
  },

  mounted() {

    //发起http请求，获取xData和yData的值
    axios.get("http://localhost:8081/grade/chart").then(
      resp => {
        console.log(resp)
        this.xData = resp.data.xdata
        this.yData = resp.data.ydata
        console.log("xData:"+this.xData)

        var chartDom = document.getElementById('main');
    var myChart = echarts.init(chartDom);
    var option = {
      textStyle: {
        color: 'black',
      },
      title: {
        text: '选课学生数量变化',
        left: 'center',
        textStyle: {
          color: "black",
          fontSize: "40",
          lineHeight: 60,
        },
      },
      tooltip: {
        // 触发类型，默认数据项触发，可选为：'item' | 'axis'  
        trigger: 'axis',
        // 坐标轴触发时，每一轴的默认格式化后的内容格式  
        axisPointer: {
          // 默认为直线，可选为：'line' | 'shadow'  
          type: 'shadow'
        },
        // 自定义内容格式器，支持字符串模板和回调函数两种形式  
        formatter: function (params) {
          // params 是一个数组，每个元素是一个系列中的数据项信息  
          // 这里假设只有一个系列，所以直接取 params[0]  
          var xAxisVal = params[0].name; // x 轴的值  
          var yAxisVal = params[0].value; // y 轴的值  
          return xAxisVal + '<br/>' + yAxisVal + ' 人';
        }
      },
      xAxis: {
        type: 'category',
        data: this.xData,
        axisLabel: {
          rotate:-60,
          textStyle: {
            fontSize: "18",
            lineStyle:{
              color:'black',
              width:10
            }
          },
        },
      },
      yAxis: {
        type: 'value',
        axisLabel: {
          textStyle: {
            fontSize: "18",
            lineStyle:{
              color:'red',
              width:10
            }
          },
        },
      },
      series: [
        {
          data: this.yData,
          type: 'bar'
        }
      ]
    };

    myChart.setOption(option);
    window.addEventListener("resize", function () {
      myChart.resize();
    });
      }
    )




    
  }
};



</script>
<style scoped></style>