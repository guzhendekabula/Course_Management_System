<!--课表（老师，学生）-->
<template>
  <el-card class="box-card" shadow="hover">
    <span>
      第
      <el-select @change="changeValue" v-model="value" placeholder="1" class="m-2" size="small">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          />
        </el-select>
        周
    </span>
  
      <el-divider />
        <el-table :data="timetable" :span-method="objectSpanMethod" border
                  :header-cell-style="{background:'#d9e5fd', color:'black', fontWeight: 1000}"
                  :cell-style="tableCellStyle"
           style="overflow-x:hidden"
        >
          <el-table-column prop="jc" label="节次" width="120" align="center">
          </el-table-column>
          <el-table-column prop="sjd" label="时间段" width="120" align="center">
          </el-table-column>
          <el-table-column prop="mon" label="星期一" align="center">
            <template #default="scope">
              <h4>{{ scope.row.mon.lessonName }}</h4>
              <p>{{ scope.row.mon.teacherName }}</p>
              <div v-html="scope.row.mon.place"></div>
            </template>
          </el-table-column>
          <el-table-column prop="tue" label="星期二" align="center">
            <template #default="scope">
              <h4>{{ scope.row.tue.lessonName }}</h4>
              <p>{{ scope.row.tue.teacherName }}</p>
              <div v-html="scope.row.tue.place"></div>
            </template>
          </el-table-column>
          <el-table-column prop="wed" label="星期三" align="center">
            <template #default="scope">
              <h4>{{ scope.row.wed.lessonName }}</h4>
              <p>{{ scope.row.wed.teacherName }}</p>
              <div v-html="scope.row.wed.place"></div>
            </template>
          </el-table-column>
          <el-table-column prop="thu" label="星期四" align="center">
            <template #default="scope">
              <h4>{{ scope.row.thu.lessonName }}</h4>
              <p>{{ scope.row.thu.teacherName }}</p>
              <div v-html="scope.row.thu.place"></div>
            </template>
          </el-table-column>
          <el-table-column prop="fri" label="星期五" align="center">
            <template #default="scope">
              <h4>{{ scope.row.fri.lessonName }}</h4>
              <p>{{ scope.row.fri.teacherName }}</p>
              <div v-html="scope.row.fri.place"></div>
            </template>
          </el-table-column>
          <el-table-column prop="sat" label="星期六" align="center">
            <template #default="scope">
              <h4>{{ scope.row.sat.lessonName }}</h4>
              <p>{{ scope.row.sat.teacherName }}</p>
              <div v-html="scope.row.sat.place"></div>
            </template>
          </el-table-column>
          <el-table-column prop="sun" label="星期日" align="center">
            <template #default="scope">
              <h4>{{ scope.row.sun.lessonName }}</h4>
              <p>{{ scope.row.sun.teacherName }}</p>
              <div v-html="scope.row.sun.place"></div>
            </template>
          </el-table-column>
        </el-table>
    </el-card>
  </template>

<script>

import axios from 'axios';
import { ElMessage } from 'element-plus';

export default {
  name: 'ClassSchedule',
  data() {
    return {
      value: 1, // 当前选择的周数
      options: [], // 周数选择项
      timetable: [], // 表格数据
      studentId: "123456",
      tableCellStyle: { 'padding': '10px' }, // 表格单元格样式
      userType: 2
    };
  },
  methods: {
    // 生成周数选择项
    generateWeekOptions(totalWeeks = 18) {
      const opts = [];
      for (let i = 1; i <= totalWeeks; i++) {
        opts.push({ value: i, label: `${i}` });
      }
      this.options = opts;
    },
    // 处理选择周数变化
    // eslint-disable-next-line
    changeValue(newWeek) {
      this.fetchTimetable();
    },
    // 获取课表数据
    async fetchTimetable() {
      const stuid = this.studentId;
      let stutype = this.userType;
      try {
        const response = await axios.post('http://localhost:8080/classtable', {
          username: stuid,
          usertype: stutype
        });

        if (response.status === 200) {
          this.processTimetable(response.data.classtabResp);
        } else {
          ElMessage.error('无法获取课表数据');
        }
      } catch (error) {
        console.error(error);
        ElMessage.error('获取课表数据失败');
      }
    },
    // 处理后端返回的数据，转换为表格需要的格式
    processTimetable(classtabResp) {
      // 初始化一个对象，用于存储每节课的信息
      const timetableMap = {};

      classtabResp.forEach(course => {
        // eslint-disable-next-line
        const { cou_name, cla_timeset, cla_weekset, cla_classroom, tch_name } = course;
        
        // 解析 cla_timeset
        const timeEntries = cla_timeset.split(',').map(Number);

        // 解析 cla_weekset
        const weeks = cla_weekset.split(',').map(Number);

        // 检查当前周是否在cla_weekset中
        if (!weeks.includes(this.value)) {
          return; // 当前周不在上课周数中，跳过
        }

        timeEntries.forEach(time => {
          const day = Math.floor(time / 10); // 十位表示星期几
          const period = time % 10; // 个位表示第几节课

          // 初始化对应的节次信息
          if (!timetableMap[period]) {
            timetableMap[period] = {
              jc: `第 ${period} 节`,
              sjd: this.getTimeRange(period),
              mon: { lessonName: '', place: '' },
              tue: { lessonName: '', place: '' },
              wed: { lessonName: '', place: '' },
              thu: { lessonName: '', place: '' },
              fri: { lessonName: '', place: '' },
              sat: { lessonName: '', place: '' },
              sun: { lessonName: '', place: '' },
            };
          }

          // 根据 day 填充对应星期的信息
          const dayKey = this.getDayKey(day);
          if (dayKey) {
            timetableMap[period][dayKey].lessonName = cou_name;
            timetableMap[period][dayKey].place = cla_classroom;
            timetableMap[period][dayKey].teacherName = tch_name;
          }
        });
      });

      // 转换为数组并排序
      const timetableArray = Object.values(timetableMap).sort((a, b) => {
        // 提取节次数字进行排序
        const aPeriod = parseInt(a.jc.replace('第 ', '').replace(' 节', ''), 10);
        const bPeriod = parseInt(b.jc.replace('第 ', '').replace(' 节', ''), 10);
        return aPeriod - bPeriod;
      });

      this.timetable = timetableArray;
    },
    // 根据节次返回时间段
    getTimeRange(period) {
      // 示例时间段，请根据实际情况调整
      const timeMap = {
        1: '08:00 - 08:45',
        2: '08:55 - 09:40',
        3: '10:00 - 10:45',
        4: '10:55 - 11:40',
        5: '14:00 - 14:45',
        6: '14:55 - 15:40',
        7: '16:00 - 16:45',
        8: '16:55 - 17:40',
        9: '18:00 - 18:45',
        10: '18:55 - 19:40',
        11: '20:00 - 20:45',
        12: '20:55 - 21:40',
      };
      return timeMap[period] || '';
    },
    // 根据数字返回星期对应的键名
    getDayKey(dayNumber) {
      const mapping = {
        1: 'mon',
        2: 'tue',
        3: 'wed',
        4: 'thu',
        5: 'fri',
        6: 'sat',
        7: 'sun',
      };
      return mapping[dayNumber] || null;
    },
    // 定义表格的跨行/列方法（如需要）
    // eslint-disable-next-line
    objectSpanMethod({ row, column, rowIndex, columnIndex }) {
      // 对于非星期列，不进行合并
      if (!['mon', 'tue', 'wed', 'thu', 'fri', 'sat', 'sun'].includes(column.property)) {
        return [1, 1];
      }

      const day = column.property; // 例如 'mon', 'tue', etc.
      const currentLesson = row[day].lessonName;

      if (!currentLesson) {
        return [1, 1];
      }

      // 如果是第一行，检查后续行是否有相同课程
      if (rowIndex === 0) {
        let rowspan = 1;
        for (let i = rowIndex + 1; i < this.timetable.length; i++) {
          if (this.timetable[i][day].lessonName === currentLesson &&
              this.timetable[i][day].teacherName === row[day].teacherName &&
              this.timetable[i][day].place === row[day].place) {
            rowspan++;
          } else {
            break;
          }
        }
        return [rowspan, 1];
      } else {
        const prevLesson = this.timetable[rowIndex - 1][day].lessonName;
        const prevTeacher = this.timetable[rowIndex - 1][day].teacherName;
        const prevPlace = this.timetable[rowIndex - 1][day].place;

        if (currentLesson === prevLesson && 
            row[day].teacherName === prevTeacher &&
            row[day].place === prevPlace) {
          // 合并到上面的单元格
          return [0, 0];
        } else {
          // 检查后续行是否有相同课程
          let rowspan = 1;
          for (let i = rowIndex + 1; i < this.timetable.length; i++) {
            if (this.timetable[i][day].lessonName === currentLesson &&
                this.timetable[i][day].teacherName === row[day].teacherName &&
                this.timetable[i][day].place === row[day].place) {
              rowspan++;
            } else {
              break;
            }
          }
          return [rowspan, 1];
        }
      }
    },
  },
  mounted() {
    this.generateWeekOptions();
    this.fetchTimetable();
  },
};
</script>



<style scoped>
	.box-card{
    margin-top: 100px;
		display: flex;
		height: 70%;
		width: 98%;
		position: relative;
		right: 1%;
		bottom: 6%;
		flex-direction: column;
		align-items:center;
	}
	.box-card /deep/ .el-card__body{
		height: 100%;
		width: 100%;
		display: flex;
		flex-direction: column;
		justify-content: space-evenly;
		margin: 0px;
		border: 0px;
		padding: 0px;
    max-height: 90vh;
	}
	.el-select {
		width: 50px;
		}
</style>
