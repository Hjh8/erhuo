<template>
  <div>
    <Drawer  title="发布二货" v-model="isDrawer" width="580" 
      :mask-closable="false" :styles="styles" class="drawer">
      <Form :model="formData" ref="goodsForm" >
        <Row :gutter="32">
          <i-col span="12">
            <FormItem label="商品名称" label-position="top">
              <Input v-model="formData.name" placeholder="输入商品名称" />
            </FormItem>
          </i-col>
          <i-col span="6">
            <FormItem label="商品价格" label-position="top">
              <Input v-model="formData.price" suffix="logo-usd"
               placeholder="输入金额" />
            </FormItem>
          </i-col>
          <i-col span="6">
            <FormItem label="数量" label-position="top">
              <Input v-model="formData.count" placeholder="输入商品数量" />
            </FormItem>
          </i-col>
        </Row>
        <Row :gutter="32">
          <i-col span="12">
            <FormItem label="交易方式" label-position="top">
              <Select v-model="formData.transaction" placeholder="选择交易方式">
                <Option value="线下面交">线下面交</Option>
                <Option value="线上支付">线上支付</Option>
              </Select>
            </FormItem>
          </i-col>
          <i-col span="12">
            <FormItem label="商品类别" label-position="top">
              <Select v-model="formData.sort" placeholder="选择商品类别">
                <Option v-for="(sort,index) in sortList" :value="sort" :key="index">
                  {{sort}}
                </Option>
              </Select>
            </FormItem>
          </i-col>
        </Row>
        <FormItem label="商品描述" label-position="top">
            <Input type="textarea" v-model="formData.remark" :rows="4" placeholder="商品的描述" />
        </FormItem>
        <FormItem label="商品图片" label-position="top">
          <div class="cover">
            <div class="upload">
              <Upload multiple type="drag"
                action="http://47.115.46.57:9999/erhuo/goods/uploadPic"
                :on-success="uploadSuccess" :on-error="uploadError">
                <div style="padding: 20px 0;">
                  <Icon type="ios-cloud-upload" size="52" style="color: #3399ff"></Icon>
                  <p>点击或者拖拽上传</p>
                </div>
              </Upload>
            </div>
            <div class="article-cover" v-if="formData.image">
              <img :src="path" alt="商品图片" style="height:150px;width:150px;">
            </div>
          </div>
        </FormItem>
      </Form>

      <div class="demo-drawer-footer">
        <Button type="success" long @click="submitForm('goodsForm')" size="large">
          发布二货
        </Button>
        <br><br>
        <Button long @click="resetForm('goodsForm')" size="large">取消</Button>
      </div>
    </Drawer>
  </div>
</template>

<script>
import {release} from '@/api/goods/release.js'

export default {
  data () {
    return {
      styles: {
        height: 'calc(100% - 55px)',
        overflow: 'auto',
        paddingBottom: '53px',
        position: 'static',
      },
      formData: {
        name: '',
        price: '',
        count: '',
        remark: '',
        sort: '',
        image: '',
        transaction: '',
        id: '',
        sellerId:'',
        sta: ''
      },
      sortList:[
        '数码电子', '衣服/日用', '图书教材',
        '运动棋牌', '商卷小物', '其他'
      ],
      headers: {},
      path:""
    }
  },
  watch:{
    isDrawer(val){
      this.isDrawer = val;
    }
  },
  props:[
    'isDrawer'
  ],
  methods:{
    submitForm(name){
      this.$refs[name].validate(async valid => {
        if (valid) {
          this.formData.sellerId = window.sessionStorage.getItem('id');
          this.formData.id = this.getID();
          const goodsDetail = this.formData;
          await release(goodsDetail).then(res => {
            this.$Message.success(res.data.message);
            // 设置抽屉显示和清空
            this.resetForm(name);
          })
        } else {
          this.$Message.error('Fail!');
        }
      })
    },
    resetForm(name){
      this.$refs[name].resetFields();
      this.$emit('setIsDrawer');
    },
    // 上传图片成功
    uploadSuccess(response) {
      // this.formData.image = 'assets/pictures/upload/'+response.data;
      // this.$Message.success(response.message);
      // this.path = require('~'+this.formData.image);

      this.formData.image = response.data;
      this.$Message.success(response.message);
      this.path = this.formData.image;
    },
    // 上传图片失败
    uploadError(response) {
      this.$Message.error('上传失败!');
      console.log(response)
    },
    getID() {
      const id = Number(Math.random().toString().substr(3, 3)+Date.now()).toString();
      return id.substring(1,13);
    }
  }
}
</script>
<style>
.demo-drawer-footer{
  width: 100%;
  position: absolute;
  bottom: 0;
  left: 0; 
  border-top: 1px solid #e8e8e8;
  padding: 10px 16px;
  text-align: right;
}
.ivu-drawer-content{
  background-image: url(~assets/pictures/pic1.png);
}
.cover {
  display: flex;
}
.cover .upload {
  width: 280px;
}
.article-cover {
  width: 120px;
  height: 120px;
}
</style>
