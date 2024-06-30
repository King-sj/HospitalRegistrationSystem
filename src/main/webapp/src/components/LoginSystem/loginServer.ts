import {defineStore} from 'pinia'
import axios from 'axios';
import {User} from './User'
import {serverConfig} from "@/configs"
export const useLoginServer = defineStore("loginSystemApiServer",()=>{
  const axiosInst = axios.create({
    baseURL: serverConfig.SERVER+":"+ serverConfig.PORT + '/loginSystem/',
    timeout: 10000,
    headers: { 'X-Custom-Header': 'foobar' }
  })
  async function sendCaptcha(email:string) {
    axiosInst.post('captcha',{
      email:email
    })
  }
  async function signUp(user:User,captcha:String) {
    const res = await axiosInst.post("signup",{
      email:user.email,
      password:user.password,
      captcha:captcha
    })
    return res
  }
  async function login(user:User) {
    const res = await axiosInst.post("login",{
      email:user.email,
      password:user.password,
    })
    return res
  }
  async function getUserInfo(token:string) {
    const res = await axiosInst.post("getUser",{
      token:token
    })
    return res
  }
  return {sendCaptcha, signUp, login, getUserInfo}
})