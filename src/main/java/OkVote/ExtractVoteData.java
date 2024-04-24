package OkVote;

import OkVote.StephenDoherty.dohertsFileDownloader;

import java.util.ArrayList;

public class ExtractVoteData {
    
    public ArrayList<String> ListOfBillNumbers = new ArrayList<String>();
    public dohertsFileDownloader CommitteeExcelFileDownloader;
    public ExtractVoteData()
    {
        CommitteeExcelFileDownloader = new dohertsFileDownloader();
        
    }
    public void Run()
    {
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
            
            // Tasmin's Code To Save the list of bills with their details to CSV file
            //<Tasmin's Code Start Here>
            writeToCsv("SomeFilePath.csv", ListOfVoteDataToSaveToCsvFile);
            // <End Tasmin's Code>
            
            
            System.out.println("ExtractVoteData Finished Running");
            
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}

public static void writeToCSV(String csvFilePath, VoteData voteData) {
        if (voteData == null) {
            System.err.println("Cannot write data to CSV as the vote data is null.");
            return;
        }

        // CSV headings
        String[] headings = {"Unique Index", "Bill Number", "Type", "Aye", "Nay", "CP", "Excused", "Vacant", "Result", "Location", "Date", "Time"};

        // Generating unique indices for each row
        List<Integer> uniqueIndices = new ArrayList<>();
        for (int i = 1; i <= voteData.billNumbers.size(); i++) {
            uniqueIndices.add(i);
        }

        List<String[]> data = new ArrayList<>();
        data.add(headings);

        // Constructing CSV rows
        for (int i = 0; i < voteData.billNumbers.size(); i++) {
            String[] row = {
                    String.valueOf(uniqueIndices.get(i)),
                    voteData.billNumbers.get(i),
                    (i < voteData.voteTypes.size()) ? voteData.voteTypes.get(i) : "N/A",
                    (i < voteData.yeas.size()) ? voteData.yeas.get(i) : "N/A",
                    (i < voteData.nays.size()) ? voteData.nays.get(i) : "N/A",
                    (i < voteData.cp.size()) ? voteData.cp.get(i) : "N/A",
                    (i < voteData.excused.size()) ? voteData.excused.get(i) : "N/A",
                    (i < voteData.vacant.size()) ? voteData.vacant.get(i) : "N/A",
                    (i < voteData.results.size()) ? voteData.results.get(i) : "N/A",
                    (i < voteData.locations.size()) ? voteData.locations.get(i) : "N/A",
                    (i < voteData.dates.size()) ? voteData.dates.get(i) : "N/A",
                    (i < voteData.times.size()) ? voteData.times.get(i) : "N/A"
            };
            data.add(row);
        }

        try (FileWriter writer = new FileWriter(csvFilePath)) {
            // Writing data to CSV file
            for (String[] row : data) {
                writer.append(String.join(",", row));
                writer.append("\n");
            }
            System.out.println("CSV file '" + csvFilePath + "' has been created with the data.");
        } catch (IOException e) {
            System.err.println("Failed to write data to CSV: " + e.getMessage());
        }
    }
