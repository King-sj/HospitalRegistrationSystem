import { User } from '@/components/LoginSystem'
export class UserInfoChange {
  public username:string="";
  public Date: string="";
  public oldUserInfo: User=new User();
  public newUserInfo: User=new User();
  public allowed:boolean=false;
  public checker:User=new User();
  public static getRecord(userInfo:UserInfoChange):Record<string,any> {
  return {
    username:userInfo.username,
    oldUserInfo:User.getRecord(userInfo.oldUserInfo),
    newUserInfo:User.getRecord(userInfo.newUserInfo),
    date:userInfo.Date,
    checker:User.getRecord(userInfo.checker),
    allowed:userInfo.allowed
  }
 }
}