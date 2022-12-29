
public abstract class Person 
{   
    protected int id;           // ID of every person
    protected String password;  // Password of every person
    protected String name;      // Name of every person
    protected String address;   // Address of every person
    protected int phoneNo;      // PhoneNo of every person
    
    static int currentIdNumber = 0;

    public Person(int idNum, String name, String address, int phoneNum)
    {
        currentIdNumber++;
        
        if(idNum==-1)
        {
            id = currentIdNumber;
        }
        else
            id = idNum;
        
        password = Integer.toString(id);
        this.name = name;
        this.address = address;
        phoneNo = phoneNum;
    }        
    
    // Printing Info of a Person
    public void printInfo()
    {
        System.out.println("-----------------------------------------");
        System.out.println("\nThe details are: \n");
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Address: " + address);
        System.out.println("Phone No: " + phoneNo + "\n");
    }

    public void setAddress(String a)
    {
        address = a;
    }
    
    public void setPhone(int p)
    {
        phoneNo = p;
    }

    public void setName(String n)
    {
        name = n;
    }
    public String getName()
    {
        return name;
    }
    
    public String getPassword()
    {
        return password;
    }
    
     public String getAddress()
    {
        return address;
    }
     
     public int getPhoneNumber()
    {
        return phoneNo;
    }
    public int getID()
    {
        return id;
    }
     public static void setIDCount(int n)
    {
        currentIdNumber=n;
    }
}
