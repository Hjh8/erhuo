<template>
  <div class="cartDiv">
    <div class="title">
      <h1><Icon type="ios-leaf" />我的二货发布</h1>
    </div>
    <div class="showCard">
      <Table :columns="columns" :data="releaseList"
            :row-class-name="rowClassName" stripe>
      </Table>
    </div>
  </div>
</template>

<script>
import {queryRelease} from '@/api/user/queryRelease.js'
import {removeRelease} from '@/api/user/removeRelease.js'

export default {
  async created(){
    let id = window.sessionStorage.getItem('id');
    await vqueryRelease(id).then(res => {
      this.releaseList = res.data.data;
      this.$Message.success(res.data.message);
    })
  },
  data(){
    return{
      releaseList: [],
      columns: [
        {
          type: 'index',
          width: 70,
          align: 'center',
          title:' '
        },
        {
          title: '二货id',
          key: 'id'
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
                src: params.row.image, 
                style: 'width: 60px;height: 60px;'
              },
            }),
            ]);
          }
        },
        {
          title: '名称',
          key: 'name'
        },
        {
          title: '分类',
          key: 'sort'
        },
        {
          title: '价钱',
          key: 'price'
        },
        {
          title: '数量',
          key: 'count'
        },
        {
          title: '交易方式',
          key: 'transaction'
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
                    const id = this.releaseList[params.index].id;
                    await removeRelease(id).then(()=>{
                      this.releaseList.splice(params.index, 1);
                      this.$Message.success({
                        background: true,
                        content: '删除发布成功！'
                      });
                    })
                  }
                }
              }, '删除')
            ]);
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
    }
  }
}
</script>

<style>
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