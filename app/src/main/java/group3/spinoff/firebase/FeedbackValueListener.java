package group3.spinoff.firebase;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import group3.spinoff.employeeUI.FeedbackHomeFragment;

import static android.support.constraint.Constraints.TAG;

public class FeedbackValueListener implements ValueEventListener {

    private ArrayList<HashMap<String, Object>> feedbacks = new ArrayList<>();

    private ArrayList<HashMap<String, Object>> meetings = new ArrayList<>();

    public ArrayList<HashMap<String, Object>> getFeedbacks(){return feedbacks;}
    public ArrayList<HashMap<String, Object>> getMeetings(){return meetings;}

    private static FeedbackHomeFragment observer;

    public FeedbackValueListener(){}

    public FeedbackValueListener(FeedbackHomeFragment fragment){
        observer = fragment;
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        Log.d(TAG, "[FIREBASE CONNECTION WORKS]");

        GenericTypeIndicator<ArrayList<HashMap<String, Object>>> genericTypeIndicator =
                new GenericTypeIndicator<ArrayList<HashMap<String, Object>>>() {};

        feedbacks = dataSnapshot.getValue(genericTypeIndicator);

        Log.d(TAG, "Value is: " + feedbacks);

        observer.refresh();

        //String value = dataSnapshot.getValue(String.class);
        //Log.d(TAG, "YAMERO Value is: " + value);
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {
        Log.d(TAG, "YAMERO ERROR");
    }
}
