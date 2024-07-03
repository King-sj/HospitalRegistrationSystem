import { useUserStore } from "./components/LoginSystem";
export const setup = ()=>{
  const userStore = useUserStore()
  const user = userStore.userStorage
  setInterval(async () => {
    userStore.updateUser()
  }, 1000 * 2) // 2 s
}
