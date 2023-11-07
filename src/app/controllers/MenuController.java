package app.controllers;

import app.models.User;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuController {
    
    protected static AuthController auth = new AuthController();
    protected static UserController uc = new UserController();
    protected static User user = new User();

    // protected static Main main = new Main();
    
    protected static void userMenu(){
        System.out.println("[USER MENU]");
        System.out.println("[1] Create Order");
        System.out.println("[2] Edit Profile");
        System.out.println("[3] Logout");

        System.out.print("Select: ");
        Scanner scan = new Scanner(System.in);
        int selector = scan.nextInt();
        switch(selector){
            case 1:
                uc.createOrder(user.getId());
                break;
            case 2:
                uc.editProfile(user.getId());
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
    }
    
    protected static void adminMenu(){
        System.out.println("[1] Add User");
        System.out.println("[2] Delete User");
        System.out.println("[3] Update User");
        System.out.println("[4] See User/Admin List");
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
            default:
                System.out.println("Invalid input");
                break;
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
}
