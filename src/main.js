import Vue from 'vue'
import VueResource from 'vue-resource';

Vue.use(VueResource);

import MainPage from './components/MainPage.vue'

new Vue({
    el: '#app',
    render: h => h(MainPage)
})
