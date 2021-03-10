import Vue from 'vue'
import VueRouter from 'vue-router'

const index = ()=>import('../components/index')
const register = ()=>import('../views/user/Register')
const login = ()=>import('../views/user/Login')
const List = () =>import('../views/goods/list')
const digital = () =>import('../views/goods/digital')
const daily = ()=>import('../views/goods/daily')
const books = ()=>import('../views/goods/books')
const sports = ()=>import('../views/goods/sports')
const coupon = ()=>import('../views/goods/coupon')
const other = ()=>import('../views/goods/other')
const search = ()=>import('../views/goods/search')
const detail = ()=>import('../views/goods/detail')
const card = ()=>import('../views/profile/card')
const profile = ()=>import('../views/profile/profile')
const myRelease = ()=>import('../views/profile/myRelease')
const orders = ()=>import('../views/profile/orders')

Vue.use(VueRouter)

const routes = [
  { path: '/', redirect:'/index' },
  {
    path:'/index',
    component:index,
    children:[
      { path:'', component:List },
      { path:'/digital', component:digital },
      { path:'/daily', component:daily },
      { path:'/books', component:books },
      { path:'/sports', component:sports },
      { path:'/coupon', component:coupon },
      { path:'/other', component:other },
      { path:'/search', name:'search', component:search }
    ]
  },
  { path:'/login', component:login },
  { path:'/register', component:register },
  { path:'/detail', component:detail },
  { path:'/card', component:card },
  { path:'/myProfile', component:profile },
  { path:'/myRelease', component:myRelease },
  { path:'/myOrders', component:orders }
]

const router = new VueRouter({
  // base:'/erhuo22/', // 线上环境
  mode: 'hash',
  base: process.env.BASE_URL, // 开发环境
  routes
})

router.beforeEach((to, from, next) => {
  const token = window.sessionStorage.getItem('token');
  if(token || to.path == '/login' || to.path == '/register'){
    next();
  }
  next();
})

export default router
