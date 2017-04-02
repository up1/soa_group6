<template>
  <div id="login">
    <section class="hero is-fullheight">
      <div class="hero-body">
        <div class="container">
          <div class="level">
            <div class="level-item" style="flex-direction: column;">
              <div class="columns">
                <div class="column has-text-centered">
                  <h1 class="title is-marginless">DocuPool</h1>
                  <h2 class="subtitle is-marginless">A document management system</h2>
                </div>
              </div>
              <div class="columns">
                <div class="column">
                  <div class="field">
                    <p class="control has-icon">
                      <input class="input" type="email" placeholder="Username">
                      <span class="icon is-small">
                        <i class="fa fa-user"></i>
                      </span>
                    </p>
                  </div>
                  <div class="field">
                    <p class="control has-icon">
                      <input class="input" type="password" placeholder="Password">
                      <span class="icon is-small">
                        <i class="fa fa-lock"></i>
                      </span>
                    </p>
                  </div>
                  <div class="field">
                    <p class="control">
                      <label class="checkbox">
                        <input type="checkbox">
                        Remember me
                      </label>
                    </p>
                  </div>
                  <div class="field">
                    <p class="control">
                      <button class="button is-success is-fullwidth" @click="$router.push('/')">
                        Login
                      </button>
                    </p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
export default {
}
</script>

<style lang="css">
</style>
