import Vuex from 'vuex'
import Vue from 'vue'
import date from "./modules/date";
import film from "./modules/film";

Vue.use(Vuex)

export default new Vuex.Store({
    modules: {
        date,
        film
    }
})