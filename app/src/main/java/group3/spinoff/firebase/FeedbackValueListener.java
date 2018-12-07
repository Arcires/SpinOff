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

        GenericTypeIndicator<HashMap<String, HashMap<String, Object>>> genericTypeIndicator =
                new GenericTypeIndicator<HashMap<String, HashMap<String, Object>>>() {};

        feedbacks = dataSnapshot.getValue(genericTypeIndicator);

        observer.refresh();
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {
    }
}
