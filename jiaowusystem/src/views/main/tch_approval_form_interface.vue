<!--我的课程（老师）-->
<template>
  <div class="grade-content">
    <div v-if="IsChangetchCourseRoute">  
      <router-view></router-view> <!-- 仅渲染子组件 -->  
    </div> 
    <div v-if="!IsChangetchCourseRoute">
      <el-container>
        <el-header> 
          <img src="@/assets/2.jpg" alt="Icon" class="badge"> 
          <h3 style="margin-right: auto;">某某学校教师端——我的课程页面</h3>    
          <div class="user-info">  
            <h3>账号：{{ user_account }}</h3>  
            <h3>姓名: {{ user_name }}</h3>  
          </div>  
          <el-button type="danger" style="float: right;" @click="goToTeacherHome">返回</el-button>  
        </el-header>

        <el-main class="main-content">
          <div class="table_container">
            <el-table :data="tableData" height="350" style="width: 1520px" border size="large" @row-click="handleRowClick" :row-class-name="tableRowClassName" :highlight-current-row="true">
              <el-table-column prop="cou_code" label="课程识别码" width="120px"></el-table-column>
              <el-table-column prop="cou_name" label="课程名称" width="150px"></el-table-column>
              <el-table-column prop="cou_status" label="状态" width="100px"></el-table-column>
              <el-table-column prop="cou_ifexam" label="是否考试" width="100px"></el-table-column>
              <el-table-column prop="cou_ifscore" label="是否评分" width="100px"></el-table-column>
              <el-table-column prop="cou_term" label="开课学期" width="100px"></el-table-column>
              <el-table-column prop="cou_type" label="课程类型" width="100px"></el-table-column>
              <el-table-column prop="cou_language" label="课程语言" width="100px"></el-table-column>
              <el-table-column prop="cou_rank" label="课程等级" width="100px"></el-table-column>
              <el-table-column prop="cou_school" label="授课单位" width="150px"></el-table-column>
              <el-table-column prop="cou_score" label="课程学分" width="100px"></el-table-column>
              <el-table-column label="课程大纲" width="100px">
                <!--添加一个按钮，点击按钮后用对话框显示课程大纲的具体内容-->
                <template #default="scope">
                  <el-button type="text" @click="handleViewOut_cou_text(scope.row)">课程大纲</el-button>
                </template>
              </el-table-column>
              <el-table-column label="审批意见" width="100px">
                <!--添加一个按钮，点击按钮后用对话框显示课程大纲的具体内容-->
                <template #default="scope">
                  <el-button type="text" @click="handleViewOut_apf_note(scope.row)">审批意见</el-button>
                </template>
              </el-table-column>
            </el-table>
            <div class="button_container">
              <el-button type="primary" size="large"  style="width: 200px; height: 50px; font-size: 18px;" @click="goToApplyCourse">添加课程</el-button>
              <el-button type="success" size="large" :disabled="!selectedRow" style="width: 200px; height: 50px; font-size: 18px;" @click="goToEditingCourse">编辑课程</el-button>
              <el-button size="large" style="width: 200px; height: 50px; font-size: 18px;" @click="clearSelection">取消</el-button>
            </div>
          </div>
        </el-main>
        <router-view></router-view>
      </el-container>
    </div>
  </div>
  <!-- 对话框组件用于显示课程大纲 -->
  <el-dialog v-model="text_dialogVisible" title="课程大纲" width="30%" align-center center>
    <div v-html="currentCourseText"></div>
    <template #footer>
      <el-button @click="text_dialogVisible = false">关闭</el-button>
    </template>
  </el-dialog>
  <!-- 对话框组件用于显示审批意见 -->
  <el-dialog v-model="note_dialogVisible" title="审批意见" width="30%" align-center center>
    <div v-html="currentapfnote"></div>
    <template #footer>
      <el-button @click="note_dialogVisible = false">关闭</el-button>
    </template>
  </el-dialog>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      user_id: '',
      user_account: '',
      user_name: '',
      IsChangetchCourseRoute: false,
      selectedRow: null, // 用来存储选中的行
      text_dialogVisible: false, // 控制对话框的显示
      currentCourseText: '', // 当前显示的课程大纲内容
      note_dialogVisible: false,
      currentapfnote: '',
      tableData: []
    };
  },
  watch: {  
    '$route'(to) {  
      this.checkRoute(to); // 监听路由变化  
    }  
  }, 
  created() {
    // 从查询参数中获取 id 和账号
    this.user_id = this.$route.query.user_id;
    this.user_account = this.$route.query.user_account;
    this.fetchUserInfo();
    this.fetchCourses(); 
    this.checkRoute(this.$route); // 确认当前路由
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
    // 映射课程状态
    mapStatus(status) {
      switch (status) {
        case 1:
          return '审批通过';
        case 2:
          return '审批通过';
        case 3:
          return '审批通过';
        case 4:
          return '待审批';
        case 5:
          return '审批驳回';
        case 6:
          return '课程关闭';
        default:
          return '未知';
      }
    },
    // 生成审批意见
    getApprovalNote(status) {
      switch (status) {
        case 1:
          return '已通过审批';
        case 2:
          return '已通过审批';
        case 3:
        return '已通过审批';  // 审批成功
        case 4:
          return '正在审核';  // 待审批
        case 5:
          return '审批驳回';  // 驳回
        case 6:
          return '已关闭';  // 课程关闭
        default:
          return '无审批意见';
      }
    },
    // 获取课程数据
    fetchCourses() {
  const url = `http://localhost:8080/tch/approvalForm/tchIdCourseSearch`;
  
  axios.post(url, { tchId: this.user_id })
    .then(response => {
      const courses = response.data;

      // 创建一个数组，保存每个课程的审批意见请求
      const courseRequests = courses.map(course => {
        return axios.post('http://localhost:8080/tch/approvalForm/couCodeSearch', {
          apfCouCode: course.cou_code // 获取每门课程的审批意见
        }).then(noteResponse => {
          // 将审批意见存储到课程数据中
          return {
            ...course,  // 保持原有课程数据
            apf_note: noteResponse.data.apf_note || '无审批意见'  // 使用接口返回的审批意见
          };
        }).catch(error => {
          console.error(`获取课程 ${course.cou_code} 的审批意见失败:`, error);
          return {
            ...course,
            apf_note: '获取审批意见失败'  // 失败时显示错误信息
          };
        });
      });

      // 使用 Promise.all 等待所有的请求完成
      Promise.all(courseRequests).then(updatedCourses => {
        // 在所有请求完成后更新表格数据
        this.tableData = updatedCourses.map(course => ({
          cou_code: course.cou_code,
          cou_name: course.cou_name,
          cou_status: this.mapStatus(course.cou_status),
          cou_ifexam: course.cou_ifexam,
          cou_ifscore: course.cou_ifscore,
          cou_term: course.cou_term,
          cou_type: course.cou_type,
          cou_language: course.cou_language,
          cou_rank: course.cou_rank,
          cou_school: course.cou_school,
          cou_score: course.cou_score,
          cou_text: course.cou_text,
          apf_note: course.apf_note,  // 从接口返回的审批意见
        }));
      });
    })
    .catch(error => {
      console.error('获取课程信息失败:', error);
    });
    },
    
    checkRoute(route) {  
      // 检查当前路由是否是需要单独显示的页面  
      const ChangetchCourseRoute = [  
        'tch_editing_course',
        'tch_apply_course' 
      ];  
      this.IsChangetchCourseRoute = ChangetchCourseRoute.includes(route.name);  
    },
    goToTeacherHome() {
      this.$router.go(-1);
    },
    goToEditingCourse() {
      const { cou_code, cou_name } = this.selectedRow;
      this.$router.push({
        name: 'tch_editing_course',
        query: {
          user_id: this.user_id,
          user_account: this.user_account,
          cou_code: cou_code,               // 传递教学班ID
          cou_name: cou_name           // 传递课程名称
        }
      });
    },
    goToApplyCourse() {
      this.$router.push({
        name: 'tch_apply_course',
        query: {
          user_id: this.user_id,
          user_account: this.user_account,
        }
      });
    },
    // 行点击事件，选择该行
    handleRowClick(row) {
      this.selectedRow = row;  // 将选中的行存储到selectedRow
      console.log('选中的行：', row);  // 打印选中的行
    },
    // 根据选中的行设置行的类名
    tableRowClassName(row) {
      // 如果当前行是选中的行，返回自定义的高亮样式类
      return row === this.selectedRow ? 'highlight-row' : '';
    },
    // 清空选中的行
    clearSelection() {
      this.selectedRow = null;  // 清空选中的行
      console.log('取消选择，已清空选中的行');
    },
    handleViewOut_cou_text(row) {
      this.currentCourseText = row.cou_text; // 假设课程大纲存储在 cou_text 字段
      this.text_dialogVisible = true;
    },
    handleViewOut_apf_note(row) {
      this.currentapfnote = row.apf_note; 
      this.note_dialogVisible = true;
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
  flex-direction: column; /* 改为垂直排列 */
  align-items: center; /* 垂直居中对齐表格和按钮 */
  margin-top: 150px;
  margin-left: 10px;
  text-align: center;
}

.button_container {
  display: flex;
  justify-content: center; /* 水平居中对齐按钮 */
  margin-top: 50px; /* 与表格底部的间距 */
  width: 100%; /* 使按钮容器宽度与表格相同 */
  gap: 40px;
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

