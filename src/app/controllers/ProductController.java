package app.controllers;

import app.configdb.DatabaseConnect;

public class ProductController extends DatabaseConnect {
    
    // Read | See all product
    public void displayProduct(){
        String qry = "Select * from products";
        try {
            ConnectDB();
            stmt = connect.createStatement();
            result = stmt.executeQuery(qry);
            System.out.println("\n[PRODUCT LIST]");
//            System.out.println("Product ID\tProduct Brand\tProduct Model\tON STOCK");
            
            while(result.next()){
                System.out.println( "ID: " + result.getInt("product_id") 
                + "\nBrand: " + result.getString("product_brand") 
                + "\nModel: "+ result.getString("product_model")
                + "\nDescription: "+ result.getString("product_description")
                + "\nOn Stock: "+ result.getInt("on_stock"));
            }
            System.out.println("-----------------------------------------------------------------------");
            connect.close();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
