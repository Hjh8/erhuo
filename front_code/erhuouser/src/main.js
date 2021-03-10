import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ViewUI from 'view-design';
import 'view-design/dist/styles/iview.css';
import Axios from './http'
import gravatar from "gravatar";
// 城市级联选择
import Viewer from 'v-viewer'

Vue.use(ViewUI);
Vue.use(gravatar);
Vue.config.productionTip = false
Vue.prototype.$axios = Axios
Vue.prototype.$gravatar = gravatar

Vue.use(Viewer)
Viewer.setDefaults({
  Options: {
    'inline': true, 'button': true, 'navbar': true,
    'title': true, 'toolbar': true, 'tooltip': true,
    'movable': true, 'zoomable': true, 'rotatable': true,
    'scalable': true, 'transition': true, 'fullscreen': true,
    'keyboard': true, 'url': 'data-source'
  }
})

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
