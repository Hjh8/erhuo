<template>
  <div>
    <el-table border style="width: 100%;height: 100%;margin-top:30px;"
      :data="tableData.filter(data => !search || data.realName.toLowerCase().includes(search.toLowerCase()))"
      height="590" stripe highlight-current-row >
      <el-table-column prop="id" label="订单id" width="120">
      </el-table-column>
      <el-table-column prop="sellerId" label="卖家id" width="110">
      </el-table-column>
      <el-table-column prop="goodsId" label="商品id" width="120">
      </el-table-column>
      <el-table-column prop="name" label="商品名称" width="120">
      </el-table-column>
      <el-table-column prop="image" label="商品图片" width="120">
        <!-- 图片的显示 -->
        <template slot-scope="scope">            
          <!-- <img :src="picPath+scope.row.image" width="70" height="70" /> -->
          <img :src="scope.row.image" width="70" height="70"/>
        </template>
      </el-table-column>
      <el-table-column prop="price" label="商品价格" width="120">
      </el-table-column>
      <el-table-column prop="nums" label="购买数量" width="120">
      </el-table-column>
      <el-table-column prop="sta" label="订单状态" width="100">
      </el-table-column>
      <el-table-column prop="transaction" label="交易方式" width="120">
      </el-table-column>
      <el-table-column prop="time" label="购买时间" width="100">
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  async created(){
    await this.$axios.get('/admin/order/queryAllOrders')
    .then(res => {
      this.tableData = res.data.data;
      console.log(this.tableData)
      
      // 开发环境所用的路径
      // for(let d of this.tableData){
      //   d.image = this.getPicPath(d.image);
      // }
    })
  },
  data(){
    return {
      tableData:[],
      search:'',
      picPath:'http://localhost:8080/static/img/'
    }
  },
  methods:{
    getPicPath(image){
      return image.split("/")[3];
    }
  }
}
</script>

<style>

</style>