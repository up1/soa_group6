import axios from 'axios'

const api = 'http://35.187.208.148:8092'

export default {
  getUsers: () => {
    const url = `${api}/users/all`
    return axios.get(url)
  },
  addUser: (user) => {
    const url = `${api}/users`
    return axios.post(url, user)
  },
  updateUser: (user) => {
    const url = `${api}/users`
    return axios.put(url, user)
  },
  deleteUser: (id) => {
    const url = `${api}/users`
    return axios.delete(url, {
      data: {
        id: id
      }
    })
  }
}
