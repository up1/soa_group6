<template>
  <div class="modal is-active" v-if="active && doc != null">
    <div class="modal-background"></div>
    <div class="modal-card">
      <header class="modal-card-head">
        <p class="modal-card-title">{{ doc.docName }}</p>
        <button class="delete" @click="done"></button>
      </header>
      <section class="modal-card-body">
        <div class="level">
          <div class="level-left">
            <div class="level-item -vertical -left">
              <h1 class="title">
                {{ doc.docName }}
                <span class="tag">#{{ doc.docId }}</span>
              </h1>
              <div class="content is-small">
                <div>By {{ doc.owner }}</div>
                <div>Last updated: {{ doc.lastUpdated }}</div>
              </div>
            </div>
          </div>
          <div class="level-right">
            <div class="level-item -vertical -right -tag-margin">
              <span class="tag is-success">Non-urgent</span>
              <span class="tag is-info">Shared</span>
            </div>
          </div>
        </div>
        <div class="columns">
          <div class="column">
            <div class="content">
              {{ doc.description }}
            </div>
          </div>
        </div>
        <div class="columns">
          <div class="column">
            <h4 class="title is-4">Attach files</h4>
          </div>
        </div>
      </section>
      <footer class="modal-card-foot" style="justify-content: flex-end;">
        <a class="button is-danger" @click="deleteDoc">Delete</a>
        <a class="button" @click="editDoc">Edit</a>
        <a class="button is-info" @click="sendDoc">Send</a>
        <a class="button is-success" @click="done">Done</a>
      </footer>
    </div>
  </div>
</template>

<script>
export default {
  props: [
    'active',
    'doc'
  ],
  methods: {
    close () {
      this.$emit('close')
    },
    done () {
      this.close()
    },
    deleteDoc () {
      alert('deleted')
      this.close()
    },
    editDoc () {
      alert('go to edit dialog')
      this.close()
    },
    sendDoc () {
      alert('go to send dialog')
      this.close()
    }
  }
}
</script>

<style lang="scss" scoped>
.level-item {
  &.-vertical {
    flex-direction: column;
    &.-left {
      align-items: flex-start;
    }
    &.-right {
      align-items: flex-end;
    }
  }
  &.-tag-margin > .tag:not(:last-child) {
    margin-bottom: 0.5rem;
  }
}
</style>
