import axios from 'axios'

const url = 'http://35.185.183.178:9000/auth'

export default {
  login: (context, creds, redirect) => {
    context.state = 'loading'

    axios.post(url, creds).then(response => {
      if (response.data.error) {
        context.error = response.data.error
      } else {
        context.error = {}

        localStorage.setItem('token', response.data.token)
        if (redirect) {
          window.location.pathname = redirect
        }
      }

      context.state = 'idle'
    }, response => {
      context.error = {
        message: 'Connection error',
        connectionError: true
      }
      context.state = 'idle'
    })
  },
  logout: () => {
    localStorage.removeItem('token')
    window.location.pathname = 'login'
  },
  check: () => {
    if (this.a.getToken()) {
      return true
    }
    return false
  },
  getToken: () => localStorage.getItem('token'),
  getHeader: () => ({
    'Authorization': 'Bearer ' + this.a.getToken()
  }),
  setUserInfo: store => {
    axios.get(url, {
      params: {
        token: this.a.getToken()
      }
    }).then(response => {
      if (response.data.error) this.logout()
      else store.commit('setUser', response.data.user)
    })
  }
}
