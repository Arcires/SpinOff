package group3.spinoff.employeeUI.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import group3.spinoff.R;

public class CreateFeedbackViewFragment extends Fragment {

    private EditText editTextComment;
    private RatingBar ratingBarView_Q1, ratingBarView_Q2, ratingBarView_Q3;
    private Button fragmentFeedbackBackButton;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meetingfeedback, container, false);

        editTextComment = view.findViewById(R.id.fragmentFeedbackComment);

        ratingBarView_Q1 = view.findViewById(R.id.ratingBarFeedback1);
        ratingBarView_Q2 = view.findViewById(R.id.ratingBarFeedback2);
        ratingBarView_Q3 = view.findViewById(R.id.ratingBarFeedback3);


        return view;

    }
}