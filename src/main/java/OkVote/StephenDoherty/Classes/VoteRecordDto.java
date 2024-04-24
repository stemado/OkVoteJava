package OkVote.StephenDoherty.Classes;

public class VoteRecordDto {
    private String dateOfVote;
    private String committee;
    private String measureNumber;
    private String information;
    private String author;
    private String action;
    private String voteResult;

    // Constructor
    public VoteRecordDto(String dateOfVote, String committee, String measureNumber, String information, String author, String action, String voteResult) {
        this.dateOfVote = dateOfVote;
        this.committee = committee;
        this.measureNumber = measureNumber;
        this.information = information;
        this.author = author;
        this.action = action;
        this.voteResult = voteResult;
    }

    // Getters and Setters
    public String getDateOfVote() { return dateOfVote; }
    public String getCommittee() { return committee; }
    public String getMeasureNumber() { return measureNumber; }
    public String getInformation() { return information; }
    public String getAuthor() { return author; }
    public String getAction() { return action; }
    public String getVoteResult() { return voteResult; }
}

