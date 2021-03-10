<template>
  <div>
    <div>
      <span style="margin:0 20px 0 50px;">
        <Button type="error" @click="release">我要发布</Button>
      </span>
      <span>
        <Button type="text" @click="showCard">
          <Icon type="md-cart" size="16"/>购物车
        </Button>
        <Button type="text" @click="myOrders">
          <Icon type="md-reorder" size="18"/>
          历史订单
        </Button>
      </span>
      <Dropdown>
        <Button shape="circle" type="text">
          我的<Icon type="ios-arrow-down" style="margin-left: 8px"/>
        </Button>
        <DropdownMenu slot="list">
          <DropdownItem>
            <p @click="myProfile">
              <Icon type="ios-person" size="18"/>
              我的信息
            </p>
          </DropdownItem>
          <DropdownItem>
            <p @click="myRelease">
              <Icon type="ios-leaf" />
              我的发布
            </p>
          </DropdownItem>
          <DropdownItem @click="out">
            <p @click="out">
              <Icon type="ios-log-out" size="18"/> 
              退出基地
            </p>
          </DropdownItem>
        </DropdownMenu>
      </Dropdown>
    </div>
    <!-- 发布商品 -->
    <release v-show="isDrawer" :isDrawer="isDrawer" @setIsDrawer="setIsDrawer">
    </release>
  </div>
</template>

<script>
import release from '../views/user/release'
import pro from '../views/profile/profile'

export default {
  data(){
    return {
      isDrawer:false
    }
  },
  methods:{
    showCard(){
      const { href } = this.$router.resolve({
        path: "/card",
        query: {
          userId: window.sessionStorage.getItem('id')
        }
      })
      window.open(href, '_blank')
    },
    release(){
      this.isDrawer = true;
    },
    setIsDrawer(){
      this.isDrawer = false;
    },
    out(){
      window.sessionStorage.removeItem('token');
      window.sessionStorage.removeItem('profile');
      window.sessionStorage.removeItem('id');
      window.sessionStorage.removeItem('goodsList');
      this.$router.push('/index');
      this.$router.go(0);
    },
    myProfile(){
      const { href } = this.$router.resolve({ path: "myProfile" });
      window.open(href, '_blank');
    },
    myRelease(){
      const { href } = this.$router.resolve({ path: "myRelease" });
      window.open(href, '_blank');
    },
    myOrders(){
      const { href } = this.$router.resolve({ path: "myOrders" });
      window.open(href, '_blank');
    }
  },
  components:{
    release, pro
  }
}
</script>

<style>

</style>