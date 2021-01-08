import 'babel-polyfill';
import Vue from 'vue';

import VueLocalStorage from 'vue-localstorage'
Vue.use(VueLocalStorage)

import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

import VueRouter from  'vue-router';
import { store } from './store';

Vue.use(ElementUI);
Vue.use(VueRouter);

import axios from 'axios';
import FileSaver from "file-saver";


import App from './App.vue'
import Home from "./Home.vue";

import notfound_component from "./404.vue";

import CategoryList from "./Category/CategoryList.vue";
import Category from "./Category/Category.vue";

import SupplierList from "./Supplier/SupplierList.vue";
import Supplier from "./Supplier/Supplier.vue";

import ProductList from "./Product/ProductList.vue";
import Product from "./Product/Product.vue";

import PlaceOrder from "./Order/PlaceOrder.vue";
import ListOrder from "./Order/ListOrder.vue";
import SingleOrder from "./Order/SingleOrder.vue";

//Vue.use(axios);

//do komunikacji między komponentami
export const bus = new Vue();

Vue.prototype.$axios = axios;

Vue.prototype.$api = "http://localhost:8080";

if (process.env.NODE_ENV === 'production') {
    Vue.prototype.$api = "http://northwind-service:8080";
}

Vue.prototype.WindowVariable = window;
//axios.defaults.withCredentials = true;

const routes = [
    { path: '/', component: Home },
    { path: '*', component: notfound_component },


    { path: '/order/view/:id', component: SingleOrder },

    { path: '/order/create',  component: PlaceOrder},
    { path: '/order/list',  component: ListOrder},

    { path: '/category/add',  component: Category},
    { path: '/category/list',  component: CategoryList},

    { path: '/supplier/add',  component: Supplier},
    { path: '/supplier/list',  component: SupplierList},

    { path: '/product/add',  component: Product},
    { path: '/product/list',  component: ProductList},
]

const router = new VueRouter({
    mode: 'history',
    routes: routes
});

Vue.mixin({
    methods: {
        flashMessengerHandle: (data, global) => {
            Object.keys(data).forEach(obj => {
                //console.log(data[obj]);
                data[obj].forEach(message => {
                    global.$message({
                        message: message,
                        dangerouslyUseHTMLString: true,
                        type: obj
                    });
                })
            })
        },
        prepareFormDataFromModel: (model) =>{
            var formData = new FormData();
            Object.keys(model).forEach(key => {
                formData.append(key, model[key])
            })
            return formData;
        },
        valid(ref){
            var valid_value = false;
            console.log(this.$refs, ref);
            this.$refs[ref].validate((valid) => {
                if (valid) {
                    valid_value = true;
                    return true;
                } else {
                    return false;
                }
            });
            return valid_value;
        },
    }

})

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app');