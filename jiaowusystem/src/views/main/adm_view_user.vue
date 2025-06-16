<!-- 查看用户信息 -->
<template>
  <div class="stu_personal_info">
      <el-container>  
        <el-header> 
          <img src="@/assets/2.jpg" alt="Icon" class="badge"> 
          <h3 style="margin-right: auto;">某某学校教务员端——{{ user_name }}个人信息页面</h3>    
          <div class="user-info">  
            <h3>账号：{{ account }}</h3>  
            <h3>姓名: {{ name }}</h3>  
          </div>  
          <el-button type="danger" style="float: right;" @click="goToHome">返回</el-button>  
        </el-header>  
        <el-main>  
          <!--学生个人信息-->
          <div class="card-container" v-if="user_role === '学生'">
            <div class="avatar-container">
              <img :src="user_picture" alt="头像" class="avatar">
              <div class="avatar-label">学生头像</div>
            </div>
            <el-card style="max-width: 800px" class="tch_personal_box-card">
              <template #header>
                <div class="card-header">
                  <span>学生个人信息</span>
                </div>
              </template>
                <div class="info-item">  
                  <span>学生id:</span>  
                  <span>{{ user_id }}</span>  
                </div>  
                <div class="info-item">  
                  <span>姓名:</span>  
                  <span>{{ user_name }}</span>  
                </div>  
                <div class="info-item">  
                  <span>性别:</span>  
                  <span>{{ user_sex }}</span>  
                </div>  
                <div class="info-item">  
                  <span>学院:</span>  
                  <span>{{ user_sch }}</span>  
                </div> 
                <div class="info-item">  
                  <span>年级:</span>  
                  <span>{{ user_grade }}级</span>  
                </div> 
                <div class="info-item">  
                  <span>电话:</span>  
                  <span>{{ user_tel }}</span>  
                </div>  
                <div class="info-item">  
                  <span>账号:</span>  
                  <span>{{ user_account }}</span>  
                </div>    
                <div class="info-item">  
                  <span>状态:</span>  
                  <span v-if="user_status === 1">启用</span>
                  <span v-else-if="user_status === 0">停用</span>  
                </div>  
            </el-card>
          </div>
          <!--教师个人信息-->
          <div class="card-container" v-if="user_role === '教师'">
            <div class="avatar-container">
              <img :src="user_picture" alt="头像" class="avatar">
              <div class="avatar-label">教师头像</div>
            </div>
            <el-card style="max-width: 800px" class="tch_personal_box-card">
              <template #header>
                <div class="card-header">
                  <span>教师个人信息</span>
                </div>
              </template>
                <div class="info-item">  
                  <span>教师id:</span>  
                  <span>{{ user_id }}</span>  
                </div>  
                <div class="info-item">  
                  <span>姓名:</span>  
                  <span>{{ user_name }}</span>  
                </div>  
                <div class="info-item">  
                  <span>性别:</span>  
                  <span>{{ user_sex }}</span>  
                </div>  
                <div class="info-item">  
                  <span>学院:</span>  
                  <span>{{ user_sch }}</span>  
                </div> 
                <div class="info-item">  
                  <span>电话:</span>  
                  <span>{{ user_tel }}</span>  
                </div>  
                <div class="info-item">  
                  <span>账号:</span>  
                  <span>{{ user_account }}</span>  
                </div>    
                <div class="info-item">  
                  <span>状态:</span>  
                  <span v-if="user_status === 1">启用</span>
                  <span v-else-if="user_status === 0">停用</span>  
                </div>  
            </el-card>
          </div>
          <!--教务员个人信息-->
          <div class="card-container" v-if="user_role === '教务员'">
            <div class="avatar-container">
              <img :src="user_picture" alt="头像" class="avatar">
              <div class="avatar-label">教务员头像</div>
            </div>
            <el-card style="max-width: 800px" class="tch_personal_box-card">
              <template #header>
                <div class="card-header">
                  <span>教务员个人信息</span>
                </div>
              </template>
                <div class="info-item">  
                  <span>教师id:</span>  
                  <span>{{ user_id }}</span>  
                </div>  
                <div class="info-item">  
                  <span>姓名:</span>  
                  <span>{{ user_name }}</span>  
                </div>  
                <div class="info-item">  
                  <span>等级:</span>  
                  <span v-if="user_rank === 1">院级</span>
                  <span v-else-if="user_rank === 2">校级</span>  
                </div>  
                <div class="info-item">  
                  <span>电话:</span>  
                  <span>{{ user_tel }}</span>  
                </div>  
                <div class="info-item">  
                  <span>账号:</span>  
                  <span>{{ user_account }}</span>  
                </div>    
                <div class="info-item">  
                  <span>状态:</span>  
                  <span v-if="user_status === 1">启用</span>
                  <span v-else-if="user_status === 0">停用</span>  
                </div>  
            </el-card>
          </div>
        </el-main>  
        <router-view></router-view>
      </el-container>
  </div>
</template>

<script>
import axios from 'axios'; 
export default {
  data() {
    return {
      user_role: '', //用户身份
      user_id: '',
      user_name: '',
      name: '', //教务员姓名
      account: '', //教务员账号
      user_account: '', //用户账号
      user_sex: '',
      user_sch: '',
      user_grade: 0,
      user_tel: '',
      user_status: 1,
      user_rank: '',
      user_picture: require('@/assets/logo.png'),
    };
  },
  created() {
    // 从查询参数中获取 id 和账号
    this.user_account = this.$route.query.account;
    this.account = this.$route.query.user_account;
    this.user_role = this.$route.query.role;
    this.fetchAdmInfo();
    this.fetchUserInfo();
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
    fetchUserInfo() {
      if(this.user_role === "学生"){
        //获取学生信息
        const url = `http://localhost:8080/personalPage/stu/${this.user_account}`;
        axios.get(url)
        .then(response => {
          const data = response.data;
          this.user_id = data.stu_id; // 更新 user_id 为 tch_id
          this.user_name = data.stu_name; // 设置 user_name 为 stu_name
          this.user_sex = data.stu_sex;
          this.user_sch = data.stu_sch;
          this.user_tel = data.stu_tel;
          this.user_grade = data.stu_grade;
          this.user_status = data.stu_status;
          this.user_picture = data.stu_picture ||require( '@/assets/logo.png');
        })
        .catch(error => {
          console.error('获取用户信息失败:', error);
        });
      } else if(this.user_role === "教师"){
        //获取教师信息
        const url = `http://localhost:8080/personalPage/tch/${this.user_account}`;
        axios.get(url)
        .then(response => {
          const data = response.data;
          this.user_id = data.tch_id; // 更新 user_id 为 tch_id
          this.user_name = data.tch_name; // 设置 user_name 为 tch_name
          this.user_sex = data.tch_sex;
          this.user_sch = data.tch_sch;
          this.user_tel = data.tch_tel;
          this.user_status = data.tch_status;
          this.user_picture = data.tch_picture ||require( '@/assets/logo.png');
        })
        .catch(error => {
          console.error('获取用户信息失败:', error);
        });
      } else if(this.user_role === "教务员"){
        //获取教务员信息
        const url = `http://localhost:8080/personalPage/adm/${this.user_account}`;
      axios.get(url)
        .then(response => {
          const data = response.data;
          this.user_id = data.adm_id; // 更新 user_id 为 adm_id
          this.user_name = data.adm_name; // 设置 user_name 为 adm_name
          this.user_rank = data.adm_rank;
          this.user_sch = data.adm_sch;
          this.user_tel = data.adm_tel;
          this.user_status = data.adm_status;
        })
        .catch(error => {
          console.error('获取用户信息失败:', error);
        });
      }
    },
    goToHome() {
      this.$router.go(-1);
    },
  }
}

</script>

<style lang='stylus' scoped>
html, body {  
  height: 100%; /* 设置html和body的高度为100% */  
  margin: 0; /* 去掉默认的margin */  
  overflow: hidden; /* 隐藏body的滚动条 */  
}  

.stu_personal_info {  
  height: 100%; /* 设置容器高度为100% */  
  overflow-y: auto; /* 允许垂直滚动 */  
}  

.el-container {  
  display: flex;  
  flex-direction: column;  
  height: 100%; /* 设置容器高度为100% */  
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

.el-main {  
  flex-grow: 1;  
  margin-top: 2.5%; /* 使内容下移，避开固定的header */  
  background-color: white;  
  color: black;  
  overflow-y: auto; /* 允许主区域垂直滚动 */  
}  

.card-header {  
  font-size: 28px;  
  font-weight: bold;  
}  

.user-info {  
  margin-left: 20px;   
  margin-right: 50px;   
}  

.tch_personal_box-card {  
  margin-left: 100px; 
  margin-top: 50px 
  width: 700px;  
}  

.info-item {  
  display: flex;  
  padding: 10px 0; /* 每行上下留出一些间距 */  
  font-size: 28px;  
}  

.info-item span:first-child {  
  font-weight: bold; /* 标签字体加粗 */  
  min-width: 150px; /* 设置最小宽度以保证对齐 */   
  margin-left: 200px;   
}  

.badge {  
  width: 85px;  
  height: 85px;  
  margin-left: 0;  
  margin-right: 10px;  
  border-radius: 50%;  
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
}  
</style>
