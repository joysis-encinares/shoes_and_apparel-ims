package app.main;

import app.controllers.MenuController;

public class Main extends MenuController{
    
    public void adminDashboard(String username, String password, int role, int user_id){
        System.out.println("\n[ADMIN DASHBOARD]");
        auth.timeIn(user_id);
        user.setUserName(username);
        user.setId(user_id);
        user.setRole(role);
        System.out.println("Welcome, " + username + "!");
        adminMenu();
     }
    
    public void userDashboard(String username, String password, int role,int user_id){
        System.out.println("\n[USER DASHBOARD]");
        auth.timeIn(user_id);
        user.setUserName(username);
        user.setId(user_id);
        user.setRole(role);
        System.out.println("Welcome, " + username + "!");
        userMenu();
     }
    
    public static void main(String[] args) {
        
        System.out.println("------------------------------------------------------------------");
        System.out.println("*                                                                *");
        System.out.println("**                      SHOES AND APPAREL                       **");
        System.out.println("***                 INVENTORY MANAGEMENT SYSTEM                ***");
        System.out.println("**                           GROUP 4                            **");
        System.out.println("*                                                                *");
        System.out.println("------------------------------------------------------------------");
        menu();
    }
    
}


