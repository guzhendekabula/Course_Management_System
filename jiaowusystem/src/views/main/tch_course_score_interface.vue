<!--教学班成绩（老师）-->
<template>
  <el-container>
    <el-header>
      <img src="@/assets/2.jpg" alt="Icon" class="badge">
      <h3 style="margin-right: auto;">某某学校教师端——教学班成绩页面</h3>
      <div class="user-info">
        <h3>账号：{{ user_account }}</h3>
        <h3>ID: {{ user_id }}</h3>
      </div>
      <el-button type="danger" style="float: right;" @click="goToTeacherHome">返回</el-button>
    </el-header>
    <el-main>
      <div>
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
        <el-row gutter="20" :justify="center" class="container">
          <!-- <el-col :span="4"> 学年： </el-col> -->
          <el-col :span="5" :offset="2">
            <!-- 选择学年 -->
            <div style="display: flex; align-items: center;">
              <span style="margin-right: 6px;">学年：</span>
              <el-select v-model="year" placeholder="Select" style="width: 240px">
                <el-option v-for="item in yearoptions" :key="item.value" :label="item.label" :value="item.value" />
              </el-select>
            </div>
          </el-col>
          <el-col :span="5">
            <!-- 选择学期 -->
            <div style="display: flex; align-items: center;">
              <span style="margin-right: 6px;">学期：</span>
              <el-select v-model="term" placeholder="Select" style="width: 240px">
                <el-option v-for="item in termoptions" :key="item.value" :label="item.label" :value="item.value" />
              </el-select>
            </div>
          </el-col>
          <el-col :span="5">
            <!-- 输入课程名 -->
            <div style="display: flex; align-items: center;">
              <span style="margin-right: 6px;">课程名：</span>
              <el-input v-model="input" style="width: 240px" placeholder="请输入课程名" />
            </div>
          </el-col>
          <el-col :span="4" :offset="2">
            <!-- 查询按钮 -->
            <el-button :dark="isDark" @click="fetchStudentScore">查询</el-button>
          </el-col>
        </el-row>
        <el-row class="container" justify="center">
          <el-col :span="23">
            <!-- 表格 -->
            <el-table :data="studentScore"  stripe style="width: 100%" :header-cell-style="{ backgroundColor: 'var(--el-color-primary-light-3)', color: '#ffffff' }">
              <el-table-column prop="number" label="教学班编号" />
              <el-table-column prop="tch" label="教师" />
              <el-table-column prop="Coursename" label="课程名" />
              <el-table-column prop="year" label="学年" />
              <el-table-column prop="term" label="学期" />
              <el-table-column prop="avg_score" label="平均成绩" />
              <el-table-column prop="operation" label="操作" :width="100">
                <template #default="scope">
                  <el-button size="small" @click="handleEdit(scope.$index, scope.row)">
                    详情
                  </el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-col>
        </el-row>
        <!-- 弹出对话框 -->
        <el-dialog v-model="dialogVisible" title="成绩详情" width="1000" @open="open()" @closed="destorychart()">
          <div class="detail">
            <!-- 数据 -->
            <div class="left">
              <el-row class="container">
                <el-col>
                  <el-text>教学班编号</el-text>
                </el-col>
              </el-row>
              <el-row class="container">
                <el-col>
                  <el-text>班主任</el-text>
                </el-col>
              </el-row>
              <el-row class="container">
                <el-col>
                  <el-text>专业</el-text>
                </el-col>
              </el-row>
              <el-row class="container">
                <el-col>
                  <el-text>课程名</el-text>
                </el-col>
              </el-row>
              <el-row class="container">
                <el-col>
                  <el-text>平均分</el-text>
                </el-col>
              </el-row>
              <el-row class="container">
                <el-col>
                  <el-text>最高分</el-text>
                </el-col>
              </el-row>
              <el-row class="container">
                <el-col>
                  <el-text>最低分</el-text>
                </el-col>
              </el-row>
              <el-row class="container">
                <el-col>
                  <el-text>极值</el-text>
                </el-col>
              </el-row>
              <el-row class="container">
                <el-col>
                  <el-text>及格率</el-text>
                </el-col>
              </el-row>
            </div>
            <div class="right">
              <!--饼状图and环形图-->
              <div class="graph-pie-warp">
                <el-row>
                  <el-col :span="24">
                    <div id="home_gathering_source" style="height: 300px; margin:30px 10px; padding: 0px;"></div>
                  </el-col>
                </el-row>
              </div>
            </div>
          </div>

          <template #footer>
            <div class="dialog-footer">
              <!-- 返回按钮 -->
              <el-button type="primary" @click="dialogVisible = false">返回</el-button>
            </div>
          </template>
        </el-dialog>
      </div>
    </el-main>
  </el-container>

</template>

<script scope setup>
// eslint-disable-next-line
import { ref, onMounted, nextTick } from 'vue';
import { ElTable, ElTableColumn, ElButton, ElDialog } from 'element-plus';
import { useRouter } from 'vue-router';
import * as ECharts from "echarts";

const router = useRouter();
const dialogVisible = ref(false);
const chartinstance = ref(null);
onMounted(() => {
  studentScore.value = studentscore;
});
const studentScore = ref([]);
const studentscore = [
  {
    number: "1",
    tch: "张三",
    Coursename: "数学",
    year: "2023",
    term: "1",
    avg_score: "90"
  },
  {
    number: "2",
    tch: "李四",
    Coursename: "英语",
    year: "2023",
    term: "1",
    avg_score: "85"
  },
];
const goToTeacherHome = async () => {
  router.go(-1);
}
const handleEdit = (index, row) => {
  console.log(index, row);
  console.log(router);
  dialogVisible.value = true; // 打开对话框
}
const gatheringSourceOption = {
  title: {
    text: '成绩占比:',
    left: 'left'
  },
  tooltip: {
    trigger: 'item',
    formatter: '{a} <br/>{b} : {c} ({d}%)'
  },
  legend: {
    type: 'scroll',
    orient: 'vertical',
    right: 0,
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
      name: '最近7日占比',
      type: 'pie',
      radius: '70%',
      center: ['25%', '55%'],
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
  chartinstance.value = ECharts.init(document.getElementById(domId), theme);
  chartinstance.value.setOption(opt)
  window.addEventListener('resize', () => {
    chartinstance.value.resize()
  })
}
const getProportionData = async () => {
  var sourceList = [
    { value: 1, name: '苹果' },
    { value: 2, name: '草莓' },
    { value: 2, name: '西瓜' },
    { value: 3, name: '桔子' },
    { value: 2, name: '葡萄' },
    { value: 4, name: '柿子' }
  ]
  gatheringSourceOption.series[0].data = sourceList
  eChartsInit('home_gathering_source', 'light', gatheringSourceOption)
}
const open = async () => {
  nextTick(() => {
    getProportionData();
  })
}
const destorychart = async () => {
  chartinstance.value.dispose();
  chartinstance.value = null;
}
const yearoptions = [
  {
    value: '2022',
    label: '2022',
  },
  {
    value: '2023',
    label: '2023',
  },
];
const termoptions = [
  {
    value: '1',
    label: '第一学期',
  },
  {
    value: '2',
    label: '第二学期',
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

.detail {
  display: flex;
  /* 启用 Flexbox 布局 */
  justify-content: flex-start;
  /* 使左右两边有间隔 */
  align-items: center;
  /* 垂直居中对齐 */
  height: 400px;
  /* 设置容器的高度，确保 ECharts 图表有空间展示 */
}

.left {
  width: 40%;
  text-align: left;
  /* 左侧文字区域占容器的 40% */
  padding: 20px;
}

.right {
  width: 55%;
  /* 右侧图表区域占容器的 55% */
  height: 100%;
  /* 确保 ECharts 图表容器有足够高度 */
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
</style>

