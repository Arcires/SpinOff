package group3.spinoff.employeeUI.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import group3.spinoff.R;

public class CreateMeetingFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_createmeeting, container, false);
        final EditText editTextCreateMeetingTitle = view.findViewById(R.id.editTextCreateMeetingTitle);
        final EditText editTextCreateMeetingDesc = view.findViewById(R.id.editTextCreateMeetingDesc);
        final EditText editTextCreateMeetingAttendants = view.findViewById(R.id.editTextCreateMeetingAttendants);

        final Button buttonCreateMeetingExit = view.findViewById(R.id.fragmentCreateMeetingBackButton);
        final Button buttonCreateMeeting = view.findViewById(R.id.buttonCreateMeeting);


        buttonCreateMeetingExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Exited meeting creation.", Toast.LENGTH_SHORT).show();
            }
        });

        buttonCreateMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Creating meeting: " + editTextCreateMeetingTitle.getText(), Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }

}
