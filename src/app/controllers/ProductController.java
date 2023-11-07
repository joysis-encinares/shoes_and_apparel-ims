package app.controllers;

import app.configdb.DatabaseConnect;
import app.models.Product;
import java.sql.SQLException;
import java.util.Scanner;

public class ProductController extends DatabaseConnect {
    
    Product product = new Product();
    
    
    // Read | See all product
    public void displayProduct(){
        String qry = "Select * from products";
        try {
            ConnectDB();
            stmt = connect.createStatement();
            result = stmt.executeQuery(qry);
            System.out.println("\n[PRODUCT LIST]\n");
//            System.out.println("Product ID\tProduct Brand\tProduct Model\tON STOCK");
            
            while(result.next()){
                System.out.println( "ID: " + result.getInt("product_id") 
                + "\nBrand: " + result.getString("product_brand") 
                + "\nModel: "+ result.getString("product_model")
                + "\nDescription: "+ result.getString("product_description")
                + "\nOn Stock: "+ result.getInt("on_stock"));
                System.out.println("-------------------------------------");
            }
            
            connect.close();
            
        } catch (SQLException e) {
        } catch(Exception e){
            System.out.println("Something went wrong...");
        }
    }
    
    void productForm(int admin_id){
        Scanner scan = new Scanner(System.in);
        
        System.out.println("[CREATE NEW PRODUCT]");
        System.out.println("Admin ID: " + admin_id);
        
        System.out.print("Brand: ");
        product.setBrand(scan.nextLine());
        
        System.out.print("Model: ");
        product.setModel(scan.nextLine());
        
        System.out.print("Description: ");
        product.setDescription(scan.nextLine());
        
        System.out.print("Select Product Size: ");
        product.setSize(scan.nextInt());
        
        System.out.print("Select Product Category: ");
        product.setCategory(scan.nextInt());
        
        System.out.print("On Stock: ");
        product.setOnStock(scan.nextInt());
        
        createProduct(admin_id);
    }
    
    public void createProduct(int user_id){
        // INSERT INTO `products` (`product_id`, `product_brand`, `product_model`, `product_description`, `product_size`, `product_category`, `product_archived`, `on_stock`, `created_by`) VALUES (NULL, 'Adidas', 'Stan Smith Shoes', 'Adidas Stan Smith Shoes', '3', '1', '0', '500', '20230001');

        String query = "INSERT INTO products (product_id, product_brand, product_model, product_description, product_size, product_category, product_archived, on_stock, created_by) VALUES (?,?,?,?,?,?,?,?,?)"; 
        try {
            ConnectDB();
            pst = connect.prepareStatement(query);
            pst.setString(1, null );
            pst.setString(2, product.getBrand());
            pst.setString(3, product.getModel());
            pst.setString(4, product.getDescription());
            pst.setInt(5, product.getSize());
            pst.setInt(6, product.getCategory());
            pst.setInt(7, 0);
            pst.setInt(8, product.getOnStock());
            pst.setInt(9, user_id);
            pst.executeUpdate();
            connect.close();
            
            System.out.println("\nProduct " + product.getModel() + " has been added successfully");
            System.out.println("Admin ID: " + user_id);
            System.out.println("On Stock: " + product.getOnStock());
            displayProduct();
        } catch (SQLException e) {
            productForm(user_id);
        } catch(Exception e){
            productForm(user_id);
            System.out.println("Something went wrong...");
        }
    }
    public static void main(String[] args) {
        ProductController pc = new ProductController();
        // pc.productForm(20230001);
        pc.displayProduct();
    }
}
