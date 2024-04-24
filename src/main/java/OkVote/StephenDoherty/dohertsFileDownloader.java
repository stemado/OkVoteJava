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
        System.out.println("*******************************");
        System.out.println("*** Stephen's Code STARTED ***");
        System.out.println("*******************************");
        
         String filePath = GetCommitteeRecordsFile();
         ArrayList<VoteRecordDto> committeeRecords = ParseCommitteeRecordsFile(filePath);

        System.out.println("Now returning Committee Records File Bills (MeasureNumbers) only");
        System.out.println("*******************************");
        System.out.println("*** Stephen's Code FINISHED ***");
        System.out.println("*******************************");
        return committeeRecords.stream().map(VoteRecordDto::getMeasureNumber).collect(Collectors.toCollection(ArrayList::new));
    }
    
    private ArrayList<VoteRecordDto> ParseCommitteeRecordsFile(String htmlFilePath)
    {
        System.out.println("Now parsing Committee Records File...");
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

            System.out.println("Set-up Playwright to use Chromium and setHeadless to false which allows us to watch the browser automation operate");
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));

            System.out.println("Opening a new Chrome browser web page");
            Page page = browser.newPage();


            System.out.println("Navigating to the Committee Votes page");
            page.navigate(CommitteeVotesUrl);

            System.out.println("Locating the Excel file download button on the page");
            Locator button = page.locator(ExcelFileDownloadButtonId);
            
            System.out.println("Waiting for the button to be ready and visible on the page");
            button.waitFor();

            // Wait for the download to start after clicking the Excel button
            Download download = page.waitForDownload(() -> {
                System.out.println("Clicking the Excel link to download the file");
                button.click();
                
            });

            System.out.println("Getting user's downloads folder path to save the file to");
            String downloadsFolderPath = GetUserDirectoryDownloadsFolderPath();
            
            System.out.println("Getting file name from server");
            suggestedFilename = download.suggestedFilename();
            
            System.out.println("Changing suggestedFileName extension from .xls to .html");
            
            suggestedFilename = suggestedFilename.replace(".xls", ".html");

            System.out.println("Joining `downloadsFolderPath` and `suggestedFilename` to outputFilePath");
            outputFilePath = Paths.get(downloadsFolderPath, suggestedFilename);
            
            System.out.println("Waiting for the download process to complete and save the downloaded file to " + outputFilePath.toString());
            download.saveAs(outputFilePath);

            System.out.println("Finished. Returning downloaded file path");
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
