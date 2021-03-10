<template>
  <div style="height:100%;width:100%;">
    <div class="register">
      <!-- 背景半透明 -->
    </div>
    <Card class="Card"  title="个人信息" icon="ios-pricetag-outline">
      <span style="color:rgb(58, 52, 52);">
        <h4>id: {{info.id}}</h4>
        <h4>注册时间: {{info.createTime}}</h4>
      </span>
      <span class="avatar">
        <img :src="info.avatar" style="width:110px;height:110px;">
      </span>

      <Divider style="margin-top:20px;"/>
      <Form ref="form-validate" :model="formValidate" :label-width="90"
        label-colon inline style="text-align:center;">
        <FormItem label="昵称" prop="name">
          <i-input  v-model="formValidate.name" :readonly="isDisable" 
            :placeholder="info.name"/>
        </FormItem>
        <Form-item label="真实姓名" prop="realName" >
          <i-input v-model="formValidate.realName" :readonly="isDisable"
            :placeholder="info.realName"/>
        </Form-item>
        <Form-item label="学号" prop="sno">
          <i-input v-model="formValidate.sno" :readonly="isDisable"
          :placeholder="info.sno"/>
        </Form-item><br>
        <Form-item label="宿舍" prop="drom">
          <i-input v-model="formValidate.drom" :readonly="isDisable"
          :placeholder="info.drom"/>
        </Form-item><br>
        <Form-item label="性别" prop="gender" label-position="left">
          <i-input readonly :placeholder="info.gender"/>
        </Form-item><br>
        <Form-item label="手机号" prop="phone">
          <i-input v-model="formValidate.phone" :readonly="isDisable"
          :placeholder="info.phone"/>
        </Form-item><br>
        <Form-item>
            <Button type="primary" @click="isDisable = !isDisable">
              修改个人信息
            </Button>
            <Button style="margin-left: 8px" :disabled="isDisable">
              保存
            </Button>
        </Form-item>
      </Form>
    </Card>
  </div>
</template>

<script>
export default {
  created(){
  },
  data(){
    return{
      formValidate:{},
      isDisable:true,
      info:JSON.parse(window.sessionStorage.getItem('profile'))
    }
  }
}
</script>

<style>
.register{
  height: 100%;
  width: 100%;
  background: url('~assets/pictures/myprofile.jpg') no-repeat;
  opacity: 0.7;
}
.Card{
  position: relative;
  top: -93%;
  left: 33%;
  width:40%;
  text-align: left;
  display: inline-flexbox;
  color: rgb(58, 52, 52);
}
.avatar{
  position: absolute;
  top: 9%;
  right: 15%;
}
h4{
  width:200px;
  margin: 10px 20px 25px 80px;
}
</style>