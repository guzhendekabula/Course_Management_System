<!--审批课程（教务）-->
<template>
  <div>
    <el-container>
      <el-header>
        <img src="@/assets/2.jpg" alt="Icon" class="badge">
        <h3 style="margin-right: auto;">某某学校教务员端——课程审批页面</h3>
        <div class="user-info">
          <h3>账号：{{ user_account }}</h3>
          <h3>管理员ID: {{ user_id }}</h3>
        </div>
        <el-button type="danger" style="float: right;" @click="goToTeacherHome">返回</el-button>
      </el-header>
      <el-main>
        <div>
          <el-card :key="i" class="box-card" :body-style="{ width: '100%', position: 'relative', overflow: 'hidden' }">
            <h1>
              <el-row>
                <el-col :span="24">
                  <div class="text-left">课程审批</div>
                </el-col>
              </el-row>
              <el-row class="container">
                <el-col :span="24">
                  <td class="line">
                    <div />
                  </td>
                </el-col>
              </el-row>
            </h1>

            <el-row gutter="20" :justify="center" class="container">
              <!-- <el-col :span="4"> 学年： </el-col> -->
              <el-col :span="6" :offset="1">
                <!-- 输入课程名 -->
                <div style="display: flex; align-items: center;">
                  <span style="margin-right: 6px;">课程id：</span>
                  <el-input v-model="inputcou" style="width: 240px" placeholder="请输入课程id" />
                </div>
              </el-col>
              <el-col :span="6">
                <!-- 输入教师名 -->
                <div style="display: flex; align-items: center;">
                  <span style="margin-right: 6px;">教师id：</span>
                  <el-input v-model="inputtch" style="width: 240px" placeholder="请输入教师id" />
                </div>
              </el-col>
              <el-col :span="6">
                <!-- 选择状态 -->
                <div style="display: flex; align-items: center;">
                  <span style="margin-right: 6px;">状态：</span>
                  <el-select v-model="status" placeholder="Select" style="width: 240px">
                    <el-option v-for="item in statusoptions" :key="item.value" :label="item.label"
                      :value="item.value" />
                  </el-select>
                </div>
              </el-col>
            </el-row>
            <el-row class="container">
              <el-col :span="1" :offset="20">
                <!-- 查询按钮 -->
                <el-button :dark="isDark" @click="fetchApproval">查询</el-button>
              </el-col>
              <el-col :span="1">
                <!-- 重置按钮 -->
                <el-button :dark="isDark" @click="Reset">重置</el-button>
              </el-col>
            </el-row>
            <el-row class="container" justify="center">
              <el-col :span="23">
                <el-table :data="activitytable" style="width: 100%" :header-cell-style="{ backgroundColor: 'var(--el-color-primary-light-3)', color: '#ffffff' }">
                  <el-table-column prop="cou_name" label="课程名" />
                  <el-table-column prop="apf_tch" label="负责教师" />
                  <el-table-column prop="cou_school" label="学院" />
                  <el-table-column prop="apf_status" label="状态" />
                  <el-table-column prop="operation" label="操作">
                    <template #default="scope">
                      <el-button :disabled="scope.row.apf_status !== '待审批'" size="small"
                        @click="handleAppr(scope.$index, scope.row)">
                        审批
                      </el-button>
                      <!-- <el-button size="small"
                        @click="handleAppr(scope.$index, scope.row)">
                        审批
                      </el-button> -->
                      <el-button :disabled="scope.row.apf_status === '通过'" size="small" type="danger"
                        @click="handleDelete(scope.$index, scope.row)">
                        删除
                      </el-button>
                      <!-- <el-button size="small" type="danger"
                        @click="handleDelete(scope.$index, scope.row)">
                        删除
                      </el-button> -->
                    </template>
                  </el-table-column>
                </el-table>
              </el-col>
            </el-row>
          </el-card>

        </div>
        <el-dialog v-model="dialogVisible" title="课程审批" width="1000">
          <div>
            <el-row class="container">
              <el-col :span="12">
                <div class="text-left">
                  <el-text :style="{ fontSize: '18px' }" tag="b">1.教师信息：</el-text>
                </div>
              </el-col>
            </el-row>
            <el-row class="container">
              <el-col :span="12">
                <div class="text-left">{{tch_id}}</div>
              </el-col>
              <el-col :span="12">
                <div class="text-left">{{tch_name}}</div>
              </el-col>
            </el-row>
            <el-row class="container">
              <el-col :span="12">
                <div class="text-left">{{tch_sex}}</div>
              </el-col>
              <el-col :span="12">
                <div class="text-left">{{tch_tel}}</div>
              </el-col>
            </el-row>
            <el-row class="container">
              <el-col :span="12">
                <div class="text-left">{{tch_sch}}</div>
              </el-col>
            </el-row>
            <el-row>
              <el-divider />
            </el-row>
            <el-row class="container">
              <el-col :span="12">
                <div class="text-left">
                  <el-text :style="{ fontSize: '18px' }" tag="b">2.课程信息</el-text>
                </div>
              </el-col>
            </el-row>
            <el-row class="course">
              <el-col :span="12">
                <div class="text-left">
                  <el-text>{{cou_name}}</el-text>
                </div>
              </el-col>
            </el-row>
            <el-row class="course">
              <el-col :span="12">
                <div class="text-left">
                  <el-text>{{cou_type}}</el-text>
                </div>
              </el-col>
            </el-row>
            <el-row class="course">
              <el-col :span="12">
                <div class="text-left">
                  <el-text>{{cou_language}}</el-text>
                </div>
              </el-col>
            </el-row>
            <el-row class="course">
              <el-col :span="12">
                <div class="text-left">
                  <el-text>{{cou_rank}}</el-text>
                </div>
              </el-col>
            </el-row>
            <el-row class="course">
              <el-col :span="12">
                <div class="text-left">
                  <el-text>{{cou_school}}</el-text>
                </div>
              </el-col>
            </el-row>
            <el-row class="course">
              <el-col :span="12">
                <div class="text-left">
                  <el-text>{{cou_score}}</el-text>
                </div>
              </el-col>
            </el-row>
            <el-row class="course">
              <el-col :span="12">
                <div class="text-left">
                  <el-text>{{cou_ifexam}}</el-text>
                </div>
              </el-col>
            </el-row>
            <el-row class="course">
              <el-col :span="12">
                <div class="text-left">
                  <el-text>{{cou_ifscore}}</el-text>
                </div>
              </el-col>
            </el-row>
            <el-row class="course">
              <el-col :span="3">
                <div class="text-left">
                  <el-text>课程大纲：</el-text>
                </div>
              </el-col>
              <el-col :span="2">
                <el-input v-model="cou_text" style="width: 480px" :rows="6" type="textarea" disabled
                  />
              </el-col>
            </el-row>
            <el-row>
              <el-divider />
            </el-row>
            <el-row class="container">
              <el-col :span="12">
                <div class="text-left">
                  <el-text :style="{ fontSize: '18px' }" tag="b">3.审批</el-text>
                </div>
              </el-col>
            </el-row>
            <el-row class="container">
              <el-col :span="3">
                <div>
                  <el-text>审批意见：</el-text>
                </div>
              </el-col>
              <el-col :span="2">
                <el-input v-model="apf_note" style="width: 480px" :rows="6" type="textarea"
                  placeholder="Please input" />
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="2" :offset="20">
                <el-button type="success"  @click="PassAppr">通过</el-button>
              </el-col>
              <el-col :span="2">
                <el-button type="danger"  @click="RejectAppr">驳回</el-button>
              </el-col>
            </el-row>
          </div>
        </el-dialog>
      </el-main>
    </el-container>
  </div>

</template>

<script scope setup>
// eslint-disable-next-line
import { ref, onMounted,nextTick } from 'vue';
import { useRouter } from 'vue-router';
import { useRoute } from 'vue-router';
import { getApproval,getCourse,deleteApproval,AddApproval,RejectApproval,GetTchInfo} from '@/api/adm_approval_form';
import { ElMessage } from 'element-plus';
// 获取路由信息
const route = useRoute();
const user_account = ref(route.query.user_account || ''); // 处理查询参数 `user_account`
const user_id = ref(route.query.user_id || ''); // 处理查询参数 `user_id
const router = useRouter();
const activitytable = ref([]);
const dialogVisible = ref(false);
// const activityTable = ref([]);
let inputcou = ref('');
let inputtch = ref('');
let status = ref('-1');
const approvaltable = ref([]);
const CoursesTable = ref([]);
const tchInfo = ref([]);
const tch_id = ref('教工号：');
const tch_name = ref('姓名：');
const tch_sex = ref('性别：');
const tch_tel = ref('电话：');
const tch_sch = ref('学院：');
const cou_name = ref('课程名：');
const cou_type = ref('课程类型：');
const cou_language = ref('课程语言：');
const cou_rank = ref('课程等级：');
const cou_school = ref('开课单位：');
const cou_score = ref('学分：');
const cou_ifexam = ref('是否考试：');
const cou_ifscore = ref('是否评分：');
const cou_text = ref('');
const apf_note = ref('');
const currentindex = ref('');
onMounted(() => {
  //  activitytable.value = activityTable;
  FetchData();
});
// eslint-disable-next-line
const FetchData = async() =>{
  FetchApprovaltable();
}
//获取审批数据
const FetchApprovaltable = async() =>{
  const params = {
    apfCouCode: inputcou.value,
    apfTchId: inputtch.value == ''?null:inputtch.value,
    apfStatus: status.value == -1?null:status.value
  };
  console.log(params);
  try {
    const response = await getApproval(params);
    approvaltable.value = response.data;
    console.log(approvaltable.value);
    getCoursesTable();
  } catch (error) {
    console.log(error);
  }
}
//获取课程数据
const getCoursesTable = async() =>{
  CoursesTable.value = await Promise.all(approvaltable.value.map(apf => {
  const course = FetchCourses(apf.apf_cou_code); // 获取与当前审批记录对应的课程数据
  return course;
}));
console.log(CoursesTable.value);
GetTchinfo();
}
const FetchCourses = async(cou_code) =>{
  const params = {
    couCode: cou_code
  };
  try {
    const response = await getCourse(params);
    console.log(response.data);
    return response.data;
  } catch (error) {
    console.log(error);
  }
  return null;
}
const GetTchinfo = async() =>{
  tchInfo.value = await Promise.all(approvaltable.value.map(apf => {
  const tchi = FetchtchInfo(apf.apf_tch_id); // 获取与当前审批记录对应的课程数据
  return tchi;
}));
console.log(tchInfo.value);
Getactivitytable();
}
const FetchtchInfo = async(tch_id) =>{
  const params = {
    tchId: tch_id
  };
  try {
    const response = await GetTchInfo(params);
    console.log(response.data);
    return response.data;
  } catch (error) {
    console.log(error);
  }
  return null;
}
const Getactivitytable = async() =>{
  activitytable.value = await Promise.all(approvaltable.value.map((apf,index) => {
  const course = CoursesTable.value[index]; // 获取与当前审批记录对应的课程数据
  const tch = tchInfo.value[index];
  let statusNumber;
      switch (apf.apf_status) {
        case 0:
          statusNumber = '待审批'; // 未审批
          break;
        case 1:
          statusNumber = '通过'; // 已审批
          break;
        case 2:
          statusNumber = '驳回'; // 审批失败
          break;
      }
  // 将数据组合成新的格式
  return {
    apf_id: apf.apf_id,
    cou_name: course.cou_name,
    apf_tch: tch.tch_name,
    cou_school: course.cou_school,
    apf_status: statusNumber,
  };
}));
console.log(activitytable.value);
}
// {
// 		"apf_id": 1,
// 		"apf_adm_id": 1,
// 		"apf_tch_id": 1,
// 		"apf_cou_code": "RJGC-0001",
// 		"apf_status": 1,
// 		"apf_note": ""
// 	}
// {
// 	"cou_code": "RJGC-0001",
// 	"cou_name": "编译原理",
// 	"cou_ifexam": "考试",
// 	"cou_ifscore": "评分",
// 	"cou_term": 2024,
// 	"cou_text": "",
// 	"cou_type": "专业课",
// 	"cou_language": "中文",
// 	"cou_rank": "专必",
// 	"cou_status": 3,
// 	"cou_school": "软件工程学院",
// 	"cou_score": 2
// }
// const activityTable = [
//   {
//     cou_code: "001",
//     cou_name: "人工智能与大数据",
//     apf_tch_id: "顶真",
//     cou_school: "人工智能",
//     apf_status: "待审批",
//   },
//   {
//     cou_code: "002",
//     cou_name: "编译原理",
//     apf_tch_id: "田磊",
//     cou_school: "软件工程",
//     apf_status: "通过",
//   },
//   {
//     cou_code: "003",
//     cou_name: "数据库原理",
//     apf_tch_id: "李四",
//     cou_school: "软件工程",
//     apf_status: "驳回",
//   },
// ]
const fetchApproval = async() =>{
  FetchApprovaltable();
}
const Reset = async() =>{
inputcou.value = '';
inputtch.value = '';
status.value = '-1';
FetchApprovaltable();
}
const handleAppr = (index, row) => {
  dialogVisible.value = true;
  FillCoursedata(index)
  console.log(index,row.couid)
}
const handleDelete = (index, row) => {
  DeleteAppr(row);
  console.log(index, row);
}
const DeleteAppr = async(row) => {
  const params = row.apf_id;
  console.log(params);
  try {
    const response = await deleteApproval(params);
    console.log(response.data);
  } catch (error) {
    console.log(error);
  }
  FetchData();
}
const FillCoursedata = async(index) =>{
  let course = CoursesTable.value[index];
  let tch = tchInfo.value[index];
  currentindex.value = index;
  tch_id.value = '教工号：'+ tch.tch_id;
  tch_name.value = '姓名：'+ tch.tch_name;
  tch_sex.value = '性别：' + tch.tch_sex;
  tch_tel.value = '电话：' + tch.tch_tel;
  tch_sch.value = '学院：'+ tch.tch_sch;
  cou_name.value = '课程名：'+course.cou_name;
  cou_type.value = '课程类型：'+ course.cou_type;
  cou_language.value = '课程语言：'+ course.cou_language;
  cou_rank.value = '课程等级：'+ course.cou_rank;
  cou_school.value = '开课单位：'+ course.cou_school;
  cou_score.value = '学分：'+ course.cou_score;
  cou_ifexam.value = '是否考试：'+ course.cou_ifexam;
  cou_ifscore.value = '是否评分：'+ course.cou_ifscore;
  cou_text.value = course.cou_text;
  apf_note.value = approvaltable.value[index].apf_note;
}
const goToTeacherHome = async () => {
  router.go(-1);
}
const PassAppr = async() => {
  const params = {
    apf_id: approvaltable.value[currentindex.value].apf_id,
    apf_adm_id: approvaltable.value[currentindex.value].apf_adm_id,
    apf_tch_id: approvaltable.value[currentindex.value].apf_tch_id,
    apf_cou_code: approvaltable.value[currentindex.value].apf_cou_code,
    "apf_status": 0,
    "apf_note": apf_note.value
  };
  console.log(params);
  try {
    const response = await AddApproval(params);
    console.log(response.data);
    dialogVisible.value = false;
    FetchData();
  } catch (error) {
    ElMessage.error(error.response.data)
    console.log(error);
  }
}
const RejectAppr = async() => {
  const params = {
    apf_id: approvaltable.value[currentindex.value].apf_id,
    apf_adm_id: approvaltable.value[currentindex.value].apf_adm_id,
    apf_tch_id: approvaltable.value[currentindex.value].apf_tch_id,
    apf_cou_code: approvaltable.value[currentindex.value].apf_cou_code,
    "apf_status": 2,
    "apf_note": apf_note.value
  };
  console.log(params);
  try {
    const response = await RejectApproval(params);
    console.log(response.data);
    dialogVisible.value = false;
    FetchData();
  } catch (error) {
    ElMessage.error(error.response.data)
    console.log(error);
  }
}
const statusoptions = [
  {
    value: '-1',
    label: '全部'
  },
  {
    value: '0',
    label: '待审批',
  },
  {
    value: '1',
    label: '通过',
  },
  {
    value: '2',
    label: '驳回',
  },
];
</script>

<style scoped>
.line div {
  width: 99vw;
  height: 0;
  border-top: 1px solid var(--el-border-color);
}

.text-left {
  text-align: left;
}


.container {
  margin-bottom: 30px;
}

.course {
  margin-bottom: 70px;
}

.overall {
  margin-bottom: 90px;
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

.badge {
  width: 85px;
  height: 85px;
  margin-left: 0;
  margin-right: 10px;
  border-radius: 50%;
}

.user-info {
  margin-left: 20px;
  /* 为用户信息添加左边距 */
  margin-right: 50px;
  /* 为用户信息添加右边距 */
}

.el-card {
  margin: auto;
}
</style>
