<template>
  <div class="card-block" v-if="state === 'idle'">
    <div class="alert alert-info" role="alert" v-show="message">
      {{message}}
    </div>
    <form @submit.prevent="submit">
      <div class="form-group">
        <label for="oldPassword">Old password</label>
        <input type="password" class="form-control" id="oldPassword" placeholder="Old password" v-model="payload.oldPassword" required>
      </div>
      <div class="form-group" :class="{'has-warning': !passwordMatch}">
        <label for="newPassword">New password</label>
        <input type="password" class="form-control" :class="{'form-control-warning': !passwordMatch}" id="newPassword" placeholder="New password" v-model="payload.newPassword" required>
        <div class="form-control-feedback" v-show="!passwordMatch">Not match!</div>
      </div>
      <div class="form-group" :class="{'has-warning': !passwordMatch}">
        <label for="confirmPassword">Confirm password</label>
        <input type="password" class="form-control" :class="{'form-control-warning': !passwordMatch}" id="confirmPassword" placeholder="Confirm password" v-model="confirmPassword" required>
        <div class="form-control-feedback" v-show="!passwordMatch">Not match!</div>
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
  name: 'change-password',
  data () {
    return {
      payload: {
        id: undefined,
        oldPassword: '',
        newPassword: ''
      },
      confirmPassword: '',
      message: undefined,
      state: 'idle',
      passwordMatch: true
    }
  },
  methods: {
    submit () {
      this.passwordMatch = true
      this.payload.id = this.$store.state.user.id
      if (this.payload.newPassword === this.confirmPassword) {
        userService.updateUserPassword(this, this.payload)
      } else {
        this.passwordMatch = false
      }
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
