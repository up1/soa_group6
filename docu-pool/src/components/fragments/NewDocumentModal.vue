<template>
  <div class="modal is-active" v-if="active">
    <div class="modal-background"></div>
    <div class="modal-card">
      <header class="modal-card-head">
        <p class="modal-card-title">New document</p>
        <button class="delete" @click="cancel"></button>
      </header>
      <section class="modal-card-body">
        <div class="level">
          <div class="level-left">
            <div class="level-item" style="flex-direction: column; align-items: flex-start">
              <div class="field">
                <p class="control">
                  <input class="input is-medium" type="text" v-model="documentName" placeholder="Document name">
                </p>
              </div>
              <div class="content is-small">
                <div>By {{$store.state.department}}</div>
                <div>Last updated: 16 Mar 2017</div>
              </div>
            </div>
          </div>
          <div class="level-right">
            <div class="level-item">
              <div class="field">
                <p class="control">
                  <span class="select">
                    <select v-model="documentType">
                      <option disabled value="" selected>Select one</option>
                      <option>Natural</option>
                      <option>Urgent</option>
                      <option>Non-urgent</option>
                    </select>
                  </span>
                </p>
              </div>
            </div>
          </div>
        </div>
        <div class="columns">
          <div class="column">
            <div class="field">
              <p class="control">
                <textarea class="textarea" v-model="documentDescription" placeholder="Document description"></textarea>
              </p>
            </div>
          </div>
        </div>
        <div class="columns">
          <div class="column">
            <div class="field">
              <label class="label">Attach files</label>
              <p class="control">
                <input class="input" type="file" accept=".pdf" multiple>
              </p>
            </div>
          </div>
        </div>
      </section>
      <footer class="modal-card-foot" style="justify-content: flex-end;">
        <a class="button is-danger" :class="{'is-disabled': inProgress}" @click="cancel">Cancel</a>
        <a class="button is-primary" :class="{'is-disabled': inProgress}" @click="save">Save</a>
        <a class="button is-primary" :class="{'is-disabled': inProgress}" @click="createAndShare">Create and share</a>
        <a class="button is-primary" :class="{'is-disabled': inProgress}" @click="create">Create</a>
      </footer>
    </div>
  </div>
</template>

<script>
export default {
  props: ['active'],
  data () {
    return {
      documentName: '',
      documentType: '',
      documentDescription: '',
      inProgress: false
    }
  },
  methods: {
    close () {
      this.reset()
      this.$emit('close')
    },
    reset () {
      this.documentName = ''
      this.documentType = ''
      this.documentDescription = ''
      this.inProgress = false
    },
    cancel () {
      if (this.documentName.length > 0 || this.documentType.length > 0 || this.documentDescription.length > 0) {
        confirm('Are you sure?') ? this.close() : ''
      } else {
        this.close()
      }
    },
    save () {
      this.inProgress = true
      alert('saved')
      this.close()
    },
    createAndShare () {
      this.inProgress = true
      alert('created and shared')
      this.close()
    },
    create () {
      this.inProgress = true
      alert('created')
      this.close()
    }
  }
}
</script>

<style lang="css">
</style>
