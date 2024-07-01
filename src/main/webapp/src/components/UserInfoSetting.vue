<script lang="ts" setup>
import {defineModel} from "vue";
import { useUserStore, type Gender, type UserType } from "./LoginSystem";
import UserInfoView from "./UserInfoView.vue";
// const user = useUserStore().userStorage

const genderOptions: Gender[] = ["Male", "Female", "Other"];
const userTypeOptions:UserType[] =["User","Administrator","Doctor","Patient"];

const old_userType = defineModel<UserType>("old_userType",{ required: true})
const old_psw = defineModel<string>("old_psw",{required:true})
const old_identity = defineModel<string>("old_identity",{required:true})
const old_name = defineModel<string>("old_name",{required:true})
const old_age = defineModel<Number>("old_age",{required:true})
const old_phone = defineModel<string>("old_phone",{required:true})
const old_address = defineModel<string>("old_address",{required:true})
const old_Hospital = defineModel<string>("old_Hospital",{required:true})
const old_department = defineModel<string>("old_department",{required:true})
const old_title = defineModel<string>("old_title", {required:true})
const old_specialty = defineModel<string>("old_specialty",{required:true})
const old_gender = defineModel<Gender>("old_gender",{required:true})

const new_userType = defineModel<UserType>("new_userType",{ required: true})
const new_psw = defineModel<string>("new_psw",{required:true})
const new_identity = defineModel<string>("new_identity",{required:true})
const new_name = defineModel<string>("new_name",{required:true})
const new_age = defineModel<Number>("new_age",{required:true})
const new_phone = defineModel<string>("new_phone",{required:true})
const new_address = defineModel<string>("new_address",{required:true})
const new_Hospital = defineModel<string>("new_Hospital",{required:true})
const new_department = defineModel<string>("new_department",{required:true})
const new_title = defineModel<string>("new_title", {required:true})
const new_specialty = defineModel<string>("new_specialty",{required:true})
const new_gender = defineModel<Gender>("new_gender",{required:true})

const date = defineModel<string>("date", {required:true})

</script>
<template>
  <main class="main-view">
  <n-flex vertical>
    {{date}}
    <p>old</p>
    <user-info-view
      v-model:user-type="old_userType"
      v-model:address="old_address"
      v-model:age="old_age"
      v-model:gender="old_gender"
      v-model:name="old_name"
      v-model:identity="old_identity"
      v-model:phone="old_phone"
      v-model:psw="old_psw"
      v-model:department="old_department"
      v-model:specialty="old_specialty"
      v-model:title="old_title"
      v-model:-hospital="old_Hospital"
    >

    </user-info-view>
    <p>new</p>
    <user-info-view
      v-model:user-type="new_userType"
      v-model:address="new_address"
      v-model:age="new_age"
      v-model:gender="new_gender"
      v-model:name="new_name"
      v-model:identity="new_identity"
      v-model:phone="new_phone"
      v-model:psw="new_psw"
      v-model:department="new_department"
      v-model:specialty="new_specialty"
      v-model:title="new_title"
      v-model:-hospital="new_Hospital"
    >
    </user-info-view>
    <n-flex>
      <n-button>
        同意
      </n-button>
      <n-button>
        驳回
      </n-button>
    </n-flex>
  </n-flex>
  </main>
</template>
<style lang="scss" scoped>
.main-view{
  border: 1px solid #1f79a9;
  border-radius: 5px;
  margin-bottom: 5px;
}
</style>