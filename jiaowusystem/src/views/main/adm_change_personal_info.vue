<template>  
    <div class="change_personal_info">  
      <el-container>  
        <el-header>  
          <img src="@/assets/2.jpg" alt="Icon" class="badge">  
          <h3 style="margin-right: auto;">某某学校教务员端——修改个人信息页面</h3>  
          <div class="user-info">  
            <h3>账号：{{ change_info.user_account }}</h3>  
            <h3>姓名: {{ change_info.name }}</h3>  
          </div>  
          <el-button type="danger" style="float: right;" @click="goToTeacherHome">返回</el-button>  
        </el-header>  
        <el-main>  
          <div class="card-container">
            
            <el-card style="max-width: 800px" class="tch_change_personal_box-card">  
              <template #header>  
                <div class="card-header">  
                  <span>个人信息</span>  
                </div>  
              </template>  
              <el-form ref="change_info" :model="change_info" :rules="rules" status-icon>  
                <el-form-item label="教务员ID:" label-width="120px">  
                  <span>{{ change_info.user_id }}</span> <!-- 显示教师ID -->  
                </el-form-item>  
                <el-form-item label="姓名:" label-width="120px">  
                  <span>{{ change_info.name }}</span> <!-- 显示姓名 -->  
                </el-form-item>  
                <el-form-item label="等级:" label-width="120px">  
                  <span v-if="change_info.rank === 1">院级</span>
                  <span v-else-if="change_info.rank === 2">校级</span>  
                </el-form-item>   
                <el-form-item label="电话:" label-width="120px" prop="tel">  
                  <el-input v-model="change_info.tel" type="text" size="large" />  
                </el-form-item>  
                <el-form-item label="账号:" label-width="120px">  
                  <span>{{ change_info.user_account }}</span> <!-- 显示账号 -->  
                </el-form-item>  
                <el-form-item label="原密码:" label-width="120px">  
                  <span>{{ change_info.password }}</span> <!-- 显示原密码 -->  
                </el-form-item>  
                <el-form-item label="新密码:" label-width="120px" prop="new_password">  
                  <el-input v-model="change_info.new_password" type="password" size="large" />  
                </el-form-item>  
                <el-form-item label="确认密码:" label-width="120px" prop="confirm_password">  
                  <el-input v-model="change_info.confirm_password" type="password" size="large" />  
                </el-form-item>  
              </el-form>  
              <template #footer>  
                <div class="button-container">  
                  <el-button type="primary" @click="saveInfo" style="margin-right: 100px;">保存信息</el-button>  
                  <el-button @click="resetForm('change_info')">撤销</el-button>  
                </div>  
              </template>  
            </el-card> 
          </div> 
        </el-main>  
      </el-container>  
    </div>  
  </template>  
  
  <script>  
  import axios from 'axios';
  import { ElMessage } from 'element-plus';
  export default {  
    data() {  
      return {  
        change_info: {  
          user_id: "",  
          name: "",  // 学生姓名示例  
          rank: 1,    // 院级1，校级2      
          tel: "",  
          user_account: "",  
          user_status: "",
          password: "",  
          new_password: "",  
          confirm_password: "",
          avatar: require('@/assets/logo.png'),
        }, 
        originalInfo: {}, // 用于存储原始信息的备份 
        options: [  
          { value: '软件工程学院', label: '软件工程学院' },  
          { value: '计算机学院', label: '计算机学院' },  
          { value: '数学学院', label: '数学学院' },  
          { value: '物理学院', label: '物理学院' },  
          { value: '大气学院', label: '大气学院' },  
          { value: '政务学院', label: '政务学院' },  
          { value: '海工学院', label: '海工学院' }  
        ],  
        rules: {  
          college: [  
            { required: true, message: "请选择学院", trigger: 'blur' }  
          ],  
          tel: [  
            { required: true, message: "请输入电话", trigger: 'blur' }  
          ],  
          new_password: [  
            { required: true, message: "请输入新密码", trigger: 'blur' }  
          ],  
          confirm_password: [  
            { required: true, message: "请确认密码", trigger: 'blur' },  
            { validator: this.validateConfirmPassword, trigger: 'blur' }  
          ]  
        }  
      };  
    },  
    created() {   
      this.change_info.user_account = this.$route.query.user_account;
      this.fetchUserInfo();
      this.originalInfo = { ...this.change_info }; // 创建原始信息的备份  
    },  
    methods: {
      fetchUserInfo() {
      const url = `http://localhost:8080/personalPage/adm/${this.change_info.user_account}`;
      axios.get(url)
        .then(response => {
          const data = response.data;
          this.change_info.user_id = data.adm_id; // 更新 user_id 为 adm_id
          this.change_info.name = data.adm_name; // 设置 user_name 为 adm_name
          this.change_info.rank = data.adm_rank;
          this.change_info.college = data.adm_sch;
          this.change_info.tel = data.adm_tel;
          this.change_info.user_status = data.adm_status;
          this.change_info.password = data.adm_password;
          this.change_info.new_password = data.adm_password;
          this.change_info.confirm_password = data.adm_password;
        })
        .catch(error => {
          console.error('获取用户信息失败:', error);
        });
    },  
      validateConfirmPassword(rule, value, callback) {  
        if (value !== this.change_info.new_password) {  
          callback(new Error("两次输入的密码不一致"));  
        } else {  
          callback();  
        }  
      },  
      goToTeacherHome() {  
        this.$router.go(-1);  
      },  
      saveInfo() {  
      if (this.change_info.new_password !== this.change_info.confirm_password) {
      ElMessage.error('新密码和确认密码不一致，请重新输入');
      return; // 阻止继续执行保存逻辑
    }
    const admData = {
      adm_id: this.change_info.user_id,
      adm_name: this.change_info.name,
      adm_rank: this.change_info.rank,
      adm_tel: this.change_info.tel,
      adm_account: this.change_info.user_account,
      adm_password: this.change_info.password,
      adm_status: this.change_info.user_status,
    };

    // 发送 PUT 请求到后端接口
    axios.put('http://localhost:8080/adm/adminManage/update', admData)
      .then(response => {
        // 处理成功的响应
        console.log('教务员更新成功:', response.data);
        this.originalInfo = { ...this.change_info };
        ElMessage.success('教务员更新成功！');
        // 清空表单，或者跳转到其他页面
        //this.adm_resetForm();
      })
      .catch(error => {
        // 处理错误
        console.error('更新教务员失败:', error);
        ElMessage.error('更新教务员失败，请检查您的输入后重试');
      });
    },  
    resetForm() {  
      this.change_info = { ...this.originalInfo }; // 重置为原始信息 
    },
      handleAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isPNG = file.type === 'image/png';
        const isLimitSize = file.size / 1024 / 1024 < 5; // 限制为5MB以内
  
        if (!isJPG && !isPNG) {
          this.$message.error('上传头像只能是 JPG/PNG 格式!');
          return false;
        }
        if (!isLimitSize) {
          this.$message.error('上传头像大小不能超过 5MB!');
          return false;
        }
  
        // 模拟上传成功，直接更新头像 URL
        const avatarURL = URL.createObjectURL(file);
        this.change_info.avatar = avatarURL;
        this.$message.success('头像上传成功！');
        
        // 阻止默认上传行为
        return false;
      },
    }  
  };  
  </script>  
  
  <style lang='stylus' scoped>  
  html, body {  
    height: 100%;  
    margin: 0;  
    overflow: hidden; /* 隐藏body的滚动条 */  
  }  
  
  .change_personal_info {  
    height: 100%;  
    overflow-y: auto; /* 允许垂直滚动 */  
  }  
  
  .el-container {  
    display: flex;  
    flex-direction: column;  
    height: 100%;  
  }  
  
  .el-header {  
    position: fixed;  
    top: 0;  
    left: 0;  
    right: 0;  
    background-color: var(--el-color-primary-light-3);  
    color: black;  
    height: 12%;   
    z-index: 1000;  
    display: flex;  
    align-items: center;  
    justify-content: space-between;  
    padding: 0 20px;  
  }  
  
  .user-info {  
    margin-left: 20px;  
    margin-right: 50px;  
  }  
  
  .badge {  
    width: 85px;  
    height: 85px;  
    border-radius: 50%;  
  }  
  
  .el-card {  
    margin-left: 250px;  
    margin-top: 50px;  
  }  
  
  .button-container {  
    text-align: center;  
  }  
  .card-container {  
    display: flex;  
  }  
  .avatar-container {  
    text-align: center; /* 使标签文本居中对齐 */  
    margin-left: 200px;  
    margin-top: 100px;  
  }  
  
  .avatar-label {  
    margin-top: 10px; /* 标签与头像之间的间距 */  
    font-size: 25px; /* 标签字体大小 */  
    font-weight: bold;  
  }  
  .avatar {  
    width: 250px; /* 设置头像宽度 */  
    height: 250px; /* 设置头像高度 */  
    border-radius: 50%; /* 使头像为圆形 */  
    margin-bottom: 20px; /* 调整头像与按钮的间距 */
  }  
  .tch_change_personal_box-card {  
    margin-left: 300px;  
    width: 700px;  
  }  
  .upload-avatar {
    display: inline-block;
    margin-top: 10px;
  }
  .upload-avatar .el-button {
    border-radius: 5px; /* 调整按钮的样式 */
    padding: 8px 20px; /* 调整按钮大小 */
  }
  </style>