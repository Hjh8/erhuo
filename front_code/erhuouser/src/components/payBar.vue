<template>
  <div class="sideDiv">
    <Menu active-name="1" style="height:50%;">
      <MenuGroup title="会员区/广告栏">
        <Tag v-for="item in count" :key="item" :name="item" closable
         @on-close="handleClose" type="dot" color="error"> 
         标签{{ item + 1 }}
        </Tag>
      </MenuGroup>
    </Menu>
  </div>
</template>

<script>
export default {
  data () {
    return {
      count: [0, 1, 2]
    }
  },
  methods: {
    handleClose(event, name) {
      const index = this.count.indexOf(name);
      this.count.splice(index, 1);
    }
  }
}
</script>

<style>

</style>