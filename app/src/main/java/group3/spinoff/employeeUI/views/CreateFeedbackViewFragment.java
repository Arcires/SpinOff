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

import org.w3c.dom.Text;

import java.util.HashMap;

import group3.spinoff.R;
import group3.spinoff.employeeUI.data.MeetingListElement;

public class CreateFeedbackViewFragment extends Fragment {

    private EditText editTextComment;
    private TextView textViewTitle;
    private TextView textViewDescription;
    private RatingBar ratingBarView_Q1, ratingBarView_Q2, ratingBarView_Q3;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference;

    private String userID = "DEFAULT_USER_ID_1";
    private String companyID = "000";
    private String pinCode = "000";

    private MeetingListElement informations;

    public void setValues(MeetingListElement informations, String userID, String companyID, String pinCode){
        this.informations = informations;
        this.companyID = companyID;
        this.pinCode = pinCode;
    }

//fragmentCreateFeedbackTitle
    //fragmentCreateFeedbackDescription
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meetingfeedback, container, false);

        editTextComment = view.findViewById(R.id.fragmentFeedbackComment);

        textViewTitle = view.findViewById(R.id.fragmentCreateFeedbackTitle);
        textViewDescription = view.findViewById(R.id.fragmentCreateFeedbackDescription);

        ratingBarView_Q1 = view.findViewById(R.id.ratingBarFeedback1);
        ratingBarView_Q2 = view.findViewById(R.id.ratingBarFeedback2);
        ratingBarView_Q3 = view.findViewById(R.id.ratingBarFeedback3);

        textViewTitle.setText(informations.getTitle());
        textViewDescription.setText(informations.getDescription());

        final Button buttonfeedbackViewBack = view.findViewById(R.id.buttonSubmitFeedback);
        final Button buttonfeedbackViewSubmit = view.findViewById(R.id.fragmentFeedbackBackButton);

        buttonfeedbackViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
        buttonfeedbackViewSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HashMap<String, Object> newMeeting = new HashMap<>();

                reference = database.getReference("Meeting/" + companyID + "/" + pinCode);

                Toast.makeText(view.getContext(), "[Tilføj logik her!]", Toast.LENGTH_SHORT).show();
            }
        });

        return view;

    }
}