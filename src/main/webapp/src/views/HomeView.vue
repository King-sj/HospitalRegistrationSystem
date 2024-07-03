<script setup lang="ts">
import { useUserStore } from '@/components/LoginSystem';
import { ElMessage, ElMessageBox } from 'element-plus';
import { onMounted } from 'vue';
import { useRouter } from 'vue-router';

const userStore = useUserStore()
const user = userStore.userStorage
const router = useRouter()
onMounted(async()=>{
  await userStore.updateUser()
  if (user.type == "User") {
    ElMessageBox.confirm("You are a normal user, please select your ruler,\n or your submit is checker, please wait","warning",
      {
        confirmButtonText: 'OK',
        cancelButtonText: 'Cancel',
        type: 'warning',
      }
    )
    .then(() => {
      router.push({name:"setting"})
    })
    .catch(() => {
      ElMessage({
        type: 'info',
        message: 'logout',
      })
      userStore.logout()
      router.push({name:"login"})
    })

  }
})
</script>

<template>
  <main>
    This is home
  </main>
</template>
