<template>
  <div class="commentDiv">
    <Modal :value="isComment" style="text-align: center;"
           @on-ok="submitComment" @on-cancel="cancelComment"
           :closable="false" :mask-closable="false">
      <h3>
        二货星星 : 
        <Rate v-model="rateValue" icon="ios-heart">
          <span style="color: #f5a623">{{ rateValue }}</span>
        </Rate>
      </h3>
      <br>
      <Input v-model="content" maxlength="50"
             show-word-limit type="textarea" 
             :autosize="{minRows: 4,maxRows: 5}"
             placeholder="二货描述..." 
             style="width: 250px;margin-bottom:20px;" />
    </Modal>
  </div>
</template>

<script>
import {addComment} from '@/api/comment/addComment.js'

export default {
  data(){
    return{
      rateValue:5,
      content:""
    }
  },
  methods:{
    async submitComment(){
      this.$emit('setIsComment');
      const comment = {};
      comment.id = this.getID();
      comment.goodsId = this.goodsId;
      comment.buyerId = window.sessionStorage.getItem('id');
      comment.content = this.content;
      comment.star = this.rateValue;
      if(this.content != ''){
        addComment(comment).then(res => {
          this.content = "";
          this.$Message.success({
            background: true,
            content: res.data.message
          });
        })
      }
    },
    cancelComment(){
      this.$emit('setIsComment');
    },
    getID() {
      const id = Number(Math.random().toString().substr(3, 3)+Date.now()).toString();
      return id.substring(1,13);
    },
  },
  watch:{
    isComment(val){
      this.isComment = val;
    },
    goodsId(goodsId){
      this.goodsId = goodsId;
    }
  },
  props:[
    'isComment', 'goodsId'
  ],
}
</script>

<style scoped>

</style>