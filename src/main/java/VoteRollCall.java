
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
public class VoteRollCall {
    private int uniqueIndex;
    private int votesDetailIndex;
    private String billNumber;
    private String member;
    private String vote;
    private String voteType;
    private String location;
    private LocalDate date;
    private LocalTime time;

    // Constructor
    public VoteRollCall(int uniqueIndex, int votesDetailIndex, String billNumber, String member, String vote, String voteType, String location, LocalDate date, LocalTime time) {
        this.uniqueIndex = uniqueIndex;
        this.votesDetailIndex = votesDetailIndex;
        this.billNumber = billNumber;
        this.member = member;
        this.vote = vote;
        this.voteType = voteType;
        this.location = location;
        this.date = date;
        this.time = time;
    }  

    public int getUniqueIndex() {
        return uniqueIndex;
    }

    public int getVotesDetailIndex() {
        return votesDetailIndex;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public String getMember() {
        return member;
    }

    public String getVote() {
        return vote;
    }

    public String getVoteType() {
        return voteType;
    }

    public String getLocation() {
        return location;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setUniqueIndex(int uniqueIndex) {
        this.uniqueIndex = uniqueIndex;
    }

    public void setVotesDetailIndex(int votesDetailIndex) {
        this.votesDetailIndex = votesDetailIndex;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    public void setVoteType(String voteType) {
        this.voteType = voteType;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
    
    
    
}
