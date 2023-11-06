package app.main;

import app.controllers.MenuController;

public class Main extends MenuController{
    
    public void adminDashboard(String username, String password, int role, int user_id){
         System.out.println("\n[ADMIN DASHBOARD]");
         System.out.println("Welcome, " + username + "!");
         adminMenu();
     }
    
    public void userDashboard(String username, String password, int role,int user_id){
        auth.timeIn(user_id);
        user.setId(user_id);
        System.out.println("\n[USER DASHBOARD]");
        System.out.println("Welcome, " + username + "!");
        userMenu();
     }
    
    public static void main(String[] args) {
        
        System.out.println("*****************************************************");
        System.out.println("*                                                   *");
        System.out.println("*                  SHOES AND APPAREL                *");
        System.out.println("*             INVENTORY MANAGEMENT SYSTEM           *");
        System.out.println("*                                                   *");
        System.out.println("*****************************************************");
        // Main m = new Main();
        menu();
    }
    
}


