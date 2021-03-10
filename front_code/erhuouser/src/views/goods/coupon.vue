<template>
  <div class="newDiv">
    <!-- 筛选栏 -->
    <div class="shaixuan">
      <ul class="sorts">
        <li class="sort" @click="comprehensive">
          <a style="color: black;">综合排序</a>
        </li>
        <li class="sort">
          <Dropdown @on-click="priceSort">
            价格排序
            <DropdownMenu slot="list">
              <DropdownItem name="up">升序</DropdownItem>
              <DropdownItem name="down">排序</DropdownItem>
            </DropdownMenu>
          </Dropdown>
        </li>
      </ul>
    </div>

    <!-- 商品分页 -->
    <div class="show">
      <div class="itemDiv">
        <div v-for="(goods,id) in goodsList" :key="id" class="item">
          <router-link tag="p" :to="'/detail?id='+goods.id">
            <a href="#" target="_blank">
              <div class="imgDiv">
                <span class="imgSpan">
                  <img :id="goods.id" :alt="goods.name"
                      :src="goods.image">
                </span>
              </div><br>
              <span class="info">
                <h3 style="color:red;"><b>￥：{{goods.price}}</b></h3>
                <h3 class="goodsName">{{goods.name}}</h3>
                <p style="text-align:left;padding-left:4px;">{{goods.sellerName}}</p>
              </span>
            </a>          
          </router-link>
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
import {queryGoodsList} from '@/api/goods/queryGoodsList.js'
import {queryTotalNum} from '@/api/goods/queryTotalNum.js'

export default {
  async created(){
    await queryGoodsList(this.sort, 1).then(res => {
      this.goodsList = res.data.data;
      this.saveGoods(this.goodsList);
    })
    await queryTotalNum(this.sort).then(res => {
      this.totalNum = res.data.data;
    })
  },
  data(){
    return {
      sort: '票券小物',
      goodsList:[],
      totalNum:0
    }
  },
  methods:{
    async changePage(num){
      await queryGoodsList(this.sort, num).then(res => {
        this.goodsList = res.data.data;
        this.saveGoods(this.goodsList);
      })
    },
    saveGoods(goods){
      // 保存原商品数组
      window.sessionStorage.setItem('goodsList', JSON.stringify(goods));
    },
    comprehensive(){
      this.goodsList = JSON.parse(window.sessionStorage.getItem('goodsList'));
    },
    priceSort(name){
      if(name === 'up'){
        this.goodsList = this.goodsList.sort((a,b) => a.price - b.price);
      }else{
        this.goodsList = this.goodsList.sort((a,b) => b.price -  a.price);
      }
    }
  }
}
</script>

<style lang="less" scoped>
.goodsName{
  overflow-y: hidden;
  white-space: nowrap;
  overflow:hidden;
  text-overflow:ellipsis;
}
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
.shaixuan{
  position: absolute;
  top: -6%;
  height: 30px;
  width: 100%;
  background-color: #f5f5f5;
}
.sorts{
  float: left;
  line-height: 30px;
  list-style: none;
}
.sort{
  float: left;
  background-color: #f5f5f5;
  border: solid 1px #e8e8e8;
  padding-left:5px;
  padding-right: 5px;
  margin-right: 5px;
}
.itemDiv{
  width: 100%;
  height: 111%;
  text-align: center;
  border: rgb(20, 143, 224);
}
.item{
  width: 185px;
  height: 270px;
  margin: 0 25px 25px 0;
  float: left;
}
.item span:hover{
  color: rgba(10, 26, 245, 0.938);
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
  color: black;
}
.pageDiv{
  position: relative;
  left: 32%;
  height: 50px;
}
.ivu-page{
  height: 32px;
  width: 300px;
}
</style>