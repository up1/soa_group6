<template>
  <span class="badge badge-pill badge-success" v-if="tag == 'urgent'">URGENT</span>
  <span class="badge badge-pill badge-default" v-else-if="tag == 'non-urgent'">NON-URGENT</span>
  <span class="badge badge-pill badge-primary" v-else-if="tag == 'general'">GENERAL</span>
</template>

<script>
export default {
  name: 'documentTag',
  props: ['tag']
}
</script>

<style lang="scss" scoped>

</style>
