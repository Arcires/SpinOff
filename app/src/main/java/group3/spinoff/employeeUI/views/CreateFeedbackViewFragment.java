package group3.spinoff.employeeUI.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import group3.spinoff.R;
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

        final Button buttonFeedbackViewSubmit = view.findViewById(R.id.buttonSubmitFeedback);
        textViewTitle.setText(informations.getTitle());
        textViewDescription.setText(informations.getDescription());

        Toolbar toolbar = view.findViewById(R.id.toolbarFeedback);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        ActionBar ab = ((AppCompatActivity) getActivity()).getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowTitleEnabled(false);


        buttonFeedbackViewSubmit.setOnClickListener(new View.OnClickListener() {
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

                //Toast.makeText(view.getContext(), getResources().getString(R.string.createfeedback_toast) + companyID + pinCode, Toast.LENGTH_SHORT).show();
                Snackbar.make(getActivity().findViewById(R.id.snackbar_placement), getResources().getString(R.string.createfeedback_toast) + companyID + pinCode, Snackbar.LENGTH_SHORT).show();


                disableUI();


            }

            private void disableUI() {
                ratingBarView_Q1.setEnabled(false);
                ratingBarView_Q2.setEnabled(false);
                ratingBarView_Q3.setEnabled(false);


                editTextComment.setEnabled(false);
                editTextComment.setAlpha(.5f);

                buttonFeedbackViewSubmit.setAlpha(.5f);
                buttonFeedbackViewSubmit.setEnabled(false);

            }
        });


        return view;

    }
}