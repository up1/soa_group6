import axios from 'axios'
import './config'

const base = 'http://35.187.208.148:8091'

export default {
  getDepartments: () => {
    return axios.get(`${base}/departments`)
  }
}
