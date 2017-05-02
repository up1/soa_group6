import axios from 'axios'
import './config'

const shareAPI = 'http://35.187.208.148:8097'
const documentAPI = 'http://35.187.208.148:8093'

export default {
  getShareData: (documentId) => {
    return axios.get(`${shareAPI}/documents/${documentId}/shares`)
  },
  addShare: (documentId, department) => {
    return axios.post(`${shareAPI}/documents/${documentId}/shares`, department)
  },
  revokeShare: (documentId, department) => {
    return axios.delete(`${shareAPI}/documents/${documentId}/shares`, {
      data: department
    })
  },
  getDocuments: (key, userID) => {
    if (typeof userID === 'number') {
      return axios.get(`${documentAPI}/documents/all/${key}?userID=${userID}&order=ASC&orderBy=id`)
    }

    return axios.get(`${documentAPI}/documents/all/${key}?order=ASC&orderBy=id`)
  },
  createDocument: (data) => {
    return axios.post(`${documentAPI}/documents`, data, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    })
  },
  deleteDocument: (id) => {
    return axios.delete(`${documentAPI}/documents/${id}`)
  }
}
