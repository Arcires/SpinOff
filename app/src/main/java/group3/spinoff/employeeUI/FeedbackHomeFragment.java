package group3.spinoff.employeeUI;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import group3.spinoff.R;
import group3.spinoff.firebase.FeedbackValueListener;
import group3.spinoff.firebase.FirebaseDB;

import static android.support.constraint.Constraints.TAG;

class FeedbackData {
    List<String> meetings;
    List<String> description;

    List<String> comments;

    List<Float> q1;
    List<Float> q2;
    List<Float> q3;

    public FeedbackData(FeedbackValueListener feedbackValueListener){
        ArrayList<HashMap<String, Object>> feedbacks = feedbackValueListener.getFeedbacks();

        meetings = new ArrayList<>();
        description = new ArrayList<>();
        comments = new ArrayList<>();

        q1 = new ArrayList<>();
        q2 = new ArrayList<>();
        q3 = new ArrayList<>();

        for(HashMap<String, Object> feed : feedbacks){
            meetings.add((String)feed.get("CompanyName"));
            description.add((String)feed.get("Desc"));

            comments.add((String)feed.get("Comment"));

            q1.add(Float.parseFloat(feed.get("Q1").toString()));
            q2.add(Float.parseFloat(feed.get("Q2").toString()));
            q3.add(Float.parseFloat(feed.get("Q3").toString()));

        }

        Log.d(TAG, "FEEDBACK: " + feedbacks);

    }
}


/**
 * A simple {@link Fragment} subclass.
 */
public class FeedbackHomeFragment extends Fragment {

    private String userID = "DEFAULT_USER_ID_1";
    private boolean isConnectedToDB = false;

    FeedbackValueListener feedbackValueListener;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference feedbackRef;

    FeedbackData data;

    RecyclerView recyclerView;

    public void refresh(){
        data = new FeedbackData(feedbackValueListener);
       adapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        recyclerView = new RecyclerView(this.getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapter);

        feedbackValueListener = new FeedbackValueListener(this);

        data = new FeedbackData(feedbackValueListener);

        if(!isConnectedToDB) {
            feedbackRef = database.getReference("User/" + userID);
            feedbackRef.addValueEventListener(feedbackValueListener);
            isConnectedToDB = true;
        }


        //FirebaseDB firebaseDB = new FirebaseDB();


        return recyclerView;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    RecyclerView.Adapter adapter = new RecyclerView.Adapter<ListViewHolder>() {

        @Override
        public int getItemCount()  {
            return data.meetings.size();
        }

        @Override
        public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LinearLayout linearLayout = new LinearLayout(parent.getContext());
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            ListViewHolder vh = new ListViewHolder(linearLayout);
            vh.linearLayout = linearLayout;
            vh.feedbackView = getLayoutInflater().inflate(R.layout.feedback_list_elements, parent, false);
            vh.titleTextView = vh.feedbackView.findViewById(R.id.feedback_list_elements_title);
            vh.descriptionTextView = vh.feedbackView.findViewById(R.id.feedback_list_elements_description);
            vh.logoImageView = vh.feedbackView.findViewById(R.id.feedback_list_elements_image);
            vh.feedbackView.setOnClickListener(vh);
            vh.feedbackView.setBackgroundResource(android.R.drawable.list_selector_background);
            vh.logoImageView.setOnClickListener(vh);
//      vh.logoImageView.setBackgroundResource(android.R.drawable.btn_default);
            vh.linearLayout.addView(vh.feedbackView);
            return vh;
        }

        @Override
        public void onBindViewHolder(ListViewHolder vh, int position) {
            vh.titleTextView.setText(data.meetings.get(position));
            vh.descriptionTextView.setText(data.description.get(position));  // TEXT HERE
        }
    };


    /**
     * En Viewholder husker forskellige views i et listeelement, sådan at søgninger i viewhierakiet
     * med findViewById() kun behøver at ske EN gang.
     * Se https://developer.android.com/training/material/lists-cards.html
     */
    class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        LinearLayout linearLayout;
        TextView titleTextView;
        TextView descriptionTextView;
        ImageView logoImageView;
        View feedbackView;

        public ListViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View v) {
            final int position = getAdapterPosition();

            FeedbackView.setValues(data.meetings.get(position), data.description.get(position),
                    data.comments.get(position),
                    data.q1.get(position), data.q2.get(position), data.q3.get(position));

            getActivity().getSupportFragmentManager().beginTransaction().replace(
                    R.id.frameLayoutEmployee, new FeedbackView()).commit();

        }
    }

}
