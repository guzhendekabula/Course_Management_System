//导入vue中创建vue实例的方法
import { createApp } from 'vue'
//导入最大组件,以便加载其它组件内容
import App from './App.vue'
//导入自定义路由文件简写from './router'
import router from './router/index.js';
//全局导入element-plus 
// eslint-disable-next-line
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
//创建vue实例
const app=createApp(App);
app.use(router);//绑定路由对象
app.mount('#app');
