<template>
  <button
    type="submit"
    class="btn btn-success btn-block"
    v-html="text"
    :disabled="processing"
    :class="{ disabled: processing }"></button>
</template>

<script>
export default {
  name: 'loginButton',
  props: ['state'],
  data () {
    return {
      texts: {
        idle: `<i class="fa fa-lock"></i> Login`,
        processing: `
          <i class="fa fa-spinner fa-spin"></i>
          <span class="sr-only">Loading...</span>
        `
      }
    }
  },
  computed: {
    text () {
      return this.texts[this.state]
    },
    processing () {
      return this.state !== 'idle'
    }
  }
}
</script>

<style lang="scss">

</style>
