import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        
        System.out.println("*****************************************************");
        System.out.println("*                                                   *");
        System.out.println("*                  SHOES AND APPAREL                *");
        System.out.println("*             INVENTORY MANAGEMENT SYSTEM           *");
        System.out.println("*                                                   *");
        System.out.println("*****************************************************");
        Main m = new Main();
        m.menu();
    }
    
     public void menu(){
        System.out.println("\n[MAIN]");
        System.out.println("[1] Login [2] Create User [3] Exit");
        System.out.print("Select: ");
        Scanner scan = new Scanner(System.in);
        
         try {
            int selector = scan.nextInt();
            switch(selector){
                case 1: 
                    System.out.println("Login User");
                    break;
                case 2: 
                    System.out.println("Register");
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
   
    
    public void userDashboard(){
        // User -> Create Order, See Product List, Edit Profile
    }
    
     public void adminDashboard(){
        // Admin -> Create Order, See Product List, Edit Profile
    }
}


