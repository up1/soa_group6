<template>
  <div class="modal" id="shareModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">
            Share document
          </h5>
          <button type="button" class="close" aria-label="Close" @click="cancel">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <div class="container-fluid">
            <div class="row" v-if="state === 'loading'">
              <div class="col text-center">
                <i class="fa fa-circle-o-notch fa-spin fa-3x fa-fw"></i>
                <span class="sr-only">Loading...</span>
              </div>
            </div>
            <div class="row" v-else-if="state === 'finished'">
              <div class="col">
                <select id="departmentSelector" data-placeholder="Choose a department..." class="chosen-select" multiple>
                  <option value=""></option>
                  <option v-for="department in departments" :value="department.id" v-text="department.name" :selected="department.shared"></option>
                </select>
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
import 'chosen-js'
import 'chosen-js/chosen.css'
import { EventBus } from '@/event-bus'
import documentService from '@/services/document'

export default {
  name: 'shareModal',
  created () {
    this.$on('open', () => {
      $('#shareModal').modal('show')
    })

    this.$on('close', () => {
      this.state = 'loading'
      this.shareState = 'idle'
      $('#shareModal').modal('hide')
      EventBus.$emit('modal', false)
    })

    EventBus.$on('modal', (modal, documentId) => {
      if (modal === 'share-document') {
        documentService.getShareData(documentId).then(response => {
          this.departments = response.data
          this.state = 'finished'
        }).then(response => {
          $('#departmentSelector').chosen({
            width: '100%',
            no_results_text: 'Department not found'
          }).on('change', (e, params) => {
            if (params.selected) {
              $(e.target).attr('disabled', true).trigger('chosen:updated')
              documentService.addShare(documentId, {
                departmentId: Number(params.selected)
              }).then(response => {
                $(e.target).attr('disabled', false).trigger('chosen:updated')
                documentService.getShareData(documentId).then(response => {
                  this.departments = response.data
                })
              })
            }
            if (params.deselected) {
              $(e.target).attr('disabled', true).trigger('chosen:updated')
              documentService.revokeShare(documentId, {
                departmentId: Number(params.deselected)
              }).then(response => {
                $(e.target).attr('disabled', false).trigger('chosen:updated')
                documentService.getShareData(documentId).then(response => {
                  this.departments = response.data
                })
              })
            }
          })
        })
        this.$emit('open')
      }
    })
  },
  data () {
    return {
      departments: [],
      state: 'loading',
      shareState: 'idle'
    }
  },
  methods: {
    cancel () {
      this.$emit('close')
    }
  }
}
</script>

<style lang="scss" scoped>
</style>
