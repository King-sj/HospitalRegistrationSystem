import './assets/main.css'

import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'

import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

import './styles/index.scss'
import {toggleTheme} from "@/styles/setting"
toggleTheme(null)

import "./permission"

import naive from 'naive-ui'


const app = createApp(App)

app.use(createPinia())
app.use(router)
app.use(ElementPlus)
app.use(naive)

app.mount('#app')
