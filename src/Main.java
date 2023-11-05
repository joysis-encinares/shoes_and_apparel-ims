import app.controllers.ProductController;

public class Main {
    public static void main(String[] args) {
        
        ProductController p = new ProductController();
        p.displayProduct();
        
        // login or menu starts here using switch case
        // if role = 0, call method userDashboard();
        // if role = 1, call method adminDashboard();
    }
    
    public void userDashboard(){
        // User -> Create Order, See Product List, Edit Profile
    }
    
     public void adminDashboard(){
        // Admin -> Create Order, See Product List, Edit Profile
    }
}


