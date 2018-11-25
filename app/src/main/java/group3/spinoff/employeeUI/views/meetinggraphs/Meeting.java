package group3.spinoff.employeeUI.views.meetinggraphs;


public class Meeting {

    private String meetingTitle;
    private String meetingDesc;
    private int meetingCount;

    public Meeting(String meetingTitle, String meetingDesc, int meetingCount) {
        this.meetingTitle = meetingTitle;
        this.meetingDesc = meetingDesc;
        this.meetingCount = meetingCount;
    }

    public String getMeetingTitle() {
        return meetingTitle;
    }

    public void setMeetingTitle(String meetingTitle) {
        this.meetingTitle = meetingTitle;
    }

    public String getMeetingDesc() {
        return meetingDesc;
    }

    public void setMeetingDesc(String meetingDesc) {
        this.meetingDesc = meetingDesc;
    }

    public int getMeetingCount() {
        return meetingCount;
    }

    public void setMeetingCount(int meetingCount) {
        this.meetingCount = meetingCount;
    }
}
