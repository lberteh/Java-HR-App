
package products;

import general.ServiceClass;

public class Product 
{
    // class variables
    private String name, category;
    private int productId;
    private Manufacturer manufacturer;
    
    // ****** CONSTRUCTOR ****** \\
    // default constructor
    public Product(){}
    
    public Product(String name, String category, Manufacturer manufacturer)
    {
        this.name = name;
        this.category = category;
        this.manufacturer = manufacturer;
        productId = ServiceClass.getProductId();
    }
    
    // ****** GETTERS ****** \\

    public String getName() {
        return name;
    }
    
    public String getCategory() {
        return category;
    }

    public int getProductId() {
        return productId;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    // ****** SETTERS ****** \\
    public void setName(String name) {
        this.name = name;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }
        
    // to string method
    @Override
    public String toString()
    {
        String product = "";
        
        product += "\nName:\t\t" + this.getName();
        product += "\nCategory:\t" + this.getCategory();
        product += "\nProduct Id:\t" + this.getProductId();
        product += "\n\n  Manufacturer" + this.getManufacturer() + "\n";
        
        return product;
    }
}
