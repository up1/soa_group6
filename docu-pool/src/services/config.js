import axios from 'axios'
import auth from './auth'

axios.defaults.headers.common['Authorization'] = auth.getHeader()['Authorization']
