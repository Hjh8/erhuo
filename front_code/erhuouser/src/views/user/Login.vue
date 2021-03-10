<template>
  <div class="login">
    <Card class="Card"  title=".用户登录，发布二货." 
          icon="logo-snapchat" v-show="flag">
      <Form ref="loginValidate" :model="loginValidate" :label-width="90"
        :rules="ruleValidate" inline style="margin:20px 20px 0px 20px;">
        <Form-item label="手机号" prop="phone">
          <i-input v-model="loginValidate.phone" placeholder="请输入手机号" autofocus>
          </i-input>
        </Form-item>
        <Form-item label="密码" prop="pwd">
          <i-input type="password" v-model="loginValidate.pwd" placeholder="请输入密码">
          </i-input>
        </Form-item>
        <Form-item>
            <Button type="primary" @click="handleSubmit('loginValidate')">提交</Button>
            <Button @click="loginBack" style="margin-left: 10px">返回</Button>
        </Form-item>
        <Form-item>
            <Button @click="flag = !flag" type="warning"
              style="position:relative;left:-50px;">
              找回密码
            </Button>
            <Button @click="toRegister">
              跑去注册
            </Button>
        </Form-item>
      </Form>
    </Card>

    <Card v-show="!flag" class="Card" title=".找回密码，重回基地." icon="logo-snapchat">
      <Form ref="forgetValidate" :model="forgetValidate" 
        :label-width="90" inline style="margin:20px 20px 0px 20px;">
        <Form-item label="手机号" prop="phone">
          <i-input v-model="forgetValidate.phone" placeholder="请输入手机号" autofocus>
          </i-input>
        </Form-item>
        <Form-item label="新密码" prop="pwd">
          <i-input type="password" v-model="forgetValidate.pwd" placeholder="请输入新密码">
          </i-input>
        </Form-item>
        <Form-item style="padding:15px 0 0 0;">
            <Button type="primary" @click="submitForget">确认修改</Button>
            <Button @click="flag = !flag" style="margin-left: 30px">返回登录</Button>
        </Form-item>
      </Form>
    </Card>
  </div>
</template>

<script>
import {login} from '@/api/user/login.js'
import {retrievePwd} from '@/api/user/retrievePwd.js'

export default {
  mounted(){
    //绑定事件
    window.addEventListener('keydown',this.keyDown);
  },
  data(){
    const checkphone = (rule, value, callback) =>{ 
      if(!(/^1[3456789]\d{9}$/.test(value))){ 
        callback(new Error("手机号码有误，请重填"));  
      }
      callback();
    }
    return{
      flag:true,
      forgetValidate:{
        phone:'', pwd:''
      },
      loginValidate: {
        phone: '', pwd:'',
      },
      ruleValidate: {
        phone: [
          { required: true, message: '请输入登录手机号', trigger: 'blur' },
          { validator:checkphone, trigger: 'blur' }
        ],
        pwd: [
          { min: 6, max: 15, message: '长度需在6到15个字符',required: true, trigger: 'blur'}
        ]
      }
    }
  },
  methods:{
    handleSubmit(name){
      this.$refs[name].validate(async (valid) => {
        if (valid) {
          const u = this.loginValidate;
          await login(u).then(res =>{
            const {data} = res.data;
            window.sessionStorage.setItem('token', data.token);
            window.sessionStorage.setItem('id', data.id);
            window.sessionStorage.setItem('profile', JSON.stringify(data));
            this.$router.push('/');
            this.$Message.success('登录成功，快去发布二货吧！');
          })
        } else {
          this.$Message.error('登录失败!');
        }
      })
    },
    loginBack(){
      this.$router.go(-1);
    },
    keyDown(e){
      //如果是回车则执行登录方法
      if(e.keyCode == 13){
        this.handleSubmit('loginValidate');
      }
    },
    async submitForget(){
      const phone = this.forgetValidate.phone;
      const newPwd = this.forgetValidate.pwd;
      await retrievePwd(phone, phone).then(res =>{
        this.$Message.success(res.data.message);
        this.$router.go(0);
      })
    },
    toRegister(){
      this.$router.push('/register')
    },
    destroyed(){
      window.removeEventListener('keydown', this.keyDown, false);
    }
  }
}
</script>

<style>
.login{
  width: 100%;
  height: 100%;
  position: relative;
  top: 5%;
  left: 38%;
}
.Card{
  width:380px;
  height: 40%;
  text-align: left;
}
</style>