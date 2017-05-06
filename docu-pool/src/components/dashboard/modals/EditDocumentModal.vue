<template>
  <div class="modal" id="editDocumentModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Edit document</h5>
          <button type="button" class="close" aria-label="Close" @click="cancel">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <template v-if="loading">
          <div class="modal-body">
            <div class="container-fluid">
              <div class="row">
                <div class="col text-center">
                  <i class="fa fa-circle-o-notch fa-spin fa-3x fa-fw"></i>
                  <span class="sr-only">Loading...</span>
                </div>
              </div>
            </div>
          </div>
        </template>
        <template v-else>
          <div class="modal-body">
            <div class="container-fluid">
              <div class="row mb-3">
                <div class="col">
                  <div class="form-inline">
                    <label class="sr-only" for="documentTitle">Name</label>
                    <input type="text" class="form-control mb-2 mr-sm-2 mb-sm-0 w-50 text-ellipsis" id="documentTitle" placeholder="Title" v-model="document.title">

                    <select class="custom-select ml-auto" v-model="document.tag">
                      <option value="" disabled selected>Select tag</option>
                      <option value="general">General</option>
                      <option value="urgent">Urgent</option>
                      <option value="non-urgent">Non-urgent</option>
                    </select>
                  </div>
                </div>
              </div>
              <div class="row mb-3">
                <div class="col">
                  <div class="form-group">
                    <label class="sr-only" for="documentDescription">Description</label>
                    <textarea class="form-control" id="documentDescription" rows="5" placeholder="Description" v-model="document.description"></textarea>
                  </div>
                </div>
              </div>
              <div class="row">
                <div class="col">
                  <div class="row mb-3">
                    <div class="col">
                      <h5>Attach files</h5>
                    </div>
                  </div>
                  <div class="row" v-show="document.attachFiles.length > 0">
                    <div class="col mb-1 d-flex justify-content-end">
                      <button class="btn btn-danger btn-sm" @click="clearAttachFiles"><i class="fa fa-times mr-2"></i>Clear</button>
                    </div>
                  </div>
                  <div class="row mb-1">
                    <div class="col">
                      <ul class="list-group">
                        <li class="list-group-item py-1" v-for="attachFile, index in document.attachFiles">
                          <div class="d-flex w-100 justify-content-between">
                            <div style="word-break: break-all">
                              <i class="fa fa-fw mr-1" :class="fileIcon(attachFile.type)"></i>{{attachFile.name}}
                              <span class="badge badge-default">{{attachFile.size | unit}}</span>
                            </div>
                            <button class="btn btn-danger btn-sm align-self-center ml-3" @click="deleteAttachFile(index)"><i class="fa fa-times"></i></button>
                          </div>
                        </li>
                      </ul>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col d-flex">
                      <input type="file" id="editAttachFile" multiple @change="fileChanged" v-show="false">
                      <button class="btn btn-success btn-sm btn-block" @click="openFileDialog"><i class="fa fa-plus"></i></button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" @click="cancel">Cancel</button>
            <button type="button" class="btn btn-primary" @click="updateDocument">Update</button>
          </div>
        </template>
      </div>
    </div>
  </div>
</template>

<script>
import DocumentTag from '@/components/fragments/DocumentTag'
import $ from 'jquery'
import { unit } from '@/filters'
import { EventBus } from '@/event-bus'
import documentService from '@/services/document'

export default {
  name: 'editDocumentModal',
  components: {
    DocumentTag
  },
  data () {
    return {
      documentId: undefined,
      document: {
        title: '',
        tag: '',
        description: '',
        attachFiles: []
      },
      loading: false
    }
  },
  filters: {
    unit
  },
  created () {
    // EventBus.$on('modal', (modal, display) => {
    //   if (modal === 'new-document') {
    //     if (typeof display === 'boolean' && !display) {
    //       this.$emit('close')
    //     } else {
    //       this.$emit('open')
    //     }
    //   }
    // })

    EventBus.$on('modal:closed', (modal) => {
      if (modal === 'edit-document') {
        this.$emit('close')
      }
    })

    EventBus.$on('modal', (modal, document) => {
      if (modal === 'edit-document') {
        this.$emit('open')
        if (document) {
          this.documentId = document.id
          this.document.title = document.title
          this.document.tag = document.tag
          this.document.description = document.description
        }
      }
    })

    this.$on('close', () => {
      this.document = {
        title: '',
        tag: '',
        description: '',
        attachFiles: []
      }

      $('#editDocumentModal').modal('hide')
      EventBus.$emit('modal', false)
    })

    this.$on('open', () => {
      $('#editDocumentModal').modal('show')
    })
  },
  methods: {
    fileChanged (e) {
      const files = e.target.files
      for (let i = 0; i < files.length; i++) {
        if (this.document.attachFiles.some(el => el.name === files[i].name)) {
          alert(`${files[i].name} is a duplicate file!`)
        } else {
          this.document.attachFiles.push(files[i])
        }
      }

      e.target.value = ''
    },
    deleteAttachFile (index) {
      this.document.attachFiles.splice(index, 1)
    },
    clearAttachFiles () {
      this.document.attachFiles = []
    },
    fileIcon (type) {
      let icon = 'fa-file-o'
      if (type.startsWith('image/')) {
        icon = 'fa-file-image-o'
      } else if (type.startsWith('audio/')) {
        icon = 'fa-file-audio-o'
      } else if (type.includes('pdf')) {
        icon = 'fa-file-pdf-o'
      } else if (type.includes('document')) {
        icon = 'fa-file-text-o'
      } else if (type.includes('zip')) {
        icon = 'fa-file-archive-o'
      }
      return icon
    },
    openFileDialog () {
      $('#editAttachFile').click()
    },
    cancel () {
      if (this.document.title ||
        this.document.tag ||
        this.document.description ||
        this.document.attachFiles.length > 0) {
        EventBus.$emit('modal', 'close-confirm')
      } else {
        this.$emit('close')
      }
    },
    updateDocument () {
      const document = new FormData()
      document.append('title', this.document.title)
      document.append('description', this.document.description)
      document.append('tag', this.document.tag)
      for (let i = 0; i < this.document.attachFiles.length; i++) {
        document.append('file', this.document.attachFiles[i], this.document.attachFiles[i].name)
      }

      this.loading = true
      documentService.updateDocument(document, this.documentId).then(response => {
        this.loading = false
        this.$emit('close')
        EventBus.$emit('documents:updated')
      })
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
