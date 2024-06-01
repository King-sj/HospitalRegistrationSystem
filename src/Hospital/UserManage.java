package HospitalRegistrationSystem.src.Hospital;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalDate;

public class UserManage {
    // 模拟数据库存储用户信息，真正的应用中应该是从数据库中查询，届时修改此部分代码
    private static Map<String, Admin> admins = new HashMap<>();
    private static Map<String, Doctor> doctors = new HashMap<>();
    private static Map<String, Patient> patients = new HashMap<>();
    private static Map<String, String> modifyNews = new HashMap<>();
    private static Map<String, String> delednames = new HashMap<>();
    private static Map<String, Map<LocalDate, Integer>> attendances = new HashMap<>();

    public void approveRegistration(User user, boolean isApproved) {
        if (user instanceof Doctor) {
            if(isApproved){
                Doctor doctor = (Doctor) user;
                doctors.put(doctor.getName(), doctor); 
            }else{
                System.out.println("Doctor registration rejected!");
            }
            // 审批医生
        } else if (user instanceof Patient) {
            if(isApproved){
                Patient patient = (Patient) user;
                patients.put(patient.getName(), patient);
            }else{
                System.out.println("Patient registration rejected!");
            }
            // 审批患者
        } else if (user instanceof Admin) {
            if(isApproved){
                Admin admin = (Admin) user;
                admins.put(admin.getName(), admin); 
            }else{
                System.out.println("Admin registration rejected!");
            }
            // 审批管理员
        }
    }

    public static void modifyAdmin(String name, String type, String modifyString){
        modifyNews.put("Admin : " + name, "modify" + type + ":" + modifyString);
        Admin user = admins.get(name);
        switch(type){
            case "name":
                user.setName(modifyString);
                break;
            case "id":
                user.setId(modifyString);
                break;
            case "address":
                user.setAddress(modifyString);
                break;
            case "contact":
                user.setContact(modifyString);
                break;
            default:
                System.out.println("Error!");
                break;
        }
    }

    public static void modifyDoctor(String name, String type, String modifyString){
        modifyNews.put("Doctor : " + name, "modify" + type + ":" + modifyString);
        Doctor user = doctors.get(name);
        switch(type){
            case "name":
                user.setName(modifyString);
                break;
            case "id":
                user.setId(modifyString);
                break;
            case "address":
                user.setAddress(modifyString);
                break;
            case "contact":
                user.setContact(modifyString);
                break;
            case "age":
                user.setAge(modifyString);
                break;
            case "gender":
                user.setGender(modifyString);
                break;
            case "hospital":
                user.setHospital(modifyString);
                break;
            case "department":
                user.setDepartment(modifyString);
                break;
            case "title":
                user.setTitle(modifyString);
                break;
            case "specialty":
                user.setSpecialty(modifyString);
                break;
            default:
                System.out.println("Error!");
                break;
        }
    }

    public static void modifyPatient(String name, String type, String modifyString){
        modifyNews.put("Patient : " + name, "modify" + type + ":" + modifyString);
        Patient user = patients.get(name);
        switch(type){
            case "name":
                user.setName(modifyString);
                break;
            case "id":
                user.setId(modifyString);
                break;
            case "address":
                user.setAddress(modifyString);
                break;
            case "contact":
                user.setContact(modifyString);
                break;
            case "age":
                user.setAge(modifyString);
                break;
            case "gender":
                user.setGender(modifyString);
                break;
            case "medicalRecord":
                user.setMedicalRecord(modifyString);
                break;
            default:
                System.out.println("Error!");
                break;
        }
    }

    public static void removeAdmin(String name) {
        delednames.put(name, admins.get(name).USER_TYPE);
        admins.remove(name);
    }

    //审核出诊信息
    public void auditAppointment(Doctor doctor, boolean isApproved){
        if(isApproved){
            attendances.put(doctor.getDepartment() +": "+ doctor.getName(), doctor.getAppointments());
            doctor.setisApproved();
        }else{
            System.out.println("The review is not passed");
        }
    }

    public static Map<String, Map<LocalDate, Integer>> publishAppointment(){
        return attendances;
    }

    public void printAppointment(){
        attendances.forEach((name, innerMap) -> {
            System.out.println("Name: " + name);
            System.out.println("Attendance:");
            innerMap.forEach((date, attendance) -> {
                System.out.println(date + ": " + attendance);
            });
        });
    }

}