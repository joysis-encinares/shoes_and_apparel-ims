package app.controllers;

import app.configdb.DatabaseConnect;


public class AdminController extends DatabaseConnect implements Controllable{
   
    protected static MenuController menu = new MenuController();
    
    public void seeReports(){
        System.out.println("\n[Invoiced Reports]");
        
        String query = "SELECT orders.invoice_id, customers.customer_name, products.product_brand, products.product_model, orders.order_quantity, orders.order_date, users.user_firstname "
            + "FROM orders "
            + "INNER JOIN customers ON orders.customer_id = customers.customer_id "
            + "INNER JOIN products ON orders.product_id = products.product_id "
            + "INNER JOIN users ON orders.invoice_manager = users.user_id ";

        try {
            ConnectDB();
            stmt = connect.createStatement();
            result = stmt.executeQuery(query);
            System.out.println("--------------------------------");
            while(result.next()){
                System.out.println("Invoice ID: " + result.getString(1));
                System.out.println("Customer Name: " + result.getString(2));
                System.out.println("Brand: " + result.getString(3));
                System.out.println("Model: " + result.getString(4));
                System.out.println("Quantity: " + result.getInt(5));
                System.out.println("Order Date: " + dtformat.format(result.getTimestamp(6)));
                System.out.println("Invoice Manager: " + result.getString(7));
                System.out.println("--------------------------------");
            }
            connect.close();
            menu.adminMenu();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void checkLogDetails(){
        System.out.println("\n[Check Log Reports]\n");
        
        String query = "SELECT log_details.log_id, log_details.date_login, log_details.time_login, log_details.time_logout, users.user_id, users.user_firstname "
            + "FROM log_details "
            + "INNER JOIN users ON log_details.users_id = users.user_id WHERE log_details.time_logout <> 'NULL' ORDER BY log_details.log_id ";
        
        try {
            ConnectDB();
            stmt = connect.createStatement();
            result = stmt.executeQuery(query);
            System.out.println("LOG ID\tLOG DATE    TIME IN   TIME OUT  USER ID\t  NAME");    
            System.out.println("------------------------------------------------------------------");

            while(result.next()){
                System.out.println(" " + result.getString(1) 
                + "\t" + result.getString(2)
                + "  " + result.getString(3)
                + "  " + result.getString(4)
                + "  " + result.getString(5)
                + "  " + result.getString(6));
            }
            System.out.println("------------------------------------------------------------------");
            connect.close();
            menu.adminMenu();
        } catch (Exception e) {
            System.out.println(e);
        }
    };
    
    public void viewUsers(){
        System.out.println("\n[All Users List]\n");
        // SELECT * FROM `users` ORDER BY `users`.`user_id` ASC
        String query = "SELECT * FROM users ORDER BY users.user_id ASC";
        try {
            ConnectDB();
            stmt = connect.createStatement();
            result = stmt.executeQuery(query);
            while(result.next()){
                System.out.println("ID: " + result.getInt(1));
                System.out.print("Role: ");
                System.out.println((result.getInt(11) > 0) ? "Admin" : "User");
                System.out.println("Name: " + result.getString(2) + " " + result.getString(3));
                System.out.println("Birthday: " + result.getString(4));
                System.out.println("Age: " + result.getString(5));
                System.out.println("Gender: " + result.getString(6));
                System.out.println("Username: " + result.getString(7));
                System.out.println("Email: " + result.getString(9));
                System.out.println("Contact: " + result.getString(10));
                System.out.println("Account Created: " + result.getString(12));
                System.out.println("--------------------------------------");
            }
            connect.close();
            menu.adminMenu();
        } catch (Exception e) {
            System.out.println(e);
        } 
    };
    
    
    
    public static void main(String[] args) {
        AdminController admin = new AdminController();
        // admin.seeReports();
        // admin.checkLogDetails();
        admin.viewUsers();
    }
}
