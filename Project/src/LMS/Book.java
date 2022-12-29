import java.io.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Book {

    private int bookID;           // ID given by a library
    private String title;         // Title of a book
    private String subject;       // Subject to which a book is related
    private String author;        // Author of book
    private boolean isIssued;
    private HoldRequestOperations holdRequestsOperations =new HoldRequestOperations();
    static int currentIdNumber = 0;
  
    public Book(int id,String t, String s, String a, boolean issued)
    {
        currentIdNumber++;
        if(id==-1)
        {
            bookID = currentIdNumber;
        }
        else
            bookID=id;
        
        title = t;
        subject = s;
        author = a;
        isIssued = issued;

    }

    public void printHoldRequests()
    {
        if (!holdRequestsOperations.holdRequests.isEmpty())
        { 
            System.out.println("\nHold Requests are: ");
            
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");            
            System.out.println("No.\t\tBook's Title\t\t\tBorrower's Name\t\t\tRequest Date");
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
            
            for (int i = 0; i < holdRequestsOperations.holdRequests.size(); i++)
            {                      
                System.out.print(i + "-" + "\t\t");
                holdRequestsOperations.holdRequests.get(i).print();
            }
        }
        else
            System.out.println("\nNo Hold Requests.");                                
    }
    public void printInfo()
    {

        System.out.println(title + "\t\t\t" + author + "\t\t\t" + subject);
    }

    public void changeBookInfo() throws IOException
    {
        Scanner scanner = new Scanner(System.in);
        String input;
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.println("\nUpdate Author? (y/n)");
        input = scanner.next();
        
        if(input.equals("y"))
        {
            System.out.println("\nEnter new Author: ");
            author = reader.readLine();
        }

        System.out.println("\nUpdate Subject? (y/n)");
        input = scanner.next();
        
        if(input.equals("y"))
        {
            System.out.println("\nEnter new Subject: ");
            subject = reader.readLine();
        }

        System.out.println("\nUpdate Title? (y/n)");
        input = scanner.next();
        
        if(input.equals("y"))
        {
            System.out.println("\nEnter new Title: ");
            title = reader.readLine();
        }        
        
        System.out.println("\nBook is successfully updated.");
        
    }

    
    public String getTitle()
    {
        return title;
    }

    public String getSubject()
    {
        return subject;
    }

    public String getAuthor()
    {
        return author;
    }
    
    public boolean getIssuedStatus()
    {
        return isIssued;
    }
    
    public void setIssuedStatus(boolean s)
    {
        isIssued = s;
    }
    
     public int getID()
    {
        return bookID;
    }
     
     public ArrayList<HoldRequest> getHoldRequests()
    {
        return holdRequestsOperations.holdRequests;
    }


    public static void setIDCount(int n)
    {
        currentIdNumber = n;
    }
    


    public void placeBookOnHold(Borrower bor)
    {
        HoldRequest hr = new HoldRequest(bor,this, new Date());

        holdRequestsOperations.addHoldRequest(hr);
        bor.addHoldRequest(hr);
        
        System.out.println("\nThe book " + title + " has been successfully placed on hold by borrower " + bor.getName() + ".\n");
    }
    

    public void makeHoldRequest(Borrower borrower)
    {
        boolean makeRequest = true;
        for(int i=0;i<borrower.getBorrowedBooks().size();i++)
        {
            if(borrower.getBorrowedBooks().get(i).getBook()==this)
            {
                System.out.println("\n" + "You have already borrowed " + title);
                return;                
            }
        }

        for (int i = 0; i < holdRequestsOperations.holdRequests.size(); i++)
        {
            if ((holdRequestsOperations.holdRequests.get(i).getBorrower() == borrower))
            {
                makeRequest = false;    
                break;
            }
        }

        if (makeRequest)
        {
            placeBookOnHold(borrower);
        }
        else
            System.out.println("\nYou already have one hold request for this book.\n");
    }

    public void serviceHoldRequest(HoldRequest hr)
    {
        holdRequestsOperations.removeHoldRequest();
        hr.getBorrower().removeHoldRequest(hr);
    }


    public void issueBook(Borrower borrower, Staff staff)
    {
        Date today = new Date();        
        
        ArrayList<HoldRequest> hRequests = holdRequestsOperations.holdRequests;
        
        for (int i = 0; i < hRequests.size(); i++)
        {
            HoldRequest hr = hRequests.get(i);
            long days =  ChronoUnit.DAYS.between(today.toInstant(), hr.getRequestDate().toInstant());        
            days = 0-days;
            
            if(days>Library.getInstance().getHoldRequestExpiry())
            {
                holdRequestsOperations.removeHoldRequest();
                hr.getBorrower().removeHoldRequest(hr);
            } 
        }
               
        if (isIssued)
        {
            System.out.println("\nThe book " + title + " is already issued.");
            System.out.println("Would you like to place the book on hold? (y/n)");
             
            Scanner sc = new Scanner(System.in);
            String choice = sc.next();
            
            if (choice.equals("y"))
            {                
                makeHoldRequest(borrower);
            }
        }
        
        else
        {               
            if (!holdRequestsOperations.holdRequests.isEmpty())
            {
                boolean hasRequest = false;
                
                for (int i = 0; i < holdRequestsOperations.holdRequests.size() && !hasRequest;i++)
                {
                    if (holdRequestsOperations.holdRequests.get(i).getBorrower() == borrower)
                        hasRequest = true;
                        
                }
                
                if (hasRequest)
                {
                    if (holdRequestsOperations.holdRequests.get(0).getBorrower() == borrower)
                        serviceHoldRequest(holdRequestsOperations.holdRequests.get(0));

                    else
                    {
                        System.out.println("\nSorry some other users have requested for this book. So you have to wait until their hold requests are processed.");
                        return;
                    }
                }
                else
                {
                    System.out.println("\nSome users have already placed this book on request, so the book can't be issued to you.");
                    
                    System.out.println("Would you like to place the book on hold? (y/n)");

                    Scanner sc = new Scanner(System.in);
                    String choice = sc.next();
                    
                    if (choice.equals("y"))
                    {
                        makeHoldRequest(borrower); 
                    }                    
                    
                    return;
                }               
            }
            setIssuedStatus(true);
            
            Loan iHistory = new Loan(borrower,this,staff,null,new Date(),null,false);
            
            Library.getInstance().addLoan(iHistory);
            borrower.addBorrowedBook(iHistory);
                                    
            System.out.println("\nThe book " + title + " is successfully issued to " + borrower.getName() + ".");
            System.out.println("\nIssued by: " + staff.getName());            
        }
    }

    public void returnBook(Borrower borrower, Loan l, Staff staff)
    {
        l.getBook().setIssuedStatus(false);        
        l.setReturnedDate(new Date());
        l.setReceiver(staff);        
        
        borrower.removeBorrowedBook(l);
        
        l.payFine();
        
        System.out.println("\nThe book " + l.getBook().getTitle() + " is successfully returned by " + borrower.getName() + ".");
        System.out.println("\nReceived by: " + staff.getName());            
    }
}