
package hr;

import general.ServiceClass;
import java.util.Scanner;

public class CommissionEmployee extends Employee {
    // class variables
    private double totalSales, hoursWorked;
    
    // ****** CONSTRUCTOR ****** \\
    // default constructor
    public CommissionEmployee(){}
    
    public CommissionEmployee(String firstName, String lastName, String address, 
            String phoneNumber, int sin,String gender,
            String department, String position,String status,
            double payRate, int year, int month, int day)
    {
        super(firstName, lastName, address, phoneNumber, sin, gender, 
                department, position, status, payRate, year, month, day);
        this.totalSales = 0;
        this.hoursWorked = 0;
    }
    
    // ****** GETTERS ****** \\
    public double getHoursWorked() 
    {
        return hoursWorked;
    }
    public double getTotalSales() 
    {
        return totalSales;
    }
    
    // ****** SETTERS ****** \\
    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }    
    public void setTotalSales(double totalSales) {
        this.totalSales = totalSales;
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
        employeeInfo+= "\nContract:\tCommission Employee";
        employeeInfo+= "\nCommission:\t" + getPayRate()*100 + "%";
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
        
        earningsDisplay += "\nTotal Earnings:\t" + "$" + earnings;
        
        return earningsDisplay;
    }
    
    // this method calculates the salary to be paid based on commission AND amount of hours worked. (not considering discounts such as taxes)
    // Based on: https://www.labour.gov.on.ca/english/es/pubs/guide/minwage.php under "Minimum Wage Calculation for Employees Who Earn Commission"
    @Override
    public double calculateEarnings()
    {
        this.checkHours();  
        
        this.checkTotalSales();
        
        if(this.getMinimumEarnings() > this.getCommissionEarnings())
        {
            return this.getMinimumEarnings();
        }
        else 
        {
            return this.getCommissionEarnings();
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
                // must be a double greater than 0 and 84 max.
                if(hours > 0 && hours <= 84) // I'm considering that the hourly employee has it's earnings calculated weekly and he cannot exceed 84 hours (12/day if working 7 days).
                {
                    this.setHoursWorked(hours);
                    keepGoing = false;
                }
                else if(hours <= 0)
                {
                    System.out.println("Invalid input! Please, enter a number greater than 0.");
                } 
                else 
                {
                    System.out.println("invalid input! The maximum number allowed is 84.");
                }
            } while(keepGoing);
        }
    }
    
    // checks if totalSales is equals to 0.
    private void checkTotalSales()
    {
        // if equals to 0, set totalSales
        if(this.getTotalSales() == 0)
        {
            boolean keepGoing = true;
            double sales;
            Scanner scan = new Scanner(System.in);
            // keep asking user to input hours until input is valid (not validating in case of string)
            do
            {
                System.out.println("Please, input Total Sales value: ");
                sales = scan.nextDouble(); // we are not supposed to use try / catches so I'm not validating for strings
                // cannot be a negative number
                if(sales >= 0) 
                {
                    this.setTotalSales(sales);
                    keepGoing = false;
                }
                else
                {
                    System.out.println("Invalid input! Please, enter a number greater than or equals to 0.");
                } 
            } while(keepGoing);
        }
    }
    
    // returns minimum earnings (hours worked * minimum wage)
    private double getMinimumEarnings()
    {
        double minWage = 11.40;
        
        // if hoursWorked exceeds 44, include Overtime Pay
        if(this.getHoursWorked() > 44)
        {
            return (minWage * 44) + ((this.getHoursWorked() - 44) * minWage * 1.5) ;
        }
        // else, earnings = amount of hours worked in the week x pay rate.
        else 
        {
            return minWage * this.getHoursWorked();
        }       
    }
    
    // returns commission based earnings
    private double getCommissionEarnings()
    {
        return this.getPayRate() * this.getTotalSales();           
    }
    
    
}
