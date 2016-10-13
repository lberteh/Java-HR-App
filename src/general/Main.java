
package general;

import hr.*;
import java.util.ArrayList;
import java.util.Scanner;
import products.Manufacturer;
import products.Product;

public class Main {
    
    // class variables
    private static Employee activeEmployee;
    private static Product tempProduct;
    
    public static void main(String[] args)
    {
        // Declare Lists 
        ArrayList<Employee> myEmployeeList = new ArrayList();
        ArrayList<Manufacturer> myManufacturersList = new ArrayList<>();
        ArrayList<Product> myProductsList = new ArrayList<>();
        
        // Instantiate a few manufacturers
        Manufacturer samsung = new Manufacturer("Samsung", "SAM123", "123 Samsung St");
        Manufacturer dell = new Manufacturer("Dell", "DEL123", "123 Dell St");
        Manufacturer apple = new Manufacturer("Apple", "APL123", "123 Apple St");
        
        // Add manufacturers to List
        myManufacturersList.add(samsung);
        myManufacturersList.add(dell);
        myManufacturersList.add(apple);
        
        // Instantiate a few products and add to List;
        myProductsList.add(new Product("Galaxy s7", "Phones", samsung));
        myProductsList.add(new Product("Galaxy Tab", "Tablets", samsung));
        myProductsList.add(new Product("Samsung J4000", "TVs", samsung));
        myProductsList.add(new Product("iPhone 7", "Phones", apple));
        myProductsList.add(new Product("MacBook Pro", "Laptops", apple));
        myProductsList.add(new Product("MacBook Air", "Laptops", apple));
        myProductsList.add(new Product("Apple TV", "Accessories", apple));
        myProductsList.add(new Product("iPod Touch", "Audio", apple));
        myProductsList.add(new Product("Dell Inspiron 15", "Laptops", dell));
        myProductsList.add(new Product("Alienware", "Laptops", dell));
        
        // Instantiate a few employees
        CommissionEmployee commEmp = new CommissionEmployee(
                "Rachel","Green","314, Grove St. E., Barrie","(705) 828 1592",
                985549932,"Female","Sales","Sales Person",
                "active",0.05, 1975,11,23);
        SalaryEmployee salEmp = new SalaryEmployee(
                "Ross","Geller","314, Grove St. E., Barrie","(705) 828 1592",
                985549932,"Male","Paleontology","Professor",
                "active",5600, 1980,11,23);
        HourlyEmployee hourEmp = new HourlyEmployee(
                "Monica","Geller","314, Grove St. E., Barrie","(705) 828 1592",
                985549932,"Female","Kitchen","Chef",
                "active",26, 1980,11,23);
        
        // Add employees to list
        myEmployeeList.add(commEmp);   
        myEmployeeList.add(salEmp);    
        myEmployeeList.add(hourEmp);    
        
        Scanner read = new Scanner(System.in);
        
        boolean stay = true;
        int input = 0; 
        
        do
        {
            // display main menu
            ServiceClass.getInitialMessage();
            ServiceClass.promptUser();

            input = read.nextInt();
            
            // user input cannot be out of [1-3] range
            if(input < 1 || input > 3)
            {
                ServiceClass.errInvalidInput();
            }
            else
            {
                // get in the hr menu
                if(input == 1)
                {                           
                    int hrInput = 0;
                    boolean stayHere = true;
                    
                    do
                    {
                        // If user have selected an employee (employee search), display employee menu.
                        if(activeEmployee != null)
                        {
                            boolean keepGoing = true;
                            do
                            {
                                // display selected employee menu
                                ServiceClass.activeEmployeeMenu();
                                ServiceClass.promptUser();

                                input = read.nextInt();
                                
                                // display errors if invalid input
                                if(input < 1 || input > 3)
                                {
                                    ServiceClass.errInvalidInput();
                                }
                                else
                                {   
                                    // Displays searched employee's info
                                    if(input == 1)
                                    {
                                        System.out.println(activeEmployee.toString());
                                    }
                                    // calculates and displays searched employee's earnings
                                    else if(input == 2)
                                    {
                                        System.out.println(activeEmployee.earningsToString());
                                    }
                                    // back to previous menu
                                    else
                                    {
                                        activeEmployee = null;
                                        keepGoing = false;                                        
                                    }
                                }
                            }
                            while(keepGoing);
                        }
                        
                        // display hr menu message
                        ServiceClass.getHROptionsMessage();
                        ServiceClass.promptUser();
                        hrInput = read.nextInt();
                        
                        // create new employee and add to Employee List
                        if(hrInput == 1)
                        {
                            myEmployeeList.add(ServiceClass.createEmployee());                            
                        }
                        // search for an employee
                        else if(hrInput == 2)
                        {
                            searchEmployee(myEmployeeList);                            
                        }
                        // Go back to previous menu
                        else if(hrInput == 3)
                        {
                            stayHere = false;                            
                        }
                        // display errors if invalid input
                        else 
                        {
                            ServiceClass.errInvalidInput();
                        }
                        
                    } while(stayHere);
                }
                // get in the products menu
                else if(input == 2)
                {
                    int prodInput = 0;
                    boolean stayHere = true;
                    
                    do
                    {
                        // display products menu
                        ServiceClass.getProductsOptionsMessage();
                        ServiceClass.promptUser();
                        
                        prodInput = read.nextInt();
                        
                        // if invalid input, display message
                        if(prodInput < 1 || prodInput > 3)
                        {
                            ServiceClass.errInvalidInput();
                        }
                        else
                        {
                            // seach product by selecting from a list
                            if(prodInput == 1)
                            {
                                searchProduct(myProductsList, prodInput);
                            }
                            // search product by name
                            else if(prodInput == 2)
                            {
                                searchProduct(myProductsList, prodInput);
                            }
                            // back
                            else
                            {
                                stayHere = false;
                            }
                        }
                    }
                    while(stayHere);
                }
                // exit application
                else
                {
                    stay = false;
                }
            }
            
        } while(stay);     
                           
    }
        // method to search and select an employee either browsing a list or inputing employee's name
    	private static void searchEmployee(ArrayList<Employee> employeeList) 
        {
            // loop until user selects:
            // 1. search by list
            // 2. search by name
            // 3. back to main menu
		
            Scanner read = new Scanner(System.in);
            int searchInput = 0;
            boolean searchSuccess = false;

            do 
            {   
                // display search employee menu
                ServiceClass.searchMenuMessage();
                ServiceClass.promptUser();
                searchInput = read.nextInt();

                if(searchInput < 1 || searchInput > 3) 
                {
                    System.out.println("Invalid input. Please enter in range [1-3]");
                }

                // Employee Search Options
                else 
                {
                    // should never happen
                    if(activeEmployee != null) 
                    {
                        System.out.println("There is an active employee!");
                        searchSuccess = true;
                    }

                    // search by list
                    if(searchInput == 1) 
                    {
                        displayEmployeeList(employeeList);
                        
                        // prompt user for input
                        int employeeSelection = receiveListInput(employeeList);

                        // assign activeCustomer
                        activeEmployee = employeeList.get(employeeSelection);
                        searchSuccess = true;
                    }

                    // search by name
                    else if(searchInput == 2) 
                    {
                        String tempFullName;

                        read.nextLine();
                        System.out.println("Please enter full name");
                        ServiceClass.promptUser();
                        tempFullName = read.nextLine();                        

                        // loop through employee list
                        for(Employee employee : employeeList) 
                        {
                            if(employee.getFullName().equalsIgnoreCase(tempFullName)) 
                            {
                                activeEmployee = employee;
                                System.out.println("\n\nSuccessfully found: " + activeEmployee.getFullName());
                                searchSuccess = true;
                            }
                        }

                        if(activeEmployee == null) 
                        {
                            System.out.println("\nEmployee not found.");
                            searchSuccess = true; // Prevent user from being stuck
                        }
                    }

                    // return to main menu
                    else if(searchInput == 3) 
                    {
                            searchSuccess = true;
                    }
                }	
        
            } 
            while(!searchSuccess);
	}
        
        // loops through the current employee list and display each employee 
        private static void displayEmployeeList(ArrayList<Employee> employeeList) 
        {
            for(int i=0; i<employeeList.size(); i++) 
            {
                System.out.println((i+1) + ". " + employeeList.get(i).getFullName());
            }
	}
        
        // receives user input and validates acording to employee list size then returns input
        private static int receiveListInput(ArrayList<Employee> employeeList) 
        {
            Scanner read = new Scanner(System.in);
            int listInput = 0;
            boolean listSuccess = false;
            do 
            {	
                ServiceClass.promptUser();
                listInput = read.nextInt();
                
                // checks if input is valid
                if(listInput < 1 || listInput > employeeList.size())
                {
                    ServiceClass.errInputOutOfRangeMessage();
                }                    
                else
                {
                    listSuccess = true;
                }	                            		

            }
            while(!listSuccess);

            // Decrement because ArrayList.get() will start at zero (0)
            return listInput-1;
	}
        
        private static void searchProduct(ArrayList<Product> productList, int prodInput) 
        {
            Scanner read = new Scanner(System.in);
            boolean searchSuccess = false;

            do 
            {   
                // Invalid input
                if(prodInput < 1 || prodInput > 3) 
                {
                    System.out.println("Invalid input. Please enter in range [1-3]");
                }

                // Product Search 
                else 
                {
                    // search by list
                    if(prodInput == 1) 
                    {
                        displayProductList(productList);
                        
                        // prompt user for input
                        int productSelection = receiveProductListInput(productList);

                        // display selected product
                        System.out.println(productList.get(productSelection).toString());
                        searchSuccess = true;
                    }

                    // search by name
                    else if(prodInput == 2) 
                    {
                        String tempProdName;
                        tempProduct = null;

                        System.out.println("Please enter product name");
                        ServiceClass.promptUser();
                        tempProdName = read.nextLine();                        

                        // loop through product list
                        for(Product product : productList) 
                        {
                            if(product.getName().equalsIgnoreCase(tempProdName)) 
                            {       
                                tempProduct = product;
                                System.out.println("\n\nProduct Successfully found:\n\n"
                                        + product.toString());
                                searchSuccess = true;
                            }
                        }

                        if(tempProduct == null) 
                        {
                            System.out.println("\nProduct not found.");
                            searchSuccess = true; 
                        }
                    }

                    // return to main menu
                    else if(prodInput == 3) 
                    {
                            searchSuccess = true;
                    }
                }	
        
            } 
            while(!searchSuccess);
        }
        
        // loops through the products list and display each product
        private static void displayProductList(ArrayList<Product> productList) 
        {
            for(int i=0; i<productList.size(); i++) 
            {
                System.out.println((i+1) + ". " + productList.get(i).getName());                
            }
	}
        
        // receives user input and validates acording to employee list size then returns input
        private static int receiveProductListInput(ArrayList<Product> productList) 
        {
            Scanner read = new Scanner(System.in);
            int listInput = 0;
            boolean listSuccess = false;
            do 
            {	
                ServiceClass.promptUser();
                listInput = read.nextInt();
                
                // checks if input is valid
                if(listInput < 1 || listInput > productList.size())
                {
                    ServiceClass.errInputOutOfRangeMessage();
                }                    
                else
                {
                    listSuccess = true;
                }	                            		

            }
            while(!listSuccess);

            // Decrement because ArrayList.get() will start at zero (0)
            return listInput-1;
	}
}
