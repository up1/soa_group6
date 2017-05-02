<template>
  <form @submit.prevent="submit" v-if="state === 'idle'">
    <div class="form-group">
      <label for="username">Username</label>
      <input type="text" class="form-control" id="username" placeholder="Username" v-model="username">
    </div>
    <div class="form-group" v-if="$store.state.user.passwordChanged">
      <label for="oldPassword">Old password</label>
      <input type="password" class="form-control" id="oldPassword" placeholder="Old password" v-model="password.old">
    </div>
    <div class="form-group">
      <label for="newPassword">New password</label>
      <input type="password" class="form-control" id="newPassword" placeholder="New password" v-model="password.new">
    </div>
    <div class="form-group">
      <label for="confirmPassword">Confirm password</label>
      <input type="password" class="form-control" id="confirmPassword" placeholder="Confirm password" v-model="password.confirm">
    </div>
    <div class="d-flex">
      <div class="ml-auto">
        <button type="submit" class="btn btn-success"><i class="fa fa-check mr-2"></i>Submit</button>
        <button type="button" class="btn btn-danger" @click="cancel"><i class="fa fa-times mr-2"></i>Cancel</button>
      </div>
    </div>
  </form>
  <div class="card-text text-center" v-else-if="state === 'processing'">
    <i class="fa fa-spinner fa-spin fa-3x fa-fw"></i>
    <span class="sr-only">Loading...</span>
  </div>
  <div class="card-text" v-else-if="state === 'finished'">
    <p>You've changed your password</p>
    <button type="button" class="btn btn-primary" @click="cancel"><i class="fa fa-chevron-left mr-2"></i>Back</button>
  </div>
</template>

<script>
export default {
  name: 'userForm',
  data () {
    return {
      username: this.$store.state.user.username,
      password: {
        old: undefined,
        new: undefined,
        confirm: undefined
      },
      state: 'idle'
    }
  },
  methods: {
    cancel () {
      window.location.pathname = ''
    },
    submit () {
      this.state = 'processing'
      setTimeout(() => {
        this.state = 'finished'
      }, 1000)
    }
  },
  watch: {
    '$store.state.user.username' (value) {
      this.username = value
    }
  }
}
</script>

<style lang="scss" scoped>
</style>
