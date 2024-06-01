package HospitalRegistrationSystem.src.Hospital;

public class Admin extends User{
    final String USER_TYPE = "Admin";
    private String token = "admin";
    
    public Admin(String id, String name, String address, String contact){
        super(id, name, address, contact);
    }

    public String getToken(){
        return this.token;
    }

    public void setToken(String token){
        this.token = token;
    }
}
