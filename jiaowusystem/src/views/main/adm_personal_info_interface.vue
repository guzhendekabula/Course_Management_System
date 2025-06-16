<!--个人信息（老师，学生，教务）-->
<template>
  <div class="personal_info">
    <div v-if="isChangePersonalInfoRoute">  
      <router-view></router-view> <!-- 仅渲染子组件 -->  
    </div>  
    <div v-if="!isChangePersonalInfoRoute">
      <el-container>  
        <el-header> 
          <img src="@/assets/2.jpg" alt="Icon" class="badge"> 
          <h3 style="margin-right: auto;">某某学校教务员端——个人信息页面</h3>    
          <div class="user-info">  
            <h3>账号：{{ user_account }}</h3>  
            <h3>姓名: {{ user_name }}</h3>  
          </div>  
          <el-button type="danger" style="float: right;" @click="goToTeacherHome">返回</el-button>  
        </el-header>  
        <el-main>  
          <div class="card-container">
            <div class="avatar-container">
              <img src="@/assets/logo.png" alt="头像" class="avatar">
              <div class="avatar-label">教务员头像</div>
            </div>
            <el-card style="max-width: 800px" class="tch_personal_box-card">
              <template #header>
                <div class="card-header">
                  <span>个人信息</span>
                </div>
              </template>
                <div class="info-item">  
                  <span>教务员id:</span>  
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
                  <span>密码:</span>  
                  <span>{{ user_password }}</span>  
                </div>  
                <div class="info-item">  
                  <span>状态:</span>  
                  <span v-if="user_status === 1">启用</span>
                  <span v-else-if="user_status === 0">停用</span>  
                </div>  
              <template #footer>
                <el-button type="primary" @click="goToChangeInfo">修改个人信息</el-button>
              </template>
            </el-card>
          </div>
        </el-main>  
        <router-view></router-view>
      </el-container>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  data() {
    return {
      user_id: '',
      user_name: '',
      user_account: '',
      user_rank: 0,  //院级1，校级2
      user_tel: '',
      user_password: '',
      user_status: 1,
      isChangePersonalInfoRoute: false
    };
  },
  watch: {  
    '$route'(to) {  
      this.checkRoute(to); // 监听路由变化  
    }  
  }, 
  created() {
    // 从查询参数中获取 id 和账号
    this.user_account = this.$route.query.user_account;
    this.fetchUserInfo();
    this.checkRoute(this.$route); // 确认当前路由
  },
  methods: {
    fetchUserInfo() {
      const url = `http://localhost:8080/personalPage/adm/${this.user_account}`;
      axios.get(url)
        .then(response => {
          const data = response.data;
          this.user_id = data.adm_id; // 更新 user_id 为 adm_id
          this.user_name = data.adm_name; // 设置 user_name 为 adm_name
          this.user_rank = data.adm_rank;
          this.user_sch = data.adm_sch;
          this.user_tel = data.adm_tel;
          this.user_password = data.adm_password;
          this.user_status = data.adm_status;
        })
        .catch(error => {
          console.error('获取用户信息失败:', error);
        });
    },
    checkRoute(route) {  
      // 检查当前路由是否是需要单独显示的页面  
      const changepersonalInfoRoutes = [  
        'adm_change_personal_info' 
      ];  
      this.isChangePersonalInfoRoute = changepersonalInfoRoutes.includes(route.name);  
    },  
    goToTeacherHome() {
      this.$router.go(-1);
    },
    goToChangeInfo() {
      this.$router.push({
        name: 'adm_change_personal_info',
        query: {
          user_id: this.user_id,
          user_account: this.user_account
        }
      });
    }
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
