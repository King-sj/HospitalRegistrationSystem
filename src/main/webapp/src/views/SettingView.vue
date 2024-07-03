<script lang="ts" setup>
import {ref, watch} from 'vue'
import { useUserStore, type Gender, type UserType } from '@/components/LoginSystem';
import { useRouter } from 'vue-router';
import {toggleTheme} from "@/styles/setting.ts"
import { useApiStore } from '@/apis/useApiStore';
import UserInfoView from '@/components/UserInfoView.vue';
import { ElMessageBox, ElMessage } from 'element-plus';
const router = useRouter()
const logout = ()=>{
  useUserStore().logout()
  router.push({name:"login"})
}
const themes = ['dark','light','white']
const theme = ref(themes[2])
const user = useUserStore().userStorage
const api = useApiStore()

watch(theme,
()=>{
  toggleTheme(theme.value);
},
{
  immediate:true
}
)
const genderOptions: Gender[] = ["Male", "Female", "OTHER"];
const gender = ref(user.gender);
const userTypeOptions:UserType[] =["User","Administrator","Doctor","Patient"];
const userType = ref(user.type);
const psw = ref(user.password)
const identity = ref(user.identity)
const name = ref(user.name)
const age = ref(user.age)
const phone = ref(user.phone)
const address = ref(user.address)
const Hospital = ref(user.Hospital)
const department = ref(user.department)
const title = ref(user.title)
const specialty = ref(user.specialty)
console.log("user", user)
const updateUserInfoReq = async ()=>{
  console.log("user", user)
  useUserStore().updateUserInfoReq({
    email:user.email,
    name:name.value,
    gender:gender.value,
    type:userType.value,
    password:psw.value,
    identity:identity.value,
    age:age.value,
    phone:phone.value,
    address:address.value,
    expiration:0,
    token:user.token,
    Hospital: Hospital.value,
    department: department.value,
    title:title.value,
    specialty:specialty.value
  })
  ElMessage.info('提交成功，请耐心等待管理员审批');
}
</script>
<template>
  <main style="text-align: center;">
    <el-row>
      <div id="change-theme">
        <el-text>主题  </el-text>
        <el-select
          id="theme-selector"
          v-model="theme"
          placeholder="Select"
          size="small"
          style="width: 240px"
        >
          <el-option
            v-for="item in themes"
            :key="item"
            :label="item"
            :value="item"
          />
        </el-select>
      </div>
    </el-row>
    <user-info-view
      v-model:user-type="userType"
      v-model:address="address"
      v-model:age="age"
      v-model:gender="gender"
      v-model:name="name"
      v-model:identity="identity"
      v-model:phone="phone"
      v-model:psw="psw"
      v-model:department="department"
      v-model:specialty="specialty"
      v-model:title="title"
      v-model:-hospital="Hospital"
    >
    </user-info-view>
    <el-row>
      <el-col :span="8">
        <n-button @click="updateUserInfoReq">
        提交更改
        </n-button>
      </el-col >
      <el-col :span="8">
        <n-button @click="logout">
        注销
        </n-button>
      </el-col>

    </el-row>

  </main>
</template>
<style scss scoped>
*{
  /* color: var(--color-text); */
  margin-top: 1rem;
}
.el-select{
  background-color: black;
}
</style>