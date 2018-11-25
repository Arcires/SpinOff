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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Random;

import group3.spinoff.R;

public class CreateMeetingFragment extends Fragment {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference;

    String company = "001";

    public CreateMeetingFragment(){}

    public void setCompany(String company){
        this.company = company;
    }

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

                if (!editTextCreateMeetingTitle.getText().toString().isEmpty()
                        && !editTextCreateMeetingDesc.getText().toString().isEmpty()
                        && !editTextCreateMeetingAttendants.getText().toString().isEmpty()) {

                    HashMap<String, Object> newMeeting = new HashMap<>();

                    String random = String.valueOf(new Random().nextInt(900) + 100);

                    newMeeting.put("Title", editTextCreateMeetingTitle.getText().toString());
                    newMeeting.put("Desc", editTextCreateMeetingDesc.getText().toString());
                    newMeeting.put("ExpectedPeople", Integer.parseInt(editTextCreateMeetingAttendants.getText().toString()));

                    reference = database.getReference("Meeting/" + company + "/"
                            + random);

                    reference.updateChildren(newMeeting);

                    Toast.makeText(view.getContext(), "The PIN Code is : " + company + random, Toast.LENGTH_SHORT).show();
                }
            }
        });


        return view;
    }

}
