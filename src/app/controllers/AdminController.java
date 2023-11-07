package app.controllers;

import app.configdb.DatabaseConnect;

interface Controllable {
    void seeReports();
    void checkLogDetails();
}

public class AdminController extends DatabaseConnect implements Controllable{
   
    protected static MenuController menu = new MenuController();
    @Override
    public void seeReports(){
        System.out.println("\n[See Invoiced Reports]");
        
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
    
    @Override
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
    
    public static void main(String[] args) {
        AdminController admin = new AdminController();
        // admin.seeReports();
        admin.checkLogDetails();
    }
}
