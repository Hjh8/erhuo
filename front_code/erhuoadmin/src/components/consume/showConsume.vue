<template>
  <div>
    <div id="consume">

    </div>
  </div>
</template>

<script>
import echarts from 'echarts'

export default{
  data(){
    return {
      //存放月销售额跟销售量
      sales:{},
      option = {
        xAxis: {
          type: 'category',
          data: ['1号', '1号', '1号', 'Thu', 'Fri', 'Sat', 'Sun']
        },
        yAxis: {
          type: 'value'
        },
        series: [{
          data: [820, 932, 901, 934, 1290, 1330, 1320],
          type: 'line'
        }]
      }
    }
  },
  mounted(){
    // 必须在DOM被渲染完毕时才可以初始化图表
    this.initEchart()
  },
  methods:{
    async initEchart(){
      await this.$axios.get('/admin/consume/get30DaySale')
      .then(res => {

        //初始化图表
        let myChart = echarts.init(document.getElementById('consume'));
        myChart.setOption(this.option,true);
      })
    }
  }
}
</script>

<style>
.salesment{
  background: #000;
  position: absolute;
  right: 7.5%;
  top:20%;
}
</style>