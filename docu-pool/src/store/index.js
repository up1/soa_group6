import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    user: {
      username: null,
      department: {}
    },
    dashboard: {
      currentView: 'recent'
    }
  },
  mutations: {
    setUser (state, user) {
      state.user = user
    }
  }
})
