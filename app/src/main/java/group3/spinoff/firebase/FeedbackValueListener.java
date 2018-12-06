package group3.spinoff.firebase;

import androidx.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import group3.spinoff.employeeUI.FeedbackHomeFragment;
import group3.spinoff.employeeUI.IDataObserver;


public class FeedbackValueListener implements ValueEventListener {

    private HashMap<String, HashMap<String, Object>> feedbacks = new HashMap<>();

    public HashMap<String, HashMap<String, Object>> getFeedbacks(){return feedbacks;}

    private IDataObserver observer;

    public FeedbackValueListener(){}

    public FeedbackValueListener(FeedbackHomeFragment fragment){
        observer = fragment;
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        //Log.d(TAG, "[FIREBASE CONNECTION WORKS]");

        GenericTypeIndicator<HashMap<String, HashMap<String, Object>>> genericTypeIndicator =
                new GenericTypeIndicator<HashMap<String, HashMap<String, Object>>>() {};

        feedbacks = dataSnapshot.getValue(genericTypeIndicator);

        //Log.d(TAG, "Value is: " + feedbacks);

        observer.refresh();

        //String value = dataSnapshot.getValue(String.class);
        //Log.d(TAG, "YAMERO Value is: " + value);
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {
        //Log.d(TAG, "YAMERO ERROR");
    }
}
