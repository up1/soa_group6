<template>
  <div>
    <div class="row" v-if="result && alert.addUser">
      <div class="col">
        <div class="alert" :class="{'alert-success': result.success, 'alert-danger': result.error}" role="alert">
          <template v-if="result.success">
            <h5>{{ result.success.message }}</h5>
            <hr>
            <div>Username: <code>{{ result.success.username }}</code></div>
            <div>password: <code>{{ result.success.password }}</code></div>
          </template>
          <template v-else>
            <p>{{ result.error.message }}</p>
          </template>
          <div class="my-3">
            <button class="btn btn-primary" @click="alert.addUser = false">Close</button>
          </div>
        </div>
      </div>
    </div>
    <div class="row" v-if="result && alert.editUser">
      <div class="col">
        <div class="alert" :class="{'alert-success': result.message}" role="alert">
          <template v-if="result.message">
            <p>{{ result.message }}</p>
          </template>
          <div class="my-3">
            <button class="btn btn-primary" @click="alert.editUser = false">Close</button>
          </div>
        </div>
      </div>
    </div>
    <!--<div class="row" v-if="deletedUsers.length > 0">
      <div class="col">
        <div class="alert" :class="{'alert-success': true}" role="alert">
          <ul>
            <li v-for="deleteUser in deleteUsers">
              <b>{{ deletedUser.username }}</b> deleted
            </li>
          </ul>
          <div class="my-3">
            <button class="btn btn-primary" @click="deletedUsers.length = 0">Close</button>
          </div>
        </div>
      </div>
    </div>-->
    <div class="row mb-3" v-if="enableEditUser">
      <div class="col">
        <div class="card">
          <div class="card-header">
            Edit user
          </div>
          <div class="card-block">
            <input class="form-control" type="hidden" v-model="editedUser.id">
            <div class="form-group row">
              <label for="username" class="col-2 col-form-label">Username</label>
              <div class="col-10">
                <input class="form-control" type="text" id="username" v-model="editedUser.username">
              </div>
            </div>
            <div class="form-group row">
              <label for="firstName" class="col-2 col-form-label">First name</label>
              <div class="col-10">
                <input class="form-control" type="text" id="firstName" v-model="editedUser.firstName">
              </div>
            </div>
            <div class="form-group row">
              <label for="lastName" class="col-2 col-form-label">Last name</label>
              <div class="col-10">
                <input class="form-control" type="text" id="lastName" v-model="editedUser.lastName">
              </div>
            </div>
            <div class="form-group row">
              <label for="department" class="col-2 col-form-label">Department</label>
              <div class="col-10">
                <select class="form-control" v-model="editedUser.department.id">
                  <option v-for="department in departments" :key="department.id" :value="department.id">
                    {{ department.name }}
                  </option>
                </select>
              </div>
            </div>
            <button class="btn btn-success" @click="updateUser">Save</button>
            <button class="btn btn-secondary" @click="enableEditUser = false">Cancel</button>
          </div>
        </div>
      </div>
    </div>
    <div class="row" v-if="loading">
      <div class="col text-center">
        <i class="fa fa-circle-o-notch fa-spin fa-3x fa-fw"></i>
        <span class="sr-only">Loading...</span>
      </div>
    </div>
    <template v-else>
      <div class="row mb-3">
        <div class="col d-flex">
          <div class="ml-auto">
            <button class="btn btn-danger" :class="{ disabled: noSelectedAccounts }" :disabled="noSelectedAccounts" @click="deleteUsers"><i class="fa fa-trash fa-lg"></i> Delete</button>
            <button class="btn btn-success" @click="newAccount"><i class="fa fa-plus fa-lg"></i> New account</button>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col">
          <table class="table table-hover table-responsive" id="user-table">
            <thead>
              <tr>
                <th>
                  <label class="custom-control custom-checkbox m-0">
                    <input type="checkbox" class="custom-control-input" v-model="selectAll">
                    <span class="custom-control-indicator"></span>
                  </label>
                </th>
                <th>#</th>
                <th>Username</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Department</th>
                <th>Role</th>
                <th></th>
                <th></th>
              </tr>
            </thead>
            <tbody>
              <tr
                v-for="user in users"
                :key="user.id">
                <td>
                  <label class="custom-control custom-checkbox m-0">
                    <input type="checkbox" class="custom-control-input" :value="user" v-model="selected">
                    <span class="custom-control-indicator"></span>
                  </label>
                </td>
                <td>{{ user.id }}</td>
                <td>{{ user.username }}</td>
                <td>{{ user.first_name }}</td>
                <td>{{ user.last_name }}</td>
                <td>{{ user.department.name }}</td>
                <td>{{ user.role !== 0 ? 'Admin' : 'Employee' }}</td>
                <td><button class="btn btn-warning btn-sm" @click="resetPassword(user, $event)"><i class="fa fa-refresh fa-fw"></i></button></td>
                <td><button class="btn btn-primary btn-sm" @click="editUser(user, $event)"><i class="fa fa-pencil fa-fw"></i></button></td>
              </tr>
              <tr id="new-user" v-if="enableNewAccount">
                <td></td>
                <td></td>
                <td>
                  <div class="form-group">
                    <input type="text" class="form-control form-control-sm" placeholder="Username" v-model="user.username">
                  </div>
                </td>
                <td>
                  <input type="text" class="form-control form-control-sm" placeholder="First name" v-model="user.firstName">
                </td>
                <td>
                  <input type="text" class="form-control form-control-sm" placeholder="Last name" v-model="user.lastName">
                </td>
                <td>
                  <select class="form-control form-control-sm" v-model="user.department.id">
                    <option v-for="department in departments" :key="department.id" :value="department.id">
                      {{ department.name }}
                    </option>
                  </select>
                </td>
                <td>
                  <label class="custom-control custom-checkbox">
                    <input type="checkbox" class="custom-control-input" v-model="user.admin">
                    <span class="custom-control-indicator"></span>
                    <span class="custom-control-description">Is admin?</span>
                  </label>
                </td>
                <td colspan="2">
                  <div class="d-flex">
                    <button type="button" class="btn btn-success btn-sm" @click="addUser">Add</button>
                    <button type="button" class="btn btn-danger btn-sm ml-1" @click="cancel">Cancel</button>
                  </div>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </template>
  </div>
</template>

<script>
import { date } from '@/filters'
import departmentService from '@/services/department'
import userService from '@/services/user'
import axios from 'axios'
import $ from 'jquery'

export default {
  name: 'account',
  data () {
    return {
      user: {
        username: '',
        firstName: '',
        lastName: '',
        department: {
          id: ''
        },
        admin: false
      },
      editedUser: {},
      users: [],
      departments: [],
      selected: [],
      enableNewAccount: false,
      loading: false,
      result: '',
      alert: {
        addUser: false,
        editUser: false
      },
      enableEditUser: false,
      deletedUsers: []
    }
  },
  filters: {
    date
  },
  computed: {
    selectAll: {
      get () {
        return this.users ? this.selected.length === this.users.length : false
      },
      set (value) {
        const selected = []

        if (value) {
          this.users.forEach(account => {
            selected.push(account)
          })
        }

        this.selected = selected
      }
    },
    noSelectedAccounts () {
      return this.selected.length <= 0
    }
  },
  methods: {
    newAccount () {
      this.enableNewAccount = true
      setTimeout(() => {
        $('html, body').animate({scrollTop: $('#new-user').offset().top})
      }, 0)
    },
    cancel () {
      this.enableNewAccount = false
    },
    addUser () {
      const user = {
        username: this.user.username.trim(),
        first_name: this.user.firstName.trim(),
        last_name: this.user.lastName.trim(),
        role: this.user.admin ? 1 : 0,
        department: this.user.department
      }

      userService.addUser(user).then(response => {
        this.result = response.data
      }).then(response => {
        this.alert.addUser = true
        this.cancel()
        this.fetchUsers()
        this.clearInput()
      })
    },
    editUser (user, event) {
      this.enableEditUser = true
      this.editedUser = {
        id: user.id,
        username: user.username,
        firstName: user.first_name,
        lastName: user.last_name,
        department: user.department
      }
      window.scrollTo(0, 0)
    },
    fetchUsers () {
      this.loading = true
      userService.getUsers().then(response => {
        this.users = response.data
        this.loading = false
      })
    },
    updateUser () {
      const user = {
        id: this.editedUser.id,
        username: this.editedUser.username,
        first_name: this.editedUser.firstName,
        last_name: this.editedUser.lastName,
        department: this.editedUser.department
      }

      userService.updateUser(user).then(response => {
        this.result = response.data
      }).then(response => {
        this.alert.editUser = true
        this.enableEditUser = false
        this.fetchUsers()
      })
    },
    deleteUsers () {
      const concurrent = []
      this.selected.forEach(user => {
        concurrent.push(userService.deleteUser(user.id))
      })
      axios.all(concurrent).then(response => {
        this.fetchUsers()
      })
    },
    clearInput () {
      this.user = {
        username: '',
        firstName: '',
        lastName: '',
        department: {
          id: ''
        },
        admin: false
      }
    },
    resetPassword (user, event) {
      userService.resetPassword({id: user.id}).then(response => {
        $(event.target).closest('button').after(` <small class="text-warning">${response.data.message}</small>`)
      })
    }
  },
  created () {
    this.fetchUsers()

    departmentService.getDepartments().then(response => {
      this.departments = response.data
    })
  }
}
</script>

<style lang="scss">
</style>
