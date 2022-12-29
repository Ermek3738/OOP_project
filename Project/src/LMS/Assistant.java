public class Assistant extends Staff {
  
    int deskNo;     //Desk Number of the Assistant
    public static int currentDeskNumber = 0;
  
    public Assistant(int id, String n, String a, int ph, double s, int dk)
    {
        super(id,n,a,ph,s);
        
        if(dk == -1)
        {
            deskNo = currentDeskNumber;
        }
        else
        {
            deskNo=dk;
        }
        
        currentDeskNumber++;
    }
    
    // Printing Assistant's Info
    @Override
    public void printInfo()
    {
        super.printInfo();
        System.out.println("Desk Number: " + deskNo);
    }
}