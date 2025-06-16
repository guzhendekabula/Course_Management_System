<!----评教（老师）---->
<template>
  <el-container>
    <el-header>
      <img src="@/assets/2.jpg" alt="Icon" class="badge">
      <h3 style="margin-right: auto;">某某学校教师端——查看评教页面</h3>
      <div class="user-info">
        <h3>账号：{{ user_account }}</h3>
        <h3>ID: {{ user_id }}</h3>
      </div>
      <el-button type="danger" style="float: right;" @click="goToTeacherHome">返回</el-button>
    </el-header>
    <el-main>
      <div class="main_container">
        <el-space direction="vertical" style="width: 50%;">
          <el-card :key="i" class="box-card" :body-style="{ width: '100%', position: 'relative', overflow: 'hidden' }">
            <h1>
              <el-row>
                <el-col :span="24">
                  <div class="text-left">
                    教学班信息
                  </div>
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
            <el-row class="container">
              <el-col :span="12">
                <div class="text-left">{{evaClaId}}</div>
              </el-col>
              <el-col :span="12">
                <div class="text-left">{{couName}}</div>
              </el-col>
            </el-row>
            <el-row class="container">
              <el-col :span="12">
                <div class="text-left">{{evaTchId}}</div>
              </el-col>
              <el-col :span="12">
                <div class="text-left">{{tchName}}</div>
              </el-col>
            </el-row>
            <el-row class="container">
              <el-col :span="12">
                <div class="text-left">{{couSchool}}</div>
              </el-col>
              <el-col :span="12">
                <div class="text-left">{{couRank}}</div>
              </el-col>
            </el-row>
            <el-row class="container">
              <el-col :span="12">
                <div class="text-left">{{couType}}</div>
              </el-col>
            </el-row>
          </el-card>

          <el-card :key="i" class="box-card" :body-style="{ width: '100%', position: 'relative', overflow: 'hidden' }">
            <h1>
              <el-row>
                <el-col :span="12">
                  <div class="text-left">
                    统计数据
                  </div>
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

            <el-row class="container">
              <el-col :span="2">
                <el-select v-model="student" placeholder="Select" style="width: 240px" @change="bindData">
                  <el-option v-for="item in studentoptions" :key="item.value" :label="item.label" :value="item.value" />
                </el-select>
              </el-col>
            </el-row>
            <el-row class="overall">
              <el-col :span="12">
                <div class="text-left">
                  <el-text>{{evaItem1}}</el-text>
                </div>
              </el-col>
            </el-row>
            <el-row class="overall">
              <el-col :span="12">
                <div class="text-left">
                  <el-text>{{evaItem2}}</el-text>
                </div>
              </el-col>
            </el-row>
            <el-row class="overall">
              <el-col :span="12">
                <div class="text-left">
                  <el-text>{{evaItem3}}</el-text>
                </div>
              </el-col>
            </el-row>
            <el-row class="overall">
              <el-col :span="12">
                <div class="text-left">
                  <el-text>{{evaItem4}}</el-text>
                </div>
              </el-col>
            </el-row>
            <el-row class="overall">
              <el-col :span="12">
                <div class="text-left">
                  <el-text>{{evaScore}}</el-text>
                </div>
              </el-col>
            </el-row>
            <el-row class="container">
              <el-col :span="12">
                <div class="text-left">
                  <el-text :style="{ fontSize: '16px' }" tag="b">希望和建议</el-text>
                </div>
              </el-col>
            </el-row>
            <el-row class="container">
              <el-col :span="2">
                <el-input v-model="evaText" style="width: 480px" :rows="6" type="textarea" disabled/>
              </el-col>
            </el-row>
          </el-card>
        </el-space>
      </div>
    </el-main>
  </el-container>
  <el-dialog
    v-model="centerDialogVisible"
    :close-on-click-modal= false
    title="找不到评教信息"
    width="500"
    align-center
    center
    :show-close = false
  >
    <template #footer>
      <div class="dialog-footer">
        <el-button type="primary" @click="GoBack">
          返回
        </el-button>
      </div>
    </template>
  </el-dialog>
</template>

<script scope setup>
import { ref, onMounted } from 'vue';
import { useRouter,useRoute } from 'vue-router';
// eslint-disable-next-line
import { getEvaluation} from '@/api/tch_evaluation';
const router = useRouter();
const route = useRoute();
const studentoptions = ref([]);
const student = ref('1');
const tchEvaluation = ref([]);
const user_id = ref('');
const user_account = ref('');
const evaClaId = ref('教学班编号：');
const couName = ref('课程名：');
const evaTchId = ref('教工号：');
const tchName = ref('姓名：');
const couSchool = ref('学院：');
const couRank = ref('课程等级：');
const couType = ref('课程类型：');
const evaItem1 = ref('1.教学态度（满分30分）：');
const evaItem2 = ref('2.教学内容（满分20分）：');
const evaItem3 = ref('3.教学方法（满分30分）：');
const evaItem4 = ref('4.教学效果（满分20分）：');
const evaScore = ref('5.总分（满分100分）：');
const evaText = ref('');
const centerDialogVisible = ref(false);
// const studentOptions = [
//   {
//     value: "1",
//     label: "学生1",
//   },
//   {
//     value: "2",
//     label: "学生2",
//   },
//   {
//     value: "3",
//     label: "学生3",
//   },
//   {
//     value: "4",
//     label: "学生4",
//   },
//   {
//     value: "5",
//     label: "学生5",
//   },
// ]
onMounted(() => {
  //studentoptions.value = studentOptions;
  user_id.value = route.query.user_id;
  user_account.value = route.query.user_account;
  console.log(user_id.value+' '+user_account.value);
  bindData();
});
const goToTeacherHome = async () => {
  router.go(-1);
}
const fetchTcheva = async() =>{
  const params = {
    evaTchId : user_id.value
  };
  console.log(params);
  try {
    const response = await getEvaluation(params);
    tchEvaluation.value = response.data;
    console.log(tchEvaluation.value);
    bindstudentOptions();
  } catch (error) {
    console.log(error);
  }
}
const bindstudentOptions = async() =>{
  studentoptions.value = await Promise.all(tchEvaluation.value.map((item, index) => {
  let i = index + 1;
  return{
    value: String(index),  // 这里的 index + 1 表示从1开始，value为索引位置+1
    label: String('学生' + i +'：')  // 将 apf_tch_id 转为字符串存入 label
  }
}));
}
const bindData = async() =>{
  await fetchTcheva();
  if(student.value == null){
    student.value = 1;
  }
  console.log(student.value)
  let te = tchEvaluation.value[student.value];
  if (!te) {
    console.log('未找到对应的数据');
    centerDialogVisible.value = true;
    return; // 提前返回，避免继续执行后续代码
  }
  evaClaId.value = '教学班编号：'+ te.evaClaId;
  couName.value = '课程名：'+ te.couName;
  evaTchId.value = '教工号：' + te.evaTchId;
  tchName.value = '姓名：' + te.tchName;
  couSchool.value = '学院：' + te.couSchool;
  couRank.value = '课程等级：'+ te.couRank;
  couType.value = '课程类型：'+ te.couType;
  evaItem1.value = '1.教学态度（满分30分）：'+ te.evaItem1;
  evaItem2.value = '2.教学内容（满分20分）：' + te.evaItem2;
  evaItem3.value = '3.教学方法（满分30分）：' + te.evaItem3;
  evaItem4.value = '4.教学效果（满分20分）：' + te.evaItem4;
  evaScore.value = '5.总分（满分100分）：' + te.evaScore;
  evaText.value = te.evaText;
}
const GoBack = async() =>{
  centerDialogVisible.value = false;
  goToTeacherHome();
}
</script>

<style scoped>
.main_container {
  overflow-x: hidden;
  box-sizing: border-box;
}

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

.overall {
  margin-bottom: 90px;
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
  overflow: visible;
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

.main_container {
  overflow-x: hidden;
  box-sizing: border-box;
}

.el-card {
  max-width: 90%;
  margin: auto;
}
</style>
