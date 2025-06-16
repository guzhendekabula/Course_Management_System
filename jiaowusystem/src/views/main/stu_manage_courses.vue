<template>
  <div v-if="isPersonalInfoRoute">
      <router-view></router-view>
  </div>
  <div v-if="!isPersonalInfoRoute" class="common-layout">
    <el-container>
      <el-header>
        <img src="@/assets/2.jpg" alt="Icon" class="badge">
        <h3 style="margin-right: auto;">某某学校学生端——我的课程页面</h3>
          <div class="user-info">
            <h3>账号：{{ user_account }}</h3>
            <h3>姓名: {{ user_name }}</h3>
          </div>
          <el-button type="danger" style="float: right;" @click="goBack">返回</el-button>
      </el-header>
      <el-main>
        <div class="course-selection">
          <!-- 查询表单 -->
          <el-card class="search-card">
            <h2>已选课程</h2>
            <el-form :model="searchForm" class="search-form">
              <!-- 第一行 -->
              <el-row :gutter="20">
                <el-col :span="8">
                  <el-form-item label="课程名称或教学班号">
                    <el-input v-model="searchForm.courseName" placeholder="输入课程名称或教学班号"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="课程等级">
                    <el-select v-model="searchForm.courseLevel" placeholder="选择课程等级">
                      <el-option label="公选" value="公选"></el-option>
                      <el-option label="公必" value="公必"></el-option>
                      <el-option label="专选" value="专选"></el-option>
                      <el-option label="专必" value="专必"></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="课程语言">
                    <el-select v-model="searchForm.language" placeholder="选择课程语言">
                      <el-option label="中文" value="中文"></el-option>
                      <el-option label="英文" value="英文"></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
              </el-row>

              <!-- 第二行 -->
              <el-row :gutter="20">
                <el-col :span="8">
                  <el-form-item label="星期">
                    <el-select v-model="searchForm.weekday" placeholder="选择星期">
                      <el-option label="星期一" value="星期一"></el-option>
                      <el-option label="星期二" value="星期二"></el-option>
                      <el-option label="星期三" value="星期三"></el-option>
                      <el-option label="星期四" value="星期四"></el-option>
                      <el-option label="星期五" value="星期五"></el-option>
                      <el-option label="星期六" value="星期六"></el-option>
                      <el-option label="星期日" value="星期日"></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="节次">
                    <el-select v-model="searchForm.period" placeholder="选择节次">
                      <el-option label="第一节" value="第一节"></el-option>
                      <el-option label="第二节" value="第二节"></el-option>
                      <el-option label="第三节" value="第三节"></el-option>
                      <el-option label="第四节" value="第四节"></el-option>
                      <el-option label="第五节" value="第五节"></el-option>
                      <el-option label="第六节" value="第六节"></el-option>
                      <el-option label="第七节" value="第七节"></el-option>
                      <el-option label="第八节" value="第八节"></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <!-- 空白占位符 -->
                </el-col>
              </el-row>

              <!-- 查询与重置按钮 -->
              <div class="search-buttons">
                <el-button type="primary" @click="search">查询</el-button>
                <el-button @click="resetForm">重置</el-button>
              </div>
            </el-form>
          </el-card>

          <!-- 课程列表 -->
          <el-card class="course-list-card">
            <el-row v-for="(course, index) in courses" :key="index" class="course-item">
              <el-col :span="20">
                <p><strong>课程：</strong>{{ course.name }}</p>
                <p><strong>评分：</strong>{{ course.score + "  " }} <strong>课程等级：</strong>{{ course.level + "  " }} <strong>授课老师：</strong>{{ course.teacher + "  " }} <strong>上课地点：</strong>{{ course.classroom + "  " }} </p>
                <p><strong>上课时间：</strong>{{ formatTime(course.timeset) }}</p>
                <p><strong>授课老师：</strong>{{ course.teacher }}</p>
              </el-col>
              <el-col :span="4" class="delete-button">
                <el-button type="primary" @click="deleteCourse(course)">退课</el-button>
              </el-col>
            </el-row>
          </el-card>
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<script>
// eslint-disable-next-line
import axios from 'axios'; // 导入 Axios
// eslint-disable-next-line
import { ElMessage, ElMessageBox } from 'element-plus'; // 导入 Element UI 的消息组件

export default {
  data() {
    return {
      user_id: '',
      user_account: '',
      user_name: '',
      // 查询表单的数据
      searchForm: {
        courseName: "",
        courseLevel: "",
        language: "",
        weekday: "",
        period: "",
      },
      // 模拟课程数据
      courses: [],
    };
  },
  created() {  
    this.user_id = this.$route.query.user_id;  
    this.user_account = this.$route.query.user_account;
    this.checkRoute(this.$route); // 确认当前路由 
    this.fetchUserInfo();
    this.fetchCourses();
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
          this.user_id = data.stu_id; // 更新 user_id 为 tch_id
          this.user_name = data.stu_name; // 设置 user_name 为 tch_name
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
    goBack() {
      this.$router.push({
        name: 'stu_interface',
        query: { 
          user_id: this.user_id, 
          user_account: this.user_account 
        }}); // 返回上一页
    },
    search() {
      // 查询逻辑
      console.log("查询条件：", this.searchForm);
    },
    resetForm() {
      // 重置查询表单
      this.searchForm = {
        courseName: "",
        courseLevel: "",
        language: "",
        weekday: "",
        period: "",
      };
      this.fetchCourses();
    },
    deleteCourse(course) {
      ElMessageBox.confirm(`确定要退掉课程 "${course.name}" 吗？`, '确认退课', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        axios.post('http://localhost:8080/stu/classTable/delete', {
          sco_stu_id: this.user_id, // 使用下划线命名
          sco_cla_id: course.id
        })
          .then(
            // eslint-disable-next-line
            response => {
            ElMessage.success('退课成功！');
            // 刷新课程列表或更新选课状态
            this.fetchCourses();
          })
          .catch(error => {
            console.error('退课失败:', error);
            // 提取并显示后端返回的错误信息
            if (error.response && error.response.data && error.response.data.message) {
              ElMessage.error(`退课失败: ${error.response.data.message}`);
            } else {
              ElMessage.error('退课失败，请稍后重试。');
            }
          });
      }).catch(() => {
        ElMessage.info('取消退课');
      });
    },
    async fetchCourses() {
      this.loading = true;
      this.errorMessage = '';
      try {
        const response = await axios.post('http://localhost:8080/stu/classTable/selected', {
          scoStuId: this.user_id,
          ...this.searchForm
        });
        if (response.status === 200) {
          this.courses = response.data.map(course => ({
            id: course.claId,
            name: course.couName,
            score: course.couScore,
            examMode: course.couIfexam,
            level: course.couRank,
            language: course.language,
            teacher: course.tchName,
            classroom: course.claClassroom,
            timeset: course.claTimeset,
            weekset: course.claWeekset
          }));
        } else {
          this.errorMessage = response.data.message || '获取课程失败';
          this.courses = [];
        }
      } catch (error) {
        console.error("获取课程时出错：", error);
        this.errorMessage = '服务器错误，请稍后再试';
        this.courses = [];
      } finally {
        this.loading = false;
      }
    },
    formatTime(timeset) {
    // 定义星期的映射
    const dayMap = {
      '1': '星期一',
      '2': '星期二',
      '3': '星期三',
      '4': '星期四',
      '5': '星期五',
      '6': '星期六',
      '7': '星期日'
    };

    // 定义节次的映射
    const periodMap = {
      '1': '08:00-09:00',
      '2': '09:00-10:00',
      '3': '10:00-11:00',
      '4': '11:00-12:00',
      '5': '13:00-14:00',
      '6': '14:00-15:00',
      '7': '15:00-16:00',
      '8': '16:00-17:00',
      '45': '17:00-18:00',
      '46': '18:00-19:00'
      // 根据实际情况添加更多节次映射
    };

    // 将timeset分割成数组
    const times = timeset.split(',');

    // 解析每个两位数
    const formattedTimes = times.map(time => {
      const dayDigit = time.slice(0, 1);
      const periodDigit = time.slice(1);

      const day = dayMap[dayDigit] || `第${dayDigit}天`;
      const period = periodMap[periodDigit] || `第${periodDigit}节`;

      return `${day} 第${periodDigit}节 (${period})`;
    });

    return formattedTimes.join(', ');
  },

  // 格式化星期（如果需要）
  formatWeek(weekset) {
    const weekMap = {
      '1': '星期一',
      '2': '星期二',
      '3': '星期三',
      '4': '星期四',
      '5': '星期五',
      '6': '星期六',
      '7': '星期日'
      // 如果有超过7的值，可以根据需要添加
    };
    return weekset.split(',').map(week => weekMap[week] || `第${week}周`).join(', ');
  },

    // 格式化学期
    formatTerm(term) {
      // 假设 term 的格式为 YYYYX，例如 20241 表示 2024年第1学期
      const year = Math.floor(term / 10);
      const semester = term % 10;
      const semesterMap = {
        1: '第一学期',
        2: '第二学期',
        // 根据实际情况添加
      };
      return `${year}年${semesterMap[semester] || '未知学期'}`;
    }
  },
};
</script>

<style scoped>
html, body {  
  height: 100%; /* 设置html和body的高度为100% */  
  margin: 0; /* 去掉默认的margin */  
  overflow: hidden; /* 隐藏body的滚动条 */  
} 
.common-layout {
  display: flex;
  flex-direction: column;
  height: 100vh;
}
.el-container {
    display: flex;
    flex-direction: column;
    height: 100vh;
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
  margin-top: 2.5%;
  background-color: white;
  color: black;
  height: 80vh;
  overflow-y: auto;
}
.user-info {
    margin-left: 20px;
    margin-right: 50px;
  }
.search-card {
  margin-bottom: 20px;
}

.search-buttons {
  margin-top: 20px;
}

.course-selection {
  padding: 20px;
}

.course-list-card {
  margin-top: 20px;
}

.delete-button {
  text-align: right;
}

.badge{
    width: 85px;
    height: 85px;
    margin-left: 0;
    margin-right: 10px;
    border-radius: 50%;
  }

.user-info h3 {
  margin-top: 10px;
}
</style>