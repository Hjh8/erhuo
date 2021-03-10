<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>

export default {
  created(){
  }
}
</script>

<style lang="less" scpoed>
html,body,#app{
  height: 100%;
  width: 100%;
  padding: 0;
  margin: 0;
  background-color: #f8f8f9;
  overflow-y: auto;
  ::-webkit-scrollbar {
  width: 5px;
  height: 5px;
  };
  ::-webkit-scrollbar-thumb:vertical {
  height: 5px;
  background-color: rgba(125, 125, 125, 0.7);
  };
}

</style>