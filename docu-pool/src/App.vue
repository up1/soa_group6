<template>
  <div id="app">
    <navbar v-if="exception" />
    <router-view></router-view>
  </div>
</template>

<script>
import Navbar from '@/components/Navbar'
import axios from 'axios'

const CHECK_TOKEN = 'http://35.187.208.148:8094/checkToken'

export default {
  name: 'app',
  components: { Navbar },
  computed: {
    exception () {
      const routeNames = ['notFound', 'login']
      return !routeNames.includes(this.$router.matcher.match(this.$route.path).name)
    }
  },
  watch: {
    '$route': function (to, from) {
      axios.get(CHECK_TOKEN, {
        params: {
          token: localStorage.getItem('token')
        }
      }).then(response => {
        this.$store.state.user = response.data
      })
    }
  },
  created () {
    axios.get(CHECK_TOKEN, {
      params: {
        token: localStorage.getItem('token')
      }
    }).then(response => {
      this.$store.state.user = response.data
    })
  }
}
</script>

<style>
@import url('https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css');

html {
  overflow-y: auto !important;
}
</style>
