<script lang="ts" setup>
import { useApiStore, AttendanceInformation } from "@/apis";
import { User, useUserStore } from "@/components/LoginSystem";
import { ElMessage } from "element-plus";
import { ref, onMounted } from "vue";
import AttendanceCard from "@/components/AttendanceInfoCard.vue";
const api = useApiStore();
const user = useUserStore().userStorage;
const infos = ref();
const update = async () => {
  var req = new AttendanceInformation();
  infos.value = await api.getAttendanceInformation(req);
};
const agree = async (req:AttendanceInformation,agr:boolean) => {
  req.pass = agr;
  req.checker = new User();
  req.checker.email = user.email;
  await api.checkAttendanceInfo(req)
  update()
}
onMounted(async () => {
  update();
});
</script>
<template>
  <div>
    <div v-for="item in infos" :key="item.id" class="check-view">
    <div v-if="item.checked == false">
      {{ item }}
      <p>账号:  {{item.doctorUsername}}</p>
      <AttendanceCard
        v-model:num="item.maxCnt"
        :attendance-time="[item.startTime, item.endTime]"
        readonly
      />
      <n-flex>
        <el-button type="primary" @click="agree(item,false)">驳回</el-button>
        <el-button type="primary" @click="agree(item,true)">通过</el-button>
      </n-flex>
    </div>
    </div>
  </div>
</template>
<style lang='scss' scoped>
.check-view{
  margin-bottom: 2px;
}
</style>