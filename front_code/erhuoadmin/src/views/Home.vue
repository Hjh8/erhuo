<template>
  <div>
    <el-container>
      <el-header>
        <div class="sub">
          二 货 基 地 后 台 管 理 系 统 <i class="el-icon-truck"/>
        </div>
        <div>
          <div style="font-size:15px;text-align:center;margin-right:30px;">
            welcome, 管理员
          </div>
          <el-button type="primary" round size="mini" @click="out">
            退出
          </el-button>
        </div>
      </el-header>

      <el-container>
        <el-aside style="width:300px;height:643px;">
          <leftMenu></leftMenu>
        </el-aside>
        <el-container>
          <el-main>
            <router-view></router-view>
          </el-main>
        </el-container>

      </el-container>
      <el-container>
        <el-footer class="layout-footer-center">
          2020 &copy; CodeKiang. 此项目仅用于Web大作业
        </el-footer>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import leftMenu from './left-menu'

export default {
  methods:{
    out(){
      this.$router.push('\login')
    }
  },
  components:{
    leftMenu
  }
}
</script>

<style lang="less" scoped>
.layout-footer-center{
  text-align: center;
  width: 100%;
  background-color:rgba(28, 28, 29, 0.795);
  height: 8%;
  position:fixed;
  padding-top: 20px;
  color: white;
  bottom: 0;
  border-top: solid rgba(126, 121, 121, 0.336);
}
.sidebar-container{
  overflow-y: auto;
  ::-webkit-scrollbar {
  width: 1px;
  height: 5px;
  };
  ::-webkit-scrollbar-thumb:vertical {
  height: 1px;
  background-color: rgba(125, 125, 125, 0.7);
  };
}
.sub{
  height: 100%;
  width: 80%;
}
.el-header{
  background-color:#373D41;
  display: flex;
  justify-content: space-between;
  color:azure;
  font-size: 20px;
  align-items: center;
  div{
    display: inline-flex;
    align-items: center;
  }
}
.el-aside{
  background-color: #333744;
}
.el-dropdown{
  color: azure;
}
</style>