import { defineStore } from 'pinia'
import { ref, computed } from 'vue'
import axios from 'axios'
import {serverConfig} from "@/configs"
import { useUserStore } from '@/components/LoginSystem'
import { UserInfoChange } from './UserInfoChange'
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
  async function executeUserInfoChangeReq(uic:UserInfoChange) {
    console.log("uic", UserInfoChange.getRecord(uic))
    const res =  await server.post("executeUserInfoChangeReq", UserInfoChange.getRecord(uic))
    return res.data;
  }
  return {
    getAllUserInfoChangeReq,
    executeUserInfoChangeReq
  }
})
