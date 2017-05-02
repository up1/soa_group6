<template>
  <nav class="navbar navbar-toggleable-md navbar-light bg-inverse navbar-inverse sticky-top mb-3">
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <a href="./" class="navbar-brand">DocuPool</a>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav ml-auto">
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle d-flex align-items-center" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <i class="fa fa-user fa-lg fa-fw"></i> {{ displayUser }}
          </a>
          <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownMenuLink">
            <router-link class="dropdown-item" to="settings"><i class="fa fa-cog fa-fw"></i> Settings</router-link>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="#" @click.prevent="logout"><i class="fa fa-sign-out fa-fw"></i> Log out</a>
          </div>
        </li>
      </ul>
    </div>
  </nav>
</template>

<script>
import auth from '@/services/auth'

export default {
  name: 'navbar',
  computed: {
    displayUser () {
      return this.$store.state.user.username ? `${this.$store.state.user.username} (${this.$store.state.user.firstName} ${this.$store.state.user.lastName})` : ''
    }
  },
  methods: {
    logout () {
      auth.logout()
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
