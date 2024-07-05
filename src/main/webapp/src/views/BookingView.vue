<script lang="ts" setup>
import { useApiStore, AttendanceInformation } from "@/apis";
import { useUserStore } from "@/components/LoginSystem";
import { ElMessage } from "element-plus";
import { ref, onMounted, type Ref } from "vue";
import AttendanceCard from "@/components/AttendanceInfoCard.vue";
import Pay from "@/components/Pay.vue";
const api = useApiStore();
const user = useUserStore().userStorage;
const infos = ref();
const date = ref("");
const department = ref("");
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
const shortcuts = [
  {
    text: "Tomorrow",
    value: () => {
      const date = new Date();
      date.setDate(date.getDate() + 1);
      return date;
    },
  },
  {
    text: "a day after Tomorrow",
    value: () => {
      const date = new Date();
      date.setDate(date.getDate() + 2);
      return date;
    },
  },
  {
    text: "3 days later",
    value: () => {
      const date = new Date();
      date.setDate(date.getDate() + 3);
      return date;
    },
  },
];
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
  payVisible.value = true
  update
}
const booked = (item:AttendanceInformation)=> {
  for (var x of medicalHistory.value) {
    if (x.id == item.id) {
      return true;
    }
  }
  return false;
}
const payVisible = ref(false)
</script>
<template>
  <div class="search-info-view">
    <n-flex>
      <el-date-picker
        v-model="date"
        type="date"
        placeholder="Pick a day"
        :disabled-date="disabledDate"
        :shortcuts="shortcuts"
        format="YYYY-MM-DD"
        value-format="YYYY-MM-DD"
        date-format="YYYY-MM-DD ddd"
      />
      <el-input v-model="department">
        <template #prefix>科室</template>
      </el-input>
      <el-input v-model="name">
        <template #prefix>专家姓名</template>
      </el-input>
    </n-flex>
  </div>
  <div>
    <div v-for="item in infos" :key="item.id">
      <div
        v-if="
          item.checked == true &&
          item.pass == true &&
          !disabledDate(new Date(item.endTime)) &&
          item.doctor.name.includes(name)
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
        <Pay v-model:visible="payVisible" id="payView"></Pay>
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
#payView{
  width:200px;
  background:red;
}
</style>
