<template>
  <div class="container">
    <div class="row">
      <div class="col">
        <h1 class="display-4"><i class="fa fa-cog fa-fw"></i>Settings</h1>
        <hr class="my-4">
      </div>
    </div>
    <div class="row">
      <div class="col-md-6">
        <user-info />
      </div>
      <div class="col-md">
        <div class="row">
          <div class="col">
            <user-form />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import UserInfo from './UserInfo'
import UserForm from './UserForm'

export default {
  name: 'settings',
  components: {
    UserInfo,
    UserForm
  }
}
</script>

<style lang="scss" scoped>

</style>
