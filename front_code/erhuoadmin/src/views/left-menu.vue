<template>
  <el-col>
    <div class="toggle-button">
      |||
    </div>

    <el-menu background-color="#545c64" text-color="#fff"
      active-text-color="#ffd04b">
      <el-submenu index="1">
        <template slot="title">
          <i class="el-icon-user"></i>
          <span>用户模块</span>
        </template>
        <el-menu-item index="range" @click="clickRange('range')">
          权限管理
        </el-menu-item>
      </el-submenu>
      <el-submenu index="2">
        <template slot="title">
          <i class="el-icon-goods"></i>
          <span>商品模块</span>
        </template>
        <el-menu-item index="sort">分类管理</el-menu-item>
      </el-submenu>
      <el-submenu index="3">
        <template slot="title">
          <i class="el-icon-s-order"></i>
          <span>订单模块</span>
        </template>
        <el-menu-item index="orders" @click="clickRange('showOrders')">
          订单管理
        </el-menu-item>
      </el-submenu>
      <el-submenu index="4">
        <template slot="title">
          <i class="el-icon-chat-line-round"></i>
          <span>评论模块</span>
        </template>
        <el-menu-item index="comment">评论管理</el-menu-item>
      </el-submenu>
      <el-submenu index="5">
        <template slot="title">
          <i class="el-icon-s-cooperation"></i>
          <span>广告模块</span>
        </template>
        <el-menu-item index="advertise">广告管理</el-menu-item>
      </el-submenu>
      <el-submenu index="6">
        <template slot="title">
          <i class="el-icon-s-marketing"></i>
          <span>销售统计</span>
        </template>
        <el-menu-item index="advertise">销售情况</el-menu-item>
      </el-submenu>
    </el-menu>
  </el-col>
</template>

<script>

export default {
  methods:{
    clickRange(path){
      this.$router.push(path)
    }
  }
}
</script>

<style lang='less' scoped>
.toggle-button{
  background-color: #4a5064;
  font-size: 10px;
  line-height: 24px;
  max-height: 24px;
  color: #fff;
  text-align: center;
  letter-spacing: 0.5em;
}
</style>