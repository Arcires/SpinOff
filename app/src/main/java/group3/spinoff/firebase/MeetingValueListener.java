package group3.spinoff.firebase;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import static android.support.constraint.Constraints.TAG;

import java.util.HashMap;

import group3.spinoff.employeeUI.FeedbackHomeFragment;

public class MeetingValueListener implements ValueEventListener {

    private HashMap<String, HashMap<String, Object>> meetings = new HashMap<>();

    public HashMap<String, HashMap<String, Object>> getMeetings(){return meetings;}

    private static FeedbackHomeFragment observer;

    public MeetingValueListener(){}

    public MeetingValueListener(FeedbackHomeFragment fragment){
        observer = fragment;
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        Log.d(TAG, "[FIREBASE CONNECTION WORKS]");

        GenericTypeIndicator<HashMap<String, HashMap<String, Object>>> genericTypeIndicator =
                new GenericTypeIndicator<HashMap<String, HashMap<String, Object>>>() {};

        meetings = dataSnapshot.getValue(genericTypeIndicator);

        Log.d(TAG, "Value is: " + meetings);

        observer.refresh();
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {
        Log.d(TAG, "YAMERO ERROR");
    }
}
