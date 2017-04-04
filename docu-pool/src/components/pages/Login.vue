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
              <!-- <error-message v-if="error" :message="errorMessage" @close="showErrorMessage = false" /> -->
              <div class="columns" v-if="error">
                <div class="column">
                  <article class="message is-danger">
                    <div class="message-body">
                      {{ errorMessage }}
                    </div>
                  </article>
                </div>
              </div>
              <div class="columns">
                <div class="column">
                  <div class="field">
                    <p class="control has-icon">
                      <input :class="['input', {'is-danger': error}]" type="email" placeholder="Username" v-model="credentials.username">
                      <span class="icon is-small">
                        <i class="fa fa-user"></i>
                      </span>
                    </p>
                  </div>
                  <div class="field">
                    <p class="control has-icon">
                      <input :class="['input', {'is-danger': error}]" type="password" placeholder="Password" v-model="credentials.password" @keyup.enter="login">
                      <span class="icon is-small">
                        <i class="fa fa-lock"></i>
                      </span>
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
import auth from '@/services/auth'

export default {
  name: 'login',
  data () {
    return {
      credentials: {
        username: '',
        password: ''
      },
      correctCredentials: false,
      errorMessage: ''
    }
  },
  computed: {
    error () {
      return this.errorMessage && this.$route.query.error === null
    }
  },
  methods: {
    login () {
      // this.correctCredentials = this.credentials.username === 'test' && this.credentials.password === 'test'
      //
      // if (this.correctCredentials) {
      //   this.$router.push('/')
      // } else {
      //   this.errorMessage = 'Incorrect username or password'
      //   this.$router.push({ name: 'login', query: { error: null } })
      // }
      const credentials = {
        username: this.credentials.username,
        password: this.credentials.password
      }

      auth.login(this, credentials, '/')
    }
  }
}
</script>

<style lang="css">
</style>
