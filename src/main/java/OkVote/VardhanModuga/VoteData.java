/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OkVote.VardhanModuga;

import java.util.List;

/**
 *
 * @author 0844660
 */


public class VoteData {

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
class HouseMembers {

    public List<String> memberNames;

    // Constructor to initialize the member names
    public HouseMembers(List<String> memberNames) {
        this.memberNames = memberNames;
    }
}

// This dummy class represents the HouseRollCall
class HouseRollCall {

    public List<String> rollCallNames;

    // Constructor to initialize the roll call names
    public HouseRollCall(List<String> rollCallNames) {
        this.rollCallNames = rollCallNames;
    }
}