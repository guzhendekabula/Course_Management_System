<!--用户管理（教务）-->
<template>
  <div class="user_manage">
    <div v-if="isUserInfoRoute">
      <router-view></router-view>
    </div>
    <div v-if="!isUserInfoRoute" class="common-layout">
    <el-container>
      <el-header> 
        <img src="@/assets/2.jpg" alt="Icon" class="badge"> 
        <h3 style="margin-right: auto;">某某学校教务员端——用户管理页面</h3>    
        <div class="user-info">  
          <h3>账号：{{ user_account }}</h3>  
          <h3>姓名: {{ user_name }}</h3>  
        </div>  
        <el-button type="danger" style="float: right;" @click="goToAdmHome">返回</el-button>  
      </el-header>
      <el-main>
        <div class="role-selector">
          <el-radio-group v-model="selectedRole">
            <el-radio size="large" label="stu">学生</el-radio>
            <el-radio size="large" label="tch">教师</el-radio>
            <el-radio size="large" label="adm">教务员</el-radio>
          </el-radio-group>
        </div>
        <div class="account_search">
          <el-input placeholder="请输入账号" v-model="searchAccount" size="large" style="width: 200px; margin-right: 10px;"></el-input>
          <el-button type="primary" size="large" @click="searchByAccount">账户查询</el-button>
          <el-button type="success" size="large" @click="clearTable">清空表格</el-button>
          <el-button size="large" @click="clearForm()">重置搜索条件</el-button>
        </div>
        <!--学生-->
        <div class="condition_search" v-if="selectedRole === 'stu'">
          <el-select v-model="selectedGrade" placeholder="请选择年级" size="large" style="width: 200px; margin-right: 10px;">
            <el-option v-for="item in grade_options" :key="item.value" :label="item.label" :value="item.value" /> 
          </el-select>
          <el-select v-model="selectedStatus" placeholder="请选择状态" size="large" style="width: 200px; margin-right: 10px;">
            <el-option label="启用" value="启用"></el-option>
            <el-option label="停用" value="停用"></el-option>
          </el-select>
          <el-input placeholder="请输入名字" v-model="searchName" size="large" style="width: 200px; margin-right: 10px;"></el-input>
          <el-button type="primary" size="large" @click="searchByCondition">查询学生</el-button>
        </div>
        <!--教师-->
        <div class="condition_search" v-if="selectedRole === 'tch'">
          <el-select v-model="selectedCollege" placeholder="请选择学院" size="large" style="width: 200px; margin-right: 10px;">
            <el-option v-for="item in college_options" :key="item.value" :label="item.label" :value="item.value" /> 
          </el-select>
          <el-select v-model="selectedStatus" placeholder="请选择状态" size="large" style="width: 200px; margin-right: 10px;">
            <el-option label="启用" value="启用"></el-option>
            <el-option label="停用" value="停用"></el-option>
          </el-select>
          <el-input placeholder="请输入名字" v-model="searchName" size="large" style="width: 200px; margin-right: 10px;"></el-input>
          <el-button type="primary" size="large" @click="searchByCondition">查询教师</el-button>
        </div>
        <!--教务员-->
        <div class="condition_search" v-if="selectedRole === 'adm'">
          <el-select v-model="selectedLevel" placeholder="请选择教务员等级" size="large" style="width: 200px; margin-right: 10px;">
            <el-option label="校级" value="校级"></el-option>
            <el-option label="院级" value="院级"></el-option>
          </el-select>
          <el-select v-model="selectedStatus" placeholder="请选择状态" size="large" style="width: 200px; margin-right: 10px;">
            <el-option label="启用" value="启用"></el-option>
            <el-option label="停用" value="停用"></el-option>
          </el-select>
          <el-input placeholder="请输入名字" v-model="searchName" size="large" style="width: 200px; margin-right: 10px;"></el-input>
          <el-button type="primary" size="large" @click="searchByCondition">查询教务员</el-button>
        </div>
        <div class="table_container">
            <el-table :data="tableData" height="350" style="width:1000px" border size="large" @row-click="handleRowClick" :row-class-name="tableRowClassName" :highlight-current-row="true">
              <el-table-column prop="role" label="身份" width="250px"></el-table-column>
              <el-table-column prop="account" label="账号" width="250px"></el-table-column>
              <el-table-column prop="name" label="姓名" width="250px"></el-table-column>
              <el-table-column prop="status" label="状态" width="250px"></el-table-column>
            </el-table>
            <div class="button_container">
              <el-button type="info" size="large" :disabled="!selectedRow" style="width: 150px; height: 50px; font-size: 18px;" @click="goToViewUser">查看用户</el-button>
              <el-button type="success" size="large" :disabled="!selectedRow" style="width: 150px; height: 50px; font-size: 18px;" @click="goToEditUser">编辑用户</el-button>
              <el-button type="primary" size="large" style="width: 150px; height: 50px; font-size: 18px;" @click="goToAddUser">添加用户</el-button>
              <el-button size="large" style="width: 150px; height: 50px; font-size: 18px;" @click="clearSelection">取消</el-button>
            </div>
          </div>
      </el-main>
      <router-view></router-view>
    </el-container>
  </div>
  </div>
</template>

<script>
import axios from 'axios';
import { ElMessage } from 'element-plus';
export default {
  data() {
    return {
      isUserInfoRoute: false,  // 用于控制显示内容
      user_id: '', // 用户 ID，用于传递到后端
      user_account: '', // 用户账号，显示在页面上
      user_name: '',
      searchAccount: '', // 用于绑定账号输入框的数据
      selectedGrade: '', // 用于绑定年级选择器的数据
      selectedStatus: '', // 用于绑定状态选择器的数据
      searchName: '', // 用于绑定名字输入框的数据
      selectedRole: 'stu', // 默认选择学生
      selectedCollege: '', // 用于绑定学院选择器的数据
      selectedLevel: '', // 用于绑定教务员等级选择器的数据
      selectedRow: null, // 当前选中的表格行
      college_options: [  
        { value: '软件工程学院', label: '软件工程学院' },  
        { value: '计算机学院', label: '计算机学院' },  
        { value: '数学学院', label: '数学学院' },  
        { value: '物理学院', label: '物理学院' },  
        { value: '大气学院', label: '大气学院' },  
        { value: '政务学院', label: '政务学院' },  
        { value: '海工学院', label: '海工学院' }  
      ], 
      grade_options: [
        { value: 2021, label: 2021},
        { value: 2022, label: 2022},
        { value: 2023, label: 2023},
        { value: 2024, label: 2024},
      ],
      tableData: [],
    };
  },
  created() {
    // 从路由参数中获取数据
    this.user_id = this.$route.query.user_id;
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
      const url = `http://localhost:8080/personalPage/adm/${this.user_account}`;
      axios.get(url)
        .then(response => {
          const data = response.data;
          this.user_id = data.adm_id; // 更新 user_id 为 adm_id
          this.user_name = data.adm_name; // 设置 user_name 为 adm_name
        })
        .catch(error => {
          console.error('获取用户信息失败:', error);
        });
    },
    checkRoute(route) {  
      // 检查当前路由是否是需要单独显示的页面  
      const UserInfoRoute = [  
        'adm_view_user',
        'adm_edit_user',
        'adm_add_user', 
      ];  
      this.isUserInfoRoute = UserInfoRoute.includes(route.name);  
    },
    searchByAccount() {
      // 在这里添加搜索账号的逻辑
      console.log('搜索账号:', this.searchAccount);
      if (this.selectedRole === 'stu') {
        // 执行学生搜索逻辑
        const url = 'http://localhost:8080/adm/studentManage/accountSearch';
        const payload = {
          stuAccount: this.searchAccount
        };
        axios.post(url, payload)
        .then(response => {
          const data = response.data;
          // 更新表格数据
          this.tableData = [
            { role: '学生', account: data.stu_account, name: data.stu_name, status: data.stu_status === 1 ? '启用' : '停用' }
          ];
        })
        .catch(error => {
          console.error('搜索学生失败:', error);
          ElMessage.error('没有该学生');
          this.tableData = [];
        });
      } else if (this.selectedRole === 'tch') {
        // 执行教师搜索逻辑
        const url = 'http://localhost:8080/adm/teacherManage/accountSearch';
        const payload = {
          tchAccount: this.searchAccount
        };
        axios.post(url, payload)
        .then(response => {
          const data = response.data;
          // 更新表格数据
          this.tableData = [
            { role: '教师', account: data.tch_account, name: data.tch_name, status: data.tch_status === 1 ? '启用' : '停用' }
          ];
        })
        .catch(error => {
          console.error('搜索教师失败:', error);
          ElMessage.error('没有该教师');
          this.tableData = [];
        });
      } else if (this.selectedRole === 'adm') {
        // 执行教务员搜索逻辑
        const url = 'http://localhost:8080/adm/adminManage/accountSearch';
        const payload = {
          admAccount: this.searchAccount
        };
        axios.post(url, payload)
        .then(response => {
          const data = response.data;
          // 更新表格数据
          this.tableData = [
            { role: '学生', account: data.adm_account, name: data.adm_name, status: data.adm_status === 1 ? '启用' : '停用' }
          ];
        })
        .catch(error => {
          console.error('搜索教务员失败:', error);
          ElMessage.error('没有该教务员');
          this.tableData = [];
        });
      }
    },
    searchByCondition() {
      // 根据 selectedRole 执行不同的搜索逻辑
      if (this.selectedRole === 'stu') {
        // 执行学生搜索逻辑
        const url = 'http://localhost:8080/adm/studentManage/conditionSearch';
        let status = null;
        if(this.selectedStatus === '启用'){
          status = 1;
        } else if(this.selectedStatus === '停用'){
          status = 0;
        }
        const payload = {
          stuGrade: this.selectedGrade ? this.selectedGrade : null,
          stuStatus: status,
          stuName: this.searchName
        };
        axios.post(url, payload)
        .then(response => {
          this.tableData = response.data.map(item => ({
            role: '学生',
            account: item.stu_account,
            name: item.stu_name,
            status: item.stu_status === 1 ? '启用' : '停用'
          }));
        })
        .catch(error => {
          console.error('搜索学生失败:', error);
          ElMessage.error('搜索失败，请检查您的查询条件后重试');
        });
      } else if (this.selectedRole === 'tch') {
        // 执行教师搜索逻辑
        const url = 'http://localhost:8080/adm/teacherManage/conditionSearch';
        let status = null;
        if(this.selectedStatus === '启用'){
          status = 1;
        } else if(this.selectedStatus === '停用'){
          status = 0;
        }
        const payload = {
          tchSch: this.selectedCollege,
          tchStatus: status,
          tchName: this.searchName
        };
        axios.post(url, payload)
        .then(response => {
          this.tableData = response.data.map(item => ({
            role: '教师',
            account: item.tch_account,
            name: item.tch_name,
            status: item.tch_status === 1 ? '启用' : '停用'
          }));
        })
        .catch(error => {
          console.error('搜索教师失败:', error);
          ElMessage.error('搜索失败，请检查您的查询条件后重试');
        });
      } else if (this.selectedRole === 'adm') {
        // 执行教务员搜索逻辑
        const url = 'http://localhost:8080/adm/adminManage/conditionSearch';
        let level = null;
        if(this.selectedLevel === '院级'){
          level = 1;
        } else if(this.selectedLevel === '校级'){
          level = 2;
        }
        let status = null;
        if(this.selectedStatus === '启用'){
          status = 1;
        } else if(this.selectedStatus === '停用'){
          status = 0;
        }
        const payload = {
          admRank: level,
          admStatus: status,
          admName: this.searchName
        };
        axios.post(url, payload)
        .then(response => {
          this.tableData = response.data.map(item => ({
            role: '教务员',
            account: item.adm_account,
            name: item.adm_name,
            status: item.adm_status === 1 ? '启用' : '停用'
          }));
        })
        .catch(error => {
          console.error('搜索教务员失败:', error);
          ElMessage.error('搜索失败，请检查您的查询条件后重试');
        });
      }
    },
    // 行点击事件，选择某一行
    handleRowClick(row) {
      this.selectedRow = row; // 保存选中行
      console.log('选中的行：', row);
    },
    // 自定义行样式
    tableRowClassName(row) {
      return row === this.selectedRow ? 'highlight-row' : ''; // 高亮选中行
    },
    goToViewUser(){
      const { account, role } = this.selectedRow;
      this.$router.push({   
        name: 'adm_view_user',  
        query: {   
          user_account: this.user_account, 
          account: account,
          role: role
        }  
      });  
    },
    goToEditUser(){
      const { account, role } = this.selectedRow;
      this.$router.push({   
        name: 'adm_edit_user',  
        query: {  
          user_account: this.user_account,
          account: account,
          role: role  
        }  
      });  
    },
    goToAddUser(){
      this.$router.push({   
        name: 'adm_add_user',  
        query: {   
          user_account: this.user_account,
        }  
      });  
    },
    // 清空选中行
    clearSelection() {
      this.selectedRow = null; // 清空选中行
      console.log('取消选择，已清空选中的行');
    },
    clearTable(){
      this.tableData = [];
      this.selectedRow = null;
    },
    // 返回教师评分页面
    goToAdmHome() {
      this.$router.go(-1); // 返回上一页
    },
    clearForm() {
      // 清空搜索条件
      this.searchAccount = '';
      this.selectedGrade = '';
      this.selectedStatus = '';
      this.searchName = '';
      this.selectedRole = 'stu'; // 默认选择学生
      this.selectedCollege = '';
      this.selectedLevel = '';
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
.specific_grade {  
  height: 100%;  
  overflow-y: auto; /* 允许垂直滚动 */  
} 
.badge {  
  width: 85px;  
  height: 85px;  
  margin-left: 0;  
  margin-right: 10px;  
  border-radius: 50%;  
}  

.user-info {  
  margin-left: 20px;   
  margin-right: 50px;   
} 
.account_search{
  margin-top: 50px;
} 
.condition_search{
  margin-top: 50px;
}
.role-selector{
  margin-top: 50px;
}
.table_container {
  display: flex;
  margin-top: 50px;
  margin-left: 0px;
  text-align: center;
}

.button_container {
  position: absolute;
  margin-top: 0px;
  right: 100px; /* 距离右侧 20px */
  display: flex;
  flex-direction: column;
  align-items: flex-end; /* 按钮右对齐 */
  gap: 50px; /* 按钮之间的间隔 */
}

.el-table thead th {
  background-color: #f5f5f5; /* 更换表头背景颜色 */
}

.el-table th,  
.el-table td {  
  text-align: center; /* 让表格内容居中对齐 */  
}

/* 自定义选中行的样式 */
.highlight-row {
  background-color: #f0f9eb; /* 选中行的背景色 */
}
</style>