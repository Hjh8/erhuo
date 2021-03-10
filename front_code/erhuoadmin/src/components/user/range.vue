<template>
  <div>
    <el-table border style="width: 100%;height: 100%;margin-top:30px;"
      :data="tableData.filter(data => !search || data.realName.toLowerCase().includes(search.toLowerCase()))"
      height="590" stripe highlight-current-row >
      <el-table-column prop="id" label="用户id" width="120">
      </el-table-column>
      <el-table-column prop="name" label="用户昵称" width="110">
      </el-table-column>
      <el-table-column prop="realName" label="真实姓名" width="110">
      </el-table-column>
      <el-table-column prop="sno" label="学号" width="120">
      </el-table-column>
      <el-table-column prop="phone" label="电话" width="120">
      </el-table-column>
      <el-table-column prop="gender" label="性别" width="100">
      </el-table-column>
      <el-table-column prop="drom" label="宿舍" width="100">
      </el-table-column>
      <el-table-column prop="sta" label="状态" width="60">
      </el-table-column>
      <el-table-column prop="createTime" label="注册时间" width="170">
      </el-table-column>
      <el-table-column align="right">
        <template slot="header">
          <el-input v-model="search" size="mini" placeholder="搜索"/>
        </template>
        <template slot-scope="scope">
          <el-button size="mini" type="danger" :disabled="scope.row.sta == 0"
            @click="blockUser(scope.$index, scope.row)">
            封号
          </el-button>
          <el-button size="mini" type="success" style="margin:0 10px 0 20px;"
            @click="leaseUser(scope.$index, scope.row)"
            :disabled="scope.row.sta == 1">
            解封
          </el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  async created(){
    await this.$axios.get('/admin/user/getUsers')
    .then(res => {
      this.tableData = res.data.data;
      console.log(this.tableData)
    })
  },
  data(){
    return {
      tableData:[],
      search:''
    }
  },
  methods:{
    async blockUser(index, row) {
      console.log(index, row);
      await this.$axios.get('/admin/user/blockadeUser?id='+row.id)
      .then(res => {
        this.$message.success(res.data.message)
      })
    },
    async leaseUser(index, row) {
      await this.$axios.get('/admin/user/leaseUser?id='+row.id)
      .then(res => {
        this.$message.success(res.data.message)
      })
    }
  }
}
</script>

<style>

</style>