<template>
  <div class="row">
    <div class="col-12 mb-3">
      <div class="card card-inverse card-primary">
        <div class="card-block">
          <h4 class="card-title"><i class="fa fa-address-card fa-fw mr-2"></i>Full name</h4>
          <p class="card-text lead" v-text="fullName"></p>
        </div>
      </div>
    </div>
    <div class="col-12 mb-3">
      <div class="card card-inverse card-primary">
        <div class="card-block">
          <h4 class="card-title"><i class="fa fa-building fa-fw mr-2"></i>Department</h4>
          <p class="card-text lead" v-text="$store.state.user.department.name"></p>
        </div>
      </div>
    </div>
    <div class="col-12 mb-3">
      <div class="card card-inverse card-primary">
        <div class="card-block">
          <h4 class="card-title"><i class="fa fa-user-circle fa-fw mr-2"></i>Account type</h4>
          <p class="card-text lead" v-text="accountType"></p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'userInfo',
  computed: {
    fullName () {
      return `${this.$store.state.user.firstName} ${this.$store.state.user.lastName}`
    },
    accountType () {
      return this.$store.state.user.admin ? 'Admin' : 'Employee'
    }
  }
}
</script>

<style lang="scss" scoped>
</style>
