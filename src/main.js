import Vue from 'vue'
import VueResource from 'vue-resource';
import VueRouter from 'vue-router';

Vue.use(VueResource);
Vue.use(VueRouter);
Vue.http.options.root = 'http://localhost:8090';


import MainPage from './components/MainPage.vue'
import Edit from './components/Edit.vue'
import EditCar from './components/EditCar.vue'

const routes = [
    {path: '/', component: MainPage},
    {path: '/edit/:id', component: Edit},
    {path: '/edit/:id/car', component: EditCar},
];

// new Vue({
//     el: '#app',
//     render: h => h(MainPage)
// });

const router = new VueRouter({
    mode: 'history',
    routes
});

const app = new Vue({
    router,
    http: {
        root: 'http://localhost:8090'
    }
}).$mount('#app');
