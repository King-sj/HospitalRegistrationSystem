import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        
        
        // 添加初始管理员
        Admin initialAdmin = new Admin("admin", "admin", "admin", "admin");

        // 第一次登录修改密码
        initialAdmin.setToken("HospitalAdmin");

        // 启动用户管理系统
        UserManage userManage = new UserManage();

        // 添加医生
        Doctor doctor = new Doctor("123456789", "John Doe", "123 Main St", "555-1234");

        // 管理员审核医生注册信息
        userManage.approveRegistration(doctor, true);

        doctor.setAge("50");
        doctor.setGender("Male");
        doctor.setToken("abcdefg");
        doctor.setDepartment("tooth");
        doctor.setSpecialty("Tooth goodness");
        doctor.setAppointment(LocalDate.of(2024, 6, 2), 2);

        // 管理员审核医生出诊信息
        userManage.auditAppointment(doctor, true);

        // 医生查看自己的出诊信息  
        LocalDate startDate = LocalDate.of(2024, 6, 1);
        LocalDate endDate = LocalDate.of(2024, 6, 3);
        doctor.getPeriodAppointments(startDate, endDate);

        // 添加患者
        Patient patient = new Patient("987654321", "Jane Smith", "456 Elm St", "555-5678");
        
        // 管理员审核患者注册信息
        userManage.approveRegistration(patient, true);

        // 患者预约挂号
        patient.appointDoctor("tooth", "John Doe");

        userManage.printAppointment();
    }
}
