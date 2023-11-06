package app.models;

public class Customer {
    private String customerName;
    private String customerContact;
    private int quantity;
        
        
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerContact(String customerContact) {
        this.customerContact = customerContact;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerContact() {
        return customerContact;
    }

    public int getQuantity() {
        return quantity;
    }
    
}
