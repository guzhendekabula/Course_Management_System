<!--注册界面（全体用户）-->
<template>  
  <div class="login">  
      <el-card style="max-width: 480px" class="box-card">  
          <template #header>  
              <div class="card-header">  
                  <span>教务系统</span>  
              </div>  
          </template>  
          <el-tabs v-model="currentIndex" stretch class="demo-tabs" @tab-click="handleTabsClick">  
              <el-tab-pane label="登录" name="login">
                  <el-form ref="loginForm" :model="loginForm" :rules="rules" status-icon >
                      
                      <el-form-item label="账号:" label-width="80px" prop="user_account">
                          <el-input v-model="loginForm.user_account" type="text"/>
                      </el-form-item>
                      <el-form-item label="密码:" label-width="80px" prop="password">
                          <el-input v-model="loginForm.password" type="password"/>
                      </el-form-item>
                      <el-form-item>
                        <div class="mb-2 ml-4">
                           <el-radio-group v-model="loginForm.role">
                              <el-radio label="adm" size="large">教务员</el-radio>
                              <el-radio label="tch" size="large">教师</el-radio>
                              <el-radio label="stu" size="large">学生</el-radio>
                            </el-radio-group>
                        </div>
                      </el-form-item>
                      <el-form-item>
                          <div class="button-container">
                              <el-button type="primary" @click="submitForm('loginForm')">登录</el-button>
                              <el-button @click="clearForm('loginForm')">清空</el-button>
                          </div>
                      </el-form-item>
                  </el-form>
              </el-tab-pane>        
              
          </el-tabs>  
      </el-card>  
  </div>  
</template>

<script>

//import api from '../api';
import axios from 'axios';
import { ElMessage } from 'element-plus'; 
export default {
  data() {
      //验证规则
      var validateID = (rule, value, callback) =>{
          if(value === ''){
              callback(new Error("请输入你的id"))
          }
          else{
              callback();
          }
      }
      var validateUserAccount = (rule, value, callback) =>{
          if(value === ''){
              callback(new Error("请输入你的账号"))
          }else if(value.length<2){
              callback(new Error("长度不够"))
          }
          else{
              callback();
          }
      }
      var validatePassWord = (rule, value, callback) =>{
          if(value === ''){
              callback(new Error("请输入你的密码"))
          }
          else{
              callback();
          }
      }
      var validateConfigurePassWord = (rule, value, callback) =>{
          if(value === ''){
              callback(new Error("请输入密码"))
          }else if(value !== this.registerForm.password){
              callback(new Error("两次密码不一致"))
          }
          else{
              callback();
          }
      }
      return {
          currentIndex:"login",
          loginForm:{
              user_id:"",
              user_account:"",
              password:"",
              role:"",
          },
          registerForm:{
              user_account:"",
              password:"",
              configurePassword:"",
              email:""
          },
          activeTab:"login",
          rules:{
              user_id:[
                {
                    validator:validateID,
                    trigger: 'blur'
                }
              ],
              user_account:[
                  {
                      validator: validateUserAccount, 
                      trigger: 'blur'
                  }
              ],
              password:[
                  {
                      validator: validatePassWord, 
                      trigger: 'blur'
                  }
              ],
              configurePassword:[
                  {
                      validator: validateConfigurePassWord,
                      trigger: 'blur'
                  }
              ]
          }
      }
  },
  methods: {
    submitForm(formName) {  
        this.$refs[formName].validate((valid) => {  
            if (valid) {    
                const user_account = this.loginForm.user_account; // 获取账号  
                const password = this.loginForm.password; // 获取密码  
                let userType; // 根据角色设置用户类型  
                
                // 根据角色设置 userType  
                switch (this.loginForm.role) {  
                    case 'adm':  
                        userType = 0; // 教务员  
                        break;  
                    case 'tch':  
                        userType = 1; // 教师  
                        break;  
                    case 'stu':  
                        userType = 2; // 学生  
                        break;  
                    default:  
                        return; // 没有选择角色，直接返回  
                }  

                // 发送登录请求到后端  
                axios.post('http://localhost:8080/login', {  
                    username: user_account,  
                    password: password,  
                    usertype: userType  
                })  
                .then(response => {  
                    if (response.status === 200 && response.data === "Login successful") {  
                        console.log("登录成功:", response.data);  
                        // 根据身份跳转  
                        switch (this.loginForm.role) {  
                            case 'adm':  
                                this.$router.push({ name: 'adm_interface', query: { user_account } });  
                                break;  
                            case 'tch':  
                                this.$router.push({ name: 'tch_interface', query: { user_account } });  
                                break;  
                            case 'stu':  
                                this.$router.push({ name: 'stu_interface', query: { user_account } });  
                                break;  
                            default:  
                                break;  
                        }  
                    }  
                })  
                .catch(error => {  
                    if (error.response) {  
                        // 检查状态码  
                        if (error.response.status === 401) {  
                            console.error("登录失败:", error.response.data);  
                            // 使用 ElMessage 提示信息 
                            if(error.response.data === "Account has been disabled"){
                                ElMessage.error("用户已被停用，请联系教务！");
                            } else{
                                ElMessage.error("无效的用户名或密码，请重试！");
                            }
                              
                        } else {  
                            // 处理其他错误状态  
                            console.error("登录失败:", error.response.data);  
                            ElMessage.error("登录请求失败：" + error.response.data);  
                        }  
                    } else if (error.request) {  
                        console.error("登录失败: 没有收到响应", error.request);  
                        ElMessage.error("服务器没有响应，请稍后再试。");  
                    } else {  
                        console.error("登录失败:", error.message);  
                        ElMessage.error("发生了错误：" + error.message);  
                    }  
                });  
            } else {  
                return;  
            }  
        });  
    },  
      clearForm(formName) {
            this.$refs[formName].resetFields();
        },
      handleTabsClick(tab){
          this.activeTab=tab.props.name;
      }
  },
}
</script>

<style lang='stylus' scoped>

.login{
    width: 1200px;
    margin: 0 auto;
    .box-card{
        width: 480px;
        margin-top: 175px;
        margin-left: 350px; 
    }
    .el-button{
      margin-right: 100px;
    }
    .button-container{
      margin-left: 100px; /* 调整为你希望的距离 */
      text-align: center;
    }
    .el-radio-group{
      margin-left: 100px;
    }
}
</style>

