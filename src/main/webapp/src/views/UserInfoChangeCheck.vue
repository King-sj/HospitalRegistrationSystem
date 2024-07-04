<script lang="ts" setup>
import { useApiStore } from '@/apis/useApiStore';
import { computed, onMounted, ref, type Ref } from 'vue';
import UserInfoSetting from '@/components/UserInfoSetting.vue';
import { ElMessage } from 'element-plus';
const api = useApiStore()

const userInfoChanges:Ref<any[]> = ref([])
const checkCnt = computed(()=>{
  var cnt = 0;
  userInfoChanges.value.forEach((item:any)=>{
    if (item.checked == true) cnt++;
  })
  return cnt
})
const update = async ()=>{
  var res = await api.getAllUserInfoChangeReq()
  userInfoChanges.value = res.sort((a:any,b:any)=>{
    return new Date(b.date).getTime() - new Date(a.date).getTime()
  })
  // console.log("user info changes", userInfoChanges.value)
  ElMessage.info("update")
}
onMounted(update)
</script>
<template>
  <div v-if="userInfoChanges.length - checkCnt <= 0">
    <p >
      暂时没有更多结果了哦
    </p>
  <el-button type='primary' @click='update'>刷新一下</el-button>
  </div>

  <div v-for="change in userInfoChanges" :key="change.date" :change="change">
  <UserInfoSetting
    v-if="change.checked == null || change.checked == false"
    @checked="change.checked = true"

    v-model:old_user-type="change.oldUserInfo.type"
    v-model:old_address="change.oldUserInfo.address"
    v-model:old_age="change.oldUserInfo.age"
    v-model:old_gender="change.oldUserInfo.gender"
    v-model:old_name="change.oldUserInfo.name"
    v-model:old_identity="change.oldUserInfo.identity"
    v-model:old_phone="change.oldUserInfo.phone"
    v-model:old_psw="change.oldUserInfo.password"
    v-model:old_department="change.oldUserInfo.department"
    v-model:old_specialty="change.oldUserInfo.specialty"
    v-model:old_title="change.oldUserInfo.title"
    v-model:old_Hospital="change.oldUserInfo.Hospital"

    v-model:new_user-type="change.newUserInfo.type"
    v-model:new_address="change.newUserInfo.address"
    v-model:new_age="change.newUserInfo.age"
    v-model:new_gender="change.newUserInfo.gender"
    v-model:new_name="change.newUserInfo.name"
    v-model:new_identity="change.newUserInfo.identity"
    v-model:new_phone="change.newUserInfo.phone"
    v-model:new_psw="change.newUserInfo.password"
    v-model:new_department="change.newUserInfo.department"
    v-model:new_specialty="change.newUserInfo.specialty"
    v-model:new_title="change.newUserInfo.title"
    v-model:new_Hospital="change.newUserInfo.Hospital"

    v-model:date="change.date"
    v-model:username="change.username"
  />
  </div>

</template>
<style lang="scss" scoped>
</style>