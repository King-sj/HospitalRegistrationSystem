import { defineStore } from "pinia";
import {useStorage} from "@vueuse/core"
import type {Ref} from "vue"
import {ref} from "vue"
import {User} from './User'
import { useLoginServer } from "./loginServer";
import { ElMessageBox } from "element-plus";
export const useUserStore = defineStore("user",()=>{
  const api = useLoginServer();
  const userStorage:Ref<User> = useStorage<User>("user",new User(),
    localStorage,
    {
      serializer:{
        read(raw:string){
          return raw ? JSON.parse(raw) : new User()
        },
        write(value:User) {
          return JSON.stringify(value)
        }
      }
    }
  );
  const ttl:number = 30*60*1000;  // 30 min
  const sendCaptcha = (email:string):boolean=>{
    try {
      api.sendCaptcha(email)
    } catch (err){
      ElMessageBox.alert("发送失败: \n" + err );
      return false;
    }
    return true
  }
  const signUp = async (user:User, captcha:string):Promise<boolean>=>{
    try {
      const res = await api.signUp(user,captcha)
      console.log(res)
      // TODO(SJ) do more
      if(!res.data.status) {
        ElMessageBox.alert(res.data.Msg)
      }
      return res.data.status
    } catch(err) {
      ElMessageBox.alert("SignUp error:\n" + err);
      return false
    }
  }
  const updateUser = async ()=>{
    userStorage.value = await getUserInfo()
  }
  const login = async (user:User):Promise<boolean>=>{
    try {
      var res = await api.login(user)
      console.log(res)
    } catch(err) {
      ElMessageBox.alert("login failed:\n"+err)
      return false;
    }
    if (!res.data.status) {
      ElMessageBox.alert(res.data.Msg)
      return false
    }
    userStorage.value = new User(user.email,user.password,res.data.token,new Date().getTime() + ttl);
    console.log("login success, cur user: ", userStorage.value)
    updateUser()
    return true
  }
  const logout = ()=>{
    userStorage.value = new User();
  }
  const isExpired = ():boolean=>{
    const res = new Date().getTime() > userStorage.value.expiration;
    if (res)logout()
    return res
  }
  const getUserInfo = async ():Promise<User>=>{
    if(isExpired()) {
      logout()
      return new User()
    }
    try {
      const res = await api.getUserInfo(userStorage.value.token)
      console.log("get user info : ",res.data)
      userStorage.value.name = res.data.name;
      userStorage.value.identity = res.data.identity;
      userStorage.value.age = res.data.age;
      userStorage.value.phone = res.data.phone;
      userStorage.value.address = res.data.address;
      userStorage.value.gender = res.data.gender;
      userStorage.value.type = res.data.type;
      userStorage.value.Hospital = res.data.Hospital;
      userStorage.value.department = res.data.department;
      userStorage.value.title = res.data.title;
      userStorage.value.specialty = res.data.specialty;
      userStorage.value.password = res.data.password;
      userStorage.value.email = res.data.email;
      return userStorage.value
    } catch(err) {
      ElMessageBox.alert("getUserInfo failed, please try re-login:\n"+err)
      logout()
      return new User()
    }
  }
  // 提交请求
  const updateUserInfoReq = async (user:User):Promise<boolean>=>{
      const res = await api.updateUserInfoReq(user)
      console.log("update user info : ",res.data)
      return true
  }
  return {
    sendCaptcha, signUp, login, logout, isExpired, userStorage,
    updateUserInfoReq, updateUser
  }
})