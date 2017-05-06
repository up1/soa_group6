import axios from 'axios'

const base = 'http://35.187.208.148:8092'

export default {
  getUsers: () => {
    return axios.get(`${base}/users/all`)
  },
  addUser: (user) => {
    return axios.post(`${base}/users`, user)
  },
  updateUser: (user) => {
    return axios.put(`${base}/users`, user)
  },
  deleteUser: (id) => {
    return axios.delete(`${base}/users`, {
      data: {
        id: id
      }
    })
  },
  updateUserUsername: (context, payload) => {
    context.state = 'processing'

    axios.put(`${base}/users/selfUpdate/username`, payload).then(response => {
      context.message = response.data.message
      context.state = 'idle'
    })
  },
  updateUserPassword: (context, payload) => {
    context.state = 'processing'

    axios.put(`${base}/users/selfUpdate/password`, payload).then(response => {
      context.message = response.data.message
      context.state = 'idle'
    })
  },
  resetPassword: (payload) => {
    return axios.put(`${base}/users/resetPwd`, payload)
  }
}
