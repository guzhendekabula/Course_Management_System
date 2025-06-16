<!--编辑课程（教师）-->
<template>
    <div class="apply_course">
      <el-container>
        <el-header> 
          <img src="@/assets/2.jpg" alt="Icon" class="badge"> 
          <h3 style="margin-right: auto;">某某学校教师端——申请开课页面</h3>    
          <div class="user-info">  
            <h3>账号：{{ user_account }}</h3>  
            <h3>姓名: {{ user_name }}</h3>  
          </div>  
          <el-button type="danger" style="float: right;" @click="goToTeacherApproval">返回</el-button>  
        </el-header>
        <el-main>
          <el-card style="max-width: 800px" class="tch_apply_course_box-card">  
            <template #header>  
              <div class="card-header">  
                <span>课程信息</span>  
              </div>  
            </template>  
            <el-form ref="applying_course" :model="applying_course" :rules="rules" status-icon>
              <el-form-item label="课程识别码" prop="cou_code">
                <el-input v-model="applying_course.cou_code" placeholder="请输入课程识别码"></el-input>
              </el-form-item>
              <el-form-item label="课程名" prop="cou_name">
                <el-input v-model="applying_course.cou_name" placeholder="请输入课程名"></el-input>
              </el-form-item>
              <el-form-item label="是否考试" prop="cou_ifexam">
                <el-radio-group v-model="applying_course.cou_ifexam">
                  <el-radio :label="true">是</el-radio>
                  <el-radio :label="false">否</el-radio>
                </el-radio-group>
              </el-form-item>
              <el-form-item label="是否评分" prop="cou_ifscore">
                <el-radio-group v-model="applying_course.cou_ifscore">
                  <el-radio :label="true">是</el-radio>
                  <el-radio :label="false">否</el-radio>
                </el-radio-group>
              </el-form-item>
              <el-form-item label="开课学期" prop="cou_term">
                <el-input v-model="applying_course.cou_term" placeholder="请输入开课学期"></el-input>
              </el-form-item>
              <el-form-item label="课程类型" prop="cou_type">
                <el-select v-model="applying_course.cou_type" placeholder="请选择课程类型">  
                  <el-option v-for="item in type_options" :key="item.value" :label="item.label" :value="item.value" />  
                </el-select>
              </el-form-item>
              <el-form-item label="课程语言" prop="cou_language">
                <el-input v-model="applying_course.cou_language" placeholder="请输入课程语言"></el-input>
              </el-form-item>
              <el-form-item label="课程等级" prop="cou_rank">
                <el-select v-model="applying_course.cou_rank" placeholder="请选择课程等级">  
                  <el-option v-for="item in rank_options" :key="item.value" :label="item.label" :value="item.value" />  
                </el-select>
              </el-form-item>
              <el-form-item label="课程学分" prop="cou_score">
              <el-input-number v-model="applying_course.cou_score" :min="0" placeholder="课程学分"></el-input-number>
            </el-form-item>
              <el-form-item label="授课单位" prop="cou_school">
                <el-select v-model="applying_course.cou_school" placeholder="请选择课程类型">  
                  <el-option v-for="item in school_options" :key="item.value" :label="item.label" :value="item.value" />  
                </el-select>
              </el-form-item>
              <el-form-item label="课程大纲" prop="cou_text">
                <el-input type="textarea" v-model="applying_course.cou_text" placeholder="请输入课程大纲"></el-input>
              </el-form-item>
            </el-form>
              <template #footer>  
                <div class="foot_button">
                  <el-button type="primary" size="large" style="width: 200px; height: 50px; font-size: 18px;" @click="onSubmit">申请开课</el-button>
                  <el-button size="large" style="width: 200px; height: 50px; font-size: 18px;" @click="resetForm">清空</el-button>
                </div> 
              </template>    
          </el-card>
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
        user_id: "",  
        user_account: "",
        user_name: "",
        applying_course: {
          cou_code: "",
          cou_name: "",
          cou_ifexam: true,
          cou_ifscore: true,
          cou_term: "",
          cou_type: "",
          cou_language: "",
          cou_rank: "",
          cou_score: "",
          cou_school: "",
          cou_text: "",
        },
        type_options: [
            { value: '公共艺术类', label: '公共艺术类' },
            { value: '核心通识类', label: '核心通识类' },
            { value: '科学素养类', label: '科学素养类' },
            { value: '专业课', label: '专业课' },
            { value: '体育类', label: '体育类' },
            { value: '思想政治类', label: '思想政治类'},
        ],
        rank_options: [
            { value: '公必', label: '公必' },
            { value: '公选', label: '公选' },
            { value: '专必', label: '专必' },
            { value: '专选', label: '专选' },
            { value: '荣誉课程', label: '荣誉课程' },
        ],
        school_options: [
            { value: '软件工程学院', label: '软件工程学院' },  
            { value: '计算机学院', label: '计算机学院' },  
            { value: '数学学院', label: '数学学院' },  
            { value: '物理学院', label: '物理学院' },  
            { value: '大气学院', label: '大气学院' },  
            { value: '政务学院', label: '政务学院' },  
            { value: '海工学院', label: '海工学院' },
        ],
        rules: {
          cou_code: [{ required: true, message: '请输入课程识别码', trigger: 'blur' }],
          cou_name: [{ required: true, message: '请输入课程名', trigger: 'blur' }],
          cou_ifexam: [{ required: true, message: '请选择是否考试', trigger: 'change' }],
          cou_ifscore: [{ required: true, message: '请选择是否评分', trigger: 'change' }],
          cou_term: [{ required: true, message: '请输入开课学期', trigger: 'blur' }],
          cou_type: [{ required: true, message: '请选择课程类型', trigger: 'blur' }],
          cou_language: [{ required: true, message: '请输入课程语言', trigger: 'blur' }],
          cou_rank: [{ required: true, message: '请选择课程等级', trigger: 'blur' }],
          cou_score: [{ required: true, message: '请输入课程学分', trigger: 'blur' }],
          cou_school: [{ required: true, message: '请选择授课单位', trigger: 'blur' }],
          cou_text: [{ required: true, message: '请输入课程大纲', trigger: 'blur' }] // 修改字段名为 cou_text
        }
      }
    },
    created() {
      // 从查询参数中获取 id 和账号
      this.user_id = this.$route.query.user_id;
      this.user_account = this.$route.query.user_account;
      this.fetchUserInfo();
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
      goToTeacherApproval() {
        this.$router.go(-1); // 返回上一页
      },
      onSubmit() {
  this.$refs.applying_course.validate((valid) => {
    if (valid) {
      // 构造请求体
      const requestData = {
        approvalFormTab: {
          apf_adm_id: 1,  // 默认值
          apf_tch_id: this.user_id,  // 教师 ID
          apf_cou_code: this.applying_course.cou_code,  // 课程识别码
          apf_status: 4,  // 默认待审批
          apf_note: "",  // 默认空值
        },
        courseTab: {
          cou_code: this.applying_course.cou_code,  // 课程识别码
          cou_name: this.applying_course.cou_name,  // 课程名
          cou_ifexam: this.applying_course.cou_ifexam ? "考试" : "考查",  // 转换为字符串
          cou_ifscore: this.applying_course.cou_ifscore ? "评分" : "不评分",  // 转换为字符串
          cou_term: this.applying_course.cou_term,  // 开课学期
          cou_text: this.applying_course.cou_text,  // 课程大纲
          cou_type: this.applying_course.cou_type,  // 课程类型
          cou_language: this.applying_course.cou_language,  // 课程语言
          cou_rank: this.applying_course.cou_rank,  // 课程等级
          cou_status: 4,  // 默认待审批
          cou_school: this.applying_course.cou_school,  // 授课单位
          cou_score: this.applying_course.cou_score,  // 课程学分
        }
      };

      // 发送 POST 请求
      axios.post('http://localhost:8080/tch/approvalForm/add', requestData)
        .then(response => {
          console.log('开课申请提交成功', response);
          ElMessage.success('开课申请已提交');
          // 提交成功后，可以选择清空表单或跳转到其他页面
          this.resetForm();
        })
        .catch(error => {
          console.error('提交开课申请失败:', error);
          ElMessage.error('提交失败，请稍后再试');
        });
    } else {
      console.log('表单验证失败');
      ElMessage.error('课程信息不完整，请补充');
      return false;
    }
  });
},
      resetForm() {
        this.$refs.applying_course.resetFields();
      }
    },
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
  .apply_course {  
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
  .el-card {  
    margin-left: 250px;  
    margin-top: 50px;  
  }  
  .user-info {  
    margin-left: 20px;   
    margin-right: 50px;   
  }  
  .tch_apply_course_box-card {  
    margin-left: 350px;  
    width: 700px;  
  }  
  .foot_button{
    text-align: center; 
  }
  .foot_button .el-button {
    margin-left: 50px; /* 按钮之间的间距 */
  }
  </style>