package OkVote;

import OkVote.StephenDoherty.dohertsFileDownloader;
import OkVote.VardhanModuga.VoteData;

import java.util.ArrayList;
import java.util.List;

public class ExtractVoteData {

    public ArrayList<String> ListOfBillNumbers = new ArrayList<String>();
    public ArrayList<VoteData> ListOfVoteDataToSaveToCsvFile = new ArrayList<>();

    public dohertsFileDownloader CommitteeExcelFileDownloader;

    public ExtractVoteData() {
        CommitteeExcelFileDownloader = new dohertsFileDownloader();

    }

    public void Run() {
        try {
            System.out.println("ExtractVoteData is running...");
            // Stephen's code to download the list of bills from the Oklahoma House of Representatives website
            // <Stephen's Code Start Here>
            ListOfBillNumbers = CommitteeExcelFileDownloader.GetCommitteeRecords();
            // <End Stephen's Code>

            // Alexis' Code to take the `ListOfBills`, build the url from each bill, and return the html content to Vardhan as an ArrayList<String>
            //<Alexis' Code Start Here>
            // ...place your code here...
            //<End Alexis' Code>
            // Vardhan and Harshitha's Code to parse the html contents and return the list of bills with their details
            //<Vardhan's Code Start Here>
            ArrayList<String> alexisHtmlFilesToParse = new ArrayList<String>();
            OkVote.VardhanModuga.VoteDetailExtractor voteDetailExtrator = new OkVote.VardhanModuga.VoteDetailExtractor();

            ListOfVoteDataToSaveToCsvFile = voteDetailExtrator.Execute(alexisHtmlFilesToParse);

            // <End Vardhan's Code>
            // Tasmin's Code To Save the ListOfVoteDataToSaveToCsvFile to CSV file
            //<Tasmin's Code Start Here>
            // ...place your code here...
            // <End Tasmin's Code>
            System.out.println("ExtractVoteData Finished Running");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
