export type UserType = "User" | "Administrator"  | "Patient" | "Doctor";
export type Gender = "Male" | "Female" | "Other";
export class User {
  public email:string;
  public password:string;
  public expiration:number;
  public token:string;
  public type:UserType = "User";
  public identity:string="";  // 身份证号
  public name:string="";  // 姓名
  public age:Number=18;  // 年龄
  public phone:string='';  // 联系方式
  public address:string="";  // 住址
  public gender:Gender="Other";  // 性别
  public constructor(email:string = "", password:string="", token:string="",expiration:number=0) {
    this.email = email;
    this.password = password;
    this.expiration = expiration;
    this.token = token;
  }
}