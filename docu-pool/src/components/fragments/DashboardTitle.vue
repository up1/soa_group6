<template>
  <div class="level">
    <div class="level-left">
      <div class="level-item">
        <h1 class="title">{{$store.state.department | uppercase}}</h1>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'dashboardTitle',
  filters: {
    uppercase (string) {
      return string.toUpperCase()
    }
  }
}
</script>

<style lang="scss" scoped>
</style>
