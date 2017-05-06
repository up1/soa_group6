<template>
  <div>
    <div class="container-fluid">
      <div class="row mb-3">
        <div class="col">
          <h2><i class="fa fa-building fa-fw"></i> {{$store.state.user.department.name}}</h2>
        </div>
      </div>
      <div class="row">
        <div class="col-12 dashboard-menu">
          <section>
            <h5><i class="fa fa-file-text fa-fw mr-2"></i>Documents</h5>
            <div class="list-group">
              <router-link to="/recent" class="list-group-item list-group-item-action py-1" :class="{active: currentPath === '/recent'}"><i class="fa fa-clock-o fa-fw mr-3"></i>Recent</router-link>
              <router-link to="/pool" class="list-group-item list-group-item-action py-1" :class="{active: currentPath === '/pool'}"><i class="fa fa-inbox fa-fw mr-3"></i>Pool</router-link>
            </div>
          </section>
          <section v-if="$store.state.user.admin">
            <h5>Administration</h5>
            <div class="list-group">
              <router-link to="/all-documents" class="list-group-item list-group-item-action py-1" :class="{active: currentPath === '/all-documents'}"><i class="fa fa-file-text fa-fw mr-3"></i>All documents</router-link>
              <router-link to="/accounts" class="list-group-item list-group-item-action py-1" :class="{active: currentPath === '/accounts'}"><i class="fa fa-users fa-fw mr-3"></i>Accounts</router-link>
            </div>
          </section>
        </div>
        <div class="col dashboard-content">
          <router-view />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'dashboard',
  computed: {
    currentPath () {
      return this.$route.fullPath
    }
  },
  watch: {
    '$route' (to, from) {
      if (to.name !== from.name) {
        window.location.reload()
      }
    }
  }
}
</script>

<style lang="scss" scoped>
$dashboard-menu-width: 300px;

.dashboard-menu {
  max-width: $dashboard-menu-width;

  > section {
    margin-bottom: 1rem;
  }

  @media (max-width: 800px) {
    max-width: none;
  }
}

.dashboard-content {
  max-width: calc(100% - #{$dashboard-menu-width});
  @media (max-width: 800px) {
    max-width: none;
  }
}
</style>
