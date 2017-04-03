<template>
  <aside class="menu">
    <ul class="menu-list">
      <li>
        <a :class="{'is-active': isActive('recent')}" @click="setActive('recent')">
          <span class="icon">
            <i class="fa fa-clock-o"></i>
          </span>
          Recent
          <span class="tag is-danger">
            1
          </span>
        </a>
      </li>
      <li>
        <a :class="{'is-active': isActive('departmentInbox')}" @click="setActive('departmentInbox')">
          <span class="icon">
            <i class="fa fa-inbox"></i>
          </span>
          Department inbox
          <span class="tag is-danger">
            1
          </span>
        </a>
      </li>
      <li>
        <a :class="{'is-active': isActive('departmentsPool')}" @click="setActive('departmentsPool')">
          <span class="icon">
            <i class="fa fa-folder"></i>
          </span>
          Department's pool
        </a>
      </li>
      <li>
        <a :class="{'is-active': isActive('myPendingDocuments')}" @click="setActive('myPendingDocuments')">
          <span class="icon">
            <i class="fa fa-hourglass"></i>
          </span>
          My pending documents
        </a>
      </li>
    </ul>
    <template v-if="$store.state.role === 'admin'">
      <p class="menu-label">
        Administration
      </p>
      <ul class="menu-list">
        <li>
          <a :class="{'is-active': isActive('allDocumentsInCompany')}" @click="setActive('allDocumentsInCompany')">
            <span class="icon">
              <i class="fa fa-globe"></i>
            </span>
            All documents in company
          </a>
        </li>
        <li>
          <a :class="{'is-active': isActive('accounts')}" @click="setActive('accounts')">
            <span class="icon">
              <i class="fa fa-users"></i>
            </span>
            Accounts
          </a>
        </li>
      </ul>
    </template>
  </aside>
</template>

<script>
export default {
  name: 'dashboardMenu',
  methods: {
    setActive (menu) {
      this.$store.state.currentMenu = menu
    },
    isActive (menu) {
      return this.$store.state.currentMenu === menu
    }
  }
}
</script>

<style lang="scss" scoped>
</style>
