package group3.spinoff.employeeUI.views.meetinggraphview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.util.Objects;

import group3.spinoff.R;
import group3.spinoff.employeeUI.FeedbackHomeFragment;
import group3.spinoff.employeeUI.data.MeetingListElement;

//Graph documentation: http://www.android-graphview.org/
//Documentation can be found here: https://github.com/lopspower/CircularProgressBar

public class MeetingGraphViewFragment extends Fragment {

    private TextView textViewFeedbackGraphsMainTitle, textViewGraphsMeetingTitle,
            textViewGraphsMeetingTitleContent, textViewGraphsMeetingDesc,
            textViewGraphsMeetingDescContent, textViewGraphsCircleProgressCount;

    private CircularProgressBar circularProgressBar;

    private Button buttonGraphsBack;

    private ViewPager viewPager;

    MeetingListElement informations;

    public void setValues(MeetingListElement meetingListElement){
        this.informations = meetingListElement;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_meetinggraph, container, false);

        textViewFeedbackGraphsMainTitle = view.findViewById(R.id.textViewGraphsMainTitle);
        textViewGraphsMeetingTitle = view.findViewById(R.id.textViewGraphsMeetingTitle);
        textViewGraphsMeetingTitleContent = view.findViewById(R.id.textViewGraphsMeetingTitleContent);
        textViewGraphsMeetingDesc = view.findViewById(R.id.textViewGraphsMeetingDesc);
        textViewGraphsMeetingDescContent = view.findViewById(R.id.textViewGraphsMeetingDescContent);
        textViewGraphsCircleProgressCount = view.findViewById(R.id.textViewGraphsCircProgressCount);

        //TODO Setting up the circular progress bar. Define the current progress(answer count) and the max progress (people count). MUST BE INTS!
        circularProgressBar = view.findViewById(R.id.circleProgressAnswerCount);

        setCircularProgress(informations.getActualPeople(), informations.getExpectedPeople());

        textViewGraphsMeetingTitleContent.setText(informations.getTitle());
        textViewGraphsMeetingDescContent.setText(informations.getDescription());
        textViewGraphsCircleProgressCount.setText(informations.getActualPeople()
                +"/"+informations.getExpectedPeople());

        buttonGraphsBack = view.findViewById(R.id.buttonGraphsBack);
        buttonGraphsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutEmployee, new FeedbackHomeFragment()).commit();
            }
        });

        viewPager = view.findViewById(R.id.viewPagerGraphs);
        viewPager.setAdapter(new GraphViewPager(getActivity().getSupportFragmentManager()));

        return view;
    }

    private void setCircularProgress(int current, int max) {
        circularProgressBar.setProgress(current);
        circularProgressBar.setProgressMax(max);
    }


}
