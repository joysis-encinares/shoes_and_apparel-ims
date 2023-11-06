package app.models;

public class User {
    
    private int id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String email;
    private String contact;
    private int yearOfBirth;    
    private int dayOfBirth;
    private String monthOfBirth;
    private int month;
    private int age;
    private String gender;
    private int role;
    private String createAt;
    private int logId;
    
    // Setter
    public void setId(int id){
        this.id = id;
    }
    
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
    
    public void setContact(String contact){
        this.contact = contact;
    }
    
    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public void setDayOfBirth(int dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public void setMonthOfBirth(String monthOfBirth) {
        this.monthOfBirth = monthOfBirth;
    }
    
    public void setAge(int age){
        this.age = age;
    }
    
    public void setGender(String gender){
        this.gender = gender;
    }
    
    public void setRole(int role){
        this.role = role;
    }
    
    public void setCreatedAt(String createAt){
        this.createAt = createAt;
    }
    
    public void setMonth(int month){
        this.month = month;
    }
    
    public void setLogId(int logId){
        this.logId = logId;
    }

    // Getter
    public int getId(){return id;}
    
    public String getFirstName(){return firstName;}
        
    public String getLastName(){return lastName;}
    
    public String getUserName(){return userName;}

    public String getPassword(){return password;}

    public String getEmail(){return email;}
    
    public String getContact(){return contact;}

    public int getAge(){return age;}

    public String getGender(){return gender;}
    
    public int getYearOfBirth() { return yearOfBirth; }

    public int getDayOfBirth() { return dayOfBirth; }
    
    public String getMonthOfBirth() { return monthOfBirth; }
    
    public int getRole(){return role;}
    
    public String getCreatedAt(){return createAt;}
    
    public int getMonth(){return month;}
    
    public int getLogId(){return logId;}

}

