package app.models;

public class User {
    
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String email;
    private String birthday;
    private int age;
    private int role;
    
    // Setter
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
        
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    
    public void setUserName(String userName){
        this.userName = userName;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public void setEmail(String email){
        this.email = email;
    }

    public void setAge(int age){
        this.age = age;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    
    public void setRole(int role){
        this.role = role;
    }
    
    // Getter
    public String getFirstName(){return firstName;}
        
    public String getLastName(){return lastName;}
    
    public String getUserName(){return userName;}

    public String getPassword(){return password;}

    public String getEmail(){return email;}

    public int getAge(){return age;}

    public String getBirthday() {return birthday;}
    
    public int getRole(){return role;}
    
}

