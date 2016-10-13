
package hr;

public class SalaryEmployee extends Employee 
{
    // ****** CONSTRUCTOR ****** \\
    // default constructor
    public SalaryEmployee(){}
    
    public SalaryEmployee(String firstName, String lastName, String address, 
            String phoneNumber, int sin, String gender, String department, 
            String position, String status, double payRate,  
            int year, int month, int day) 
    {
        super(firstName, lastName, address, phoneNumber, sin, gender, 
                department, position, status, payRate, year, month, day);
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
        employeeInfo+= "\nContract:\tSalary Employee";
        employeeInfo+= "\nSalary:\t\t" + "$" + getPayRate() + " / month";
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
    
    // Calculate earnings
    @Override
    public double calculateEarnings()
    {
        return super.getPayRate();
    }
    
    
}
