package HospitalRegistrationSystem.src.Hospital;

import java.util.Map;
import java.util.Set;
import java.util.Arrays;
import java.util.HashMap;
import java.time.LocalDate;

public class Doctor extends User{
    final String USER_TYPE = "Doctor";
    private String token;
    private String age;
    private String gender;
    private String hospital;
    private String department;
    private String title;
    private String specialty;

    private static Map<LocalDate, Integer> appointments = new HashMap<>();
    private static boolean isApproved = false;

    public Doctor(String id, String name, String address, String contact) {
        super(id, name, address, contact);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public void setAppointment(LocalDate date, int appointCount){
        appointments.put(date, appointCount);
        setisNotApproved(); // 等待管理员审核
    }

    public Map<LocalDate, Integer> getAppointments() {
        return appointments;
    }

    public void setisApproved(){
        isApproved = true; // 管理员调用，设置审核通过
    }

    public static void setisNotApproved(){
        isApproved = false;
    }

    // 查看出诊信息（start起始时间，end束时间）
    public void getPeriodAppointments(LocalDate start, LocalDate end){
        if(isApproved){
            Set<LocalDate> set=appointments.keySet();
            Object[] arr=set.toArray();
            Arrays.sort(arr);
            for(Object key:arr){
                LocalDate appointTime = (LocalDate)key;
                if(appointTime.compareTo(start) >= 0 && appointTime.compareTo(end) <= 0){
                    System.out.println(key+": "+ appointments.get(key));
                }
            }
        }else{
            System.out.println("The review is still not passed, and the house call information cannot be viewed");
        }
    }

    public void getAppointments(LocalDate date){
        if(isApproved){
            System.out.println(date+": "+ appointments.get(date));
        }else{
            System.out.println("The review is still not passed, and the house call information cannot be viewed");
        }    
    }
}
