<template>
  <div style="height:100%;width:100%;">
    <div class="register">
      <!-- 背景半透明 -->
    </div>
    <Card class="Card"  title="二货注册" icon="ios-pricetag-outline">
      <Form ref="form-validate" :model="formValidate" :label-width="90"
        :rules="ruleValidate" label-colon inline>
        <FormItem label="昵称" prop="name">
          <i-input  v-model="formValidate.name" placeholder="请输入昵称"></i-input>
        </FormItem><br>
        <Form-item label="真实姓名" prop="realName">
          <i-input v-model="formValidate.realName" placeholder="请输入真实姓名"></i-input>
        </Form-item><br>
        <Form-item label="学号" prop="sno">
          <i-input v-model="formValidate.sno" placeholder="请输入学号"></i-input>
        </Form-item><br>
        <Form-item label="宿舍" prop="drom">
          <i-input v-model="formValidate.drom" placeholder="请输入宿舍号，例如C15"></i-input>
        </Form-item><br>
        <Form-item label="性别" prop="gender" label-position="left">
          <RadioGroup v-model="formValidate.gender" >
            <Radio label="male">Male</Radio>
            <Radio label="female">Female</Radio>
          </RadioGroup>
        </Form-item><br>
        <Form-item label="手机号" prop="phone">
          <i-input v-model="formValidate.phone" placeholder="请输入手机号"></i-input>
        </Form-item><br>
        <Form-item label="密码" prop="pwd">
          <i-input type="password" v-model="formValidate.pwd"></i-input>
        </Form-item><br>
        <Form-item label="确认密码" prop="passwdCheck">
          <i-input type="password" v-model="formValidate.passwdCheck"></i-input>
        </Form-item><br>
        <Form-item style="padding-left:15px;">
            <Button type="primary" @click="handleSubmit('form-validate')">提交</Button>
            <Button @click="handleReset('form-validate')" style="margin-left: 8px">重置</Button>
        </Form-item>
      </Form>
    </Card>
  </div>
</template>

<script>
import {register} from '@/api/user/register.js'

export default {
  data () {
    const checkphone = (rule, value, callback) =>{ 
      if(!(/^1[3456789]\d{9}$/.test(value))){ 
        callback(new Error("手机号码有误，请重填"));  
      }
      callback();
    };
    const validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (this.formValidate.passwdCheck !== '') {
          if(value !== this.formValidate.passwdCheck){
            callback(new Error('两次输入密码不一致!'));
          }
        }
        callback();
      }
    };
    const validatePassCheck = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.formValidate.pwd) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {
      formValidate: {
        id:'',
        name: '',
        realName: '',
        sno: '',
        drom: '',
        gender: '',
        phone: '',
        pwd:'',
        passwdCheck:'',
        avatar:'',
        status:''
      },
      ruleValidate: {
        name: [
          { required: true, message: '姓名不能为空', trigger: 'blur' },
          { max: 10, message: '昵称长度不能超过10', trigger: 'blur'}
        ],
        realName: [
          { required: true, message: '真实姓名不能为空', trigger: 'blur'},
          { max: 4, message: '请填写真实的姓名', trigger: 'blur'}
        ],
        sno: [
          { required: true, message: '学号不能为空', trigger: 'blur' }
        ],
        gender: [
          { required: true, message: '请选择性别', trigger: 'change' }
        ],
        drom: [
          { required: true, message: '宿舍号不能为空', trigger: 'blur' },
          { max: 3, message: '请填写正确的宿舍号', trigger: 'blur'}
        ],
        phone: [
          { required: true, message: '请输入注册手机号', trigger: 'blur' },
          { validator:checkphone, trigger: 'blur' }
        ],
        pwd: [
          { validator: validatePass, trigger: 'blur',required: true },
          { min: 6, max: 15, message: '长度需在6到15个字符', trigger: 'blur'}
        ],
        passwdCheck: [
          { validator: validatePassCheck, trigger: 'blur',required: true },
          { min: 6, max: 15, message: '长度需在6到15个字符', trigger: 'blur'}
        ]
      }
    }
  },
  methods: {
    handleSubmit (name) {
      this.$refs[name].validate(async (valid) => {
        if (valid) {
          //一个手机对应一个头像 s:size  r:rating  d:default没有这张图片是返回的内容
          const aStr = this.$gravatar.url(this.formValidate.phone,{s:'200',r:'pg',d:'wavatar'})
          this.formValidate.id = this.getID();
          this.formValidate.status = 1;
          this.formValidate.avatar = 'http:'+aStr;
          const user = this.formValidate;
          
          await register(user).then(()=>{
            this.$router.push('/');
            this.$Message.success('注册成功!');
          })

        } else {
          this.$Message.error('表单验证失败!');
        }
      })
    },
    handleReset (name) {
      this.$refs[name].resetFields();
    },
    getID() {
      const id = Number(Math.random().toString().substr(3, 3)+Date.now()).toString(36);
      return id.substring(1,11);
    }
  }
}
</script>

<style lang="less" scoped>
.register{
  height: 100%;
  width: 100%;
  background: url('~assets/pictures/registerPic.jpg') no-repeat;
  opacity: 0.75;
}
.Card{
  position: relative;
  top: -95%;
  left: 38%;
  width:380px;
  text-align: left;
}
</style>