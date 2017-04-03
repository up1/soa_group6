import Vue from 'vue'
import Router from 'vue-router'
import Dashboard from '@/components/pages/Dashboard'
import Login from '@/components/pages/Login'
import Settings from '@/components/pages/Settings'
import NotFound from '@/components/pages/NotFound'
import 'bulma/css/bulma.css'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'dashboard',
      component: Dashboard
    },
    {
      path: '/login',
      name: 'login',
      component: Login
    },
    {
      path: '/settings',
      name: 'settings',
      component: Settings
    },
    {
      path: '*',
      name: 'notFound',
      component: NotFound
    }
  ]
})
