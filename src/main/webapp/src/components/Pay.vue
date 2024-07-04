<template>
  <el-dialog
    v-model="dialogVisible"
    title="Pay"
    width="500"
  >
    <span>是否确认支付</span>
    <template #footer>
      <div class="dialog-footer">
        <el-button type="primary" @click="dialogVisible = false">
          Confirm
        </el-button>
      </div>
    </template>
  </el-dialog>
</template>

<script lang="ts" setup>
import { ref } from 'vue'
import { ElMessageBox } from 'element-plus'
const dialogVisible = defineModel<boolean>("visible", { required: true });
</script>

