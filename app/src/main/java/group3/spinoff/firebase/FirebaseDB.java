package group3.spinoff.firebase;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.support.constraint.Constraints.TAG;

public class FirebaseDB {

    private String userID = "DEFAULT_USER_ID_1";

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference feedbackRef;

    public FirebaseDB() {
        feedbackRef = database.getReference("User/"+userID);
        feedbackRef.addValueEventListener(new FeedbackValueListener());
    }

    public FirebaseDB(String userID) {
        this.userID = userID;
        feedbackRef = database.getReference("User/"+userID);
        feedbackRef.addValueEventListener(new FeedbackValueListener());
    }

}
