package group3.spinoff.employeeUI;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import group3.spinoff.R;
import group3.spinoff.employeeUI.data.MeetingListElement;
import group3.spinoff.employeeUI.views.CreateFeedbackViewFragment;
import group3.spinoff.employeeUI.views.CreateMeetingViewFragment;
import group3.spinoff.firebase.MeetingValueListener;

import static android.support.constraint.Constraints.TAG;

public class MeetingFragment extends Fragment implements IDataObserver {

    View view;

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference meetingRef;

    MeetingValueListener meetingValueListener = new MeetingValueListener(this);

    String companyID;
    String pinCode;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_meeting, container, false);

        final Button buttonSearchMeeting = view.findViewById(R.id.buttonSearchMeeting);
        final Button buttonCreateMeeting = view.findViewById(R.id.buttonCreateMeeting);

        final EditText editTextMeetingPin = view.findViewById(R.id.editTextEmployeeMeetingEnter);

        final CheckBox checkBox = view.findViewById(R.id.checkBoxTestMeeting);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox.isChecked()) {
                    editTextMeetingPin.setText("001118");
                } else{
                    editTextMeetingPin.setText("");
                }
            }
        });
        buttonSearchMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String pin = editTextMeetingPin.getText().toString();

                if (pin.length() > 5) {

                    companyID = pin.substring(0, 3);
                    pinCode = pin.substring(3);

                    meetingRef = database.getReference("Meeting/" + companyID + "/" + pinCode);
                    meetingRef.addValueEventListener(meetingValueListener);

                    Toast.makeText(view.getContext(), "Leder efter møde: " + companyID + "/" + pinCode, Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Check if the current user is an employee or a company.
        // If they have a user mail, they are registered company.

        if(user.isAnonymous()){
            buttonCreateMeeting.setVisibility(View.INVISIBLE);
        }

        buttonCreateMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(
                        R.id.frameLayoutEmployee, new CreateMeetingViewFragment()).commit();
                Toast.makeText(view.getContext(), "Trykkede på Lav nyt møde.", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public void refresh() {
        String meetingTitle = (String) meetingValueListener.getMeets().get("Title");
        String meetingDesc = (String) meetingValueListener.getMeets().get("Desc");

        if (meetingTitle != null) {
            Toast.makeText(view.getContext(), "Meeting found!", Toast.LENGTH_SHORT).show();

            CreateFeedbackViewFragment createFeedbackViewFragment = new CreateFeedbackViewFragment();
            createFeedbackViewFragment.setValues(
                    new MeetingListElement()
                            .setTitle(meetingTitle)
                            .setDescription(meetingDesc),
                    user.getDisplayName(),
                    companyID, pinCode);

            getActivity().getSupportFragmentManager().beginTransaction().replace(
                    R.id.frameLayoutEmployee, createFeedbackViewFragment).commit();

        } else {
            Toast.makeText(view.getContext(), "This Meeting does not exist.", Toast.LENGTH_SHORT).show();
        }

        meetingValueListener.init();
        meetingRef.removeEventListener(meetingValueListener);
    }
}
