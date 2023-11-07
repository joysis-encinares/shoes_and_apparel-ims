package app.models;

/**
 *
 * @author Mark
 */

public class Category {
    private String categoryName;
    private String categoryDescription;
    private int categoryId;
        
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }
    
    public void setCategoryId(int categoryId){
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }
    
    public int getCategoryId(){
        return categoryId;
    }
}
