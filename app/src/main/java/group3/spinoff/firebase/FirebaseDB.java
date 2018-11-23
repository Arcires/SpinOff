package group3.spinoff.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
