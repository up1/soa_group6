<template>
  <div>
    <template v-if="loading">
      <div class="row">
        <div class="col text-center">
          <i class="fa fa-circle-o-notch fa-spin fa-3x fa-fw"></i>
          <span class="sr-only">Loading...</span>
        </div>
      </div>
    </template>
    <template v-else>
      <div class="row mb-3" v-if="$route.fullPath !== '/all-documents'">
        <div class="col d-flex">
          <div class="ml-auto">
            <button class="btn btn-danger" :class="{disabled: noSelectedFiles}" @click="deleteDocument(selected)" :disabled="noSelectedFiles"><i class="fa fa-trash fa-lg"></i> Delete</button>
            <button class="btn btn-success" @click="newDocument"><i class="fa fa-plus fa-lg"></i> New document</button>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col">
          <table class="table table-hover table-responsive">
            <thead>
              <tr>
                <th>
                  <label class="custom-control custom-checkbox m-0">
                    <input type="checkbox" class="custom-control-input" v-model="selectAll">
                    <span class="custom-control-indicator"></span>
                  </label>
                </th>
                <th>Tag</th>
                <th>#</th>
                <th>Title</th>
                <th>Last updated</th>
                <th>Owner</th>
              </tr>
            </thead>
            <tbody>
              <tr
                v-for="document in documents"
                :style="{ cursor: 'pointer' }"
                :key="document.id"
                @click="documentInfo(document, $event)">
                <td>
                  <label class="custom-control custom-checkbox m-0">
                    <input type="checkbox" class="custom-control-input" :value="document" v-model="selected">
                    <span class="custom-control-indicator"></span>
                  </label>
                </td>
                <td><document-tag :tag="document.tag" /></td>
                <td>{{ document.id }}</td>
                <td>{{ document.title }}</td>
                <td>{{ document.lastUpdated | date }}</td>
                <td>{{ document.department.name }}</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </template>

    <new-document-modal />
    <delete-documents-modal />
    <document-info-modal />
    <share-document-modal />
    <edit-document-modal />
    <close-confirm-modal />
  </div>
</template>

<script>
import NewDocumentModal from './modals/NewDocumentModal'
import DeleteDocumentsModal from './modals/DeleteDocumentsModal'
import DocumentInfoModal from './modals/DocumentInfoModal'
import ShareDocumentModal from './modals/ShareDocumentModal'
import EditDocumentModal from './modals/EditDocumentModal'
import CloseConfirmModal from './modals/CloseConfirmModal'
import DocumentTag from '@/components/fragments/DocumentTag'
import { date } from '@/filters'
import { EventBus } from '@/event-bus'
import documentService from '@/services/document'

export default {
  name: 'recent',
  components: {
    NewDocumentModal,
    DocumentTag,
    DeleteDocumentsModal,
    DocumentInfoModal,
    ShareDocumentModal,
    EditDocumentModal,
    CloseConfirmModal
  },
  created () {
    EventBus.$on(['documents:deleted', 'documents:created'], () => {
      this.fetchDocuments()
    })

    this.loading = true

    switch (this.$route.fullPath) {
      case '/recent':
        this.fetchDocuments('recent')
        break

      case '/pool':
        this.fetchDocuments('pool')
        break

      case '/all-documents':
        this.fetchDocuments('recent', 'all-documents')
        break
    }
  },
  data () {
    return {
      selected: [],
      documents: [],
      departments: [],
      loading: false
    }
  },
  filters: {
    date
  },
  methods: {
    documentInfo (document, e) {
      EventBus.$emit('modal', 'document-info', document, e)
    },
    newDocument () {
      EventBus.$emit('modal', 'new-document')
    },
    deleteDocument (documents) {
      EventBus.$emit('modal', 'delete-documents', documents)
    },
    fetchDocuments (key, alternate) {
      this.loading = true
      let promise
      if (alternate === 'all-documents') {
        promise = documentService.getDocuments(key)
      } else {
        if (key) {
          promise = documentService.getDocuments(key, this.$store.state.user.id)
        } else {
          promise = documentService.getDocuments('recent', this.$store.state.user.id)
        }
      }
      promise.then(response => {
        this.documents = response.data
        this.loading = false
      })
    }
  },
  computed: {
    selectAll: {
      get () {
        return this.documents ? this.selected.length === this.documents.length : false
      },
      set (value) {
        const selected = []

        if (value) {
          this.documents.forEach(document => {
            selected.push(document)
          })
        }

        this.selected = selected
      }
    },
    noSelectedFiles () {
      return this.selected.length <= 0
    }
  },
  watch: {
    '$store.state.user.id' (value) {
      this.fetchDocuments()
    },
    '$route' (to, from) {
      if (to.fullPath === '/recent') {
        this.fetchDocuments('recent')
      }

      if (to.fullPath === '/pool') {
        this.fetchDocuments('pool')
      }

      if (to.fullPath === '/all-documents') {
        this.fetchDocuments('recent', 'all-documents')
      }
    }
  }
}
</script>
