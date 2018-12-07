package group3.spinoff.employeeUI.views;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

import group3.spinoff.R;
import group3.spinoff.employeeUI.MeetingFragment;

public class CreateMeetingViewFragment extends Fragment {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference;

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();
    String company = user.getEmail().split("\\.")[1];

    public CreateMeetingViewFragment() {
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_createmeeting, container, false);

        final EditText editTextCreateMeetingTitle = view.findViewById(R.id.editTextCreateMeetingTitle);
        final EditText editTextCreateMeetingDesc = view.findViewById(R.id.editTextCreateMeetingDesc);
        final EditText editTextCreateMeetingAttendants = view.findViewById(R.id.editTextCreateMeetingAttendants);

        final Button buttonCreateMeeting = view.findViewById(R.id.buttonCreateMeeting);

        Toolbar toolbar = view.findViewById(R.id.toolbarCreateMeeting);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        ActionBar ab = ((AppCompatActivity) getActivity()).getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowTitleEnabled(false);

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

                    Toast.makeText(view.getContext(), getResources().getString(R.string.createMeetingCreationToast) + company + random, Toast.LENGTH_LONG).show();

                    changeButton(random);
                }
            }

            private void changeButton(final String random) {
                buttonCreateMeeting.setBackgroundResource(R.drawable.button_loginblue);
                buttonCreateMeeting.setText(getResources().getString(R.string.createMeetingButtonTextNewText) + " " +company + String.valueOf(random));
                buttonCreateMeeting.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ClipboardManager clipboard = (ClipboardManager) Objects.requireNonNull(getContext()).getSystemService(Context.CLIPBOARD_SERVICE);

                        ClipData clipData = ClipData.newPlainText("linkdata", getResources().getString(R.string.createMeetingLinkText) + " " + company + String.valueOf(random));

                        Objects.requireNonNull(clipboard).setPrimaryClip(clipData);

                        buttonCreateMeeting.setText(R.string.createMeetingLinkCopy);
                    }
                });
            }
        });


        return view;
    }

}
