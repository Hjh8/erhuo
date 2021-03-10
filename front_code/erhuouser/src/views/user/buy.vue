<template>
  <div>
    <Modal v-model="isBuy" :key="index" @on-ok="submitOrder"
      :closable="false" :mask-closable="false"
      v-for="(goods, index) in this.$store.state.GoodsList">
      <div>
        <p>{{order[index].id}}</p>
        <div class="saller">
          <p>卖家昵称：{{goods.sellerName}}</p>
          <p>卖家电话：{{query.phone}}</p>
        </div>
        <br>
        <div class="goods">
          <p>商品名称：{{goods.goodsName}}</p>
          <p>商品数量：{{goods.nums}}</p>
        </div>
        <div>
          <p>交易方式：{{query.transaction}}</p>
          <P style="margin-top:20px;">金额：{{goods.price*goods.nums}} 元</P>
        </div>
      </div>
      <Divider>二货订单</Divider>
      <div>
        <p>买家昵称：{{buyer.name}}</p>
        <p>买家电话：{{buyer.phone}}</p>
      </div>
      <p>交易时间：{{new Date().toLocaleString()}}</p>
    </Modal>
  </div>
</template>

<script>
import {getOrderShow} from '@/api/order/getOrderShow.js'
import {addOrder} from '@/api/order/addOrder.js'

export default {
  async created(){
    const list = this.$store.state.GoodsList;
    list.forEach(li => {
      this.idList.push(li.goodsId);
      const or = {};
      or.id = this.getID();
      or.nums = li.nums;
      or.buyerId = this.buyer.id;
      or.sellerId = li.sellerId;
      or.goodsId = li.goodsId;
      this.order.push(or);
    });

    const ids = this.idList;
    await getOrderShow(ids).then((res)=>{
      this.query = res.data.data[0];
      this.$Message.success("购买成功");
    })
  },
  data(){
    return {
      query:'',
      idList:[],
      buyer:JSON.parse(window.sessionStorage.getItem('profile')),
      order:[]
    }
  },
  watch:{
    isBuy(val){
      this.isBuy = val;
    }
  },
  props:[
    'isBuy'
  ],
  methods:{
    getID() {
      const id = Number(Math.random().toString().substr(3, 3)+Date.now()).toString();
      return id.substring(1,13);
    },
    async submitOrder(){
      const orders = this.order;
      await addOrder(orders).then(res => {
        this.$Message.success(res.data.message);
        this.$emit('setIsBuy');
      })
    }
  }
}
</script>

<style>
p{
  margin: 10px 30px 10px 10px;
}
.saller{
  display: inline-flex;
}
.goods{
  display: inline-flex;
}
</style>