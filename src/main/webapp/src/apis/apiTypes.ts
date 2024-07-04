import { User } from "@/components/LoginSystem";

export class AttendanceInformation {
  public id:string="";
  public doctorUsername:string="";  // username 唯一， 暂时以email来填
  public startTime:string="";
  public endTime:string="";
  public maxCnt:number=0;
  public cnt:number=0;  // 出诊次数
  public checked:boolean=false;
  public checker:User=new User();
  public pass:boolean=false;
  public constructor() {
  }
  public static getRecord(obj:AttendanceInformation):Record<string,any> {
    return {
      id:obj.id,
      doctorUsername:obj.doctorUsername,
      startTime:obj.startTime,
      endTime:obj.endTime,
      maxCnt:obj.maxCnt,
      cnt:obj.cnt,
      checked:obj.checked,
      checker:User.getRecord(obj.checker),
      pass:obj.pass,
    }
  }
}