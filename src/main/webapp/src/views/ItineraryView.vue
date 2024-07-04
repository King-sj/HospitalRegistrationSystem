<script lang="ts" setup>
import { useApiStore, AttendanceInformation } from "@/apis";
import { useUserStore } from "@/components/LoginSystem";
import { ElMessage } from "element-plus";
import { ref, onMounted, type Ref } from "vue";
import AttendanceCard from "@/components/AttendanceInfoCard.vue";
const api = useApiStore();
const user = useUserStore().userStorage;
const infos = ref();
const name = ref("");
const medicalHistory:Ref<any[]> = ref([])
const update = async () => {
  var req = new AttendanceInformation();
  // req.doctorUsername = user.email; // 使用email作为唯一用户名
  const res = await api.getAttendanceInformation(req);
  infos.value = res.sort((a: any, b: any) => {
    return new Date(b.endTime).getTime() - new Date(a.endTime).getTime();
  });
  medicalHistory.value = await api.getMedicalRecord(user);
};
onMounted(async () => {
  update();
});
const disabledDate = (time: Date) => {
  const now = new Date();
  const threeDaysLater = new Date(now.getTime() + 3 * 24 * 60 * 60 * 1000);
  return time.getTime() < now.getTime() || time.getTime() > threeDaysLater.getTime();
};
const booking = async (req:AttendanceInformation) => {
  req.patients = [user]
  console.log("dd", req)
  const res =await api.bookDoctor(req);
  ElMessage.info("派对中，等通知...")
}
const booked = (item:AttendanceInformation)=> {
  for (var x of medicalHistory.value) {
    if (x.id == item.id) {
      return true;
    }
  }
  return false;
}
</script>
<template>
  <div>
    <div v-for="item in infos" :key="item.id">
      <div
        v-if="
          item.checked == true &&
          item.pass == true &&
          !disabledDate(new Date(item.endTime)) &&
          item.doctor.name.includes(name) &&
          booked(item)
        "
        class="infos-view"
        :key="item.id"
      >
        <div style="color: red">
          <p v-if="new Date(item.endTime).getTime() < new Date().getTime()">已结束</p>
        </div>
        <div class="basic-info" :key="item.id">
        <p>名字: {{item.doctor.name}}</p>
        <p>email: {{item.doctor.email}}</p>
        <p>科室: {{item.doctor.department}}</p>
        <p>职称: {{item.doctor.title}}</p>
        <p>专长: {{item.doctor.specialty}}</p>
        <p>最大挂号数量: {{item.maxCnt}}</p>
        </div>
        <AttendanceCard
          v-model:num="item.cnt"
          :attendance-time="[item.startTime, item.endTime]"
          readonly
        />
        <n-flex>
          <p v-if="booked(item)">已预约</p>
          <el-button type="primary" @click="booking(item)"
            v-if="!booked(item)"
          >预约</el-button>
        </n-flex>
      </div>
    </div>
  </div>
</template>
<style lang="scss" scoped>
.search-info-view {
  .el-input {
    width: 30%;
  }
}
.infos-view {
  border: 1px solid #000;
  margin-bottom: 2px;
}
</style>
