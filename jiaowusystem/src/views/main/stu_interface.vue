<template>
  <div class="stu_interface">
    <div v-if="isPersonalInfoRoute">
      <router-view></router-view>
    </div>
    <div v-if="!isPersonalInfoRoute" class="common-layout">
    <el-container>
      <el-header>
        <img src="@/assets/2.jpg" alt="Icon" class="badge">
        <h3 style="margin-right: auto;">学生主页面</h3>
        <el-button @click="goToPersonalInfo">个人信息</el-button>
          <div class="user-info">
            <h3>账号：{{ user_account }}</h3>
            <h3>姓名: {{ user_name }}</h3>
          </div>
          <el-button type="danger" style="float: right;" @click="logout">退出</el-button>
      </el-header>
      <el-main>
        <div class="icons-container">
            
            <img src="@/assets/1.png" alt="Icon" class="icon">
            <img src="@/assets/1.png" alt="Icon" class="icon">
            <img src="@/assets/1.png" alt="Icon" class="icon">
            <img src="@/assets/1.png" alt="Icon" class="icon">
          </div>
        
        <router-link class="link-item" :to="{ path: '/stu_interface/stu_select_courses', query: { user_id: user_id, user_account: user_account } }">我的选课</router-link>
        <router-link class="link-item" :to="{ path: '/stu_interface/stu_manage_courses', query: { user_id: user_id, user_account: user_account } }">我的课程</router-link>
        <router-link class="link-item" :to="{ path: '/stu_interface/stu_evaluation_interface', query: { user_id: user_id, user_account: user_account } }">我的评教</router-link>
        <router-link class="link-item" :to="{ path: '/stu_interface/stu_score_interface', query: { user_id: user_id, user_account: user_account } }">我的学业</router-link>
        <router-view></router-view>

        <stu_class_table_interface />
      </el-main>
    </el-container>
    </div>
  </div>
</template>

<script>  
import axios from 'axios'; 
import stu_class_table_interface from './stu_class_table_interface.vue';
export default {  
  components: {
  // eslint-disable-next-line
    stu_class_table_interface
  },
  data() {  
    return {  
      user_id: '',  
      user_account: '',
      user_name: '',  
      isPersonalInfoRoute: false  // 用于控制显示内容  
    };  
  },  
  created() {  
     
    this.user_account = this.$route.query.user_account;
    this.fetchUserInfo();  
    this.checkRoute(this.$route); // 确认当前路由  
  },  
  watch: {  
    '$route'(to) {  
      this.checkRoute(to); // 监听路由变化  
    }  
  },  
  methods: {  
    fetchUserInfo() {
      const url = `http://localhost:8080/personalPage/stu/${this.user_account}`;
      axios.get(url)
        .then(response => {
          const data = response.data;
          this.user_id = data.stu_id; 
          this.user_name = data.stu_name; 
        })
        .catch(error => {
          console.error('获取用户信息失败:', error);
        });
    },
    checkRoute(route) {  
      // 检查当前路由是否是需要单独显示的页面  
      const personalInfoRoutes = [  
        'stu_personal_info_interface',   
           
        'stu_select_courses',   
        'stu_manage_courses',   
        'stu_evaluation_interface',   
        'stu_score_interface',
        'stu_change_personal_info'
      ];  
      this.isPersonalInfoRoute = personalInfoRoutes.includes(route.name);  
    },  
    goToPersonalInfo() {  
      this.$router.push({   
        name: 'stu_personal_info_interface',  
        query: {  
          user_id: this.user_id,  
          user_account: this.user_account  
        }  
      });  
    },  
    logout() {  
      this.$router.push({ name: 'login_interface' });  
    }  
  }  
}  
</script>

<style lang='stylus' scoped>

.stu_interface{
  .el-container {
    display: flex;
    flex-direction: column;
    height: 100vh; // 设置容器高度为视口高度
  }
  .el-header{
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
  .el-main{
    flex-grow: 1;
    margin-top: 2.5%;
    background-color: white;
    color: black;
    height: 80vh;
    overflow-y: auto;
  }
  .user-info {
    margin-left: 20px; /* 为用户信息添加左边距 */
    margin-right: 50px; /* 为用户信息添加右边距 */
  }
  .link-item{
    margin: 0 90px;
    font-size: 18px;
  }
  .icons-container {
    display: flex;
    justify-content: center;
    margin-bottom: 20px; // 为图片容器和链接容器之间添加间距
  }
  .icon{
    width: 120px; // 图片宽度
    height: 120px; // 图片高度
    margin: 0 66px; // 图片之间的间距
  }
  .badge{
    width: 85px;
    height: 85px;
    margin-left: 0;
    margin-right: 10px;
    border-radius: 50%;
  }
}
</style>


