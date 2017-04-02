<template>
  <div class="container is-fluid">
    <nav class="nav">
      <div class="nav-left">
        <router-link :to="'/'" class="nav-item">
          <!-- <img src="http://bulma.io/images/bulma-logo.png" alt="Bulma logo"> -->
          <h1 class="subtitle">DocuPool</h1>
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
          {{$store.state.username}}
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
export default {
  name: 'navbar'
}

</script>

<style scoped>

</style>
