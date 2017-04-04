import router from '@/router'
import axios from 'axios'

const LOGIN_URL = 'http://35.187.208.148:8094/genToken'

export default {
  data: {},
  login (context, creds, redirect) {
    axios.post(LOGIN_URL, creds).then(response => {
      localStorage.setItem('token', response.data.token)

      if (redirect) {
        router.push('/')
      }
    }, error => {
      context.errorMessage = error
    })
  },
  logout () {
    localStorage.removeItem('token')
  },
  checkAuth () {
    const jwt = localStorage.getItem('token')
    if (jwt) {
      return true
    }
    return false
  },
  getAuthHeader () {
    return {
      'Authorization': 'Bearer ' + localStorage.getItem('token')
    }
  }
}
