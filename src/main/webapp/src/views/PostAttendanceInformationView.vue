<script lang="ts" setup>
import { useApiStore, AttendanceInformation } from "@/apis";
import { useUserStore } from "@/components/LoginSystem";
import { ElMessage } from "element-plus";
import { ref, onMounted } from "vue";
import AttendanceCard from "@/components/AttendanceInfoCard.vue";
const api = useApiStore();
const user = useUserStore().userStorage;
const infos = ref();
const attendanceTime = ref([]);
const selectMode = ref();
const num = ref(5);
const update = async () => {
  var req = new AttendanceInformation();
  req.doctorUsername = user.email; // 使用email作为唯一用户名
  const res  = await api.getAttendanceInformation(req);
  infos.value = res.sort((a:any, b:any) => {
    return new Date(b.endTime).getTime() - new Date(a.endTime).getTime()
  })
};
onMounted(async () => {
  update();
});
const post = async () => {
  // TODO 添加对时间合法性的检验
  selectMode.value = "";
  var info = new AttendanceInformation();
  info.doctorUsername = user.email; // 使用email作为唯一用户名
  info.maxCnt = num.value;
  info.startTime = attendanceTime.value[0];
  info.endTime = attendanceTime.value[1];
  await api.postAttendanceInformation(info);
  ElMessage.info("已发布");
  update();
}
</script>
<template>
  <div>
    <el-collapse v-model="selectMode" accordion>
      <el-collapse-item title="发布出诊信息" name="1">
        <div>
          <AttendanceCard v-model:num="num" v-model:attendance-time="attendanceTime" />
          <el-button type="primary" @click="post" style="margin-left: 85%"
            >发布</el-button
          >
        </div>
      </el-collapse-item>
      <el-collapse-item title="查看出诊信息" name="2">
        <div v-for="item in infos" :key="item.id" class="infos-view">
          <div style="color: red;">
            <p v-if="new Date(item.endTime).getTime() < new Date().getTime()">
              已结束
            </p>
            <p v-else-if="item.checked == true">
              {{item.pass == true ? (item.cnt == item.maxCnt ?  "已报满":"通过") : "未通过"}}
            </p>
            <p v-else>未审核</p>
          </div>

          <AttendanceCard
            v-model:num="item.cnt"
            :attendance-time="[item.startTime, item.endTime]"
            readonly
          />
          <div v-for="patient in item.patients" :key="patient.username"
          class="patient-view">
            <p>
              患者姓名:{{ patient.name }}
            </p>
            <p>患者联系方式:{{ patient.email }}</p>
          </div>
        </div>
      </el-collapse-item>
    </el-collapse>
  </div>
</template>
<style lang='scss' scoped>
.infos-view,.patient-view{
  border: 1px solid #000;
  margin-bottom: 2px;
}
</style>