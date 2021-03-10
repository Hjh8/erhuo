<template>
  <div class="cartDiv">
    <div class="title">
      <h1><Icon type="md-reorder"/>历史订单</h1>
    </div>
    <div class="showCard">
      <Table :columns="columns" :data="releaseList"
            :row-class-name="rowClassName" stripe>
      </Table>
    </div>
    <comment :isComment="isComment" :goodsId="goodsId"
              @setIsComment="setIsComment" v-if="isComment">
    </comment>
  </div>
</template>

<script>
import comment from '../comment/comment'
import {queryOrder} from '@/api/order/queryOrder.js'
import {confirmOrder} from '@/api/order/confirmOrder.js'
import {cancelOrder} from '@/api/order/cancelOrder.js'

export default {
  async created(){
    let id = window.sessionStorage.getItem('id');
    await queryOrder(id).then(res => {
      this.releaseList = res.data.data;
      this.$Message.success(res.data.message);
    })
  },
  data(){
    return{
      goodsId:'',
      isComment:false,
      releaseList: [],
      columns: [
        {
          type: 'index',
          width: 70,
          align: 'center',
          title:' '
        },
        { title: '订单id', key: 'id' },
        { title: '卖家id', key: 'sellerId' },
        { title: '二货名称', key: 'name' },
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
                src:params.row.image, 
                style: 'width: 60px;height: 60px;'
              },
            }),
            ]);
          }
        },
        { title: '价钱/元', key: 'price' },
        { title: '数量', key: 'nums', width: 80},
        { title: '订单状态', key: 'sta' },
        { title: '交易方式', key: 'transaction' },
        { title: '下单时间', key: 'time' },
        {
          title: ' ',
          key: 'confirm',
          render: (h, params) => {
            const status = this.releaseList[params.index].sta;
            if(status == '发货中'){
              return h('div', [
                h('Button', {
                  props: {type: 'success'},
                  on: {
                    click: async () => {
                      const orderId = this.releaseList[params.index].id;
                      const goodsId = this.releaseList[params.index].goodsId;
                      await confirmOrder(orderId, goodsId).then(res => {
                        this.releaseList.splice(params.index, 1);
                        this.$Message.success({
                          background: true,
                          content: res.data.message
                        });
                      })
                    }
                  }
                }, '确认收货')
              ]);
            }
            else{
              return h('div', [
                h('Button', {
                  on: {
                    click: () => {
                      this.isComment = true;
                      this.goodsId = this.releaseList[params.index].goodsId;
                    }
                  }
                }, '评价')
              ]);
            }
          }
        },
        {
          title: ' ',
          key: 'cancel',
          render: (h, params) => {
            const status = this.releaseList[params.index].sta;
            if(status == '发货中'){
              return h('div', [
                h('Button', {
                  props: {type: 'error'},
                  on: {
                    click: async () => {
                      const orderId = this.releaseList[params.index].id;
                      const goodsId = this.releaseList[params.index].goodsId;
                      const nums = this.releaseList[params.index].nums;
                      await cancelOrder(orderId, goodsId, nums).then(res => {
                        this.releaseList.splice(params.index, 1);
                        this.$Message.success({
                          background: true,
                          content: res.data.message
                        });
                      })
                    }
                  }
                }, '取消订单')
              ]);
            }
          }
        }
      ],
    }
  },
  methods:{
    rowClassName(row, index) {
      if(index%2==0){
        return 'ivu-table-stripe-even';
      }
      else return 'ivu-table-stripe-odd';
    },
    setIsComment(){
      this.isComment = false;
    }
  },
  components:{
    comment
  }
}
</script>

<style scoed>
.cartDiv{
  width:100%;
  height:100%;
  background: url('~assets/pictures/market.jpg');
  background-size:cover;
  padding-bottom: 30px;
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