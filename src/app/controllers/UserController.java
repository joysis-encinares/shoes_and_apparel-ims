package app.controllers;

import app.configdb.DatabaseConnect;
import app.models.Customer;
import java.util.Scanner;

public class UserController extends DatabaseConnect{
   Scanner scan = new Scanner(System.in);
   Customer customer = new Customer();
   
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
    
    // Create Order, Di pa naumpisahan
    public void createOrder(int costumer_id){
        String qry = "";
        try {
            ConnectDB();
            pst = connect.prepareStatement(qry);
            pst.executeUpdate();
            connect.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
