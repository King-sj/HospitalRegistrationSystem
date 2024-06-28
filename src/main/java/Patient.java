import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;

public class Patient extends User {
    final String USER_TYPE = "Patient";
    private String token;
    private String age;
    private String gender;
    private String medicalRecord;

    public Patient(String id, String name, String address, String contact){
        super(id, name, address, contact);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token){
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

    public String getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(String medicalRecord) {
        this.medicalRecord = medicalRecord;
    }


    // 预约挂号（三天内）
    public void appointDoctor(String doctorDepartment, String doctorName){
        Map<String, Map<LocalDate, Integer>> attendances = UserManage.publishAppointment();
        Map<LocalDate, Integer> appointment = attendances.get(doctorDepartment + ": " + doctorName);
        LocalDate date = LocalDate.now();
        Scanner scanner = new Scanner(System.in);
        for(int i = 1; i <= 3; i++){
            String currentDate = date.plusDays(i).toString();
            if(appointment.get(date.plusDays(i)) == null){
                break;
            }
            if(appointment.get(date.plusDays(i)) > 0){
                System.out.println("Doctor " + doctorName + " is available on " + currentDate + ". Do you want to make an appointment? (yes/no)");
                String userInput = scanner.next();

                if ("yes".equalsIgnoreCase(userInput)) {
                    System.out.println("Doctor " + doctorName + " is available on " + currentDate + ". Do you want to pay? (yes/no)");
                    String userInputToPay = scanner.next();
                    if("yes".equalsIgnoreCase(userInputToPay)){
                        appointment.put(date.plusDays(i), appointment.get(date.plusDays(i)) - 1);
                        System.out.println("Appointment confirmed for " + currentDate + " with doctor " + doctorName);
                    }else{
                        System.out.println("Appointment cancelled.");
                    }  
                } else {
                    System.out.println("No appointment made for " + currentDate + " with doctor " + doctorName);
                }
            }
        }
        scanner.close();
    }
}