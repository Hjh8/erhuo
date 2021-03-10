<template>
  <div class="newDiv">
    <!-- 商品分页 -->
    <div class="show">
      <div class="itemDiv">
        <div v-for="(goods,id) in searchList" :key="id" class="item">
          <router-link tag="p" v-if="!isNaN(goods.genderOrMoney)"
                       :to="'/detail?id='+goods.id">
            <a target="_blank">
              <div class="imgDiv">
                <span class="imgSpan">
                  <img :id="goods.id" :alt="goods.name"
                      :src="goods.image">
                </span>
              </div><br>
              <span class="info">
                <h3 style="color:red;" class="goodsName">
                  <b>{{goods.name}} {{goods.genderOrMoney}}￥</b>
                </h3>
                <h3>二货id：{{goods.id}}</h3>
                <p style="text-align:left;padding-left:4px;">{{goods.dromOrSeller}}</p>
              </span>
            </a>
          </router-link>
          <p v-else @click="temp">
            <a href="javascript:void(0);">
              <div class="imgDiv">
                <span class="imgSpan">
                  <img :id="goods.id" :alt="goods.name"
                       :src="goods.image">
                </span>
              </div><br>
              <span class="info">
                <h3 style="color:red;" class="goodsName">
                  <b>{{goods.name}}</b>
                </h3>
                <h3>id：{{goods.id}}</h3>
                <p style="text-align:left;padding-left:4px;">
                  宿舍：{{goods.dromOrSeller}} 
                  <span style="margin-left:30px;">
                    {{goods.genderOrMoney}}
                  </span>
                </p>
              </span>
            </a>
          </p>
        </div>
      </div>
      <div class="pageDiv">
        <Page :total="totalNum" size="small" 
              show-total :page-size="8"
              @on-change="changePage"/>
      </div>
    </div>
  </div>
</template>

<script>
import {searchGoods} from '@/api/search/searchGoods.js'
import {searchUser} from '@/api/search/searchUser.js'
import {queryGoodsNums} from '@/api/search/queryGoodsNums.js'
import {queryUserNums} from '@/api/search/queryUserNums.js'

export default {
  async created(){
    const {select, value} = this.$route.params;
    this.s = select;
    this.v = value;
    if(select === 'goods' && value != ''){
      // 查询商品
      await searchGoods(value, 1).then(res => {
        this.searchList = res.data.data;
      })
      // 查询商品的数量
      await queryGoodsNums(value).then(res => {
        this.totalNum = res.data.data;
        if(this.totalNum == 0) this.$Message.warning('抱歉，无此二货！');
      })
    }else{
      await searchUser(value, 1).then(res => {
        this.searchList = res.data.data;
        console.log(this.searchList)
      })
      await queryUserNums(value).then(res => {
        this.totalNum = res.data.data;
        if(this.totalNum == 0) this.$Message.warning('抱歉，查无此用户！');
      })
    }
  },
  data(){
    return {
      searchList: [],
      s:'',
      v:'',
      totalNum: 0
    }
  },
  methods:{
    async changePage(num){
      if(this.s === 'goods' && this.v != ''){
        await searchGoods(value, num).then(res => {
          this.searchList = res.data.data;
        })
      }else{
        await searchUser(value, num).then(res => {
          this.searchList = res.data.data;
        })
      }
    },
    temp(){
      this.$Message.success({
        background: true,
        content: '抱歉，暂不支持查看用户详情'
      });
    }
  }
}
</script>

<style lang="less" scoped>
.show{
  height: 95%;
  margin-top: 15px;
  overflow-y: auto;
  scrollbar-width: none; /* firefox */
  -ms-overflow-style: none; /* IE 10+ */
}
.show::-webkit-scrollbar {
  display: none; /* Chrome Safari */
}
.newDiv{
  position: absolute;
  top: 142px;
  left: 25%;
  height: 73%;
  width: 55%;
  margin-bottom: 15px;
}
.itemDiv{
  width: 100%;
  height: 111%;
  text-align: center;
  border: rgb(155, 207, 241);
}
.item{
  width: 185px;
  height: 270px;
  margin: 0 25px 25px 0;
  float: left;
}
.goodsName{
  overflow-y: hidden;
  white-space: nowrap;
  overflow:hidden;
  text-overflow:ellipsis
}
.item a:hover{
  color:  rgba(121, 129, 240, 0.726);
  border: 1px solid rgba(199, 54, 54, 0.521);
}
div{
  display:inline-flexbox;
}
a{
  text-decoration: none;
}
img{
  width: 100%;
  height: 100%;
}
.imgDiv{
  width: 180px;
  height: 180px;
}
.imgSpan{
  width: 180px;
  height: 180px;
  text-align: center;
  display: table-cell;
  vertical-align: middle;
}
.info{
  width: 180px;
  height: 80px;
}
.pageDiv{
  position: relative;
  left: 32%;
  height: 30px;
}
</style>