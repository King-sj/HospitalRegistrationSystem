<script setup lang="ts">
import { RouterView } from 'vue-router'
import { useRouter } from 'vue-router';
import { useUserStore } from './components/LoginSystem';

const router = useRouter()
router.push({ name: "home" })
const user = useUserStore().userStorage
</script>

<template>
  <main>
    <n-layout embedded has-sider style="height:100%;width: 100vw;"
      position="absolute"
    >
      <n-layout-sider collapse-mode="transform" :native-scrollbar="true" show-trigger="bar"
        content-style="padding: 0px;" :inverted="true" :show-collapsed-content="false" :default-collapsed="true"
        :collapsed-width="1"
        :width="400"
        bordered>
        <n-flex vertical style="height:100%">
          <n-button quaternary size="small" type="info" style="font-size: 3.5rem;margin: 2rem;"
            @click="router.push({ name: 'home' })">
            Home
          </n-button>
          <n-divider />

          <n-button
            v-if="user.type=='Administrator'"
            quaternary size="small" type="info" style="font-size: 3.5rem;margin: 2rem;"
            @click="router.push({ name: 'userInfoChangeCheck' })">
            信息变更审核
          </n-button>
          <n-divider v-if="user.type=='Administrator'"/>

          <n-button quaternary size="small" type="info" style="font-size: 3.5rem;margin: 2rem;"
            @click="router.push({ name: 'postAttendanceInformation' })"
            v-if="user.type=='Doctor'"
          >
            出诊信息
          </n-button>
          <n-divider v-if="user.type=='Doctor'"/>
          <n-button quaternary size="small" type="info" style="font-size: 3.5rem;margin: 2rem;"
            @click="router.push({ name: 'postAttendanceInformationCheck' })"
            v-if="user.type=='Administrator'"
          >
            出诊信息审核
          </n-button>
          <n-divider v-if="user.type=='Administrator'"/>

          <n-button quaternary size="small" type="info" style="font-size: 3.5rem;margin: 2rem;"
            @click="router.push({ name: 'bookingDoctor' })"
            v-if="user.type=='Patient'"
          >
            预约挂号
          </n-button>
          <n-divider v-if="user.type=='Patient'"/>

          <n-button quaternary size="small" type="info" style="font-size: 3.5rem;margin: 2rem;"
            @click="router.push({ name: 'itinerary' })"
            v-if="user.type=='Patient'"
          >
            行程
          </n-button>
          <n-divider v-if="user.type=='Patient'"/>


          <n-button quaternary size="small" type="info" style="font-size: 3.5rem;margin: 2rem;"
          @click="router.push({name:'setting'})"
        >
          Settings
        </n-button>
        <n-divider />
        </n-flex>
      </n-layout-sider>

      <n-layout-content content-style="padding: 24px;">
          <router-view />
      </n-layout-content>
    </n-layout>
  </main>
</template>

<style lang="scss" scoped>
.n-button{
  margin: 0;
  padding: 0;
}
*{
  background-color: var(--color-background);
  color: var(--color-text);
}
</style>
