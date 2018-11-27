package group3.spinoff.employeeUI.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import group3.spinoff.R;

//Documentation can be found here: https://github.com/lopspower/CircularProgressBar

public class MeetingFeedbackGraphsViewFragment extends Fragment {

    private TextView textViewFeedbackGraphsMainTitle, textViewGraphsMeetingTitle,
            textViewGraphsMeetingTitleContent, textViewGraphsMeetingDesc,
            textViewGraphsMeetingDescContent, textViewGraphsCircleProgressCount;

    private CircularProgressBar circularProgressBar;

    private Button buttonGraphsBack;

    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_feedbackgraphs, container, false);

        textViewFeedbackGraphsMainTitle = view.findViewById(R.id.textViewGraphsMainTitle);
        textViewGraphsMeetingTitle = view.findViewById(R.id.textViewGraphsMeetingTitle);
        textViewGraphsMeetingTitleContent = view.findViewById(R.id.textViewGraphsMeetingTitleContent);
        textViewGraphsMeetingDesc = view.findViewById(R.id.textViewGraphsMeetingDesc);
        textViewGraphsMeetingDescContent = view.findViewById(R.id.textViewGraphsMeetingDescContent);
        textViewGraphsCircleProgressCount = view.findViewById(R.id.textViewGraphsCircProgressCount);

        //TODO Setting up the circular progress bar. Define the current progress(answer count) and the max progress (people count). MUST BE INTS!
        circularProgressBar = view.findViewById(R.id.circleProgressAnswerCount);
        setCircularProgress();

        buttonGraphsBack = view.findViewById(R.id.buttonGraphsBack);
        buttonGraphsBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });

        return view;
    }

    private void setCircularProgress() {
        int progressCurrent = 50;
        int progressMax = 100;

        circularProgressBar.setProgress(progressCurrent);
        circularProgressBar.setProgressMax(progressMax);
    }


}
