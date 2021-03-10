import Vue from 'vue'
import VueRouter from 'vue-router'

const login = ()=>import('../views/Login.vue')
const home = ()=>import('../views/Home.vue')
const range = ()=>import('../components/user/range.vue')
const welcome = ()=>import('../views/welcome')
const showOrders = ()=>import('../components/orders/showOrders')


Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    component:login
  },
  {
    path:"/home",
    component:home,
    children:[
      {path:'',component:welcome},
      {
        path:'/range',
        component:range
      },
      {
        path:'/showOrders',
        component:showOrders
      }
    ]
  }
]

const router = new VueRouter({
  base:'/erhuoBack/',
  mode: 'hash',
  // base: process.env.BASE_URL,
  routes
})

export default router
