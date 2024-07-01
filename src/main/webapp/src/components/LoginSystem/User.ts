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
  public Hospital:string="";
  public department:string="";
  public title:string="";
  public specialty:string="";
  public constructor(email:string = "", password:string="", token:string="",expiration:number=0) {
    this.email = email;
    this.password = password;
    this.expiration = expiration;
    this.token = token;
  }
  public static getRecord(user:User):Record<string,any> {
    return {
      // id: undefined,
      type: user.type,
      username: user.email,
      password: user.password,
      identity: user.identity,
      name: user.name,
      age: user.age,
      email:user.email,
      phone: user.phone,
      address: user.address,
      gender: user.gender,
      Hospital: user.Hospital,
      department: user.department,
      title: user.title,
      specialty:user.specialty,
    }
  }
}