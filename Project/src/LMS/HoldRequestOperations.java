
import java.util.ArrayList;

public class HoldRequestOperations {

   static ArrayList <HoldRequest> holdRequests;

    public HoldRequestOperations()
    {
        holdRequests= new ArrayList<>();
    }

    public void addHoldRequest(HoldRequest hr)
    {
        holdRequests.add(hr);
    }

    public void removeHoldRequest()
    {
        if(!holdRequests.isEmpty())
        {
            holdRequests.remove(0);
        }
    }
}
