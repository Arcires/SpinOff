package group3.spinoff.employeeUI;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import group3.spinoff.R;
import group3.spinoff.employeeUI.data.MeetingListElement;
import group3.spinoff.employeeUI.views.CreateFeedbackViewFragment;
import group3.spinoff.employeeUI.views.CreateMeetingViewFragment;
import group3.spinoff.firebase.MeetingValueListener;

public class MeetingFragment extends Fragment implements IDataObserver {

    View view;

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference meetingRef;

    MeetingValueListener meetingValueListener = new MeetingValueListener(this);

    String companyID;
    String pinCode;

    CircularProgressButton buttonSearchMeeting;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_meeting, container, false);

        buttonSearchMeeting = view.findViewById(R.id.buttonSearchMeeting);
        final Button buttonCreateMeeting = view.findViewById(R.id.buttonCreateMeeting);

        final TextInputEditText textInputEditTextPin = view.findViewById(R.id.textInputEditTextPin);

        final CheckBox checkBox = view.findViewById(R.id.checkBoxTestMeeting);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox.isChecked()) {
                    textInputEditTextPin.setText("001118");
                } else {
                    textInputEditTextPin.setText("");
                }
            }
        });
        buttonSearchMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String pin = textInputEditTextPin.getText().toString();

                if (pin.length() > 5 && isConnected()) {

                    companyID = pin.substring(0, 3);
                    pinCode = pin.substring(3);

                    meetingRef = database.getReference("Meeting/" + companyID + "/" + pinCode);
                    meetingRef.addValueEventListener(meetingValueListener);
                    buttonSearchMeeting.startAnimation();
                } else {
                    Toast.makeText(getContext(), "Intet internet detekteret.\nTjek din netforbindelse", Toast.LENGTH_SHORT).show();
                }
            }

            private boolean isConnected() {
                ConnectivityManager cm = (ConnectivityManager) Objects.requireNonNull(getActivity()).getSystemService((Context.CONNECTIVITY_SERVICE));

                return Objects.requireNonNull(cm).getActiveNetworkInfo() != null;

            }
        });

        //Check if the current user is an employee or a company.
        // If they have a user mail, they are registered company.

        if (user.isAnonymous()) {
            buttonCreateMeeting.setVisibility(View.INVISIBLE);
        }
        buttonCreateMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(
                        R.id.frameLayoutEmployee, new CreateMeetingViewFragment()).commit();
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

            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(
                    R.id.frameLayoutEmployee, createFeedbackViewFragment).addToBackStack(null).commit();

        } else {
            Toast.makeText(view.getContext(), "This Meeting does not exist.", Toast.LENGTH_SHORT).show();
            buttonSearchMeeting.revertAnimation();
        }

        meetingValueListener.init();
        meetingRef.removeEventListener(meetingValueListener);
    }
}
