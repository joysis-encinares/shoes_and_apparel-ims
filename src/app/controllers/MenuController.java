package app.controllers;

import app.models.User;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuController {
    
    protected static AuthController auth = new AuthController();
    protected static UserController uc = new UserController();
    protected static AdminController admin = new AdminController();
    protected static ProductController pc = new ProductController();
    protected static CategoryControler cc = new CategoryControler();

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
                pc.displayProduct();
                uc.createOrder(user.getId());
                break;
            case 2:
                uc.editProfile(user.getId(),user.getRole());
                break;
            case 3:
                auth.timeOut();
                menu();
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
        System.out.println("[1] Check Products");
        System.out.println("[2] Check Category");
        System.out.println("[3] Check Users");
        System.out.println("[4] Invoice Reports");
        System.out.println("[5] Log Details");
        System.out.println("[6] Edit Profile");
        System.out.println("[7] Logout");

        try {
            System.out.print("Select: ");
            Scanner scan = new Scanner(System.in);
            int selector = scan.nextInt();
            switch(selector){
                case 1:
                    System.out.println("\n[Check Products]");
                    System.out.println("[1] Create Product\n[2] Update Product\n[3] Delete Product\n[4] View Products");
                    System.out.print("Select:");
                    selector = scan.nextInt();
                    switch(selector){
                        case 1:
                            System.out.println("Create Product");
                            pc.createProduct(user.getId());
                            break;
                        case 2:
                            System.out.println("Update Product");
                            break;
                        case 3:
                            System.out.println("Delete Product");
                            break;
                        case 4:
                            System.out.println("View Products");
                            break;
                        default:
                             System.out.println("Invalid input");
                             adminMenu();
                             break;
                    }
                    break;
                case 2:
                    System.out.println("\n[Check Category]");
                    System.out.println("[1] New Category\n[2] Update Category\n[3] Delete Category\n[4] View Categories");
                    System.out.print("Select:");
                    selector = scan.nextInt();
                    switch(selector){
                        case 1:
                            System.out.println("New Category");
                            cc.newCategoryForm();
                            break;
                        case 2:
                            System.out.println("Update Category");
                            break;
                        case 3:
                            System.out.println("Delete Category");
                            cc.deleteCategoryForm();
                            break;
                        case 4:
                            System.out.println("View Categories");
                            cc.categoryForm();
                            break;
                        default:
                             System.out.println("Invalid input");
                             adminMenu();
                             break;
                    }
                    break;
                case 3:
                    System.out.println("\n[Check Users]");
                    System.out.println("[1] Create User\n[2] Update User\n[3] Delete User\n[4] View Users");
                    System.out.print("Select:");
                    selector = scan.nextInt();
                    switch(selector){
                        case 1:
                            System.out.println("Create User");
                            break;
                        case 2:
                            System.out.println("Update User");
                            break;
                        case 3:
                            System.out.println("Delete User");
                            break;
                        case 4:
                            System.out.println("View Users");
                            admin.viewUsers();
                            break;
                        default:
                             System.out.println("Invalid input");
                             adminMenu();
                             break;
                    }
                    break;
                case 4:
                    admin.seeReports();
                    break;
                case 5:
                    admin.checkLogDetails();
                    break;
                case 6:
                    uc.editProfile(user.getId(),user.getRole());
                    break;
                case 7:
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
