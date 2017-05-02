<template>
  <div class="modal" id="deleteDocumentsModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel" v-if="documents.length > 1">Delete documents</h5>
          <h5 class="modal-title" id="exampleModalLabel" v-else>Delete document</h5>
          <button type="button" class="close" aria-label="Close" @click="close" v-show="['idle', 'finished'].includes(status)">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <div class="container-fluid">
            <div class="row">
              <div class="col" v-if="status === 'idle'">
                <p v-if="documents.length > 1">Are you sure to delete these documents?</p>
                <p v-else>Are you sure to delete this document?</p>
                <ul>
                  <li v-for="document in documents">
                    <span class="badge badge-default">#{{document.id}}</span> {{document.title}}
                  </li>
                </ul>
              </div>
              <div class="col text-center" v-else-if="status === 'deleting'">
                <i class="fa fa-spinner fa-spin fa-3x fa-fw"></i>
                <span class="sr-only">Loading...</span>
              </div>
              <div class="col" v-else-if="status === 'finished'">
                <p>
                  <i class="fa fa-check fa-fw"></i>
                  <span v-if="documents.length > 1">Documents have been deleted.</span>
                  <span v-else>Document has been deleted.</span>
                </p>
              </div>
            </div>
          </div>
        </div>
        <div class="modal-footer" v-if="status === 'idle'">
          <button type="button" class="btn btn-danger" @click="confirm">Yes</button>
          <button type="button" class="btn btn-primary" @click="close">No</button>
        </div>
        <div class="modal-footer" v-if="status === 'finished'">
          <button type="button" class="btn btn-primary" @click="close">Close</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import $ from 'jquery'
import { EventBus } from '@/event-bus'
import documentService from '@/services/document'
import axios from 'axios'

export default {
  name: 'deleteDocumentsModal',
  created () {
    EventBus.$on('modal', (modal, documents) => {
      if (modal === 'delete-documents') {
        this.documents = documents.sort((a, b) => a.id - b.id)
        this.$emit('open')
      }
    })

    EventBus.$on('documents:deleted', () => {
      this.status = 'idle'
    })

    this.$on('open', () => {
      $('#deleteDocumentsModal').modal('show')
    })

    this.$on('close', () => {
      if (this.status === 'finished') EventBus.$emit('documents:deleted')
      $('#deleteDocumentsModal').modal('hide')
      EventBus.$emit('modal', false)
    })
  },
  data () {
    return {
      status: 'idle',
      documents: []
    }
  },
  methods: {
    close () {
      this.$emit('close')
    },
    confirm () {
      this.status = 'deleting'

      const concurrent = []
      this.documents.forEach(document => {
        concurrent.push(documentService.deleteDocument(document.id))
      })

      axios.all(concurrent).then(response => {
        this.status = 'finished'
      })
    }
  }
}
</script>

<style lang="scss" scoped>
#deleteDocumentsModal {
  z-index: 1051;
}
</style>
