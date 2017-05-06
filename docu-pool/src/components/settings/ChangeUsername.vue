<template>
  <div class="card-block" v-if="state === 'idle'">
    <div class="alert alert-info" role="alert" v-show="message">
      {{message}}
    </div>
    <form @submit.prevent="submit">
      <div class="form-group">
        <label for="newUsername">New username</label>
        <input type="text" class="form-control" id="newUsername" placeholder="Username" v-model="payload.username" required>
      </div>
      <div class="form-group">
        <label for="newUsernamePasswordConfirm">Password</label>
        <input type="password" class="form-control" id="newUsernamePasswordConfirm" placeholder="Password" v-model="payload.password" required>
      </div>
      <button class="btn btn-success btn-block"><i class="fa fa-check fa-fw"></i> Submit</button>
    </form>
  </div>
  <div class="card-block" v-else>
    <div class="progress">
      <div class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%"></div>
    </div>
  </div>
</template>

<script>
import userService from '@/services/user'

export default {
  name: 'change-username',
  data () {
    return {
      payload: {
        id: undefined,
        username: '',
        password: ''
      },
      message: undefined,
      state: 'idle'
    }
  },
  methods: {
    submit () {
      this.payload.id = this.$store.state.user.id
      userService.updateUserUsername(this, this.payload)
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
