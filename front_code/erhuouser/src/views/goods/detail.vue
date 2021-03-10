<template>
  <div class="detailMain">
    <h></h>

    <div class="imgDiv">
      <img :id="goods.id" :alt="goods.name" style="width:400px;height:400px;"
           :src="goods.image">
    </div>
    <div class="info">
      <h1>二货名称：{{goods.name}}</h1>
      <h2> {{goods.remark}}</h2>
      <hr>
      <div class="shopPanel">
        <h2 style="color:red;padding-left:0px;">
          价钱：¥ {{goods.price}}
        </h2>
        <h3>交易方式：{{goods.transaction}}</h3>
      </div>
      <hr>

      <h2>分类：{{goods.sort}}</h2>
      <h2 v-if="goods.count != 0">状态：在售</h2>
      <h2 v-else>状态：售罄</h2>

      <div style="padding-left:10px;margin-top:10px;">
        <InputNumber v-model="num" :max="goods.count" :min="1"
          style="padding-left:20px;">
        </InputNumber> 件
      </div>
      <br>
      <hr><br>
      <h4 style="width:250px">上架时间：{{goods.time}}</h4>

      <div class="btn" v-if="goods.count != 0">
        <Button type="info" @click="toCard">
          <Icon type="md-cart" size="16"/>加入购物车
        </Button>
        <Button type="error" style="margin-left: 30px"  @click="toBuy">
          <Icon type="logo-usd" size="16"/> 立即购买
        </Button>
      </div>
    </div>
    <br>
    <Card class="seller">
      <span>
        <img :src="goods.user.avatar" style="width:100px;height:100px;"/>
      </span>
      <span style="text-align:left;">
        <p>id：{{goods.user.id}}</p>
        <p>昵称：{{goods.user.name}}</p>
        <p>宿舍：{{goods.user.drom}}</p>
        <p>性别：{{goods.user.gender}}</p>
      </span>
    </Card>
    <div class="comment">
      <List style="width:48%; background:white;" item-layout="vertical"
            border header="商品累计评论:" size="large">
        <ListItem v-if="comments.length == 0">
          <div style="text-align:center;width:100%">暂无商品评论</div>
        </ListItem>
        <div v-else>
          <ListItem v-for="(item,index) in comments" :key="index">
            <ListItemMeta :title="'用户id：'+item.buyerId" />
            <P>
              二货星星 : 
              <Rate icon="ios-heart" disabled :value="item.star">
                <span style="color: #f5a623">{{ item.star }}</span>
              </Rate>
            </P>
            <p>评价内容：{{item.content}}</p>
            {{item.time}}
          </ListItem>
        </div>
      </List>
    </div>
    <buy v-if="isBuy" @setIsBuy="setIsBuy" :isBuy="isBuy"></buy>
  </div>
</template>

<script>
import {queryGoodsDetail} from '@/api/goods/queryGoodsDetail.js'
import {queryCommentList} from '@/api/comment/queryCommentList.js'
import {add} from '@/api/card/add.js'
import h from '../../components/head'
import buy from '../user/buy'

export default {
  async created(){
    // 获取商品详情
    await queryGoodsDetail(this.id).then(res => {
      this.goods = res.data.data;
    })
    // 获取商品评论
    await queryCommentList(this.id).then(res => {
      this.comments = res.data.data;
      // console.log(this.comments)
    })
  },
  data(){
    return{
      isBuy: false,
      id: this.$route.query.id,
      goods: [],
      comments:[],
      num: 1,
      card: {
        id:'',buyerId:'',sellerName:'',goodsId:'',nums:''
      }
    }
  },
  methods:{
    async toCard(){
      if(this.isLogin()){
        this.card.id = this.getID();
        this.card.buyerId = window.sessionStorage.getItem('id');
        this.card.sellerName = this.goods.user.name;
        this.card.goodsId = this.goods.id;
        this.card.nums = this.num;
        const c = this.card;
        // 添加到购物车
        await add(c).then((res)=>{
          this.$Message.success(res.data.message);
        })
      }
      else{
        this.$Message.error('请先登录！');
        this.$router.push('/login');
      }
    },
    toBuy(){
      if(this.isLogin()){
        this.$store.commit('setGoodsList', {
          flag:'detail',
          goods:this.goods
        });
        this.isBuy = true;
      }
      else{
        this.$Message.error('请先登录！');
        this.$router.push('/login');
      }
    },
    isLogin(){
      if(window.sessionStorage.getItem('token') == null)
        return false
      else
        return true
    },
    getID() {
      const id = Number(Math.random().toString().substr(3, 3)+Date.now()).toString(36);
      return id.substring(1,13);
    },
    setIsBuy(){
      this.isBuy = false;
    }
  },
  components:{
    h, buy
  }
}
</script>

<style scoped>
.comment{
  position: relative;
  top: -75%;
  left: 30%;
}
.seller{
  width: 220px;
  height: 255px;
  position: relative;
  top: -43%;
  left: 11%;
  text-align: center;
}
.btn{
  margin: 30px 20px 0 35px;
}
.shopPanel{
  margin:10px 0 10px 0;
  background:rgba(255, 115, 208, 0.6);
  padding-left:5px;
}
.info{
  width: 460px;
  height: 460px;
  color: black;
  position: relative;
  top:-46.5%;
  left: 40%;
}
.detailMain{
  height: 100%;
  width: 100%;
}
.imgDiv{
  width: 400px;
  height: 400px;
  position: relative;
  top: 7%;
  left: 10%;
}
h2{
  padding-left: 10px;
}
</style>