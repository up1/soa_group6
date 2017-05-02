<template>
  <div>
    <div class="modal" id="editDocumentModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Edit document</h5>
            <button type="button" class="close" aria-label="Close" @click="cancel">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
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
                </div>
              </div>
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" @click="cancel">Cancel</button>
            <button type="button" class="btn btn-primary" @click="createDocument">Create</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import DocumentTag from '@/components/fragments/DocumentTag'
import $ from 'jquery'
import { unit } from '@/filters'
import { EventBus } from '@/event-bus'

export default {
  name: 'editDocumentModal',
  components: {
    DocumentTag
  },
  data () {
    return {
      document: {
        title: '',
        tag: '',
        description: '',
        attachFiles: []
      }
    }
  },
  filters: {
    unit
  },
  created () {
    EventBus.$on('modal', (modal, document) => {
      if (modal === 'edit-document') {
        this.$emit('open')

        this.document.title = document.title
        this.document.tag = document.tag
        this.document.description = document.description
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
      EventBus.$emit('modal-level', 'close')
    })

    this.$on('open', () => {
      $('#editDocumentModal').modal('show')
      EventBus.$emit('modal-level')
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
      $('#attachFile').click()
    },
    cancel () {
      if (this.document.title ||
        this.document.tag ||
        this.document.description ||
        this.document.attachFiles.length > 0) {
        EventBus.$emit('new-document-close-confirm')
      } else {
        this.$emit('close')
      }
    },
    createDocument () {
      // Create document
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
