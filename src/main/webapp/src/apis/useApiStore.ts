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

  async function getAllUserInfoChangeReq() {
    const res = await server.get('getAllUserInfoChangeReq')
    return res.data
  }
  return {
    getAllUserInfoChangeReq
  }
})
