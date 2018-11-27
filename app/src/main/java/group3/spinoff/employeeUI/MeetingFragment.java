package group3.spinoff.employeeUI;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import group3.spinoff.R;

public class MeetingFragment extends Fragment {

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meeting, container, false);

        final Button buttonSearchMeeting = view.findViewById(R.id.buttonSearchMeeting);
        final Button buttonCreateMeeting = view.findViewById(R.id.buttonCreateMeeting);

        final EditText editTextMeetingPin = view.findViewById(R.id.editTextEmployeeMeetingEnter);

        buttonSearchMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Leder efter møde: " + editTextMeetingPin.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        //Check if the current user is an employee or a company.
        // If they have a user mail, they are registered company.
        try {
            if (user.getEmail() == null) {
                buttonCreateMeeting.setVisibility(View.INVISIBLE);
            }
        } catch (Exception e) {
            e.getMessage();
        }

        buttonCreateMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Trykkede på Lav nyt møde.", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

}
