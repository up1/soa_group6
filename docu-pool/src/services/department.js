import axios from 'axios'
import './config'

const url = 'http://35.187.208.148:8091/departments'

export default {
  getDepartments: () => {
    return axios.get(url)
  }
}
