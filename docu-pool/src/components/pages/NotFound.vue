<template>
  <section class="hero is-info is-fullheight">
    <div class="hero-body">
      <div class="container has-text-centered">
        <h1 class="title">
          404 Not Found
        </h1>
        <h2 class="subtitle">
          {{ $route.path }}
        </h2>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  name: 'notFound'
}
</script>

<style lang="scss" scoped>

</style>
