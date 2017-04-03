<template>
  <div id="app">
    <navbar v-if="exception" />
    <router-view></router-view>
  </div>
</template>

<script>
import Navbar from '@/components/Navbar'

export default {
  name: 'app',
  components: { Navbar },
  computed: {
    exception () {
      const routeNames = ['notFound', 'login']
      return !routeNames.includes(this.$router.matcher.match(this.$route.path).name)
    }
  }
}
</script>

<style>
@import url('https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css');
</style>
