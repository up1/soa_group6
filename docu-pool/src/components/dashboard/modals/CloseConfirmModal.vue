<template>
  <div class="modal" id="closeConfirmModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">
            Close confirmation
          </h5>
          <button type="button" class="close" aria-label="Close" @click="cancel">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <div class="container-fluid">
            <div class="row">
              <div class="col">
                <p>Are you sure to close?</p>
              </div>
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" @click="confirm">Yes</button>
          <button type="button" class="btn btn-primary" @click="cancel">No</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import $ from 'jquery'
import { EventBus } from '@/event-bus'

export default {
  name: 'closeConfirmModal',
  created () {
    EventBus.$on('modal', (modal) => {
      if (modal === 'close-confirm') this.$emit('open')
    })

    this.$on('open', () => {
      $('#closeConfirmModal').modal('show')
    })

    this.$on('close', () => {
      $('#closeConfirmModal').modal('hide')
      EventBus.$emit('modal', false)
    })
  },
  methods: {
    confirm () {
      this.$emit('close')
      EventBus.$emit('modal:closed', 'new-document')
    },
    cancel () {
      this.$emit('close')
    }
  }
}
</script>

<style lang="scss" scoped>
#closeConfirmModal {
  z-index: 1051;
}
</style>
