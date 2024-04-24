
import OkVote.BillExcelFileDownloader;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author sdoherty
 */
public class App {
    public static void main(String[] args)
    {
        try {
            // Stephen Doherty's Code
            BillExcelFileDownloader billExcelFileDownloader = new BillExcelFileDownloader();
            billExcelFileDownloader.GetCommitteeRecords();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
