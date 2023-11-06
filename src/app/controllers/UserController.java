package app.controllers;

import app.configdb.DatabaseConnect;
import app.models.Customer;
import app.models.Product;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserController extends DatabaseConnect{
    Scanner scan = new Scanner(System.in);
    Customer customer = new Customer();
    Customer c = new Customer();

    private static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat tf = new SimpleDateFormat("HH:mm:ss");
    Date date = new Date();

   // new customer form
    private void form(){
        System.out.println("New Customer");
        System.out.print("Name: ");
        customer.setCustomerName(scan.nextLine()); // use our setter method from models
        System.out.print("Contact: ");
        customer.setCustomerContact(scan.nextLine());
        newCustomer(); 
    }
    
    // insert new customer to customers table
    private void newCustomer(){
        String qry = "INSERT INTO customers (customer_name, customer_contact) VALUES (?,?)";
        
        // use our getter method, checking 
        System.out.println("Name & Contact: " + customer.getCustomerName() + ", " + customer.getCustomerContact());
        try {
            ConnectDB();
            pst = connect.prepareStatement(qry);
            pst.setString(1, customer.getCustomerName()); 
            pst.setString(2, customer.getCustomerContact());
            pst.executeUpdate();
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
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
//    private void getStockById(int productId){
//        int stock = 0;
//        String query = "SELECT on_stock FROM products WHERE product_id = " + productId;
//        try {
//            ConnectDB();
//            stmt = connect.createStatement();
//            result = stmt.executeQuery(query);
//
//            while(result.next()){
//                System.out.println(result.getInt(1));
//                stock = result.getInt(1);
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        System.out.println("stock: " +  stock);
//    }
    
    int getStockById(int productId){
        int stock = 0;
        String query = "SELECT on_stock FROM products WHERE product_id = " + productId;
        try {
            ConnectDB();
            stmt = connect.createStatement();
            result = stmt.executeQuery(query);

            while(result.next()){
                System.out.println(result.getInt(1));
                stock = result.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        updateStock(productId, stock);
        // System.out.println("stock: " +  stock);
        return stock;
    }
    
    // update stock on database
    void updateStock(int productId, int stock){
        int updatedStock = stock - c.getQuantity();
        System.out.println("USTOCK: " + updatedStock);
        String query = "UPDATE products SET on_stock = " + updatedStock + " WHERE products.product_id = " + productId;
        try {
            ConnectDB();
            pst = connect.prepareStatement(query);
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    // Create Order 
    public void createOrder(){
        Product p = new Product();
        //  System.out.println( p.getOnStock());
        System.out.println("\n[CREATE ORDER]");
        System.out.println("\n[1] Get Customer Info (new) \n[2] Regular Customer");
        Scanner sc = new Scanner(System.in);
        ProductController pc = new ProductController();

        try {
            System.out.print("Select: ");
            int selector = sc.nextInt();
            switch(selector){
                case 1: 
                    form();
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
            createOrder();
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
        c.setQuantity(quantity);
        
        String query = "INSERT INTO orders (customer_id, product_id, order_quantity, invoice_manager) VALUES (?,?,?,?)";
        getStockById(productId);
        
        try {
            ConnectDB();
            pst = connect.prepareStatement(query);
            pst.setInt(1, customerId);
            pst.setInt(2, productId);
            pst.setInt(3, quantity);
            pst.setInt(4, 101);
            
            pst.executeUpdate();
            System.out.println("Order successfully created for customer id: " + customerId);
            System.out.println( "Date: " + df.format(date));
            System.out.println( "Time: " + tf.format(date));
            // menu();
            connect.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public static void main(String[] args) {
        UserController uc = new UserController();
        uc.createOrder();
        // uc.getStockById(1000);
    }
}
