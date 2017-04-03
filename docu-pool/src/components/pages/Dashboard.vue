<template>
  <div id="dashboard">
    <div class="container is-fluid">
      <dashboard-title />

      <div class="columns">
        <div class="column is-one-quarter">
          <dashboard-menu />
        </div>

        <div class="column">
          <component :is="$store.state.currentMenu" />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import DashboardTitle from '@/components/fragments/DashboardTitle'
import DashboardMenu from '@/components/fragments/DashboardMenu'
import Recent from '@/components/fragments/Recent'
import Accounts from '@/components/fragments/Accounts'

export default {
  name: 'dashboard',
  components: {
    DashboardTitle,
    DashboardMenu,
    Recent,
    Accounts
  }
}
</script>

<style lang="scss" scoped>
</style>
