<!--成绩查询（学生）-->
<template>
  <div>
    <el-container>
      <el-header>
        <img src="@/assets/2.jpg" alt="Icon" class="badge">
        <h3 style="margin-right: auto;">某某学校学生端——课程成绩页面</h3>
        <div class="user-info">
          <h3>账号：{{ user_account }}</h3>
          <h3>ID: {{ user_id }}</h3>
        </div>
        <el-button type="danger" style="float: right;" @click="goToTeacherHome">返回</el-button>
      </el-header>
      <el-main>
        <div class="main_container">
          <el-space direction="vertical" style="width: 50%;">
            <!-- 成绩查询 -->
            <el-card :key="i" class="box-card"
              :body-style="{ width: '100%', position: 'relative', overflow: 'hidden' }">
              <h1>
                <el-row>
                  <el-col :span="24">
                    <div class="text-left">成绩查询</div>
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
                <el-col :span="6" :offset="2">
                  <!-- 选择学年 -->
                  <div style="display: flex; align-items: center;">
                    <span style="margin-right: 6px;">学年：</span>
                    <el-select v-model="year" placeholder="Select" style="width: 240px">
                      <el-option v-for="item in yearoptions" :key="item.value" :label="item.label"
                        :value="item.value" />
                    </el-select>
                  </div>
                </el-col>
                <el-col :span="6">
                  <!-- 选择学期 -->
                  <div style="display: flex; align-items: center;">
                    <span style="margin-right: 6px;">学期：</span>
                    <el-select v-model="term" placeholder="Select" style="width: 240px">
                      <el-option v-for="item in termoptions" :key="item.value" :label="item.label"
                        :value="item.value" />
                    </el-select>
                  </div>
                </el-col>

                <el-col :span="4">
                  <!-- 查询按钮 -->
                  <el-button :dark="isDark" @click="fetchStudentScore">查询</el-button>
                </el-col>
              </el-row>
              <el-row :justify="center" class="container">
                <el-col :span="4"> 已修学分： </el-col>
                <el-col :span="4" :pull="1"> 平均学分绩点： </el-col>
                <el-col :span="4">
                  <el-checkbox v-model="checked1" label="公必" @change="fetchStudentScore" />
                </el-col>
                <el-col :span="4" :pull="2">
                  <el-checkbox v-model="checked2" label="公选" @change="fetchStudentScore" />
                </el-col>
                <el-col :span="4" :pull="4">
                  <el-checkbox v-model="checked3" label="专必" @change="fetchStudentScore" />
                </el-col>
                <el-col :span="4" :pull="6">
                  <el-checkbox v-model="checked4" label="专选" @change="fetchStudentScore" />
                </el-col>
              </el-row>
              <el-row class="container" justify="center">
                <el-col :span="23">
                  <el-table :data="studentScore" stripe style="width: 100%"
                    :header-cell-style="{ backgroundColor: 'var(--el-color-primary-light-3)', color: '#ffffff' }">
                    <el-table-column prop="rank" label="类别" />
                    <el-table-column prop="couname" label="课程名" :width="200" />
                    <el-table-column prop="tch" label="教师" />
                    <el-table-column prop="year" label="学年" />
                    <el-table-column prop="term" label="学期" />
                    <el-table-column prop="c_score" label="学分" />
                    <el-table-column prop="score" label="成绩" />
                    <el-table-column prop="score2" label="绩点" />
                    <el-table-column prop="pass" label="是否通过" />
                    <el-table-column prop="position" label="排名" />
                  </el-table>
                </el-col>
              </el-row>
            </el-card>
            <el-card :key="i" class="box-card"
              :body-style="{ width: '100%', position: 'relative', overflow: 'hidden' }">
              <h1>
                <el-row>
                  <el-col :span="24">
                    <div class="text-left">排名总览</div>
                  </el-col>
                </el-row>
                <el-row class="container">
                  <el-col :span="24">
                    <td class="line">
                      <div />
                    </td>
                  </el-col>
                </el-row>
                <el-row class="container" justify="center">
                  <el-col :span="23">
                    <el-table :data="scoreoverview" stripe style="width: 100%">
                      <el-table-column prop="rank" label="课程类型" />
                      <el-table-column :label="yearterm">
                        <el-table-column prop="n_c_score" label="已修学分" />
                        <el-table-column prop="n_score2" label="平均绩点" />
                      </el-table-column>
                      <el-table-column label="总完成情况">
                        <el-table-column prop="a_c_score" label="已修学分" />
                        <el-table-column prop="a_score2" label="平均绩点" />
                      </el-table-column>
                    </el-table>
                  </el-col>
                </el-row>
              </h1>
            </el-card>
            <el-card :key="i" class="box-card"
              :body-style="{ width: '100%', position: 'relative', overflow: 'hidden' }">
              <h1>
                <el-row>
                  <el-col :span="24">
                    <div class="text-left">成绩分布</div>
                  </el-col>
                </el-row>
                <el-row class="container">
                  <el-col :span="24">
                    <td class="line">
                      <div />
                    </td>
                  </el-col>
                </el-row>
                <el-row>
                  <el-col :span="24">
                    <div class="home-index-box">
                      <!--饼状图and环形图-->
                      <div class="graph-pie-warp">
                        <el-row :gutter="30">
                          <el-col :span="12">
                            <div id="home_gathering_source" style="height: 300px; margin:30px 10px; padding: 0px;">
                            </div>
                          </el-col>
                        </el-row>
                      </div>
                    </div>
                  </el-col>
                </el-row>
              </h1>
            </el-card>
          </el-space>
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<script scope setup>
// eslint-disable-next-line
import { getStuScore } from '@/api/stu_score';
// eslint-disable-next-line
import { useRouter } from 'vue-router';
import * as ECharts from "echarts";
import { ref, onMounted, nextTick } from 'vue';
const year = ref('');
const term = ref('');
const sourceList = ref([]);
const router = useRouter();
const studentScore = ref([]);
const checked1 = ref(true);
const checked2 = ref(true);
const checked3 = ref(true);
const checked4 = ref(true);
onMounted(() => {
  changeTitle1('默认名字');  // 页面加载时自动修改为 '默认名字'
  fetchStudentScore();
});
const goToTeacherHome = async () => {
  router.go(-1);
}
const gatheringSourceOption = {
  title: {
    text: '大学成绩分布图',
    left: '15%',//'5' | '5%'，title 组件离容器左侧的距离
    top: 'auto',//title 组件离容器上侧的距离
    bottom: 'auto',//title 组件离容器下侧的距离
  },
  tooltip: {
    trigger: 'item',
    formatter: '{a} <br/>{b} : {c} ({d}%)'
  },
  legend: {
    type: 'scroll',
    orient: 'vertical',
    right: 150,
    top: 'center',
    icon: 'circle',
    selectedMode: 'multiple',
    formatter: (name) => { //legend显示数据格式化,每一个 legend 渲染都会回调这个函数
      let data = gatheringSourceOption.series[0].data //获取当前对象的series.data
      let total = 0 //数据对象总和存放
      let tarValue = 0 // 当前对象的value 值
      for (let i = 0, l = data.length; i < l; i++) { //循环饼状图数据对象
        total += data[i].value  //计算出当前对象总和
        if (data[i].name == name) {
          tarValue = data[i].value //存放当前渲染的legend 对象
        }
      }
      let p = (tarValue / total * 100).toFixed(2) //百分比保留小数点后两位
      return `${name}  |  ${p}%  `
    }
  },
  series: [
    {
      name: '成绩分布图',
      type: 'pie',
      radius: '70%',
      center: ['25%', '60%'],
      selectedMode: 'single',
      data: [],
      label: {
        show: false
      },
      itemStyle: {
        emphasis: {
          shadowBlur: 10,
          shadowOffsetX: 0,
          shadowColor: 'rgba(0, 0, 0, 0.5)'
        }
      }
    }
  ]
}
const eChartsInit = async (domId, theme, opt) => {
  ECharts.init(document.getElementById(domId), theme).setOption(opt)
  window.addEventListener('resize', () => {
    ECharts.init(document.getElementById(domId), theme).resize()
  })
}
const getProportionData = async () => {
  gatheringSourceOption.series[0].data = sourceList.value
  eChartsInit('home_gathering_source', 'light', gatheringSourceOption)
}
const fetchStudentScore = async () => {
  const params =
  {
    studentId: 1,
    year: year.value,
    term: term.value,
    公必: checked1.value,
    公选: checked2.value,
    专必: checked3.value,
    专选: checked4.value
  };
  if (year.value === "") {
    params.year = '-1';
  }
  if (term.value === "") {
    params.term = '-1';
  }
  console.log(params);
  // studentScore.value = stuscore;
  //固定数据，后面再改

  studentScore.value = await changestuscore(stuscore);
  sourceList.value = sourcelist;
  console.log(sourcelist);
  console.log(sourceList.value);
  nextTick(() => {
    getProportionData();
  })

  //
  try {
    const response = await getStuScore(params);
    console.log(response);
    studentScore.value = response.data.data;

  } catch (error) {
    console.log(error);
  }
}
// eslint-disable-next-line
const changestuscore = async (stuscore) => {
  for (let i = 0; i < stuscore.length; i++) {
    stuscore[i].pass = stuscore[i].pass === 'true' ? '通过' : '未通过';
  }
  stuscore = stuscore.map(item => {
    let year = item.couterm.slice(0, 4);
    let term = item.couterm.slice(4, 5);
    return {
      ...item,
      year,
      term
    };
  }
  );
  return stuscore;
}
var sourcelist = [
  { value: 4, name: '优秀', itemStyle: { color: '#91cd77' } },
  { value: 2, name: '良好', itemStyle: { color: '#f9c956' } },
  { value: 2, name: '合格', itemStyle: { color: '#fc8251' } },
  { value: 3, name: '不合格', itemStyle: { color: '#ef6567' } },
]
// eslint-disable-next-line
const stuscore = [
  {
    rank: "公选",
    couname: "急救知识",
    tch: "Tom",
    couterm: "20242",
    c_score: "2",
    score: "100",
    score2: "5.0",
    pass: "true",
    position: "1/200"
  },
  {
    rank: "公选",
    couname: "玉石鉴赏",
    tch: "Tom",
    couterm: "20241",
    c_score: "2",
    score: "100",
    score2: "5.0",
    pass: "true",
    position: "1/200"
  },
  {
    rank: "公选",
    couname: "中国古典文学史",
    tch: "Tom",
    couterm: "20222",
    c_score: "2",
    score: "100",
    score2: "5.0",
    pass: "true",
    position: "1/200"
  },
  {
    rank: "公选",
    couname: "心理、情感与社会",
    tch: "Tom",
    couterm: "20221",
    c_score: "2",
    score: "100",
    score2: "5.0",
    pass: "true",
    position: "1/200"
  },
];
const yearterm = ref('2023-2024学年第二学期完成情况');
const changeTitle1 = (newName) => {
  newName = "修改后的字，记得删掉"
  yearterm.value = newName;
};
const scoreoverview = [
  {
    rank: "公必",
    n_c_score: "8",
    n_score2: "4.5",
    a_c_score: "20",
    a_score2: "4",
  },
  {
    rank: "专必",
    n_c_score: "8",
    n_score2: "4.5",
    a_c_score: "20",
    a_score2: "4",
  },
  {
    rank: "公选",
    n_c_score: "8",
    n_score2: "4.5",
    a_c_score: "20",
    a_score2: "4",
  },
  {
    rank: "专选",
    n_c_score: "8",
    n_score2: "4.5",
    a_c_score: "20",
    a_score2: "4",
  },
  {
    rank: "合计",
    n_c_score: "8",
    n_score2: "4.5",
    a_c_score: "20",
    a_score2: "4",
  },
]
const yearoptions = [
  {
    value: "2024",
    label: "2024",
  },
  {
    value: "2025",
    label: "2025",
  },
  {
    value: "2026",
    label: "2026",
  },
  {
    value: "2027",
    label: "2027",
  },
  {
    value: "2028",
    label: "2028",
  },
];
const termoptions = [
  {
    value: "1",
    label: "第一学期",
  },
  {
    value: "2",
    label: "第二学期",
  }
];
</script>
<style scoped>
/*
main_container是套在main的父容器上的，可以保证在使用el-space的时候横向不会出现滚动条
*/
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

.el-card {
  max-width: 90%;
  margin: auto;
}
</style>
