
package hr;

import general.ServiceClass;
import java.util.Scanner;

public class HourlyEmployee extends Employee {

    // Class variables
    private double hoursWorked;
    
    // ****** CONSTRUCTOR ****** \\
    // default constructor
    public HourlyEmployee(){}
    
    public HourlyEmployee(String firstName, String lastName, String address, 
            String phoneNumber, int sin, String gender, String department, 
            String position, String status, double payRate, 
            int year, int month, int day) 
    {
        super(firstName, lastName, address, phoneNumber, sin, gender, 
                department, position, status, payRate, year, month, day);
        this.hoursWorked = 0;        
    }
    
    // ****** GETTERS ****** \\
    public double getHoursWorked() {
        return hoursWorked;
    }
    
    // ****** SETTERS ****** \\
    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }   
    
    
    
    // ****** OVERRIDEN METHODS ****** \\
    
    //toString method
    @Override
    public String toString()
    {
        String employeeInfo="";

        employeeInfo+= "\nName:\t\t" + getFullName();  
        employeeInfo+= "\nGender:\t\t" + getGender();
        employeeInfo+= "\nAge:\t\t" + getAge();
        employeeInfo+= "\nDepartment:\t" + getDepartment();
        employeeInfo+= "\nPosition:\t" + getPosition();
        employeeInfo+= "\nContract:\tHourly Employee";
        employeeInfo+= "\nPay Rate:\t" + "$" + getPayRate() + " / hour";
        employeeInfo+= "\nEmployee Id:\t" + getEmployeeId();
        employeeInfo+= "\nHire Date:\t" + getDateOfHire();
        
        return employeeInfo;
    }   
    
    // Displays calculated earnings
    @Override
    public String earningsToString()
    {
        double earnings = this.calculateEarnings();
        String earningsDisplay = "";
        
        earningsDisplay += "\nTotal Earnings:\t" + "$" + earnings + " for specified period";
        
        return earningsDisplay;
    }
    
    // this method calculates the salary to be paid based on amount of hours worked. (not considering discounts such as taxes)
    @Override
    public double calculateEarnings()
    {
        this.checkHours();  
        double hours = this.getHoursWorked();
        
        // if hoursWorked exceeds 44, include Overtime Pay
        if(hours > 44)
        {
            return (super.getPayRate() * 44) + ((hours - 44) * super.getPayRate() * 1.5) ;
        }
        // else, earnings = amount of hours worked in the week x pay rate.
        else 
        {
            return super.getPayRate()*this.getHoursWorked();
        }         
        
    }
    
    // ***** Private Methods ***** \\
    
    // checks if hoursWorked is equals to 0.
    private void checkHours()
    {
        // if equals to 0, set worked hours 
        if(this.getHoursWorked() == 0)
        {
            boolean keepGoing = true;
            double hours;
            Scanner scan = new Scanner(System.in);
            // keep asking user to input hours until input is valid (not validating in case of string)
            do
            {
                System.out.println("Please, input amount of hours worked for desired WEEK: ");
                ServiceClass.promptUser();
                hours = scan.nextDouble(); // we are not supposed to use try / catches so I'm not validating for strings
                // must be a double greater than or equals to 0 and 84 max. 
                // (I decided to accept 0 as well, I don't want to force the user to add hours in case he "missclicked" the calculate earnings button.)
                if(hours >= 0 && hours <= 84) // I'm considering that the hourly employee has it's earnings calculated weekly and he cannot exceed 84 hours (12/day if working 7 days).
                {
                    this.setHoursWorked(hours);
                    keepGoing = false;
                }
                else if(hours < 0)
                {
                    System.out.println("Invalid input! Please, enter a number greater than or equals to 0.");
                } 
                else 
                {
                    System.out.println("invalid input! The maximum number allowed is 84.");
                }
            } while(keepGoing);
        }
    }
    
    
}
