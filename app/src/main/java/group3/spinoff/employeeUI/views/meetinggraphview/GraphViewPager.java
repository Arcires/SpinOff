package group3.spinoff.employeeUI.views.meetinggraphview;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class GraphViewPager extends FragmentStatePagerAdapter {

    public GraphViewPager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new AverageGraphFragment(); //View average graph first.
            case 1:
                return new Q1Fragment(); //View average graph first.
            case 2:
                return new Q2Fragment(); //View average graph first.
            case 3:
                return new Q3Fragment(); //View average graph first.
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
