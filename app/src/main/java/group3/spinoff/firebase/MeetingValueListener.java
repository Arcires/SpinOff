package group3.spinoff.firebase;

import androidx.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;


import java.util.HashMap;

import group3.spinoff.employeeUI.FeedbackHomeFragment;
import group3.spinoff.employeeUI.IDataObserver;

public class MeetingValueListener implements ValueEventListener {

    private HashMap<String, HashMap<String, Object>> meetings = new HashMap<>();
    private HashMap<String, Object> meets = new HashMap<>();

    public HashMap<String, HashMap<String, Object>> getMeetings(){return meetings;}
    public HashMap<String, Object> getMeets(){return meets;}
    public void init(){meetings = new HashMap<>(); meets = new HashMap<>();}

    private IDataObserver observer;

    public MeetingValueListener(){}

    public MeetingValueListener(IDataObserver fragment){
        observer = fragment;
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        //Log.d(TAG, "[FIREBASE CONNECTION WORKS]");

        try {
            GenericTypeIndicator<HashMap<String, HashMap<String, Object>>> genericTypeIndicator =
                    new GenericTypeIndicator<HashMap<String, HashMap<String, Object>>>() {
                    };

            meetings = dataSnapshot.getValue(genericTypeIndicator);

        }catch (DatabaseException e){
            GenericTypeIndicator<HashMap<String, Object>> genericTypeIndicator =
                    new GenericTypeIndicator<HashMap<String, Object>>() {
                    };

            meets = dataSnapshot.getValue(genericTypeIndicator);
        }

        //Log.d(TAG, "Value is: " + meetings);

        observer.refresh();
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {
        //Log.d(TAG, "YAMERO ERROR");
    }
}
