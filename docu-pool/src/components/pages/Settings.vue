<template>
  <div id="settings">
    <div class="container">
      <div class="columns">
        <div class="column">
          <h1 class="title is-1">Settings</h1>
        </div>
      </div>
      <div class="columns">
        <div class="column is-one-quarter">
          <div class="field">
            <label class="label">Full name</label>
            <p class="control">
              Hanjo Shimada
            </p>
          </div>
          <div class="field">
            <label class="label">Department</label>
            <p class="control">
              IT Offfense Department
            </p>
          </div>
          <div class="field">
            <label class="label">Account type</label>
            <p class="control">
              System administrator
            </p>
          </div>
          <div class="field">
            <label class="label">Username</label>
            <p class="control">
              <input class="input" type="text" placeholder="<no-change>">
            </p>
          </div>
          <div class="field">
            <label class="label">New password</label>
            <p class="control">
              <input class="input" type="text" placeholder="<no-change>">
            </p>
          </div>
          <div class="field">
            <label class="label">Confirm new password</label>
            <p class="control">
              <input class="input" type="text">
            </p>
          </div>
          <div class="field">
            <label class="label">Confirm old password</label>
            <p class="control">
              <input class="input" type="text">
            </p>
          </div>
          <div class="field is-grouped">
            <p class="control">
              <button class="button is-success" @click="save">Save</button>
            </p>
            <p class="control">
              <button class="button" @click="cancel">Cancel</button>
            </p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'settings',
  methods: {
    cancel () {
      this.$router.replace('/')
    },
    save () {
      this.$router.replace('/')
    }
  }
}
</script>

<style lang="css">
</style>
