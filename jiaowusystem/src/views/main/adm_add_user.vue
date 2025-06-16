<!-- 添加用户 -->
<template>
  <div class="add_user_info">
      <el-container>  
        <el-header> 
          <img src="@/assets/2.jpg" alt="Icon" class="badge"> 
          <h3 style="margin-right: auto;">某某学校教务员端——添加用户页面</h3>    
          <div class="user-info">  
            <h3>账号：{{ account }}</h3>  
            <h3>姓名: {{ name }}</h3>  
          </div>  
          <el-button type="danger" style="float: right;" @click="goToHome">返回</el-button>  
        </el-header>  
        <el-main>  
          <div class="role-selector">
            <el-radio-group v-model="user_role">
              <el-radio size="large" border label="stu">学生</el-radio>
              <el-radio size="large" border label="tch">教师</el-radio>
              <el-radio size="large" border label="adm">教务员</el-radio>
            </el-radio-group>
          </div>
          <!--添加学生-->
          <div class="card-container" v-if="user_role === 'stu'">
            <div class="avatar-container">
              <img :src="adding_stu_info.user_picture" alt="头像" class="avatar"> 
              <div class="avatar-label">学生头像</div>
              <el-upload
              class="upload-avatar"
              :before-upload="handleAvatarUpload"
              :show-file-list="false"
              >
              <template v-slot:trigger>
                <el-button size="large" type="primary">点击上传头像</el-button>
              </template>
            </el-upload>
            </div>
            <el-card style="max-width: 800px" class="adding_personal_box-card">  
              <template #header>  
                <div class="card-header">  
                  <span>学生个人信息</span>  
                </div>  
              </template>  
              <el-form ref="adding_stu_info" :model="adding_stu_info" :rules="stu_rules" status-icon>  
                <el-form-item label="姓名:" label-width="120px" prop="user_name">  
                  <el-input v-model="adding_stu_info.user_name" type="text" size="large" placeholder="请输入姓名" />  
                </el-form-item>  
                <el-form-item label="性别:" label-width="120px" prop="user_sex">  
                  <el-radio-group v-model="adding_stu_info.user_sex">
                    <el-radio size="large" label="男">男</el-radio>
                    <el-radio size="large" label="女">女</el-radio>
                  </el-radio-group> 
                </el-form-item>  
                <el-form-item label="学院:" label-width="120px" prop="user_sch">  
                  <el-select v-model="adding_stu_info.user_sch" placeholder="请选择学院" size="large" style="width: 240px">  
                    <el-option v-for="item in sch_options" :key="item.value" :label="item.label" :value="item.value" />  
                  </el-select>  
                </el-form-item>  
                <el-form-item label="年级:" label-width="120px" prop="user_grade">  
                  <el-select v-model="adding_stu_info.user_grade" placeholder="请选择年级" size="large" style="width: 240px">  
                    <el-option v-for="item in grade_options" :key="item.value" :label="item.label" :value="item.value" />  
                  </el-select>  
                </el-form-item>  
                <el-form-item label="电话:" label-width="120px" prop="user_tel">  
                  <el-input v-model="adding_stu_info.user_tel" type="text" size="large" placeholder="请输入电话" />  
                </el-form-item>  
                <el-form-item label="账号:" label-width="120px" prop="user_account">  
                  <el-input v-model="adding_stu_info.user_account" type="text" size="large" placeholder="请输入账号" />  
                </el-form-item>
                <el-form-item label="是否启用:" label-width="120px" prop="user_status">  
                  <el-radio-group v-model="adding_stu_info.user_status">
                    <el-radio size="large" label="启用">启用</el-radio>
                    <el-radio size="large" label="停用">停用</el-radio>
                  </el-radio-group> 
                </el-form-item>  
                <el-form-item label="新密码:" label-width="120px" prop="new_password">  
                  <el-input v-model="adding_stu_info.new_password" type="password" size="large" placeholder="请输入密码" />  
                </el-form-item>  
                <el-form-item label="确认密码:" label-width="120px" prop="confirm_password">  
                  <el-input v-model="adding_stu_info.confirm_password" type="password" size="large" placeholder="请确认密码" />  
                </el-form-item>  
              </el-form>  
              <template #footer>  
                <div class="button-container">  
                  <el-button type="primary" @click="add_stu" style="margin-right: 100px;">添加该学生</el-button>  
                  <el-button @click="stu_resetForm('adding_stu_info')">撤销</el-button>  
                </div>  
              </template>  
            </el-card> 
          </div>
          <!--添加教师-->
          <div class="card-container" v-if="user_role === 'tch'">
            <div class="avatar-container">
              <img :src="adding_tch_info.user_picture" alt="头像" class="avatar"> 
              <div class="avatar-label">教师头像</div>
              <el-upload
              class="upload-avatar"
              :before-upload="handleAvatarUpload"
              :show-file-list="false"
              >
              <template v-slot:trigger>
                <el-button size="large" type="primary">点击上传头像</el-button>
              </template>
            </el-upload>
            </div>
            <el-card style="max-width: 800px" class="adding_personal_box-card">  
              <template #header>  
                <div class="card-header">  
                  <span>教师个人信息</span>  
                </div>  
              </template>  
              <el-form ref="adding_tch_info" :model="adding_tch_info" :rules="tch_rules" status-icon>  
                <el-form-item label="姓名:" label-width="120px" prop="user_name">  
                  <el-input v-model="adding_tch_info.user_name" type="text" size="large" placeholder="请输入姓名" />  
                </el-form-item>    
                <el-form-item label="学院:" label-width="120px" prop="user_sch">  
                  <el-select v-model="adding_tch_info.user_sch" placeholder="请选择学院" size="large" style="width: 240px">  
                    <el-option v-for="item in sch_options" :key="item.value" :label="item.label" :value="item.value" />  
                  </el-select>  
                </el-form-item>   
                <el-form-item label="电话:" label-width="120px" prop="user_tel">  
                  <el-input v-model="adding_tch_info.user_tel" type="text" size="large" placeholder="请输入电话" />  
                </el-form-item>  
                <el-form-item label="账号:" label-width="120px" prop="user_account">  
                  <el-input v-model="adding_tch_info.user_account" type="text" size="large" placeholder="请输入账号" />  
                </el-form-item>
                <el-form-item label="是否启用:" label-width="120px" prop="user_status">  
                  <el-radio-group v-model="adding_tch_info.user_status">
                    <el-radio size="large" label="启用">启用</el-radio>
                    <el-radio size="large" label="停用">停用</el-radio>
                  </el-radio-group> 
                </el-form-item>  
                <el-form-item label="新密码:" label-width="120px" prop="new_password">  
                  <el-input v-model="adding_tch_info.new_password" type="password" size="large" placeholder="请输入密码" />  
                </el-form-item>  
                <el-form-item label="确认密码:" label-width="120px" prop="confirm_password">  
                  <el-input v-model="adding_tch_info.confirm_password" type="password" size="large" placeholder="请确认密码" />  
                </el-form-item>  
              </el-form>  
              <template #footer>  
                <div class="button-container">  
                  <el-button type="primary" @click="add_tch" style="margin-right: 100px;">添加该教师</el-button>  
                  <el-button @click="tch_resetForm('adding_tch_info')">撤销</el-button>  
                </div>  
              </template>  
            </el-card> 
          </div>
          <!--添加教务员-->
          <div class="card-container" v-if="user_role === 'adm'" style="margin-left: 225px;">
            <el-card style="max-width: 800px" class="adding_personal_box-card">  
              <template #header>  
                <div class="card-header">  
                  <span>教务员个人信息</span>  
                </div>  
              </template>  
              <el-form ref="adding_adm_info" :model="adding_adm_info" :rules="adm_rules" status-icon>  
                <el-form-item label="姓名:" label-width="120px" prop="user_name">  
                  <el-input v-model="adding_adm_info.user_name" type="text" size="large" placeholder="请输入姓名" />  
                </el-form-item>    
                <el-form-item label="等级:" label-width="120px" prop="user_rank">  
                  <el-radio-group v-model="adding_adm_info.user_rank">
                    <el-radio size="large" label="校级">校级</el-radio>
                    <el-radio size="large" label="院级">院级</el-radio>
                  </el-radio-group>   
                </el-form-item>   
                <el-form-item label="电话:" label-width="120px" prop="user_tel">  
                  <el-input v-model="adding_adm_info.user_tel" type="text" size="large" placeholder="请输入电话" />  
                </el-form-item>  
                <el-form-item label="账号:" label-width="120px" prop="user_account">  
                  <el-input v-model="adding_adm_info.user_account" type="text" size="large" placeholder="请输入账号" />  
                </el-form-item>
                <el-form-item label="是否启用:" label-width="120px" prop="user_status">  
                  <el-radio-group v-model="adding_adm_info.user_status">
                    <el-radio size="large" label="启用">启用</el-radio>
                    <el-radio size="large" label="停用">停用</el-radio>
                  </el-radio-group> 
                </el-form-item>  
                <el-form-item label="新密码:" label-width="120px" prop="new_password">  
                  <el-input v-model="adding_adm_info.new_password" type="password" size="large" placeholder="请输入密码" />  
                </el-form-item>  
                <el-form-item label="确认密码:" label-width="120px" prop="confirm_password">  
                  <el-input v-model="adding_adm_info.confirm_password" type="password" size="large" placeholder="请确认密码" />  
                </el-form-item>  
              </el-form>  
              <template #footer>  
                <div class="button-container">  
                  <el-button type="primary" @click="add_adm" style="margin-right: 100px;">添加该教务员</el-button>  
                  <el-button @click="adm_resetForm('adding_adm_info')">撤销</el-button>  
                </div>  
              </template>  
            </el-card> 
          </div>
        </el-main>  
        <router-view></router-view>
      </el-container>
  </div>
</template>
  
<script>
import axios from 'axios'; 
import { ElMessage } from 'element-plus';
export default {
  data() {
    return {
      user_role: 'stu', //用户身份
      name: '', //教务员姓名
      account: '', //教务员账号
      adding_stu_info: {  
        user_name: '',
        user_account: '', //用户账号
        user_sex: '男',
        user_sch: '',
        user_grade: null,
        user_tel: '',
        user_status: '启用',
        new_password: '',
        confirm_password: '',
        user_picture: require('@/assets/default.png'),
      },
      adding_tch_info: {  
        user_name: '',
        user_account: '', //用户账号
        user_sex: '男',
        user_sch: '',
        user_tel: '',
        user_status: '启用',
        new_password: '',
        confirm_password: '',
        user_picture: require('@/assets/default.png'),
      },
      adding_adm_info: {  
        user_name: '',
        user_account: '', //用户账号
        user_tel: '',
        user_status: '启用',
        user_rank: '院级',
        new_password: '',
        confirm_password: '',
      },
      stu_originalInfo: {},
      tch_originalInfo: {},
      adm_originalInfo: {},
      grade_options: [
        { value: 2021, label: 2021 },
        { value: 2022, label: 2022 },
        { value: 2023, label: 2023 },
        { value: 2024, label: 2024 },
      ],
      sch_options: [  
        { value: '软件工程学院', label: '软件工程学院' },  
        { value: '计算机学院', label: '计算机学院' },  
        { value: '数学学院', label: '数学学院' },  
        { value: '物理学院', label: '物理学院' },  
        { value: '大气学院', label: '大气学院' },  
        { value: '政务学院', label: '政务学院' },  
        { value: '海工学院', label: '海工学院' }  
      ],
      stu_rules: {  
        user_name: [  
          { required: true, message: "请输入姓名", trigger: 'blur' }  
        ],  
        user_account: [  
          { required: true, message: "请选择账号", trigger: 'blur' }  
        ],  
        user_sch: [  
          { required: true, message: "请选择学院", trigger: 'blur' }  
        ],  
        user_grade: [  
          { required: true, message: "请选择年级", trigger: 'blur' }  
        ],  
        user_tel: [  
          { required: true, message: "请输入电话", trigger: 'blur' }  
        ],  
        new_password: [  
          { required: true, message: "请输入新密码", trigger: 'blur' }  
        ],  
        confirm_password: [  
          { required: true, message: "请确认密码", trigger: 'blur' },  
          { validator: this.stu_validateConfirmPassword, trigger: 'blur' }  
        ]  
      },
      tch_rules: {  
        user_name: [  
          { required: true, message: "请输入姓名", trigger: 'blur' }  
        ],  
        user_account: [  
          { required: true, message: "请选择账号", trigger: 'blur' }  
        ],  
        user_sch: [  
          { required: true, message: "请选择学院", trigger: 'blur' }  
        ],  
        user_tel: [  
          { required: true, message: "请输入电话", trigger: 'blur' }  
        ],  
        new_password: [  
          { required: true, message: "请输入新密码", trigger: 'blur' }  
        ],  
        confirm_password: [  
          { required: true, message: "请确认密码", trigger: 'blur' },  
          { validator: this.tch_validateConfirmPassword, trigger: 'blur' }  
        ]  
      },
      adm_rules: {  
        user_name: [  
          { required: true, message: "请输入姓名", trigger: 'blur' }  
        ],  
        user_account: [  
          { required: true, message: "请选择账号", trigger: 'blur' }  
        ],  
        user_rank: [  
          { required: true, message: "请选择等级", trigger: 'blur' }  
        ],   
        user_tel: [  
          { required: true, message: "请输入电话", trigger: 'blur' }  
        ],  
        new_password: [  
          { required: true, message: "请输入新密码", trigger: 'blur' }  
        ],  
        confirm_password: [  
          { required: true, message: "请确认密码", trigger: 'blur' },  
          { validator: this.adm_validateConfirmPassword, trigger: 'blur' }  
        ]  
      }   
    };
  },
  created() {
    // 从查询参数中获取 id 和账号
    this.account = this.$route.query.user_account;
    this.fetchAdmInfo();
    this.stu_originalInfo = { ...this.adding_stu_info };
    this.tch_originalInfo = { ...this.adding_tch_info };
    this.adm_originalInfo = { ...this.adding_adm_info };
  },
  methods: {
    fetchAdmInfo() {
      const url = `http://localhost:8080/personalPage/adm/${this.account}`;
      axios.get(url)
        .then(response => {
          const data = response.data; 
          this.name = data.adm_name;
        })
        .catch(error => {
          console.error('获取用户信息失败:', error);
        });
    },
    stu_validateConfirmPassword(rule, value, callback) {  
      if (value !== this.adding_stu_info.new_password) {  
        callback(new Error("两次输入的密码不一致"));  
      } else {  
        callback();  
      }  
    }, 
    tch_validateConfirmPassword(rule, value, callback) {  
      if (value !== this.adding_tch_info.new_password) {  
        callback(new Error("两次输入的密码不一致"));  
      } else {  
        callback();  
      }  
    }, 
    adm_validateConfirmPassword(rule, value, callback) {  
      if (value !== this.adding_adm_info.new_password) {  
        callback(new Error("两次输入的密码不一致"));  
      } else {  
        callback();  
      }  
    }, 
    goToHome() {
      this.$router.go(-1);
    },
    stu_resetForm() {  
      this.adding_stu_info = { ...this.stu_originalInfo }; // 重置为原始信息 
    },
    tch_resetForm() {  
      this.adding_tch_info = { ...this.tch_originalInfo }; // 重置为原始信息 
    },
    adm_resetForm() {  
      this.adding_adm_info = { ...this.adm_originalInfo }; // 重置为原始信息 
    },
    add_adm() {
    // 收集数据
    const admData = {
      adm_name: this.adding_adm_info.user_name,
      adm_rank: this.adding_adm_info.user_rank === '院级' ? 1 : 2,
      adm_tel: this.adding_adm_info.user_tel,
      adm_account: this.adding_adm_info.user_account,
      adm_password: this.adding_adm_info.new_password,
      adm_status: this.adding_adm_info.user_status === '启用' ? 1 : 0,
    };

    // 发送 POST 请求到后端接口
    axios.post('http://localhost:8080/adm/adminManage/add', admData)
      .then(response => {
        // 处理成功的响应
        console.log('教务员添加成功:', response.data);
        ElMessage.success('教务员添加成功！');
        // 清空表单，或者跳转到其他页面
        this.adm_resetForm();
      })
      .catch(error => {
        // 处理错误
        console.error('添加教务员失败:', error);
        ElMessage.error('添加教务员失败，请检查您的输入后重试');
      });
    },
    add_tch() {
    // 收集数据
    const tchData = {
      tch_name: this.adding_tch_info.user_name,
      tch_sex: this.adding_tch_info.user_sex,
      tch_sch: this.adding_tch_info.user_sch,
      tch_tel: this.adding_tch_info.user_tel,
      tch_picture: this.adding_tch_info.user_picture,
      tch_account: this.adding_tch_info.user_account,
      tch_password: this.adding_tch_info.new_password,
      tch_status: this.adding_tch_info.user_status === '启用' ? 1 : 0,
    };
    console.log(tchData);
    // 发送 POST 请求到后端接口
    axios.post('http://localhost:8080/adm/teacherManage/add', tchData)
      .then(response => {
        // 处理成功的响应
        console.log('教师添加成功:', response.data);
        ElMessage.success('教师添加成功！');
        // 清空表单，或者跳转到其他页面
        this.tch_resetForm();
      })
      .catch(error => {
        // 处理错误
        console.error('添加教师失败:', error);
        ElMessage.error('添加教师失败，请检查您的输入后重试');
      });
    },
    add_stu(){
      //添加该学生
      // 收集数据
    const stuData = {
      stu_name: this.adding_stu_info.user_name,
      stu_sex: this.adding_stu_info.user_sex,
      stu_sch: this.adding_stu_info.user_sch,
      stu_tel: this.adding_stu_info.user_tel,
      stu_grade: this.adding_stu_info.user_grade,
      stu_picture: this.adding_stu_info.user_picture,
      stu_account: this.adding_stu_info.user_account,
      stu_password: this.adding_stu_info.new_password,
      stu_status: this.adding_stu_info.user_status === '启用' ? 1 : 0,
    };
    console.log(stuData);
    // 发送 POST 请求到后端接口
    axios.post('http://localhost:8080/adm/studentManage/add', stuData)
      .then(response => {
        // 处理成功的响应
        console.log('学生添加成功:', response.data);
        ElMessage.success('学生添加成功！');
        // 清空表单，或者跳转到其他页面
        this.stu_resetForm();
      })
      .catch(error => {
        // 处理错误
        console.error('添加学生失败:', error);
        ElMessage.error('添加学生失败，请检查您的输入后重试');
      });
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
      if(this.user_role === 'stu'){
        this.adding_stu_info.user_picture = avatarURL;
      }
      else if(this.user_role === 'tch'){
        this.adding_tch_info.user_picture = avatarURL;
      }
      this.$message.success('头像上传成功！');
      
      // 阻止默认上传行为
      return false;
    },
  }
}
</script>
  
<style lang='stylus' scoped>
html, body {  
  height: 100%;  
  margin: 0;  
  overflow: hidden; /* 隐藏body的滚动条 */  
}  

.add_user_info {  
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
.adding_personal_box-card {  
  margin-left: 100px;  
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
.role-selector{
  margin-top: 50px;
}
</style>