
import java.time.LocalDate;
import java.time.LocalTime;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author sdoherty
 */
public class VoteDetail {
    private int uniqueIndex;
    private String billNumber;
    private String voteType;
    private int yea;
    private int nay;
    private int cp;
    private int excused;
    private int vacant;
    private String result;
    private String location;
    private LocalDate date;
    private LocalTime time;

    public VoteDetail(int uniqueIndex, String billNumber, String voteType, int yea, int nay, int cp, int excused, int vacant, String result, String location) {
        this.uniqueIndex = uniqueIndex;
        this.billNumber = billNumber;
        this.voteType = voteType;
        this.yea = yea;
        this.nay = nay;
        this.cp = cp;
        this.excused = excused;
        this.vacant = vacant;
        this.result = result;
        this.location = location;
        this.date = LocalDate.now();
        this.time = LocalTime.now();
    }  

    public int getUniqueIndex() {
        return uniqueIndex;
    }

    public void setUniqueIndex(int uniqueIndex) {
        this.uniqueIndex = uniqueIndex;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    public String getVoteType() {
        return voteType;
    }

    public void setVoteType(String voteType) {
        this.voteType = voteType;
    }

    public int getYea() {
        return yea;
    }

    public void setYea(int yea) {
        this.yea = yea;
    }

    public int getNay() {
        return nay;
    }

    public void setNay(int nay) {
        this.nay = nay;
    }

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    public int getExcused() {
        return excused;
    }

    public void setExcused(int excused) {
        this.excused = excused;
    }

    public int getVacant() {
        return vacant;
    }

    public void setVacant(int vacant) {
        this.vacant = vacant;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
    
    
}
