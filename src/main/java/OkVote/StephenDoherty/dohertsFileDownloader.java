/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OkVote.StephenDoherty;

import OkVote.StephenDoherty.Classes.VoteRecordDto;
import com.microsoft.playwright.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;
/**
 *
 * @author sdoherty
 * https://playwright.dev/java/docs/browsers#install-browsers
 * https://playwright.dev/java/docs/downloads#introduction
 * https://chat.openai.com/share/e/9b46d859-f39a-4829-a682-8e6e1d6e23ea
 */
public class dohertsFileDownloader {
    public String CommitteeVotesUrl = "https://former.okhouse.gov/Committees/ShowVotes.aspx";
    private String ExcelFileDownloadButtonId = "button#ctl00_ContentPlaceHolder1_RadGrid1_ctl00_ctl02_ctl00_ExportToExcelButton";
   
    public ArrayList<String> GetCommitteeRecords()
    {
         String filePath = GetCommitteeRecordsFile();
         ArrayList<VoteRecordDto> committeeRecords = ParseCommitteeRecordsFile(filePath);
         
        return committeeRecords.stream().map(VoteRecordDto::getMeasureNumber).collect(Collectors.toCollection(ArrayList::new));
    }
    
    private ArrayList<VoteRecordDto> ParseCommitteeRecordsFile(String htmlFilePath)
    {
        ArrayList<VoteRecordDto> voteRecords = new ArrayList<>();

        try {
            Document doc = Jsoup.parse(new File(htmlFilePath), "UTF-8");

            Element table = doc.select("table").first();
            Elements rows = table.select("tbody tr");

            for (Element row : rows) {
                Elements cells = row.select("td");
                if (cells.size() >= 7) { // Ensure there are enough cells in the row
                    VoteRecordDto record = new VoteRecordDto(
                            cells.get(1).text(), // Date of Vote
                            cells.get(2).text(), // Committee
                            cells.get(3).text(), // Measure #
                            cells.get(4).text(), // Information
                            cells.get(5).text(), // Author
                            cells.get(6).text(), // Action
                            cells.get(7).text()  // Yea/Nay
                    );
                    voteRecords.add(record);
                }
            }

            System.out.println("Parsed " + voteRecords.size() + " records successfully!");
            // You can now use the voteRecords list for further processing

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return voteRecords;
    }

    private String GetCommitteeRecordsFile() {
        String suggestedFilename = "";
        Path outputFilePath;
        
        try (Playwright playwright = Playwright.create()) {
            // Set-up Playwright to use Chromium and setHeadless to false which allows us to watch the browser automation operate
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
            
            // Open a new Chrome browser page
            Page page = browser.newPage();
            
            // Navigate to the Committee Votes page
            page.navigate(CommitteeVotesUrl);

            // Locate the Excel file download button on the page
            Locator button = page.locator(ExcelFileDownloadButtonId);
            
            // Wait for the button to be ready and visible on the page
            button.waitFor();

            // Wait for the download to start after clicking the Excel button
            Download download = page.waitForDownload(() -> {
                // click the Excel link to download the file
                button.click();
                
            });

            // Get user's downloads folder path to save the file to
            // Call GetUserDirectoryDownloadsFolderPath() from constructor
            
            String downloadsFolderPath = GetUserDirectoryDownloadsFolderPath();
            
            // Get suggested file name which is the name of the file being downloaded
            suggestedFilename = download.suggestedFilename();
            
            // Change suggestedFileName extension from .xls to .html
            suggestedFilename = suggestedFilename.replace(".xls", ".html");
            
            // Join downloadsFolderPath, suggestedFilename to outputFilePath
            outputFilePath = Paths.get(downloadsFolderPath, suggestedFilename);
            
            // Wait for the download process to complete and save the downloaded file somewhere
            download.saveAs(outputFilePath);

            return outputFilePath.toString();
            
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        return "File not found";
    }

    private String GetUserDirectoryDownloadsFolderPath()
    {
        // Get the user's home directory
        String userHome = System.getProperty("user.home");

        // Append the Downloads directory path
        Path downloadsPath = Paths.get(userHome, "Downloads");
        
        return downloadsPath.toString();
    }
}
