<template>
  <div class="index">
    <!-- 头部 -->
    <h></h>

    <Divider />
    <!-- 侧边分类栏 -->
    <div style="height: 71%;width:240px; display: inline-block;">
      <sideBar style="margin-top:12%;margin-left:30%;">
      </sideBar>
    </div>

    <!-- 中间显示商品 -->
    <router-view></router-view>

    <!-- 会员区 -->
    <payBar class="payBarDiv"></payBar>

    <Footer class="layout-footer-center">
      2020 &copy; CodeKiang. 此项目仅用于Web大作业
    </Footer>
  </div>
</template>

<script>
import h from './head'
import payBar from './payBar'
import sideBar from './sideBar'

export default {
  components:{
    h, sideBar, payBar
  }
}
</script>

<style lang="less" scpoed>
.layout-footer-center{
  text-align: center;
  width: 100%;
  height: 8%;
  padding-bottom: 30px;
  position:fixed;
  bottom: 0;
  border-top: solid rgba(126, 121, 121, 0.336);
}
.payBarDiv{
  position: absolute;
  top: 25%;
  right:4%;
  height: 50%;
}
.index{
  height: 100%;
  width: 100%;
  padding: 0;
  margin: 0;
}
</style>