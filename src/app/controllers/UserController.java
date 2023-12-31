package app.controllers;

import app.configdb.DatabaseConnect;
import static app.controllers.AuthController.user;
import static app.controllers.MenuController.adminMenu;
import static app.controllers.MenuController.userMenu;
import app.models.Customer;
import java.sql.SQLException;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserController extends DatabaseConnect{
    public static AuthController auth = new AuthController();
    Scanner scan = new Scanner(System.in);
    Customer customer = new Customer();
    Date date = new Date();

    // new customer form
    private void customerForm(){
        System.out.println("New Customer");
        System.out.print("Name: ");
        customer.setCustomerName(scan.nextLine()); // use our setter method from models
        System.out.print("Contact: ");
        customer.setCustomerContact(scan.nextLine());
        newCustomer(); 
    }
    
    // insert new customer to customers table
    private void newCustomer(){
        String query = "INSERT INTO customers (customer_name, customer_contact) VALUES (?,?)";
        
        // use our getter method, checking 
        System.out.println("Name & Contact: " + customer.getCustomerName() + ", " + customer.getCustomerContact());
        try {
            ConnectDB();
            pst = connect.prepareStatement(query);
            pst.setString(1, customer.getCustomerName()); 
            pst.setString(2, customer.getCustomerContact());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        } 
        System.out.println("Customer " + customer.getCustomerName() + " added successfully");
    }
    
    public void displayCustomer(){
        String qry = "Select * from customers";
        try {
            ConnectDB();
            stmt = connect.createStatement();
            result = stmt.executeQuery(qry);
            System.out.println("\n[Customer LIST]");
            System.out.println("ID    Name\t    Contact Number");
            while(result.next()){
                System.out.println(result.getInt("customer_id") + "  |  " + result.getString("customer_name") + "\t  | " + result.getString("customer_contact"));
            }
            connect.close();
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
    
    int getStockById(int productId){
        int stock = 0;
        String query = "SELECT on_stock FROM products WHERE product_id = " + productId;
        try {
            ConnectDB();
            stmt = connect.createStatement();
            result = stmt.executeQuery(query);

            while(result.next()){
                // System.out.println(result.getInt(1));
                stock = result.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        } 
        
        updateStock(productId, stock);
        // System.out.println("stock: " +  stock);
        return stock;
    }
    
    // update stock on database
    void updateStock(int productId, int stock){
        int updatedStock = stock - customer.getQuantity();
        System.out.println("On Stock (updated): " + updatedStock);
        String query = "UPDATE products SET on_stock = " + updatedStock + " WHERE products.product_id = " + productId;
        try {
            ConnectDB();
            pst = connect.prepareStatement(query);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        } 
    }
    
    // Create Order 
    public void createOrder(int user_id){
        // Product p = new Product();
        //  System.out.println( p.getOnStock());
        System.out.println("\n[CREATE ORDER]");
        System.out.println("Invoice Manager ID: " + user_id);

        System.out.println("\n[1] Get Customer Info (new) \n[2] Regular Customer");
        Scanner sc = new Scanner(System.in);
        ProductController pc = new ProductController();

        try {
            System.out.print("Select: ");
            int selector = sc.nextInt();
            switch(selector){
                case 1: 
                    customerForm();
                    displayCustomer();
                    break;
                case 2: 
                    displayCustomer();
                    break;
                default:
                    System.out.println("Invalid Input");
            }
        } catch (InputMismatchException ime) {
            System.out.println("Please enter number only");
            createOrder(user_id);
        } catch (Exception e){
            System.out.println(e);
        }
        
        System.out.print("Please input customer id: ");
        int customerId = sc.nextInt();
        pc.displayProduct();
        System.out.print("Please input product id: ");
        int productId = sc.nextInt();
        System.out.print("Please input quantity: ");
        int quantity = sc.nextInt();
        customer.setQuantity(quantity);       
        
        try {
            System.out.println("Continue? [1]Yes [Any]Back to Menu");
            int selector = sc.nextInt();
            switch(selector){
                case 1:
                    System.out.println("Preparing order...");
                    break;
                 default:
                    System.out.println("Back to Menu");
                    userMenu();
                    break;
            }
        } catch (java.util.InputMismatchException e) {
            System.out.println("Please input number only");
            userMenu();
        } catch (Exception e) {
            System.out.println("Order failed.\nSomething went wrong. Please try again");
            userMenu();
        }
        
        // System.out.println("Invoice Manager: " + user.getId());
        String query = "INSERT INTO orders (customer_id, product_id, order_quantity, invoice_manager) VALUES (?,?,?,"+ user_id +")";
        try {
            ConnectDB();
            pst = connect.prepareStatement(query);
            pst.setInt(1, customerId);
            pst.setInt(2, productId);
            pst.setInt(3, quantity);
            // pst.setInt(4, user_id); 
            pst.executeUpdate();
            getStockById(productId);
            System.out.println("Order successfully created for customer id: " + customerId);
            System.out.println( "Date: " + df.format(date));
            System.out.println( "Time: " + tf.format(date));
            userMenu();
            connect.close();
        } catch (Exception e) {
            System.out.println("Order failed.\nSomething went wrong. Please try again");
            userMenu();
        }
    }
  
    // UPDATE `users` SET `password` = '12345' WHERE `users`.`user_id` = 20230003;
    public void editProfile(int user_id, int role){
        // auth.userForm(20230007);
        auth.userForm(user_id);
        String birthday = user.getMonthOfBirth() + " " + user.getDayOfBirth() + ", " + user.getYearOfBirth();
        // UPDATE users SET user_firstname = 'Don',user_lastname = 'Martin' WHERE users.user_id = 20230007;
        String query = "UPDATE users SET user_firstname = '"+ user.getFirstName() + "', "
                    + "user_lastname = '" + user.getLastName() + "', "
                    + "user_birthday = '" + birthday + "', "
                    + "user_age = '" + user.getAge() + "', "
                    + "user_contact = '" + user.getContact() + "', "
                    + "password = '" + user.getPassword() + "' "
                    + " WHERE users.user_id = " + user_id +"";
        try {
            ConnectDB();
            pst = connect.prepareStatement(query);
            pst.executeUpdate();
            
            System.out.println("User ID `" + user_id +"` updated successfully");
            System.out.println("First Name: " + user.getFirstName());
            System.out.println("Last Name: " + user.getLastName());
            System.out.println("Contact: " + user.getContact());
            System.out.println("Birthday: " + birthday);
            System.out.println("Age: " + user.getAge());
            System.out.print("Password: ");
            for (int i = 0; i < user.getPassword().length(); i++) {
                System.out.print("*");
            }
            System.out.println("");
            
            if(user.getRole() == 1){
                adminMenu();
            } else {
                userMenu();
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public static void main(String[] args) {
        UserController uc = new UserController();
        // uc.createOrder(20230004);
    }
}
