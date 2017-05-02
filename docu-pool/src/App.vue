<template>
  <div id="app">
    <navbar v-if="exception" />
    <router-view></router-view>
  </div>
</template>

<script>
import 'bootstrap/dist/js/bootstrap'

import Navbar from '@/components/fragments/Navbar'

// import axios from 'axios'
import auth from '@/services/auth'
import $ from 'jquery'
import { EventBus } from './event-bus'

export default {
  name: 'app',
  components: {
    Navbar
  },
  created () {
    auth.setUserInfo(this.$store)

    $(() => {
      $('.modal').modal({
        show: false,
        keyboard: false,
        backdrop: 'static'
      })

      $('[data-toggle="tooltip"]').tooltip({
        animation: false
      })

      $('[data-toggle="popover"]').popover({
        animation: false
      })
    })

    EventBus.$on('modal', (display) => {
      typeof display === 'boolean' && !display ? this.modalLevel-- : this.modalLevel++

      if (this.modalLevel > 0) $('body').addClass('modal-open')
      else $('body').removeClass('modal-open')
    })
  },
  data () {
    return {
      modalLevel: 0
    }
  },
  computed: {
    exception () {
      const routeNames = ['Login']
      return !routeNames.includes(this.$router.matcher.match(this.$route.path).name)
    }
  }
}
</script>

<style lang="scss">
@import "~bootstrap/scss/bootstrap";
@import url("https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css");

.text-ellipsis {
  overflow: hidden;
  text-overflow: ellipsis;
}

.modal-dialog {
  box-shadow: 0 19px 38px rgba(0,0,0,0.30), 0 15px 12px rgba(0,0,0,0.22);
}
</style>
