<template>
  <div id="login" class="login d-flex">
    <div class="login__container">
      <div class="row">
        <div class="col text-center mt-3 mb-3">
          <h4>DocuPool</h4>
          <h5>A document management system</h5>
        </div>
      </div>
      <div class="row">
        <div class="col">
          <div class="alert alert-danger" role="alert" v-if="error.message">
            {{error.message}}
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col">
          <form @submit.prevent="login">
            <div class="form-group" :class="{'has-danger': error.message && !error.connectionError}">
              <label class="sr-only" for="username">Username</label>
              <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                <div class="input-group-addon"><i class="fa fa-user fa-fw"></i></div>
                <input type="text" class="form-control form-control-danger" id="username" placeholder="Username" v-model="credentials.username" required>
              </div>
            </div>
            <div class="form-group" :class="{'has-danger': error.message && !error.connectionError}">
              <label class="sr-only" for="password">Password</label>
              <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                <div class="input-group-addon"><i class="fa fa-key fa-fw"></i></div>
                <input type="password" class="form-control form-control-danger" id="password" placeholder="Password" v-model="credentials.password" required>
              </div>
            </div>
            <login-button :state="state" />
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import LoginButton from './LoginButton'
import auth from '@/services/auth'

export default {
  name: 'login',
  components: {
    LoginButton
  },
  data () {
    return {
      credentials: {
        username: '',
        password: ''
      },
      error: {},
      state: 'idle'
    }
  },
  methods: {
    login () {
      auth.login(this, this.credentials, '/')
    }
  }
}
</script>

<style lang="scss" scoped>
.login {
  position: absolute;
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
  > .login__container {
    max-width: 300px;
    margin: auto;
  }
}
</style>
