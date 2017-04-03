<template>
  <div id="login">
    <section class="hero is-fullheight">
      <div class="hero-body">
        <div class="container">
          <div class="level">
            <div class="level-item" style="flex-direction: column;">
              <div class="columns">
                <div class="column has-text-centered">
                  <h1 class="title is-marginless">DocuPool</h1>
                  <h2 class="subtitle is-marginless">A document management system</h2>
                </div>
              </div>
              <error-message v-if="error" :message="errorMessage" @close="showErrorMessage = false" />
              <div class="columns">
                <div class="column">
                  <div class="field">
                    <p class="control has-icon">
                      <input class="input" type="email" placeholder="Username" v-model="username">
                      <span class="icon is-small">
                        <i class="fa fa-user"></i>
                      </span>
                    </p>
                  </div>
                  <div class="field">
                    <p class="control has-icon">
                      <input class="input" type="password" placeholder="Password" v-model="password" @keyup.enter="login">
                      <span class="icon is-small">
                        <i class="fa fa-lock"></i>
                      </span>
                    </p>
                  </div>
                  <div class="field">
                    <p class="control" style="text-align: center">
                      <label class="checkbox">
                        <input type="checkbox" v-model="remember">
                        Remember me
                      </label>
                    </p>
                  </div>
                  <div class="field">
                    <p class="control">
                      <button class="button is-success is-fullwidth" @click="login">
                        Login
                      </button>
                    </p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import Vue from 'vue'

const errorMessage = Vue.extend({
  template: `
    <div class="columns">
      <div class="column">
        <div class="notification is-danger">
          <button class="delete" @click="close"></button>
          {{ message }}
        </div>
      </div>
    </div>
  `,
  props: ['message'],
  methods: {
    close () {
      this.$emit('close')
    }
  }
})

const ErrorMessageComponent = Vue.component('error-message', errorMessage)

export default {
  name: 'login',
  components: {
    ErrorMessageComponent
  },
  data () {
    return {
      username: '',
      password: '',
      remember: false,
      correctCredentials: false,
      errorMessage: 'Incorrect username or password',
      showErrorMessage: true
    }
  },
  computed: {
    error () {
      return this.$route.query.error === null && this.showErrorMessage
    }
  },
  methods: {
    login () {
      this.correctCredentials = this.username === 'test' && this.password === 'test'

      if (this.correctCredentials) {
        this.$router.push('/')
      } else {
        this.showErrorMessage = true
        this.$router.push({ name: 'login', query: { error: null } })
      }
    }
  }
}
</script>

<style lang="css">
</style>
