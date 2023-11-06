package app.controllers;

import app.configdb.DatabaseConnect;


public class AdminController extends DatabaseConnect{
   
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
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public static void main(String[] args) {
        AdminController admin = new AdminController();
        admin.seeReports();
    }
}
