package group3.spinoff.employeeUI.data;

import java.util.ArrayList;

public class MeetingListElement {

    String companyName = "NULL COMPANY";
    String title = "NULL TITLE";
    String description = "NULL DESCRIPTION";
    String comments = "NULL COMMENTS";

    float q1 = 0f;
    float q2 = 0f;
    float q3 = 0f;

    int expectedPeople = 0;
    int actualPeople = 0;

    ArrayList<String> commentsList = new ArrayList<>();

    public MeetingListElement addToComments(String comment){
        commentsList.add(comment);
        return this;
    }

    public ArrayList<String> getCommentsList(){return commentsList;}
    public MeetingListElement setCommentsList(ArrayList<String> commentsList){this.commentsList = commentsList; return this;}

    public MeetingListElement(){}

    public String getCompanyName() {
        return companyName;
    }

    public MeetingListElement setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public MeetingListElement setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public MeetingListElement setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getComments() {
        return comments;
    }

    public MeetingListElement setComments(String comments) {
        this.comments = comments;
        return this;
    }

    public float getQ1() {
        return q1;
    }

    public MeetingListElement setQ1(float q1) {
        this.q1 = q1;
        return this;
    }

    public float getQ2() {
        return q2;
    }

    public MeetingListElement setQ2(float q2) {
        this.q2 = q2;
        return this;
    }

    public float getQ3() {
        return q3;
    }

    public MeetingListElement setQ3(float q3) {
        this.q3 = q3;
        return this;
    }

    public int getExpectedPeople() {
        return expectedPeople;
    }

    public MeetingListElement setExpectedPeople(int expectedPeople) {
        this.expectedPeople = expectedPeople;
        return this;
    }

    public int getActualPeople() {
        return actualPeople;
    }

    public MeetingListElement setActualPeople(int actualPeople) {
        this.actualPeople = actualPeople;
        return this;
    }
}
