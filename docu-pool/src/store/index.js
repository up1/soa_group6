import Vue from 'vue'
import Vuex from 'vuex'
import auth from '@/services/auth'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    authenticated: auth.checkAuth(),
    user: {},
    role: 'admin',
    department: 'Shimada Department',
    currentMenu: 'recent'
  }
})
