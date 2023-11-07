package app.controllers;

import app.models.User;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuController {
    
    protected static AuthController auth = new AuthController();
    protected static UserController uc = new UserController();
    protected static AdminController admin = new AdminController();
    protected static User user = new User();

    protected static void userMenu(){
        System.out.println("\n[USER MENU]");
        System.out.println("[1] Create Order");
        System.out.println("[2] Edit Profile");
        System.out.println("[3] Logout");

        try {
            System.out.print("Select: ");
            Scanner scan = new Scanner(System.in);
            int selector = scan.nextInt();
            switch(selector){
            case 1:
                uc.createOrder(user.getId());
                break;
            case 2:
                uc.editProfile(user.getId(),user.getRole());
                break;
            case 3:
                auth.timeOut();
                menu();
                break;
            case 4:
                break;
            default:
                System.out.println("Invalid input");
                break;
            }
        } catch (InputMismatchException ime) {
             System.out.println("Please enter number only");
             userMenu();
        } catch (Exception e){
             System.out.println("Something went wrong...");
             userMenu();
        } 
    }
    
    protected static void adminMenu(){
        System.out.println("\n[ADMIN MENU]");
        System.out.println("[1] Create Product     [6] Update User      [11] Edit Profile");
        System.out.println("[2] Delete Product     [7] Delete User      [12] Logout");
        System.out.println("[3] Update Product     [8] View Users");
        System.out.println("[4] View All Products  [9] Invoiced Reports");
        System.out.println("[5] View All Users     [10] Log Reports");

        try {
            System.out.print("Select: ");
            Scanner scan = new Scanner(System.in);
            int selector = scan.nextInt();
            switch(selector){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    admin.seeReports();
                    break;
                case 10:
                    admin.checkLogDetails();
                    break;
                case 11:
                    uc.editProfile(user.getId(),user.getRole());
                    break;
                case 12:
                    auth.timeOut();
                    menu();
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
         } catch (InputMismatchException ime) {
             System.out.println("Please enter number only");
             adminMenu();
         } catch (Exception e){
             System.out.println("Something went wrong...");
             adminMenu();
         } 
    }
    
    public static void menu(){
        System.out.println("\n[MAIN]");
        System.out.println("[1] Login [2] Create User [3] Exit");
        System.out.print("Select: ");
        Scanner scan = new Scanner(System.in);
         try {
            int selector = scan.nextInt();
            switch(selector){
                case 1: 
                    auth.login(0,null,null);
                    break;
                case 2: 
                    auth.userForm();
                    menu();
                    break;
                case 3: 
                    System.out.println("Program Terminated");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Input");
                    menu();
            }
         } catch (InputMismatchException ime) {
             System.out.println("Please enter number only");
             menu();
         } catch (Exception e){
             System.out.println(e);
         } 
    }
    
    public static void main(String[] args) {
        adminMenu();
    }
}
