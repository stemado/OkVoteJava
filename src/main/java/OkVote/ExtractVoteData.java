package OkVote;

import OkVote.StephenDoherty.dohertsFileDownloader;

import java.util.ArrayList;

public class ExtractVoteData {
    
    public ArrayList<String> ListOfBillNumbers = new ArrayList<String>();
    
    public void Run()
    {
        try {
            // Stephen's code to download the list of bills from the Oklahoma House of Representatives website
            // <Stephen's Code Start Here>
            dohertsFileDownloader committeeExcelFileDownloader = new dohertsFileDownloader();
            
            ListOfBillNumbers = committeeExcelFileDownloader.GetCommitteeRecords();
            // <End Stephen's Code>
            
            // Alexis' Code to take the `ListOfBills`, build the url from each bill, and return the html content to Vardhan as an ArrayList<String>
            //<Alexis' Code Start Here>
            // ...place your code here...
            //<End Alexis' Code>
            
            // Vardhan and Harshitha's Code to parse the html contents and return the list of bills with their details
            //<Vardhan's Code Start Here>
            // ...place your code here...
            // <End Vardhan's Code>
            
            // Tasmin's Code To Save the list of bills with their details to CSV file
            //<Tasmin's Code Start Here>
            // ...place your code here...
            // <End Tasmin's Code>
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
