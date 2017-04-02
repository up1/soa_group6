<template>
  <div>
    <div class="level">
      <div class="level-left"></div>
      <div class="level-right">
        <div class="level-item">
          <div class="field is-grouped">
            <p class="control">
              <a class="button is-danger is-disabled">
                <span class="icon">
                  <i class="fa fa-trash"></i>
                </span>
                <span>Delete</span>
              </a>
            </p>
            <p class="control">
              <a class="button is-success" @click="enableAddAccount = true">
                <span class="icon">
                  <i class="fa fa-plus"></i>
                </span>
                <span>New account</span>
              </a>
            </p>
          </div>
        </div>
      </div>
    </div>
    <table class="table">
      <thead>
        <tr>
          <th><input type="checkbox"></th>
          <th>Account ID</th>
          <th>Username</th>
          <th>First name</th>
          <th>Last name</th>
          <th>Department</th>
          <th>Role</th>
          <th>Created</th>
          <th>Password</th>
          <th></th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="data in mockupData" :key="data.accountId">
          <td><input type="checkbox"></td>
          <td>{{data.accountId}}</td>
          <td>{{data.username}}</td>
          <td>{{data.name.firstName}}</td>
          <td>{{data.name.lastName}}</td>
          <td>{{data.department}}</td>
          <td>{{data.role}}</td>
          <td>{{data.created}}</td>
          <td>
            <span class="icon">
              <i class="fa fa-refresh"></i>
            </span>
          </td>
          <td>
            <span class="icon">
              <i class="fa fa-pencil"></i>
            </span>
          </td>
        </tr>
        <tr v-if="enableAddAccount">
          <td></td>
          <td></td>
          <td>
            <div class="field">
              <p class="control">
                <input class="input is-small" type="text" placeholder="Username">
              </p>
            </div>
          </td>
          <td>
            <div class="field">
              <p class="control">
                <input class="input is-small" type="text" placeholder="First name">
              </p>
            </div>
          </td>
          <td>
            <div class="field">
              <p class="control">
                <input class="input is-small" type="text" placeholder="Last name">
              </p>
            </div>
          </td>
          <td>
            <div class="field">
              <p class="control">
                <span class="select is-small">
                  <select>
                    <option v-for="department in departments">
                      {{department}}
                    </option>
                  </select>
                </span>
              </p>
            </div>
          </td>
          <td colspan="2">
            <div class="field">
              <p class="control">
                <label class="checkbox">
                  <input type="checkbox">
                  Is admin
                </label>
              </p>
            </div>
          </td>
          <td colspan="2">
            <div class="level">
              <div class="level-left"></div>
              <div class="level-right">
                <div class="level-item">
                  <div class="field is-grouped">
                    <p class="control">
                      <a class="button is-danger is-small" @click="enableAddAccount = false">
                        Cancel
                      </a>
                    </p>
                    <p class="control">
                      <a class="button is-primary is-small">
                        Create
                      </a>
                    </p>
                  </div>
                </div>
              </div>
            </div>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  name: 'accounts',
  data () {
    const mockupData = [
      {
        'accountId': '000001',
        'username': 'taotheadmin',
        'name': {
          'firstName': 'Tao',
          'lastName': 'TheAdminz'
        },
        'department': 'IT Offense',
        'role': 'Admin',
        'created': '1 Jan 2017'
      },
      {
        'accountId': '000002',
        'username': 'jeffisgay',
        'name': {
          'firstName': 'Jeff',
          'lastName': 'Kaplan'
        },
        'department': 'Human Resource',
        'role': 'Employee',
        'created': '2 Jan 2017'
      },
      {
        'accountId': '000003',
        'username': 'hanjoz',
        'name': {
          'firstName': 'Hanjo',
          'lastName': 'Shimada'
        },
        'department': 'Shimada',
        'role': 'Employee',
        'created': '14 Feb 2017'
      }
    ]

    return {
      mockupData: mockupData,
      enableAddAccount: false
    }
  }
}
</script>

<style lang="scss" scoped>
</style>
