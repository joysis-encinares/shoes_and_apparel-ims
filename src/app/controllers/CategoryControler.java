
package app.controllers;
import app.configdb.DatabaseConnect;
import static app.controllers.MenuController.adminMenu;
import app.models.Category;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author Mark
 */

public class CategoryControler extends DatabaseConnect{
    Scanner scan = new Scanner(System.in);
    Category category = new Category();
    
    public void categoryForm(){
       String query = "SELECT * FROM categories WHERE categories_archieved = " + 0 ;
        try {
            ConnectDB();
            stmt = connect.createStatement();
            result = stmt.executeQuery(query);
            System.out.println("\n[PRODUCT CATEGORIES]");
            System.out.println("------------------------------------------------");
            System.out.println("ID\tCategory Name\t  Category Description");
            while(result.next()){
                int categoryId = result.getInt("categories_id");
                String categoryName = result.getString("categories_name");
                String categoryDescription = result.getString("categories_description");
                
                System.out.println(categoryId + "\t" + categoryName + "\t   " + categoryDescription );
            } System.out.println("------------------------------------------------");

        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
        adminMenu();
    }

    public void newCategoryForm(){
       
        System.out.println("New Category");
        System.out.print("Category Name: ");
        category.setCategoryName(scan.nextLine());
        System.out.print("Category Details: ");
        category.setCategoryDescription(scan.nextLine());
        newCategory();
    }
    
    public void newCategory(){
        String qry = "INSERT INTO categories (categories_name, categories_description) VALUES (?,?)";
        try {
            ConnectDB();
            pst = connect.prepareStatement(qry);
            pst.setString(1, category.getCategoryName()); 
            pst.setString(2, category.getCategoryDescription());
            pst.executeUpdate();
            System.out.println("\nCategory " + category.getCategoryName() + " added successfully");
            categoryForm();
        } catch (SQLException e) {
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void deleteCategoryForm(){
        System.out.println("Delete Category");
        System.out.print("Category ID : ");
        category.setCategoryId(scan.nextInt());
        deleteCategory();
    }
    
    public void deleteCategory(){
        String qry = "update categories set categories_archieved = 1 where categories_id = ?";
        try {
            ConnectDB();
            pst = connect.prepareStatement(qry);
            pst.setInt(1, category.getCategoryId()); 
            pst.executeUpdate();
            categoryForm();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
   
    
    public static void main(String[] args) {
        CategoryControler categoryControl = new CategoryControler();
        // categoryControl.categoryForm();
       // categoryControl.newCategoryForm();
        categoryControl.deleteCategoryForm();
    }
}
