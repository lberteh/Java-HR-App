
package hr;
import general.ServiceClass;
import java.util.*;

public abstract class Employee 
{
    
    //superclass variables 
    private String firstName, lastName,position, status, gender,
            address, phoneNumber, department;
    private int empId, sin;
    private GregorianCalendar dateOfBirth;
    private Date dateOfHire; 
    private double payRate;
    
    // ****** CONSTRUCTOR ****** \\
    // default constructor
    public Employee(){}
    
    public Employee(String firstName, String lastName, String address, 
            String phoneNumber, int sin,String gender,
            String department, String position,String status,
            double payRate, int year, int month, int day)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.sin = sin;
        this.gender = gender;
        this.dateOfHire = new GregorianCalendar().getTime();
        //set the date of birth
        this.dateOfBirth = new GregorianCalendar();
        this.dateOfBirth.set(Calendar.YEAR, year);
        this.dateOfBirth.set(Calendar.MONTH, month-1);
        this.dateOfBirth.set(Calendar.DATE,day);
        
        this.department = department;
        this.position = position;
        this.status = status;
        this.payRate = payRate;
        empId = ServiceClass.getEmployeeId();
    }
    
    //****** GETTERS ******\\
    
    public String getFirstName()
    {
        return this.firstName;
    }
    public String getLastName()
    {
        return this.lastName;
    }
    public String getFullName()
    {
        return this.firstName + " " + this.lastName;
    }
    public String getAddress()
    {
        return this.address;
    }
    public String getPhoneNumber()
    {
        return this.phoneNumber;
    }
    public int getSin()
    {
        return this.sin;
    }
    public String getGender()
    {
        return this.gender;
    }
    public String getDepartment()
    {
        return this.department;
    }
    public String getPosition()
    {
        return this.position;
    }
    public int getEmployeeId()
    {
        return this.empId;
    }
    public String getEmployeeStatus()
    {
        return this.status;
    }
    public double getPayRate()
    {
        return this.payRate;
    }     
    public Date getDateOfBirth()
    {
        return this.dateOfBirth.getTime();
    }       
    public int getAge()
    {     
        Calendar calendar = this.dateOfBirth;
        Calendar now = new GregorianCalendar();
        int age = now.get(Calendar.YEAR) - calendar.get(Calendar.YEAR);
        if ((calendar.get(Calendar.MONTH) > now.get(Calendar.MONTH))
            || (calendar.get(Calendar.MONTH) == now.get(Calendar.MONTH) && 
                calendar.get(Calendar.DAY_OF_MONTH) > now.get(Calendar.DAY_OF_MONTH))) 
        {
            age--;
        }
        
        return age; 
    }
    public Date getDateOfHire()
    {
        return this.dateOfHire;
    }
    
    //****** SETTERS ******\\
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }
    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }
    public void setSin(int sin)
    {
        this.sin = sin;
    }
    public void setGender(String gender)
    {
        this.gender = gender;
    }
    public void setDepartment(String department)
    {
        this.department = department;
    }
    public void setPosition(String position)
    {
        this.position = position;
    }
    public void setEmployeeStatus(String status)
    {
        this.status = status;
    }
    public void setPayRate(double payRate)
    {
        this.payRate = payRate;
    }     
    public void setDateOfBirth(int year,int month, int day)
    {
        this.dateOfBirth = new GregorianCalendar();
        this.dateOfBirth.set(Calendar.YEAR, year);
        this.dateOfBirth.set(Calendar.MONTH,month-1);
        this.dateOfBirth.set(Calendar.DATE,day);
    }       
    public void setDateOfHire(int year,int month, int day)
    {
        GregorianCalendar tempDate = new GregorianCalendar();
        tempDate.set(Calendar.YEAR, year);
        tempDate.set(Calendar.MONTH,month-1);
        tempDate.set(Calendar.DATE,day);
        
        this.dateOfHire = tempDate.getTime();
    }
    
    // ****** ABSTRACT METHODS ****** \\
    //toString method
    @Override
    public abstract String toString();
    public abstract double calculateEarnings();
    public abstract String earningsToString();

    
    
    
  
            
}
