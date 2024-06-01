package HospitalRegistrationSystem.src.Hospital;

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
}