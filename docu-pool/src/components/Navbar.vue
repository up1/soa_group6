<template>
  <div class="container is-fluid">
    <nav class="nav">
      <div class="nav-left">
        <router-link :to="'/'" class="nav-item">
          <h1 class="subtitle">DocuPool <sup style="color: skyblue"><small>Alpha</small></sup></h1>
        </router-link>
        <div class="field has-addons nav-item is-hidden-mobile">
          <p class="control">
            <input class="input" type="text" placeholder="Search...">
          </p>
          <p class="control">
            <button class="button">
              <span class="icon">
                <i class="fa fa-search"></i>
              </span>
            </button>
          </p>
        </div>
      </div>

      <!-- This "nav-toggle" hamburger menu is only visible on mobile -->
      <!-- You need JavaScript to toggle the "is-active" class on "nav-menu" -->
      <span class="nav-toggle">
        <span></span>
        <span></span>
        <span></span>
      </span>

      <div class="nav-right nav-menu">
        <a class="nav-item">
          <span class="icon">
            <i class="fa fa-user"></i>
          </span>
          {{$store.state.user.username}}
        </a>
        <a class="nav-item">
          <a class="button is-danger" @click="logout">
            Logout
          </a>
        </a>
        <router-link :to="'settings'" replace class="nav-item">
          <span class="icon">
            <i class="fa fa-cog"></i>
          </span>
        </router-link>
      </div>
    </nav>
  </div>
</template>

<script>
import auth from '@/services/auth'

export default {
  name: 'navbar',
  methods: {
    logout () {
      const ok = confirm('Are you sure to log out?')
      ok ? auth.logout() : false
    }
  }
}

</script>

<style scoped>

</style>
