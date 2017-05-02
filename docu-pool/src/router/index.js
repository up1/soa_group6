import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/login/Login'
import Dashboard from '@/components/dashboard/Dashboard'
import Settings from '@/components/settings/Settings'
import auth from '@/services/auth'
// import store from '@/store'
import Recent from '@/components/dashboard/Recent'
import Accounts from '@/components/dashboard/Accounts'

Vue.use(Router)

const router = new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Dashboard',
      component: Dashboard,
      redirect: '/recent',
      children: [
        {
          path: '/recent',
          name: 'Recent',
          component: Recent,
          alias: ['/pool', '/all-documents']
        },
        {
          path: '/accounts',
          name: 'Accounts',
          component: Accounts
        }
      ]
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/settings',
      name: 'Settings',
      component: Settings
    },
    {
      path: '*',
      redirect: '/'
    }
  ]
})

router.beforeEach((to, from, next) => {
  if (to.fullPath !== '/login') {
    if (auth.check()) {
      next()
    } else {
      next('/login')
    }

    // if (auth.check() && !store.state.user.passwordChanged) {
    //   next('/settings')
    // }
  }

  next()
})

export default router
