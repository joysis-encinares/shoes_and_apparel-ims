package app.controllers;

import app.configdb.DatabaseConnect;
import app.main.Main;
import app.models.User;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;  
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

interface Controllable {
    abstract String validateUsername(String name);
    abstract String validateContact(String contact);
    abstract String validateEmail(String email);
    void timeIn(int user_id);
    void birthMonth(String month);
    void birthDay(int day);
    void birthYear(int year);
    void userForm();
    void userForm(int user_id);
}

public class AuthController extends DatabaseConnect implements Controllable{
    
    public static User user = new User();
    static Main main = new Main();
    
    @Override
    public void birthMonth(String month){
        Scanner sc = new Scanner(System.in);
        final String months[] = {
                                    "January", "February", "March", "April", "May","June",
                                    "July", "August", "September", "October", "November","December" 
                                };
        try {
            System.out.print("[1-12] Birth Month: ");

            int m = sc.nextInt();
            user.setMonth(m); // get months, numeric
            user.setMonthOfBirth(months[m-1]);
            sc.nextLine();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please input valid number ranging [1-12]");
            birthMonth(month);
        } catch (java.util.InputMismatchException e) {
            System.out.println("Please input numbers only");
            sc.nextLine();
            birthMonth(month);
        } 
    }
    
    @Override
    public void birthDay(int day){
        Scanner sc = new Scanner(System.in);
        try {
            do {
                System.out.print("[1-31] Birth Day: ");
                day = sc.nextInt();
                user.setDayOfBirth(day);
            } while (day > 31 || day < 1);
            
        } catch (java.util.InputMismatchException e) {
            System.out.println("Please input numbers only");
            sc.nextLine();
            birthDay(day);
        }
        
        if (day < 0 && day > 31) {
            System.out.println("Please input valid number ranging [1-31]");
            birthDay(day);
        }
    }
    
    @Override
    public void birthYear(int year){
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("[eg.. 1998] Birth Year: ");
            year = sc.nextInt();
            user.setYearOfBirth(year);
            sc.nextLine();
            user.setAge(userAge());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please input valid year number");
            sc.nextLine();
            birthYear(year);
        } catch (java.util.InputMismatchException e) {
            System.out.println("Please input numbers only");
            sc.nextLine();
            birthYear(year);
        }
    }
    
    private int userAge(){
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
        Date date = new Date();
        int year = Integer.parseInt(yearFormat.format(date));
        int month = Integer.parseInt(monthFormat.format(date));
        int day = Integer.parseInt(dayFormat.format(date));
        
        int age;
        if(month < user.getMonth()){
            age = (year - user.getYearOfBirth()) - 1;
        } else if(month == user.getMonth() && day == user.getDayOfBirth()){
            age = (year - user.getYearOfBirth());
            System.out.println("Today is your birthday, " + user.getFirstName() + "!");
        } else if(month == user.getMonth() && day < user.getDayOfBirth()){
            age = (year - user.getYearOfBirth()) - 1;
        } else {
            age = (year - user.getYearOfBirth());
        }
        return age;
    }
    
    @Override
    public String validateUsername(String username){
        Scanner sc = new Scanner(System.in);
        System.out.print("Username: ");
        username = sc.nextLine();
        user.setUserName(username);
        
        String query = "SELECT username FROM users WHERE username = '" + username + "'";
        try {
            ConnectDB();
            stmt = connect.createStatement();
            result = stmt.executeQuery(query);
            
            while(result.first()){
                if (result.getString(1).equals(user.getUserName()) == true) {
                    System.out.println("The username `" + user.getUserName() + "` has already taken.\nPlease try another one.");
                    validateUsername(username);
                    result.close();
                    break;
                } 
            }
        } catch (java.sql.SQLException e) {
        } catch (Exception e) {
            System.out.println(e);
        } 
        return user.getUserName();
    }
    
    @Override
    public String validateContact(String contact){
        Scanner sc = new Scanner(System.in);
        System.out.print("Contact: ");
        contact = sc.nextLine();
        user.setContact(contact);
        System.out.println(user.getContact());
        //  String query = "SELECT username FROM users WHERE username = '" + username + "'";
        // String query = "SELECT user_contact FROM users WHERE user_contact = `" + user.getContact() + "`";
        String query = "SELECT user_contact FROM users WHERE user_contact = "
                     + user.getContact() + "";
        try {
            ConnectDB();
            stmt = connect.createStatement();
            result = stmt.executeQuery(query);
             while(result.next()){
                if (result.getString(1).equals(user.getContact()) == true) {
                    System.out.println("The contact `" + user.getContact() + "` has already taken.\nPlease input new one.");
                    validateContact(contact);
                    result.close();
                    break;
                } 
            }
        } catch (java.sql.SQLException e) {
        } catch (Exception e) {
            System.out.println(e);
        } 
        return contact;
    }
    
    @Override  // email validation using regular expression
    public String validateEmail(String email){
        Scanner sc = new Scanner(System.in);
        System.out.print("Email: ");
        email = sc.nextLine().toLowerCase();
        
        String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern p = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(email);
        while(m.matches()== true){
            System.out.println("Email " + email + " is valid\n");
            verifyEmail(email);
            break;
        }
        while(m.matches()== false){
            System.out.println("Email " + email + " is invalid\n");
            validateEmail(email);
            break;
        }
        return email;
    }
    
    public void verifyEmail(String email){
        user.setEmail(email);
        String query = "SELECT user_email FROM users WHERE user_email = '" + email + "'";
            try {
                ConnectDB();
                stmt = connect.createStatement();
                result = stmt.executeQuery(query);
                 while(result.first()){
                    if (result.getString(1).equals(user.getEmail()) == true) {
                        System.out.println("The email `" + email + "` has already taken.\nPlease input new one.");
                        result.close();   
                        validateEmail(email);
                        break;
                    }      
                }                    
            } catch (java.sql.SQLException e) {
            } catch (Exception e) {
                System.out.println();
            } 
    }
    
    @Override
    public void userForm(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n[REGISTER]");
        System.out.println("Please fill up the form");

        String name = "";
        do {            
            System.out.print("First Name: ");
            name = sc.nextLine().trim();
            user.setFirstName(name);
        } while (name.equals(""));
        
        do {            
        System.out.print("Last Name: ");
            name = sc.nextLine().trim();
            user.setLastName(name);
        } while (name.equals(""));
        
        birthMonth(user.getMonthOfBirth());
        birthDay(user.getDayOfBirth());
        birthYear(user.getYearOfBirth());
        
        System.out.println("[M] Male  [F] Female  [Any] Unknown");
        System.out.print("Gender: ");
        String gender = sc.nextLine().toUpperCase();
        switch(gender){
            case "M":
                user.setGender("Male");
                break;
            case "F":
                user.setGender("Female");
                break;
            default:
                user.setGender("Unknown");
                break;
        }
        
        validateEmail(null);
        System.out.println("Email " + user.getEmail() + " is available.");
        
        validateContact(user.getContact());
        System.out.println("Contact " + user.getContact() + " is available.");

        
        validateUsername(user.getUserName());
        System.out.println("Username " + user.getUserName() + " is available.");
        
        System.out.print("Create Password: ");
        
        user.setPassword(sc.nextLine());   
        user.setRole(0);
        createUser();
    };
    
    @Override
    public void userForm(int user_id){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n[Edit User Profile]");
        System.out.println("User ID: " + user_id);
        System.out.println("Please fill up the form correctly");
        
        String name = "";
        do {            
            System.out.print("First Name: ");
            name = sc.nextLine().trim();
            user.setFirstName(name);
        } while (name.equals(""));
        
        do {            
        System.out.print("Last Name: ");
            name = sc.nextLine().trim();
            user.setLastName(name);
        } while (name.equals(""));
        
        birthMonth(user.getMonthOfBirth());
        birthDay(user.getDayOfBirth());
        birthYear(user.getYearOfBirth());
        
        System.out.println("[M] sMale  [F] Female  [Any] Unknown");
        System.out.print("Gender: ");
        String gender = sc.nextLine().toUpperCase();
        switch(gender){
            case "M":
                user.setGender("Male");
                break;
            case "F":
                user.setGender("Female");
                break;
            default:
                user.setGender("Unknown");
                break;
        }
//        
//        validateEmail(null);
//        System.out.println("Email " + user.getEmail() + " is available.");
//        
        validateContact(user.getContact());
        System.out.println("Contact " + user.getContact() + " is available.");
        
//        validateUsername(user.getUserName());
//        System.out.println("Username " + user.getUserName() + " is available.");


        System.out.print("Create Password: ");
        user.setPassword(sc.nextLine());   
    };
    
    // "insert into users (id,username,password,email,firstname,lastname,dateofbirth,age,role)values(?,?,?,?,?,?,?,?,?)"
    public void createUser(){
        String query = "INSERT INTO USERS (user_firstname,user_lastname,user_birthday,user_age,user_gender,username,password,user_email,user_contact,role,created_At) "
                     + "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        
        String birthday = user.getMonthOfBirth() + " " + user.getDayOfBirth() + ", " + user.getYearOfBirth();
        Date date = new Date();
        user.setCreatedAt(dtformat.format(date));
        try {
            ConnectDB();
            pst = connect.prepareStatement(query);
            pst.setString(1, user.getFirstName());
            pst.setString(2, user.getLastName());
            pst.setString(3, birthday);
            pst.setInt(4, user.getAge());
            pst.setString(5, user.getGender());
            pst.setString(6, user.getUserName());
            pst.setString(7, user.getPassword());
            pst.setString(8, user.getEmail());
            pst.setString(9, user.getContact());
            pst.setInt(10, user.getRole());
            pst.setString(11, user.getCreatedAt());
            pst.executeUpdate();
            
            System.out.println("\nUser `" + user.getUserName() + "` successfully added");
            display();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    void display(){
        System.out.println("Name: " + user.getFirstName() + " " + user.getLastName());
        System.out.println("Age: " + user.getAge());
        System.out.println("Gender: " + user.getGender());
        System.out.println("Birthday: " + user.getMonthOfBirth() + " " + user.getDayOfBirth() + ", " + user.getYearOfBirth());
        System.out.println("Contact; " + user.getContact());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Date Registered: " + user.getCreatedAt());
        System.out.println("----------------------------------------");
    }
    
    public void login(int user_id, String username, String password){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n[Login User]");
        
        System.out.print("User ID: ");
        user.setId(sc.nextInt()); sc.nextLine();
        System.out.print("Username: ");
        user.setUserName(sc.nextLine());
        user.getUserName();
        System.out.print("Password: ");
        user.setPassword(sc.nextLine());
        
        String query = "SELECT * FROM users WHERE user_id=? and username=? and password=?";
        
        try {
            ConnectDB();
            pst = connect.prepareStatement(query);
            pst.setInt(1,user.getId());
            pst.setString(2,user.getUserName());
            pst.setString(3,user.getPassword());
            
            result = pst.executeQuery();
            if(result.next()){
                user.setRole(result.getInt(11));
                if(user.getRole() == 1){
                    // System.out.println("Role: " + result.getInt(11));
                    main.adminDashboard(user.getUserName(),user.getPassword(), user.getRole(),user.getId());
                } else {
                    // System.out.println("Role: " + result.getInt(11));
                    main.userDashboard(user.getUserName(),user.getPassword(), user.getRole(),user.getId());
                } 
            } 
            else{
                System.out.println("Access Denied. Invalid username or password. Try Again");
                login(user.getId(),user.getUserName(),user.getPassword());
            }
        } catch (SQLException e) {
            System.out.println(e); 
        }
    }
    // INSERT INTO `log_details` (`log_id`, `users_id`, `time_login`, `time_logout`) VALUES (NULL, '20230004', '', '');
    
    @Override
    public void timeIn(int user_id){
        Date date = new Date();
        String timeIn = tf.format(date);
        String dateTimeIn = df.format(date);
        String query = "INSERT INTO log_details (users_id, date_login, time_login) values (" + user_id + ", ?,?)";
        
        try {
            ConnectDB();
            pst = connect.prepareStatement(query);
            pst.setString(1, dateTimeIn);
            pst.setString(2, timeIn);
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void timeOut(){
        Date date = new Date();
        String timeOut = tf.format(date);
        String query = "SELECT log_id FROM log_details ORDER BY log_id";
        try {
            ConnectDB();
            stmt = connect.createStatement();
            result = stmt.executeQuery(query);
            System.out.println("--------------------------------");
            while(result.last()){
                user.setLogId(result.getInt(1));
                break;
            }
            connect.close();
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
        
        String query2 = "UPDATE log_details SET time_logout = '"+ timeOut +"'  WHERE log_id = " + user.getLogId() + " ";
        try {
            ConnectDB();
            pst = connect.prepareStatement(query2);
            pst.executeUpdate();
            System.out.println("Time out: " + timeOut);
            System.out.println("User " + user.getUserName() + " has been logged out");
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public static void main(String[] args) {
        // AuthController auth = new AuthController();
        // auth.timeOut();
        // auth.userForm(20230007);
        // System.out.println("First Name: " + user.getFirstName());
        // System.out.println("Last Name: " + user.getLastName());
        // System.out.println("Contact: " + user.getLastName());
        // System.out.println("Birthday: " + user.getMonthOfBirth() + " " + user.getDayOfBirth() + ", " + user.getYearOfBirth());
        // System.out.print("Password: ");
        // for (int i = 0; i < user.getPassword().length(); i++) {
        //   System.out.print("*");
        // }System.out.println("");
    }
}
