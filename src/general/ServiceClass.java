package general;

import hr.*;
import java.util.Scanner;

public class ServiceClass 
{
    // class variables
    private static int employeeId = 10000;
    private static int productId = 1000;
    
    // ****** Generate Ids from static variables ****** \\
    public static int getEmployeeId()
    {
        return ++employeeId;
    }
    
    public static int getProductId()
    {
        return ++productId;
    }
    
    // ****** MENU MESSAGES ****** \\
    public static void promptUser() 
    {
    	System.out.print(">> ");
    }
    // main menu
    public static void getInitialMessage()
    {
        System.out.println("\nWELCOME TO BLACK CAT - SMART SOLUTIONS"
                + "\n\nPlease, enter an Option:"
                + "\n\n1 - Human Resources"
                + "\n2 - Products"
                + "\n3 - Exit.");
    }
    // human resources menu
    public static void getHROptionsMessage()
    {
        System.out.println("\nPlease, enter an Option:"
                + "\n\n1 - Register new Employee"
                + "\n2 - Search Employees"
                + "\n3 - Back");
    }
    // search products menu
    public static void getProductsOptionsMessage()
    {
        System.out.println("\nPlease, enter an Option:"
                + "\n\n1 - Search Product by List"
                + "\n2 - Search Product by Name"
                + "\n3 - Back");
    }
    // selected employee menu
    public static void activeEmployeeMenu()
    {
        System.out.println("\nPlease, enter an Option:"
                + "\n\n1 - View Employee Details"
                + "\n2 - View Employee Earnings"
                + "\n3 - Back");
    }
    // register employee menu
    public static void askEmployeeType()
    {
        System.out.println("\nPlease, enter an Option:"
                + "\n\n1 - Register new Salary Employee"
                + "\n2 - Register new Hourly Employee"
                + "\n3 - Register new Commission Employee"
                + "\n4 - Cancel and Back");
    }
    // search employee menu
    public static void searchMenuMessage() 
    {
    	System.out.println("\nPlease, enter an Option: ");
    	System.out.println("\n1. Search by list"
                + "\n2. Search by name"
                + "\n3. Back to main menu");
    }
    
    // End of menu messages \\
    
    // ****** ERROR MESSAGES ****** \\
    public static void errInvalidInput()
    {
        System.out.println("Invalid input! Please, try again.");
    }     
    
    public static void errNoEmptyString()
    {
        System.out.println("Empty strings are not accepted! Please, try again.");
    }
    
    public static void errInvalidPhoneFormat()
    {
        System.out.println("Invalid phone number! Please, try again.");
    }
    
    public static void errInvalidSin()
    {
        System.out.println("Invalid SIN! SIN should be 9 digits long");
    }
    
    public static void errInvalidYear()
    {
        System.out.println("Invalid year! Please, try again.");
    }
    
    public static void errInvalidMonth()
    {
        System.out.println("Invalid month! Please, try again.");
    }
    
    public static void errInvalidDay()
    {
        System.out.println("Invalid day! Please, try again.");
    }
    
    public static void errInvalidSalary()
    {
        System.out.println("Input does not meet minimum wage! "
                + "\nMinimum wage is 1811.33."
                + "\n Please, try again.");
    }
    
    public static void errInputOutOfRangeMessage() 
    {
    	System.out.println("\nInvalid input. Number is out of valid range.\n");
    }
    
    public static void errInvalidHourlyRate()
    {
        System.out.println("Input does not meet mininum hourly rate!"
                + "\nMinimum rate is 11.40."
                + "\nPlease, try again.");
    }
    
    public static void errInvalidCommissionRate(double payRate)
    {
        if(payRate < 1)
        {
            System.out.println("Invalid Commission Rate! "
                    + "\nMinimum rate is 1."
                    + "\nPlease, try again.");
        } 
        else
        {
            System.out.println("Invalid Commission Rate! "
                    + "\nMaximum rate is 20."
                    + "\nPlease, try again.");
        }
    }
    
    // ****** GENERAL SERVICE METHODS ****** \\
    
    // checks to see if string entered by user is empty
    public static boolean stringCheck(String testString)
    {
        return testString.trim().isEmpty();
    }
    
    // This method validates a few phone number formats using the String class "matches()" that uses regex as param
    public static boolean phoneNumFormatCheck(String phoneNumber)
    {
        //validate phone numbers of format "1234567890"
        if (phoneNumber.matches("\\d{10}"))
        {
            return true;
        }
        //validate phone numbers of format "123-456-7890" (-, or . or spaces are accepted)
        else if(phoneNumber.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}"))
        {
            return true;
        }
        //validate phone number of format "(123)-456-7890"
        else if(phoneNumber.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) 
        {
            return true;
        }
        //validate phone number of format "(123) 456 7890"
        else if(phoneNumber.matches("\\(\\d{3}\\)[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) 
        {
            return true;
        }
        //return false if nothing matches the input
        else 
        {
            return false;
        }
    }    
    
    // create a new employee object
    public static Employee createEmployee()
    {
        Scanner read = new Scanner(System.in);      
                       
        boolean keepAsking = true;       
        
        do            
        {
            // get employee type from user
            askEmployeeType();
            promptUser();
            int input = read.nextInt();
            
            // validade input (no try/catch, exceptions not handled)
            if(input < 1 || input > 4)
            {
                errInvalidInput();
            }
            // back to previous menu
            else if(input == 4)
            {
                keepAsking = false;
            }
            
            else 
            {
                // calls returnEmployee method that return an employee and assigns it to Employee type variable
                Employee newEmployee = returnEmployee(input);
                // displays success message
                System.out.println("\nNew employee " + newEmployee.getFullName() + " successfully registered.");
                // returns employee
                return newEmployee;                       
            }
    
        } while(keepAsking);     
        
        return null; 
     
    }
    // return employee object acording to type sent as param
    private static Employee returnEmployee(int employeeType)
    {   
        Scanner read = new Scanner(System.in);
        // method scope variables
        String firstName, lastName, address, phoneNumber, gender, 
                department, status, position;
        int sin, year, month, day;
        double payRate;
        
        boolean inputSuccess = false;
        
        System.out.println("\nPlease, enter the following required fields:");
        
        // ****** USER INPUT ****** \\
        
        // get first name - keeps asking if input is empty string
    	do 
        {
            inputSuccess = false;
            System.out.print("First Name: ");
            firstName = read.nextLine();
            if(stringCheck(firstName)) { 
                errNoEmptyString();
            }
            else inputSuccess = true;
    	} 
        while(!inputSuccess);
        
        // get last name - keeps asking if input is empty string
    	do 
        {
            inputSuccess = false;
            System.out.print("Last Name: ");
            lastName = read.nextLine();
            if(stringCheck(lastName)) { 
                errNoEmptyString();
            }
            else inputSuccess = true;
    	} 
        while(!inputSuccess);
        
        // get gender 
        do 
        {
            inputSuccess = false;
            System.out.print("Gender: ");
            gender = read.nextLine();
            if(stringCheck(gender)) { // keeps asking if input is empty string
                errNoEmptyString();
            }
            // input has to be either 'm', 'f', 'male' or 'female'
            else if(gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female")
                    || gender.equalsIgnoreCase("m") || gender.equalsIgnoreCase("f"))
            {
                // changes m to male and f to female for consistency.
                if(gender.equalsIgnoreCase("m"))
                {
                    gender = "Male";
                } 
                else if (gender.equalsIgnoreCase("f"))
                {
                    gender = "Female";
                }
                inputSuccess = true;
            }   
            // error message if not valid input
            else if(!gender.equalsIgnoreCase("male") || !gender.equalsIgnoreCase("female")
                    ||!gender.equalsIgnoreCase("m") || !gender.equalsIgnoreCase("f")) 
            {
                System.out.println("Please enter either 'male' or 'female'");
            }
    	} 
        while(!inputSuccess);
        
        // get address - keeps asking if input is empty string
    	do 
        {
            inputSuccess = false;
            System.out.print("Address: ");
            address = read.nextLine();
            if(stringCheck(address)) { 
                errNoEmptyString();
            }
            else inputSuccess = true;
    	} 
        while(!inputSuccess);
        
        // get phone number 
    	do 
        {
            inputSuccess = false;
            System.out.print("Phone Number: ");
            phoneNumber = read.nextLine();
            if(stringCheck(phoneNumber)) // validation for empty string
            {
                errNoEmptyString();
            }
            else if(!phoneNumFormatCheck(phoneNumber)) // checks for valid phone number format
            { 
                errInvalidPhoneFormat();
            }
            else inputSuccess = true;
    	} 
        while(!inputSuccess);
        
        
        
        // get department - keeps asking if input is empty string
    	do 
        {
            inputSuccess = false;
            System.out.print("Department: ");
            department = read.nextLine();
            if(stringCheck(department)) { 
                errNoEmptyString();
            }
            else inputSuccess = true;
    	} 
        while(!inputSuccess);
        
        // get position - keeps asking if input is empty string
    	do 
        {
            inputSuccess = false;
            System.out.print("Position: ");
            position = read.nextLine();
            if(stringCheck(position)) { 
                errNoEmptyString();
            }
            else inputSuccess = true;
    	} 
        while(!inputSuccess);
        
        // get status 
        do 
        {
            inputSuccess = false;
            System.out.print("Status (Enter 'A' for Active or 'I' for Inactive): ");
            status = read.nextLine();
            if(stringCheck(status)) { // keeps asking if input is empty string
                errNoEmptyString();
            }
            // Validates input, has to be either A or I
            else if(status.equalsIgnoreCase("A") || status.equalsIgnoreCase("I"))                    
            {
                // If A, status = active
                if(status.equalsIgnoreCase("A"))
                {
                    status = "Active";
                } 
                // if I, status = inactive 
                else if(status.equalsIgnoreCase("I"))
                {
                    status = "Inactive";
                }
                inputSuccess = true;
            }              
            else if(!status.equalsIgnoreCase("A") || !status.equalsIgnoreCase("I"))                  
            {
                System.out.println("Please enter either 'A' for 'active' or 'I' for Inactive");
            }
    	} 
        while(!inputSuccess);
        
        // get sin
    	do {    		
            inputSuccess = false;
            System.out.print("SIN (no spaces or dashes):");
            sin = read.nextInt();
            // sin has to be 9 digits long
            if(sin < 100000000 || sin > 999999999) {
                errInvalidSin();
            }
            else inputSuccess = true;    		
    	} 
        while(!inputSuccess);
        
        // get year of birthday
    	do {    		
            inputSuccess = false;
            System.out.print("Year of birth (yyyy): ");
            year = read.nextInt();
            // validation, accepts ints from 1850 to 2016 (no exception handling)
            if(year < 1850 || year > 2016) 
            {
                errInvalidYear();
            }
            else inputSuccess = true;    		
    	} 
        while(!inputSuccess);
    	
    	// get month of birthday
    	do {    		
            inputSuccess = false;
            System.out.print("Month of birth (mm): ");
            month = read.nextInt();
            // validation, accepts int from 1 to 12 (no exception handling)
            if(month < 1 || month > 12) {
                errInvalidMonth();
            }
            else inputSuccess = true;    		
    	} 
        while(!inputSuccess);
    	
    	// get day of birthday
    	do {    		
            inputSuccess = false;
            System.out.print("Day of birth (dd): ");
            day = read.nextInt();
            if(day < 1 || day > 31) {
                errInvalidDay();
            }
            else inputSuccess = true;    		
    	} 
        while(!inputSuccess);
        
        // get payRate
    	do {    		
            inputSuccess = false;
            // specific message and validation based on employee type
            // salary employee
            if(employeeType == 1){
                System.out.print("Employee Salary: ");
                payRate = read.nextDouble();
                if(payRate < 1811.33) { // based on https://en.wikipedia.org/wiki/Minimum_wage_in_Canada
                    errInvalidSalary();
                }
                else inputSuccess = true;    
            }
            // hourly employee
            else if(employeeType == 2)
            {
                System.out.print("Employee Hourly Pay Rate: ");
                payRate = read.nextDouble();
                if(payRate < 11.40) { // Ontario min hourly rate
                    errInvalidHourlyRate();
                }
                else inputSuccess = true;    
            }
            // commission employee
            else 
            {
                System.out.print("Employee Commission % (Enter a number between 1 and 20):"); // considering that 1% is min and 20% is max commission acording to company policy.
                payRate = read.nextDouble();
                if(payRate < 1.00 || payRate > 20.00) { 
                    errInvalidCommissionRate(payRate);
                }
                else {
                    payRate = payRate/100;
                    inputSuccess = true;
                }    
            }
            		
    	} 
        while(!inputSuccess);        
        
        
        // instantiate employee object based on employee type
        // create salary employee
        if(employeeType == 1)
        {
            return new SalaryEmployee(firstName,lastName,address,phoneNumber,
                123456678,gender,department,position,status ,payRate, year, month, day);
        } 
        // create hourly employee
        else if(employeeType == 2)
        {
            return new HourlyEmployee(firstName,lastName,address,phoneNumber,
                123456678,gender,department,position,status ,payRate, year, month, day);
        } 
        // create commission employee
        else
        {
            return new CommissionEmployee(firstName,lastName,address,phoneNumber,
                123456678,gender,department,position,status ,payRate, year, month, day);
        }
        
        
    }
}
