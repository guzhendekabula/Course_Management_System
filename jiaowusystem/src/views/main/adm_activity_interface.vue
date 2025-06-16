<template>
  <div>
    <h1>活动管理</h1>
    <el-row gutter="20" :justify="center" class="container">
      <!-- <el-col :span="4"> 学年： </el-col> -->
      <el-col :span="6" :offset="2">
        <!-- 选择学年 -->
        <div style="display: flex; align-items: center;">
          <span style="margin-right: 6px;">学年：</span>
          <el-select v-model="year" placeholder="Select" style="width: 240px">
            <el-option v-for="item in yearoptions" :key="item.value" :label="item.label" :value="item.value" />
          </el-select>
        </div>
      </el-col>
      <el-col :span="6">
        <!-- 选择学期 -->
        <div style="display: flex; align-items: center;">
          <span style="margin-right: 6px;">学期：</span>
          <el-select v-model="term" placeholder="Select" style="width: 240px">
            <el-option v-for="item in termoptions" :key="item.value" :label="item.label" :value="item.value" />
          </el-select>
        </div>
      </el-col>
      <el-col :span="6">
        <!-- 选择状态 -->
        <div style="display: flex; align-items: center;">
          <span style="margin-right: 6px;">状态：</span>
          <el-select v-model="status" placeholder="Select" style="width: 240px">
            <el-option v-for="item in statusoptions" :key="item.value" :label="item.label" :value="item.value" />
          </el-select>
        </div>
      </el-col>
    </el-row>
    <el-row class="container">
      <el-col :span="1" :offset="20">
        <!-- 查询按钮 -->
        <el-button :dark="isDark" @click="fetchStudentScore">查询</el-button>
      </el-col>
      <el-col :span="1">
        <!-- 重置按钮 -->
        <el-button :dark="isDark" @click="fetchStudentScore">重置</el-button>
      </el-col>
    </el-row>
    <el-row class="container" justify="center">
      <el-col :span="23">
        <el-table :data="activitytable" style="width: 100%" :header-cell-style="{ backgroundColor: 'var(--el-color-primary-light-3)', color: '#ffffff' }">
          <el-table-column prop="year" label="学年" />
          <el-table-column prop="term" label="学期" />
          <el-table-column prop="status" label="状态" />
          <el-table-column prop="s_time" label="开始时间" />
          <el-table-column prop="e_time" label="结束时间" />
          <el-table-column prop="operation" label="操作">
            <template #default="scope">
              <el-button size="small" @click="handleEdit(scope.$index, scope.row)">
                编辑
              </el-button>
              <el-button size="small" type="danger" @click="handleDelete(scope.$index, scope.row)">
                删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-col>
    </el-row>
    <el-row class="container">
      <el-col :span="4" :offset="19">
        <!-- 新增活动按钮 -->
        <el-button type="success" @click="addActivity">新增活动</el-button>
      </el-col>
    </el-row>
    <!-- 弹出框-->
    <el-dialog v-model="dialogVisible" title="添加活动" width="400px">
      <div class="detail">
        <el-form :model="form" label-width="auto" style="max-width: 600px">
          <el-form-item label="类型：">
            <el-select v-model="acttype" placeholder="Select" style="width: 220px">
              <el-option v-for="item in typeoptions" :key="item.value" :label="item.label" :value="item.value" />
            </el-select>
          </el-form-item>
          <div v-show="acttype == 1">
            <el-form-item label="轮次：">
              <el-select v-model="round" placeholder="请选择轮次" style="width: 220px">
                <el-option v-for="item in roundoptions" :key="item.value" :label="item.label" :value="item.value" />
              </el-select>
            </el-form-item>
          </div>
          <el-form-item label="开始时间：">
            <el-config-provider :locale="locale">
              <el-date-picker v-model="value1" type="datetime" placeholder="选择日期" format="YYYY-MM-DD HH:mm:ss"
                date-format="MMM DD, YYYY" time-format="HH:mm" />
            </el-config-provider>
          </el-form-item>
          <el-form-item label="结束时间：">
            <el-config-provider :locale="locale">
              <el-date-picker v-model="value2" type="datetime" placeholder="选择日期" format="YYYY-MM-DD HH:mm:ss"
                date-format="MMM DD, YYYY" time-format="HH:mm" />
            </el-config-provider>
          </el-form-item>
        </el-form>
      </div>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="dialogVisible = false">返回</el-button>
          <el-button type="primary" @click="handleaccept()">确认</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script scoped setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import zhCn from 'element-plus/es/locale/lang/zh-cn';
const year = ref('');
const term = ref('');
const status = ref('');
const activitytable = ref([]);
const value1 = ref('')
const value2 = ref('')
// eslint-disable-next-line
const router = useRouter();
const dialogVisible = ref(false);
const acttype = ref('');
const round = ref('');
const locale = zhCn;

onMounted(() => {
  activitytable.value = activityTable;
});

const activityTable = [
  {
    year: '2022',
    term: '1',
    status: '未开始',
    s_time: '2022-09-01',
    e_time: '2022-12-31',
  },
  {
    year: '2022',
    term: '2',
    status: '进行中',
    s_time: '2023-01-01',
    e_time: '2023-03-31',
  },
];
const handleEdit = (index, row) => {
  console.log(index, row);
  //router.push({ path:"adm_add_modify_activity" },);
  dialogVisible.value = true;
}
const handleDelete = (index, row) => {
  console.log(index, row)
}
const addActivity = () => {
  console.log('addActivity');
  //router.push({ path:"adm_add_modify_activity" },);
  dialogVisible.value = true;
}
const handleaccept = () => {
  console.log('hh' + acttype.value == 1);
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
const statusoptions = [
  {
    value: '1',
    label: '未开始',
  },
  {
    value: '2',
    label: '进行中',
  },
  {
    value: '3',
    label: '已结束',
  },
];
const typeoptions = [
  {
    value: '1',
    label: '选课',
  },
  {
    value: '2',
    label: '评教',
  },
]
const roundoptions = [
  {
    value: '1',
    label: '第一轮',
  },
  {
    value: '2',
    label: '第二轮',
  },
  {
    value: '3',
    label: '第三轮',
  },
]
</script>

<style scoped>

.text-left {
  text-align: left;
}

.container {
  margin-bottom: 30px;
}

.overall {
  margin-bottom: 90px;
}
</style>
