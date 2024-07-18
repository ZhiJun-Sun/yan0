<template>
     <div id="main" style="width: 100%; height: 600px" ></div>
</template>



  <script>
import * as echarts from "echarts";
export default {
  name:"BarChart",
  data() {
    return{

    }
  },
  mounted() {
    var chartDom = document.getElementById('main');
    var myChart = echarts.init(chartDom);
    var option = {
      textStyle:{
        color:'black',
      },

      title: {
        text: '选课学生数量变化',
        left: 'center',
        textStyle: {
            color: "black",
            fontSize:"40",
            lineHeight:60,
            
          },
      },
      xAxis: {
        type: 'category',
        data: [],
        axisLabel: {
            textStyle: {
              fontSize: "25",
            },
          },
      },
      yAxis: {
        type: 'value',
        axisLabel: {
            textStyle: {
              fontSize: "25",
            },
          },
      },   

      series: [
      {
      data: [],
      type: 'bar'
    }
      ]
    };
    myChart.setOption(option);
    window.addEventListener("resize",function(){
        myChart.resize();
    });
  }
};



</script>
<style scoped>
</style>