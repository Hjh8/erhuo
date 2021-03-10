<template>
  <div class="cartDiv">
    <div class="title">
      <h1><Icon type="ios-cart-outline" />二货购物车</h1>
    </div>
    <div class="showCard">
      <Table :columns="columns" :data="cardList" ref="cardTable"
            :row-class-name="rowClassName" stripe
            @on-select="addition" @on-select-cancel="deletion"
            @on-select-all="selectAll"
            @on-select-all-cancel="cancelAll">
      </Table>
      <div class="sum">
        <h1>总价：{{total}}￥</h1>
        <Button type="error" circle class="jiesuan" 
          size="large" @click="payment" :disabled="selectedList.length == 0">
          结 算
        </Button>
      </div>
    </div>

    <buy v-if="isBuy" @setIsBuy="setIsBuy" :isBuy="isBuy"></buy>
  </div>
</template>

<script>
import { deleteCard } from '@/api/card/deleteCard.js'
import { show } from '@/api/card/show.js'
import buy from '../user/buy'

export default {
  async created(){
    await show(this.$route.query.userId).then(res => {
      this.cardList = res.data.data;
    })
    
  },
  data(){
    return{
      cardList: [],
      columns: [
        {
          type: 'selection',
          width: 60,
          align: 'center'
        },
        {
          type: 'index',
          width: 70,
          align: 'center',
          title:' '
        },
        {
          title: ' ',
          key: 'image',
          render: (h, params) => {
            return h('div', {
              attrs: {
                style: 'width: 62px;height: 62px;'
              },
            }, [
            h('img', {
              props: {
                type: 'primary',
                size: 'small'
              },
              attrs: {
                src: params.row.image, //require('../../'+params.row.image)
                style: 'width: 60px;height: 60px;'
              },
            }),
            ]);
          }
        },
        {
          title: '卖家',
          key: 'sellerName'
        },
        {
          title: '名称',
          key: 'goodsName'
        },
        {
          title: '价钱',
          key: 'price'
        },
        {
          title: '数量',
          key: 'nums'
        },
        {
          title: '金额',
          key: 'totalMoney'
        },
        {
          title: ' ',
          key: 'delete',
          render: (h, params) => {
            return h('div', [
              h('Button', {
                props: {type: 'error'},
                on: {
                  click: async () => {
                    const id = this.cardList[params.index].id;
                    deleteCard(id).then(()=>{
                      this.cardList.splice(params.index, 1);
                      this.$Message.success({
                        background: true,
                        content: '移出购物车成功！'
                      });
                    })
                  }
                }
              }, '删除')
            ]);
          }
        }
      ],
      total:0,
      isBuy:false,
      selectedList:[], // 选中的商品
      indexList:[]  // 选择的商品的下标，便于表格删除
    }
  },
  methods:{
    addition(selection, row){
      this.selectedList.push(row);
      this.total = this.total + row.price*row.nums;
    },
    deletion(selection, row){
      this.selectedList.pop(row);
      this.total = this.total - row.price*row.nums;
    },
    selectAll(){
      this.selectedList = this.$refs.cardTable.getSelection();
      this.cardList.forEach(c => {
        this.total = this.total + c.price*c.nums;
      });
    },
    cancelAll() {
      this.selectedList = [];
      this.total = 0;
    },
    rowClassName(row, index) {
      if(index%2==0){
        return 'ivu-table-stripe-even';
      }
      else return 'ivu-table-stripe-odd';
    },
    payment(){
      this.cardList = this.cardList.filter(li => {
        this.selectedList.indexOf(li) < 0;
      })
      this.total = 0;
      
      this.$store.commit('setGoodsList', {
        flag:'card', 
        goods:this.selectedList
      });
      this.isBuy = true;
    },
    setIsBuy(){
      this.isBuy = false;
    }
  },
  components:{
    buy
  }
}
</script>

<style>
.jiesuan{
  margin-left: 15px;
  
}
.sum{
  padding: 10px 0px 5px 30px;
  text-align: right;
  display: inline-flex;
  color: #eb3a22;
  position: fixed;
  bottom: 2px;
  width: 85%;
  height:8%;
  background: rgba(222, 223, 228, 0.842);
}
.cartDiv{
  width:100%;
  height:100%;
  background: url('~assets/pictures/大菠萝.jpg');
  background-size:cover;
}
.title{
  position: relative;
  left: 9%;
  width: 80%;
  height: 5%;
  text-align: center;
  color: rgba(243, 106, 26, 0.842);
  font: 12px/1.2 tahoma,arial,'Hiragino Sans GB','\5b8b\4f53',sans-serif;
  font-size: 25px ;
}
.showCard{
  position: relative;
  top: 7%;
  left: 8%;
  width: 85%;
  height: 88%;
  background-color: #1c343f;
  overflow-y: auto;
  scrollbar-width: none; /* firefox */
  -ms-overflow-style: none; /* IE 10+ */
}
.showCard::-webkit-scrollbar {
  display: none; /* Chrome Safari */
}
.ivu-table td{
  background-color: #182328;
  color: rgba(231, 141, 227, 0.76);
}
 /*头部th*/
.ivu-table-header th{
  color:#FFD3B4;
  font-weight: bold;
  background-color: #212c31;
  border: none;
}
/*偶数行*/
.ivu-table-stripe-even td{
  background-color: #434343!important;
}
/*奇数行*/
.ivu-table-stripe-odd td{
  background-color: #282828!important;
}
/*浮在某行*/
.ivu-table-row-hover td {
  background-color: #dd5858!important;
}
/*选中某一行高亮*/
.ivu-table-row-highlight td {
background-color: #eb7878!important;
}
</style>