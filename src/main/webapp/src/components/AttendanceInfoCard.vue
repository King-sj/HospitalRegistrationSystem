<script lang='ts' setup>
const props = withDefaults(defineProps<{
  readonly:boolean
}>(),{
  readonly:false
})

const attendanceTime = defineModel<string[]>("attendanceTime",{required:true});
// const startTime = defineModel<string>("startTime",{required:true})
const num = defineModel<number>("num",{required:true});
  const shortcuts = [
  {
    text: '今天早上',
    value: () => {
      const now = new Date();
      const start = new Date(now);
      start.setHours(8, 0, 0, 0); // 设置为今天早上8点
      const end = new Date(now);
      end.setHours(12, 0, 0, 0); // 设置为今天中午12点
      return [start, end];
    },
  },
  {
    text: '今天下午',
    value: () => {
      const now = new Date();
      const start = new Date(now);
      start.setHours(13, 0, 0, 0); // 设置为今天下午1点
      const end = new Date(now);
      end.setHours(18, 0, 0, 0); // 设置为今天下午6点
      return [start, end];
    },
  },
  {
    text: '明天早上',
    value: () => {
      const now = new Date();
      const start = new Date(now);
      start.setDate(start.getDate() + 1); // 设置为明天
      start.setHours(8, 0, 0, 0); // 设置为明天早上8点
      const end = new Date(now);
      end.setDate(end.getDate() + 1); // 设置为明天
      end.setHours(12, 0, 0, 0); // 设置为明天中午12点
      return [start, end];
    },
  },
  {
    text: '明天下午',
    value: () => {
      const now = new Date();
      const start = new Date(now);
      start.setDate(start.getDate() + 1); // 设置为明天
      start.setHours(13, 0, 0, 0); // 设置为明天下午1点
      const end = new Date(now);
      end.setDate(end.getDate() + 1); // 设置为明天
      end.setHours(18, 0, 0, 0); // 设置为明天下午6点
      return [start, end];
    },
  },
  {
    text: '后天早上',
    value: () => {
      const now = new Date();
      const start = new Date(now);
      start.setDate(start.getDate() + 2);
      start.setHours(8, 0, 0, 0);
      const end = new Date(now);
      end.setDate(end.getDate() + 2);
      end.setHours(12, 0, 0, 0);
      return [start, end];
    },
  },
  {
    text: '后天下午',
    value: () => {
      const now = new Date();
      const start = new Date(now);
      start.setDate(start.getDate() + 2);
      start.setHours(13, 0, 0, 0);
      const end = new Date(now);
      end.setDate(end.getDate() + 2);
      end.setHours(18, 0, 0, 0);
      return [start, end];
    },
  },
];
</script>
<template>
  <p>attendanceTime{{attendanceTime}}</p>

  <el-card>
    <template #header>
      <el-date-picker
        v-model="attendanceTime"
        type="datetimerange"
        :shortcuts="shortcuts"
        range-separator="To"
        start-placeholder="Start date"
        end-placeholder="End date"
        format="YYYY-MM-DD HH:mm:ss"
        value-format="YYYY-MM-DD HH:mm:ss"
        date-format="YYYY-MM-DD ddd"
        time-format="HH:mm:ss"
        :readonly="props.readonly"
        :disabled="props.readonly"
      />
    </template>
    <p>出诊数量</p>
    <el-input-number v-model="num" :min="0" :max="10" :readonly="props.readonly" :disabled="props.readonly"/>
  </el-card>
</template>
