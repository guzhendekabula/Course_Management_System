<template>
  <div class="grade-content">
    <div v-if="IsChangetchGradeRoute">  
      <router-view></router-view> <!-- 仅渲染子组件 -->  
    </div> 
    <div v-if="!IsChangetchGradeRoute">
      <el-container>
        <el-header> 
          <img src="@/assets/2.jpg" alt="Icon" class="badge"> 
          <h3 style="margin-right: auto;">某某学校教师端——我的评分页面</h3>    
          <div class="user-info">  
            <h3>账号：{{ user_account }}</h3>  
            <h3>姓名: {{ user_name }}</h3>  
          </div>  
          <el-button type="danger" style="float: right;" @click="goToTeacherHome">返回</el-button>  
        </el-header>

        <el-main class="main-content">
          <div class="table_container">
            <el-table :data="tableData" height="350" style="width: 600px" border size="large" @row-click="handleRowClick" :row-class-name="tableRowClassName" :highlight-current-row="true">
              <el-table-column prop="cla_id" label="教学班 ID" width="250px"></el-table-column>
              <el-table-column prop="cou_name" label="课程名称" width="350px"></el-table-column>
            </el-table>
            <div class="button_container">
              <el-button type="success" size="large" :disabled="!selectedRow" style="width: 200px; height: 50px; font-size: 18px;" @click="goToSpecificGrade">进入评分</el-button>
              <el-button size="large" style="width: 200px; height: 50px; font-size: 18px;" @click="clearSelection">取消</el-button>
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

export default {
  data() {
    return {
      user_id: '', // 用户 ID，用于传递到后端
      user_account: '', // 用户账号，显示在页面上
      user_name: '',
      IsChangetchGradeRoute: false, // 判断是否在评分页面
      selectedRow: null, // 当前选中的表格行
      tableData: [] // 表格数据
    };
  },
  watch: {
    '$route'(to) {
      this.checkRoute(to); // 监听路由变化
    }
  },
  created() {
    // 从路由参数中获取用户 ID 和账号
    this.user_id = this.$route.query.user_id;
    this.user_account = this.$route.query.user_account;
    this.fetchUserInfo();
    this.checkRoute(this.$route); // 检查路由
    this.fetchTableData(); // 获取表格数据
  },
  methods: {
    fetchUserInfo() {
      const url = `http://localhost:8080/personalPage/tch/${this.user_account}`;
      axios.get(url)
        .then(response => {
          const data = response.data;
          this.user_id = data.tch_id; // 更新 user_id 为 tch_id
          this.user_name = data.tch_name; // 设置 user_name 为 tch_name
        })
        .catch(error => {
          console.error('获取用户信息失败:', error);
        });
    },
    checkRoute(route) {
      // 检查当前路由是否为子页面
      const ChangetchGradeRoute = ['tch_specific_grade'];
      this.IsChangetchGradeRoute = ChangetchGradeRoute.includes(route.name);
    },
    goToTeacherHome() {
      this.$router.go(-1); // 返回上一页
    },
    goToSpecificGrade() {
      const { cla_id, cou_name } = this.selectedRow;
      this.$router.push({
        name: 'tch_specific_grade',
        query: {
          user_id: this.user_id,
          user_account: this.user_account,
          cla_id: cla_id, // 传递教学班 ID
          cou_name: cou_name // 传递课程名称
        }
      });
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
    // 清空选中行
    clearSelection() {
      this.selectedRow = null; // 清空选中行
      console.log('取消选择，已清空选中的行');
    },
    // 获取表格数据
    fetchTableData() {
      const body = { claTchId: this.user_id }; // 使用 user_id 作为 claTchId 传递到后端
      axios.post('http://localhost:8080/tch/studentGrade/conditionSearch2', body)
        .then(response => {
          const data = response.data;

          // 去重逻辑：根据 cla_id 去重
          const uniqueData = [];
          const seenClaIds = new Set(); // 用于存储已经出现过的 cla_id
          data.forEach(item => {
            if (!seenClaIds.has(item.claID)) {
              seenClaIds.add(item.claID); // 标记 cla_id 已处理
              uniqueData.push({
                cla_id: item.claID,
                cou_name: item.couName
              });
            }
          });

          // 更新表格数据
          this.tableData = uniqueData;
        })
        .catch(error => {
          console.error('获取表格数据失败：', error);
        });
    }
  }
};

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

.grade-content {  
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

.table_container {
  display: flex;
  margin-top: 150px;
  margin-left: 150px;
  text-align: center;
}

.button_container {
  position: absolute;
  margin-top: 35px;
  right: 200px; /* 距离右侧 20px */
  display: flex;
  flex-direction: column;
  align-items: flex-end; /* 按钮右对齐 */
  gap: 200px; /* 按钮之间的间隔 */
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
