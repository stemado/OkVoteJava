/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OkVote;

import com.microsoft.playwright.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 *
 * @author sdoherty
 * https://playwright.dev/java/docs/browsers#install-browsers
 * https://playwright.dev/java/docs/downloads#introduction
 * https://chat.openai.com/share/e/960a7028-afa0-496c-ab0f-b1439f05ab09
 */
public class BillExcelFileDownloader {
    public String CommitteeVotesUrl = "https://former.okhouse.gov/Committees/ShowVotes.aspx";
    private String ExcelFileDownloadButtonId = "button#ctl00_ContentPlaceHolder1_RadGrid1_ctl00_ctl02_ctl00_ExportToExcelButton";

   
    public ArrayList<String> GetCommitteeRecords()
    {

         String filePath = DownloadCommitteeRecordsFile();
    
         // Default until I finish logic for parsing the file.
        return new ArrayList<String>();
    }
    
    private ArrayList<String> ParseCommitteeRecordsFile(String filePath)
    {
        // Parse the Excel file and return the data as a list of strings
        return new ArrayList<String>();
    }

    private String DownloadCommitteeRecordsFile() {
        String suggestedFilename = "";
        
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
            
            // Wait for the download process to complete and save the downloaded file somewhere
            download.saveAs(Paths.get(downloadsFolderPath, suggestedFilename));
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        return suggestedFilename;
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
