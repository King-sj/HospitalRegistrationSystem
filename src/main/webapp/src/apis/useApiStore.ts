import { defineStore } from 'pinia'
import { ref, computed } from 'vue'
import axios from 'axios'
import {serverConfig} from "@/configs"
import { useUserStore } from '@/components/LoginSystem'
useUserStore().userStorage.token
export const useApiStore = defineStore('apiStore', () => {
  const server = axios.create({
    baseURL: serverConfig.SERVER + ':' + serverConfig.PORT + '/api/',
    timeout: 10000,
    headers: { 'X-Custom-Header': 'foobar' }
  })
  const user = useUserStore().userStorage;
  async function getUserInfo() {
    const res = await server.post('/user/info', {
      token: user.token
    })
    return res.data
  }
  return {
    getUserInfo,
  }
})
