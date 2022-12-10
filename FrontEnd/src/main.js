// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import locale from 'element-ui/lib/locale/lang/en'
import ViewUI from 'view-design';
import 'view-design/dist/styles/iview.css';
import axios from "axios";
import store from './store';

router.beforeEach(function(to, form, next) {
    if (store.state.userInfo || to.path === './login') {
        next();
    } else {
        next({ path: './login' })
    }
})

Vue.use(ElementUI, { locale })
Vue.prototype.$ajax =  axios
axios.defaults.withCredentials = true

axios.defaults.baseURL = '/apis'

Vue.use(ViewUI);



new Vue({
    el: '#app',
    router,
    components: { App },
    store,
    template: '<App/>'
});
