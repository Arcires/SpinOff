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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;

import group3.spinoff.R;
import group3.spinoff.employeeUI.MeetingFragment;
import group3.spinoff.employeeUI.data.DummyUser;
import group3.spinoff.employeeUI.data.MeetingListElement;

public class CreateFeedbackViewFragment extends Fragment {

    private EditText editTextComment;
    private TextView textViewTitle;
    private TextView textViewDescription;
    private RatingBar ratingBarView_Q1, ratingBarView_Q2, ratingBarView_Q3;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference;

    private String userID = DummyUser.USERID;
    private String companyID = "000";
    private String pinCode = "000";

    private MeetingListElement informations;

    public void setValues(MeetingListElement informations, String userID, String companyID, String pinCode) {
        this.informations = informations;
        this.companyID = companyID;
        this.pinCode = pinCode;
    }

    //fragmentCreateFeedbackTitle
    //fragmentCreateFeedbackDescription
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meeting_view, container, false);

        editTextComment = view.findViewById(R.id.fragmentFeedbackComment);

        textViewTitle = view.findViewById(R.id.fragmentCreateFeedbackTitle);
        textViewDescription = view.findViewById(R.id.textViewCreateFeedbackDescription);

        ratingBarView_Q1 = view.findViewById(R.id.ratingBarFeedback1);
        ratingBarView_Q2 = view.findViewById(R.id.ratingBarFeedback2);
        ratingBarView_Q3 = view.findViewById(R.id.ratingBarFeedback3);

        textViewTitle.setText(informations.getTitle());
        textViewDescription.setText(informations.getDescription());

        final Button buttonfeedbackViewBack = view.findViewById(R.id.fragmentFeedbackBackButton);
        final Button buttonfeedbackViewSubmit = view.findViewById(R.id.buttonSubmitFeedback);

        buttonfeedbackViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutEmployee, new MeetingFragment()).commit();
            }
        });
        buttonfeedbackViewSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HashMap<String, Object> newFeedback = new HashMap<>();
                newFeedback.put("Comment", editTextComment.getText().toString());
                newFeedback.put("Q1", ratingBarView_Q1.getRating());
                newFeedback.put("Q2", ratingBarView_Q2.getRating());
                newFeedback.put("Q3", ratingBarView_Q3.getRating());

                reference = database.getReference("Meeting/" + companyID + "/" + pinCode +
                        "/Feedback/Answers" + "/" + userID);

                reference.updateChildren(newFeedback);

                String token = companyID + pinCode;

                reference = database.getReference("User/" + userID + "/" + token);

                newFeedback.put("Desc", informations.getDescription());
                newFeedback.put("Title", informations.getTitle());
                reference.updateChildren(newFeedback);

                Toast.makeText(view.getContext(), getResources().getString(R.string.createfeedback_toast) + companyID + pinCode, Toast.LENGTH_SHORT).show();
            }
        });

        return view;

    }
}