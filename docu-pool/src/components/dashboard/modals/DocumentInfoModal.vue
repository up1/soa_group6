<template>
  <div class="modal" id="documentInfoModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel" v-text="document.title"></h5>
          <button type="button" class="close" aria-label="Close" @click="close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <div class="container-fluid">
            <div class="row mb-3">
              <div class="col d-flex">
                <div>
                  <h3 v-text="document.title"></h3>
                  <div class="document-tag">
                    <document-tag :tag="document.tag" />
                    <span class="badge badge-pill badge-primary" v-if="document.shared">SHARED</span>
                  </div>
                  <div class="small">
                    <div><span class="badge badge-default">By</span> {{ document.department.name }}</div>
                    <div><span class="badge badge-default">Last updated</span> {{ document.lastUpdated | date }}</div>
                  </div>
                </div>
                <div class="ml-auto d-flex align-items-start">
                  <button class="btn btn-outline-primary btn-sm mr-1" data-toggle="tooltip" title="Share" @click="share"><i class="fa fa-share fa-fw"></i></button>
                  <button class="btn btn-outline-primary btn-sm mr-1" data-toggle="tooltip" title="Edit" @click="edit"><i class="fa fa-edit fa-fw"></i></button>
                  <button class="btn btn-outline-danger btn-sm" data-toggle="tooltip" title="Delete" @click="del"><i class="fa fa-trash fa-fw"></i></button>
                </div>
              </div>
            </div>

            <div class="row mb-3">
              <div class="col">
                <blockquote class="blockquote">
                  <p class="mb-0" v-text="document.description"></p>
                </blockquote>
              </div>
            </div>

            <div class="row" v-if="document.files.length > 0">
              <div class="col">
                <h5>Attach files</h5>
                <div class="row mt-3">
                  <div class="col-md-6 col-lg-4 mb-3" v-for="file in document.files" :key="file.id">
                    <div class="card card-inverse" style="background-color: #333; border-color: #333">
                      <div class="card-block d-flex flex-column py-2 px-2">
                        <div style="word-break: break-word">
                          <h6 class="card-title" v-text="file.name"></h6>
                          <p class="card-text small">
                            <span class="badge badge-default">File size</span> {{ file.size | unit }}<br>
                            <span class="badge badge-default">Last updated</span> {{ file.lastUpdated | date }}<br>
                            <span class="badge badge-default">Revision</span> {{ file.revision }}
                          </p>
                        </div>
                        <div class="mt-2">
                          <a :href="`http://35.187.208.148:8099/documents/${document.id}/files?filename=${file.name}&revision=${file.revision}&id=${file.id}`" class="btn btn-primary btn-block btn-sm">
                            <i class="fa fa-download fa-lg"></i>
                          </a>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import $ from 'jquery'
import DocumentTag from '@/components/fragments/DocumentTag'
import { unit, date } from '@/filters'
import { EventBus } from '@/event-bus'

export default {
  name: 'documentInfoModal',
  components: {
    DocumentTag
  },
  created () {
    EventBus.$on('modal', (modal, document, e) => {
      if (modal === 'document-info') {
        if (['input', 'label'].includes(e.target.localName)) {
          e.stopPropagation()
          EventBus.$emit('modal', false)
        } else {
          this.document = document
          this.$emit('open')
        }
      }
    })

    EventBus.$on('documents:deleted', () => {
      this.$emit('close')
    })

    this.$on('open', () => {
      $('#documentInfoModal').modal('show')
    })

    this.$on('close', () => {
      $('#documentInfoModal').modal('hide')
      EventBus.$emit('modal', false)
    })
  },
  data () {
    return {
      document: {
        department: {},
        files: []
      }
    }
  },
  filters: {
    date,
    unit
  },
  methods: {
    close () {
      this.$emit('close')
    },
    share () {
      EventBus.$emit('modal', 'share-document', this.document.id)
    },
    edit () {
      EventBus.$emit('modal', 'edit-document', this.document)
      this.$emit('close')
    },
    del () {
      EventBus.$emit('modal', 'delete-documents', [this.document])
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
