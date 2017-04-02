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
              <a class="button is-success" @click="activateNewDocumentModal = true">
                <span class="icon">
                  <i class="fa fa-plus"></i>
                </span>
                <span>New document</span>
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
          <th>Tag</th>
          <th>Document ID</th>
          <th>Document name</th>
          <th>Last updated</th>
          <th>Owner</th>
        </tr>
      </thead>
      <tbody>
        <tr
          v-for="data in mockupData"
          :style="{cursor: 'pointer'}"
          :key="data.docId"
          @click="selectedDocument = data; activateDocumentInfoModal = true">
          <td><input type="checkbox"></td>
          <td>
            <span class="icon" v-for="tag in data.tags">
              <i :class="'fa fa-' + tag"></i>
            </span>
          </td>
          <td>{{data.docId}}</td>
          <td>{{data.docName}}</td>
          <td>{{data.lastUpdated}}</td>
          <td>{{data.owner}}</td>
        </tr>
      </tbody>
    </table>

    <new-document-modal
      :active="activateNewDocumentModal"
      @close="activateNewDocumentModal = false" />
    <document-info-modal
      :active="activateDocumentInfoModal"
      :doc="selectedDocument"
      @close="activateDocumentInfoModal = false" />
  </div>
</template>

<script>
import NewDocumentModal from '@/components/fragments/NewDocumentModal'
import DocumentInfoModal from '@/components/fragments/DocumentInfoModal'

export default {
  name: 'recent',
  components: {
    NewDocumentModal,
    DocumentInfoModal
  },
  data () {
    const mockupData = [
      {
        'tags': [],
        'docId': '17000001',
        'docName': 'Docu name1',
        'description': 'Uvuvwevwevwe Onyetenyevwe Ugwemubwem Ossas',
        'lastUpdated': '1 Jan 2017',
        'owner': 'IT Offense Department'
      },
      {
        'tags': ['arrow-up'],
        'docId': '17000002',
        'docName': 'xDDDDDD',
        'description': 'The quick brown fox jumps over the lazy dog.',
        'lastUpdated': '4 Feb 2017',
        'owner': 'Human Resource Department'
      },
      {
        'tags': ['home', 'arrow-down'],
        'docId': '17000003',
        'docName': 'I need healing!',
        'description': 'Lorem ipsum dolor sit amet ossas..',
        'lastUpdated': '20 Apr 2017',
        'owner': 'Shimada Department'
      }
    ]

    return {
      mockupData: mockupData,
      activateNewDocumentModal: false,
      activateDocumentInfoModal: false,
      selectedDocument: null
    }
  }
}
</script>

<style lang="scss" scoped>
</style>
