<script lang="ts" setup>
import {ref, watch} from 'vue'
import { useUserStore, type Gender, type UserType } from '@/components/LoginSystem';
import { useRouter } from 'vue-router';
import {toggleTheme} from "@/styles/setting.ts"
import { useApiStore } from '@/apis/useApiStore';
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
const genderOptions: Gender[] = ["Male", "Female", "Other"];
const gender = ref(user.gender);
const userTypeOptions:UserType[] =["User","Administrator","Doctor","Patient"];
const userType = ref(user.type);
const psw = ref(user.password)
const identity = ref(user.identity)
const name = ref(user.name)
const age = ref(user.age)
const phone = ref(user.phone)
const address = ref(user.address)
console.log("user", user)
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

    <el-row>
      <div id="change-UserType">
        <el-text>用户类型  </el-text>
        <el-select
          v-model="userType"
          placeholder="Select"
          size="small"
          style="width: 240px"
        >
          <el-option
            v-for="item in userTypeOptions"
            :key="item"
            :label="item"
            :value="item"
          />
        </el-select>
      </div>
    </el-row>

    <el-row>
        <el-input v-model="name">
          <template #prefix>姓名: </template>
        </el-input>
    </el-row>

    <el-row>
      <div id="change-Gender">
        <el-text>性别  </el-text>
        <el-select
          v-model="gender"
          placeholder="Select"
          size="small"
          style="width: 240px"
        >
          <el-option
            v-for="item in genderOptions"
            :key="item"
            :label="item"
            :value="item"
          />
        </el-select>
      </div>
    </el-row>

    <el-row>
      <el-input v-model="psw" type="password">
        <template #prefix>密码: </template>
      </el-input>
    </el-row>

    <el-row>
      <el-input v-model="identity">
        <template #prefix>身份证号: </template>
      </el-input>
    </el-row>

    <el-row>
      <el-input v-model="age">
        <template #prefix>年龄： </template>
      </el-input>
    </el-row>

    <el-row>
      <el-input v-model="phone">
        <template #prefix>
          电话:
        </template>
      </el-input>
    </el-row>

    <el-row>
      <el-input v-model="address">
        <template #prefix>住址: </template>
      </el-input>
    </el-row>

    <el-row>
      <el-col :span="8">
        <n-button @click="">
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