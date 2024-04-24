/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OkVote.VardhanModuga;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.File;
import org.jsoup.nodes.Document;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author vardh
 */
public class VoteDetailExtractor {
     public static void main(String[] args) {
      var filePath = "C:\\Users\\vardh\\OneDrive - Pittsburg State University\\GRT713_Vardhan\\Java\\mavenproject7\\House Votes.html";  
   try {
    //Reads the html file 
    File htmlFile = new File(filePath);
    Document document = Jsoup.parse(htmlFile, "UTF-8");
    String text = document.text();
        // Creating an instance of the VoteDataExtractor class to extract vote data
        VoteDataExtractor dataExtractor = new VoteDataExtractor();
        votesdetail.VoteData voteData = dataExtractor.extractData(text);

        if (voteData != null) {
            // If vote data is successfully extracted, write it to a CSV file
            CSVWriter.writeToCSV("PerepoVotesDetail.csv", voteData);
            System.out.println("CSV file 'PerepoVotesDetail.csv' has been created with the data.");
            } 
        else {
            System.err.println("Failed to extract data from the HTML file.");
            }
        }
        catch (IOException e) {
            System.err.println("Failed to read the HTML file or process the data: " + e.getMessage());
        }

     }
    
}
     
     
     static class VoteDataExtractor {
        // VoteDataExtracted class responsible for extracting data from webpage
        public votesdetail.VoteData extractData(String text) {
            // Method to extract data from text
            try{
                // To extract the pieces of data using regular expression
                List<String> billNumbers = extractData("HB\\d+", text);
                List<String> voteTypes = extractData("([A-z_]+)\\s*\\(?RCS#\\s*\\d+\\)?", text);
                List<String> yeas = extractData("YEAS:\\s+(\\d+)", text);
                List<String> nays = extractData("NAYS:\\s+(\\d+)", text);
                List<String> cp = extractData("C/P :\\s+(\\d+)", text);
                List<String> excused = extractData(" EXC :\\s+(\\d+)", text);
                List<String> vacant = extractData("VACA:\\s+(\\d+)", text);
                List<String> results = extractData("(PASS|FAIL)", text);
                List<String> locations = extractData("(HOUSE OF|Senate|Commitee)", text);
                List<String> dates = extractData("\\d{1,2}/\\d{1,2}/\\d+)", text);
                List<String> times = extractData("\\b\\d{1,2}:\\d{1,2}(?::\\d{2}?\\s+[APMapm]{2}\\b",text);
                
                // Create instances of Housemembers and house rollcalls class with sample data
                votesdetail.HouseMembers houseMembers = new votesdetail.HouseMembers( Arrays.asList("Member 1", "Member 2", "Member 3"));
                var houseRollCall = new votesdetail.HouseRollCall(Arrays.asList(" Roll Call 1", "Roll Call 2", "Roll Call 3"));
                
                // Return the extracted data as a VoteData object
                 return new votesdetail.VoteData(billNumbers, voteTypes, yeas, nays, cp, excused, vacant, results, locations, dates, times, houseMembers, houseRollCall);
            } catch (Exception e) {
                System.err.println("Error while extracting data: " + e.getMessage());
                return null;
               
            }
            
        }

// Helper method to extract data using regular expressions
    private List<String> extractData(String regex, String text) {
        List<String> dataList = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            dataList.add(matcher.group(0));
        }

        return dataList;
    }
}
}



// Main class 
public class votesdetail {
    public static void main(String[] args) {
      var filePath = "C:\\Users\\vardh\\OneDrive - Pittsburg State University\\GRT713_Vardhan\\Java\\mavenproject7\\House Votes.html";  
   try {
    //Reads the html file 
    File htmlFile = new File(filePath);
    Document document = Jsoup.parse(htmlFile, "UTF-8");
    String text = document.text();
        // Creating an instance of the VoteDataExtractor class to extract vote data
        VoteDataExtractor dataExtractor = new VoteDataExtractor();
        VoteData voteData = dataExtractor.extractData(text);

        if (voteData != null) {
            // If vote data is successfully extracted, write it to a CSV file
            CSVWriter.writeToCSV("PerepoVotesDetail.csv", voteData);
            System.out.println("CSV file 'PerepoVotesDetail.csv' has been created with the data.");
            } 
        else {
            System.err.println("Failed to extract data from the HTML file.");
            }
        }
        catch (IOException e) {
            System.err.println("Failed to read the HTML file or process the data: " + e.getMessage());
        }


    
}


//Class defines the extracted data
static class VoteData {
    // Data fields to store vote information
    public List<String> billNumbers;
    public List<String> voteTypes;
    public List<String> yeas;
    public List<String> nays;
    public List<String> cp;
    public List<String> excused;
    public List<String> vacant;
    public List<String> results;
    public List<String> locations;
    public List<String> dates;
    public List<String> times;
    public HouseMembers houseMembers;
    public HouseRollCall houseRollCall;

    // Constructor to initialize the vote data
    public VoteData(List<String> billNumbers, List<String> voteTypes, List<String> yeas, List<String> nays, List<String> cp, List<String> excused, List<String> vacant, List<String> results, List<String> locations, List<String> dates, List<String> times, HouseMembers houseMembers, HouseRollCall houseRollCall) {
        this.billNumbers = billNumbers;
        this.voteTypes = voteTypes;
        this.yeas = yeas;
        this.nays = nays;
        this.cp = cp;
        this.excused = excused;
        this.vacant = vacant;
        this.results = results;
        this.locations = locations;
        this.dates = dates;
        this.times = times;
        this.houseMembers = houseMembers;
        this.houseRollCall = houseRollCall;
    }
}

// This dummy class represents the HouseMembers
static class HouseMembers {
    public List<String> memberNames;

    // Constructor to initialize the member names
    public HouseMembers(List<String> memberNames) {
        this.memberNames = memberNames;
    }
}

// This dummy class represents the HouseRollCall
static class HouseRollCall {
    public List<String> rollCallNames;

    // Constructor to initialize the roll call names
    public HouseRollCall(List<String> rollCallNames) {
        this.rollCallNames = rollCallNames;
    }
}
}

//links used for extracting data
//https://chat.openai.com/share/5405ec80-50fc-47a0-a0ae-b35f3af24e04
//https://chat.openai.com/share/c702d348-84ac-4416-bc8f-e48ad2f8f260

//usefulwebsites
//*https://www.geeksforgeeks.org/regular-expressions-in-java/ to search patterns in html.
