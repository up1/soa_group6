<template>
  <div>
    <div id="accordion" role="tablist" aria-multiselectable="true">
      <div class="card">
        <div class="card-header" role="tab" id="changeUsername">
          <h5 class="mb-0">
            <a data-toggle="collapse" data-parent="#accordion" href="#changeUsernameCollapse" aria-expanded="true" aria-controls="changeUsernameCollapse">
              Change username
            </a>
          </h5>
        </div>

        <div id="changeUsernameCollapse" class="collapse" role="tabpanel" aria-labelledby="changeUsername">
          <change-username />
        </div>
      </div>
      <div class="card">
        <div class="card-header" role="tab" id="changePassword">
          <h5 class="mb-0">
            <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#changePasswordCollapse" aria-expanded="false" aria-controls="changePasswordCollapse">
              Change password
            </a>
          </h5>
        </div>
        <div id="changePasswordCollapse" class="collapse" role="tabpanel" aria-labelledby="changePassword">
          <change-password />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ChangeUsername from './ChangeUsername'
import ChangePassword from './ChangePassword'

export default {
  name: 'userForm',
  components: {
    ChangeUsername,
    ChangePassword
  }
}
</script>

<style lang="scss" scoped>
</style>
