
package products;

public class Manufacturer {
    // class variables
    private String name, code, address;
    
    // ****** CONSTRUCTOR ****** \\
    // default constructor
    public Manufacturer(){}
    
    public Manufacturer(String name, String code, String address)
    {
        this.name = name;
        this.code = code;
        this.address = address;
    }

    // ****** GETTERS ****** \\
    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getAddress() {
        return address;
    }

    // ****** SETTERS ****** \\
    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    // to String Method
    @Override
    public String toString()
    {
        String manufacturer = "";
        
        manufacturer += "\nName:\t" + this.getName();
        manufacturer += "\nCode:\t" + this.getCode();
        
        return manufacturer;
    }
}
