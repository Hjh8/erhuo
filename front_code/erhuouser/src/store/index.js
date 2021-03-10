import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const store = new Vuex.Store({
  state:{
    GoodsList:[]
  },
  mutations: {
    setGoodsList(state,{flag, goods}){
      if(flag === 'card'){
        // 购物车中购买
        state.GoodsList = goods;
      }else{
        // 直接购买
        const list = {};
        list.goodsId = goods.id
        list.sellerId = goods.user.id;
        list.nums = goods.count;
        list.sellerName = goods.user.name;
        list.goodsName = goods.name;
        list.price = goods.price;
        state.GoodsList.push(list);
      }
    }
  },
  actions: {}
})

export default store
