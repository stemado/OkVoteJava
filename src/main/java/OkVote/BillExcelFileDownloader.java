/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OkVote;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import com.microsoft.playwright.*;
/**
 *
 * @author sdoherty
 * https://playwright.dev/java/docs/browsers#install-browsers
 * https://chat.openai.com/share/e/960a7028-afa0-496c-ab0f-b1439f05ab09
 */
public class BillExcelFileDownloader {
    public ArrayList<String> GetCommitteeRecords()
    {
        try (Playwright playwright = Playwright.create()) {
            
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
            Page page = browser.newPage();
            page.navigate("http://playwright.dev");
            // Wait for the download to start
            Download download = page.waitForDownload(() -> {
                // Perform the action that initiates download
                page.getByText("Download file").click();
            });
            
            // Get user's downloads folder path
            String downloadsFolderPath = GetUserDirectoryDownloadsFolderPath();

            // Wait for the download process to complete and save the downloaded file somewhere
            download.saveAs(Paths.get(downloadsFolderPath, download.suggestedFilename()));
            System.out.println(page.title());
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        
        return new ArrayList<>();
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
