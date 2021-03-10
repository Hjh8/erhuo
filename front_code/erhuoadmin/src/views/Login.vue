 <template>
  <div class="login_container">
    <div class="opi">
      <!-- 背景半透明 -->
    </div>
    <el-card shadow="hover" class="login">
      <el-form :model="loginmodel" ref="loginref">
        <el-form-item prop="phone">
          <el-input placeholder="请输入管理员账号"
            prefix-icon="el-icon-user-solid"
            v-model="loginmodel.phone">
          </el-input>
        </el-form-item>

        <el-form-item prop="password">
          <el-input placeholder="请输入管理员密码"
            prefix-icon="el-icon-lock"
            v-model="loginmodel.password"
            type="password">
          </el-input>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="btnlogin" round size="small">登录</el-button>
          <el-button type="info" @click="resetlogin" 
              round size="small" style="margin-left:30px;">
            重置
          </el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>

export default {
  mounted(){
    //绑定事件
    window.addEventListener('keydown',this.keyDown);
  },
  data(){
    return{
      loginmodel:{
        phone: '',
        password: ''
      }
    }
  },
  methods:{
    resetlogin(){
      this.$refs.loginref.resetFields();
    },
    btnlogin(){
      this.$refs.loginref.validate(valid => {
        if(!valid) return;
        if(this.loginmodel.phone=="123456" 
            && this.loginmodel.password=="5642818"){
          this.$message.success("登录成功")
          this.$router.push('/home');
        }
        else{
          this.$message.error("登录失败")
        }
      })
    },
    keyDown(e){
      if(e.keyCode == 13){
        this.btnlogin();
      }
    }
  },
  destroyed(){
    window.removeEventListener('keydown',this.keyDown,false);
  }
}
</script>

<style lang="less" scoped>
.opi{
  height: 100%;
  width: 100%;
  background: url('~assets/market.jpg');
  opacity: 0.75;
}
.login_container{
  width: 100%;
  height: 100%;
}
.login{
  position: relative;
  top: -75%;
  left: 38%;
  width:380px;
  text-align: center;
  margin: 20px;
  padding: 10px 10px 0 10px;
}
.el-header{
  background-color:#373D41;
  display: flex;
  justify-content: space-between;
  color:azure;
  font-size: 20px;
  align-items: center;
  div{
    display: flex;
    align-items: center;
    span{
      margin-left: 15px;
      margin-right: 35px;
    };
  }
}
.el-aside{
  background-color: #333744;
}
.el-dropdown{
  color: azure;
}
</style>
