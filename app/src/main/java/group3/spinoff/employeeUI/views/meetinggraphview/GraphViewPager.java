package group3.spinoff.employeeUI.views.meetinggraphview;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import group3.spinoff.employeeUI.data.MeetingListElement;

public class GraphViewPager extends FragmentStatePagerAdapter {

    MeetingListElement informations;

    public void setValues(MeetingListElement meetingListElement) {
        this.informations = meetingListElement;
    }

    public GraphViewPager(FragmentManager fm) {
        super(fm);
    }

    AverageGraphFragment averageGraphFragment = new AverageGraphFragment();
    Q1Fragment q1Fragment = new Q1Fragment();
    Q2Fragment q2Fragment = new Q2Fragment();
    Q3Fragment q3Fragment = new Q3Fragment();

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                averageGraphFragment.setValues(informations);
                return averageGraphFragment; //View average graph first.
            case 1:
                return q1Fragment; //View average graph first.
            case 2:
                return q2Fragment; //View average graph first.
            case 3:
                return q3Fragment; //View average graph first.
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
