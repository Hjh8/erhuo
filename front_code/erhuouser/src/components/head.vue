<template>
  <Header style="background-color:white; margin-left:10;padding-top: 5px;">
    <div style="height: 100%; width:100%;">
      <span class="title">
        二 货 基 地
      </span>
      <span class="span_center">
        <Select v-model="select" slot="prepend" style="width: 80px;">
          <Option value="goods">商品</Option>
          <Option value="seller">用户</Option>
        </Select>
        <Input v-model="searchValue" style="width:35%;heigth:100%;" />
        <Button icon="ios-search" @click="searchSome"></Button>
      </span>

      <div class="rightDiv" v-if="!flag">
        <Button icon="md-person" type="text" @click="login"
          style="margin:20px;color: rgba(245, 18, 18, 0.938);">
          发布二货
        </Button>
        <Button icon="logo-reddit" type="text" @click="register">
          新人注册
        </Button>
      </div>
      <profile v-else class="rightDiv"></profile>
    </div>
  </Header>
</template>

<script>
import profile from './profile'

export default {
  created(){
    if(window.sessionStorage.getItem('token')){
      this.flag = true;
    }
  },
  data(){
    return {
      select:"goods",
      searchValue:"",
      flag:false, // 显示登录按钮还是个人信息按钮
    }
  },
  methods:{
    login(){
      this.$router.push('/login');
    },
    register(){
      this.$router.push('register');
    },
    searchSome(){
      this.$router.push({
        name:'search',
        params:{
          select:this.select,
          value:this.searchValue
        }
      }, onComplete => {}, onAbort => {});
      this.searchValue = '';
      
    }
  },
  components:{
    profile
  }
}
</script>

<style>
.title{
  width: 20%;
  height: 110%;
  color: rgba(39, 102, 197, 0.938);
  font: tahoma,arial,'Hiragino Sans GB',sans-serif;
  font-size: 200%;
  font-weight: bolder;
  margin-left: 6%;
  margin-right: 20px;
  padding:20px;
  text-align: center;
  
}
.span_center{
  padding: 5px;
  height: 100%;
  margin-left: 2%;
}
.rightDiv{
  display: inline-block;
}
</style>