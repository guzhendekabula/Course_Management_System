<!--个人信息（老师，学生，教务）-->
<template>
  <div class="specific_grade">
    <el-container>
        <el-header> 
          <img src="@/assets/2.jpg" alt="Icon" class="badge"> 
          <h3 style="margin-right: auto;">某某学校教师端——我的评分页面</h3>    
          <div class="user-info">  
            <h3>账号：{{ user_account }}</h3>  
            <h3>姓名: {{ user_name }}</h3>  
          </div>  
          <el-button type="danger" style="float: right;" @click="goToTeacherGrade">返回</el-button>  
        </el-header>
        <el-main>
          <div class="lab">
           <h3>教学班号：{{ cla_id }}</h3>
           <h3>课程名：{{ cou_name }}</h3> 
           <h3>班级人数：{{ member_count }}</h3>
          </div>
          <!-- 打分表格 -->
        <el-table :data="students" style="width: 100%" border height="350px">
          <el-table-column label="学号" prop="stu_id" width="100px"></el-table-column>
          
          
          <!-- 评分项列-->
          <el-table-column label="评分项1" prop="sco_item_1" width="175px">
            <template v-slot="scope">
              <el-input-number v-model="scope.row.sco_item_1" :min="0" :max="100" label="评分项1"></el-input-number>
            </template>
          </el-table-column>

          <el-table-column label="评分项2" prop="sco_item_2" width="175px">
            <template v-slot="scope">
              <el-input-number v-model="scope.row.sco_item_2" :min="0" :max="100" label="评分项2"></el-input-number>
            </template>
          </el-table-column>

          <el-table-column label="评分项3" prop="sco_item_3" width="175px">
            <template v-slot="scope">
              <el-input-number v-model="scope.row.sco_item_3" :min="0" :max="100" label="评分项3"></el-input-number>
            </template>
          </el-table-column>

          <el-table-column label="评分项4" prop="sco_item_4" width="175px">
            <template v-slot="scope">
              <el-input-number v-model="scope.row.sco_item_4" :min="0" :max="100" label="评分项4"></el-input-number>
            </template>
          </el-table-column>

          <!-- 备注列 -->
          <el-table-column label="备注" prop="sco_note"  width="250px">
            <template v-slot="scope">
              <el-input v-model="scope.row.sco_note" style="width: 225px" :autosize="{ minRows: 2, maxRows: 4 }" type="textarea" placeholder="Please input"/>
            </template>
          </el-table-column>

          <!-- 总分列 -->
          <el-table-column label="总分" prop="sco_score_1">
            <template v-slot="scope">
              <span>{{ calculateTotalScore(scope.row) }}</span>
            </template>
          </el-table-column>
          <!-- 绩点列 -->
          <el-table-column label="绩点" prop="sco_score_2">
            <template v-slot="scope">
              <span>{{ calculateScore2(scope.row) }}</span>
            </template>
          </el-table-column>
        </el-table>
        <!--保存成绩按钮以及取消按钮-->
        <div class="foot_button">
          <el-button type="primary" size="large" style="width: 200px; height: 50px; font-size: 18px;" @click="saveGrades">保存成绩</el-button>
          <el-button size="large" style="width: 200px; height: 50px; font-size: 18px;" @click="cancelChanges">取消</el-button>
        </div>
        </el-main>
    </el-container>
    
  </div>
</template>

<script>
import { ElMessage } from 'element-plus';
import axios from 'axios';

export default {
  data() {
    return {
      user_id: "", // 用户 ID
      user_account: "", // 用户账号
      user_name: "",
      cla_id: 0, // 当前教学班 ID
      cou_name: "", // 当前课程名
      member_count: 0, // 班级人数
      originalStudents: [], // 初始学生数据备份
      students: [] // 表格中显示的学生数据
    };
  },
  created() {
    // 从路由参数中获取数据
    this.user_id = this.$route.query.user_id;
    this.user_account = this.$route.query.user_account;
    this.cla_id = this.$route.query.cla_id;
    this.cou_name = this.$route.query.cou_name;
    this.fetchUserInfo();
    // 获取学生数据
    this.fetchStudents();
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
    // 获取学生数据
    fetchStudents() {
    // 构建请求体
    const body = {
      scoClaId: this.cla_id // 使用前端代码中的 cla_id 作为请求参数
    };
    // 发送 POST 请求到新的接口 URL
    axios.post('http://localhost:8080/tch/studentGrade/conditionSearch3', body)
      .then(response => {
        const data = response.data; // 后端返回的数据

        // 调试：打印后端返回的数据
        console.log('后端返回的数据:', data);

        // 将返回的数据映射到表格的格式
        this.students = data.map(item => ({
          stu_id: item.sco_stu_id, // 学号
          sco_item_1: item.sco_item_1 !== null ? item.sco_item_1 : 0, // 评分项1，如果为null则初始化为0
          sco_item_2: item.sco_item_2 !== null ? item.sco_item_2 : 0, // 评分项2，如果为null则初始化为0
          sco_item_3: item.sco_item_3 !== null ? item.sco_item_3 : 0, // 评分项3，如果为null则初始化为0
          sco_item_4: item.sco_item_4 !== null ? item.sco_item_4 : 0, // 评分项4，如果为null则初始化为0
          sco_note: item.sco_note || '', // 备注，如果为null则初始化为空字符串
          sco_score_1: item.sco_score_1 !== null ? item.sco_score_1 : 0, // 总分，如果为null则初始化为0
          sco_score_2: item.sco_score_2 !== null ? item.sco_score_2 : 0 // 绩点，如果为null则初始化为0
        }));

        // 更新班级人数
        this.member_count = this.students.length;

        // 如果没有数据，输出提示日志
        if (this.students.length === 0) {
          console.warn('没有匹配的学生数据，请检查 cla_id 是否正确');
        }

        // 备份初始学生数据
        this.originalStudents = this.students.map(student => ({ ...student }));
      })
      .catch(error => {
        console.error('获取学生数据失败：', error);
      });
  },
    // 计算每个学生的总分
    calculateTotalScore(student) {
      return (
        ((student.sco_item_1 || 0) +
          (student.sco_item_2 || 0) +
          (student.sco_item_3 || 0) +
          (student.sco_item_4 || 0)) /
        4
      );
    },
    // 计算绩点
calculateScore2(student) {
  const score = (this.calculateTotalScore(student) / 10) - 5; // 基于总分计算绩点
  const gpa = score <= 0 ? 0 : score;
  student.sco_score_2 = gpa; // 同步更新到数据对象
  student.sco_score_1 = (gpa + 5) * 10;
  return gpa;
},
    // 保存所有学生成绩
    saveGrades() {
      // 遍历表格中的每个学生数据
      this.students.forEach(student => {
        const payload = {
          sco_stu_id: student.stu_id,
          sco_cla_id: this.cla_id,
          sco_score_1: student.sco_score_1,
          sco_item_1: student.sco_item_1,
          sco_item_2: student.sco_item_2,
          sco_item_3: student.sco_item_3,
          sco_item_4: student.sco_item_4,
          sco_note: student.sco_note,
          sco_score_2: student.sco_score_2
        };

        // 调用后端接口更新成绩
        axios.put('http://localhost:8080/tch/studentGrade/update', payload)
          .then(response => {
            if (response.data === '更新成功') {
              ElMessage.success(`学号 ${student.stu_id} 的成绩已更新`);
            } else {
              ElMessage.warning(`学号 ${student.stu_id} 的成绩更新返回非预期结果`);
            }
          })
          .catch(error => {
            console.error(`更新学号 ${student.stu_id} 的成绩失败：`, error);
            ElMessage.error(`学号 ${student.stu_id} 的成绩更新失败`);
          });
      });

      // 更新备份
      this.originalStudents = this.students.map(student => ({ ...student }));
    },
    // 取消更改（恢复初始数据）
    cancelChanges() {
      // 使用备份的数据重置表格
      this.students = this.originalStudents.map(student => ({ ...student }));
    },
    // 返回教师评分页面
    goToTeacherGrade() {
      this.$router.go(-1); // 返回上一页
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
.lab{
    display: flex;
    gap: 350px;
    justify-content: center;  /* 垂直居中对齐 */
    align-items: center;
    margin-top: 50px;
    font-size: 20px;
}
.el-table th {
  font-weight: bold;  /* 设置表头字体加粗 */
}
.foot_button{
  margin-top: 100px;
  margin-left: 200px;
  display: flex; /* 使用 Flexbox 布局 */
  align-items: center; /* 垂直居中对齐 */
}
.foot_button .el-button {
  margin-left: 200px; /* 按钮之间的间距 */
}
</style>