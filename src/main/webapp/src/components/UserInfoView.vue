<script lang="ts" setup>
import {defineModel} from "vue";
import { useUserStore, type Gender, type UserType } from "./LoginSystem";
// const user = useUserStore().userStorage

const genderOptions: Gender[] = ["Male", "Female", "Other"];
const userTypeOptions:UserType[] =["User","Administrator","Doctor","Patient"];
const userType = defineModel<UserType>("userType",{ required: true})
const psw = defineModel<string>("psw",{required:true})
const identity = defineModel<string>("identity",{required:true})
const name = defineModel<string>("name",{required:true})
const age = defineModel<Number>("age",{required:true})
const phone = defineModel<string>("phone",{required:true})
const address = defineModel<string>("address",{required:true})
const Hospital = defineModel<string>("Hospital",{required:true})
const department = defineModel<string>("department",{required:true})
const title = defineModel<string>("title", {required:true})
const specialty = defineModel<string>("specialty",{required:true})
const gender = defineModel<Gender>("gender",{required:true})
</script>
<template>
  <main class="main-view">
  <el-row>
    <div id="change-UserType">
      <el-text>用户类型 </el-text>
      <el-select v-model="userType" placeholder="Select" size="small" style="width: 240px">
        <el-option v-for="item in userTypeOptions" :key="item" :label="item" :value="item" />
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
      <el-text>性别 </el-text>
      <el-select v-model="gender" placeholder="Select" size="small" style="width: 240px">
        <el-option v-for="item in genderOptions" :key="item" :label="item" :value="item" />
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
  <el-row v-if="userType === 'Doctor'">
    <el-input v-model="Hospital">
      <template #prefix>所在医院: </template>
    </el-input>
  </el-row>
  <el-row v-if="userType === 'Doctor'">
    <el-input v-model="department">
      <template #prefix>科室: </template>
    </el-input>
  </el-row>
  <el-row v-if="userType === 'Doctor'">
    <el-input v-model="title">
      <template #prefix>职称: </template>
    </el-input>
  </el-row>
  <el-row v-if="userType === 'Doctor'">
    <el-input v-model="specialty">
      <template #prefix>专长: </template>
    </el-input>
  </el-row>
</main>
</template>
<style lang="scss" scoped>
.main-view{
  border: 1px solid #000;
  margin: 2px;
}
</style>