public class User {
    public String id;
    public String name;
    public String address;
    public String contact;
    
    public User(String id, String name, String address, String contact) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact = contact;
    }
    
    public void setId(String id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setContact(String contact){
        this.contact = contact;
    }

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getAddress(){
        return address;
    }

    public String getContact(){
        return contact;
    }
}
