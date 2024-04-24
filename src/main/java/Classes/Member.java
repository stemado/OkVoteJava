package Classes;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author sdoherty
 */
public class Member {
    // Fields are private to enforce encapsulation
    private int uniqueIndex;
    private String chamber;
    private int district;
    private String memberName;
    private String title;

    // Constructor to initialize all the fields
    public Member(int uniqueIndex, String chamber, int district, String memberName, String title) {
        this.uniqueIndex = uniqueIndex;
        this.chamber = chamber;
        this.district = district;
        this.memberName = memberName;
        this.title = title;
    }

    public int getUniqueIndex() {
        return uniqueIndex;
    }

    public void setUniqueIndex(int uniqueIndex) {
        this.uniqueIndex = uniqueIndex;
    }

    public String getChamber() {
        return chamber;
    }

    public void setChamber(String chamber) {
        this.chamber = chamber;
    }

    public int getDistrict() {
        return district;
    }

    public void setDistrict(int district) {
        this.district = district;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
}
