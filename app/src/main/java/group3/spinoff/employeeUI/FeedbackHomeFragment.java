package group3.spinoff.employeeUI;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import group3.spinoff.R;
import group3.spinoff.employeeUI.data.DummyUser;
import group3.spinoff.employeeUI.data.MeetingListElement;
import group3.spinoff.employeeUI.views.FeedbackViewFragment;
import group3.spinoff.employeeUI.views.meetinggraphview.MeetingGraphViewFragment;
import group3.spinoff.firebase.FeedbackValueListener;
import group3.spinoff.firebase.MeetingValueListener;

import static androidx.constraintlayout.motion.widget.MotionScene.TAG;

class FeedbackData {
    List<MeetingListElement> meetings;


    public FeedbackData(FeedbackValueListener feedbackValueListener, MeetingValueListener meetingValueListener) {
        HashMap<String, HashMap<String, Object>> feedbacks = feedbackValueListener.getFeedbacks();
        HashMap<String, HashMap<String, Object>> companymeetings = meetingValueListener.getMeetings();

        meetings = new ArrayList<>();

        if (feedbacks != null) {
            for (HashMap<String, Object> feed : feedbacks.values()) {

                meetings.add(new MeetingListElement()
                        .setTitle(Objects.requireNonNull(feed.get("Title")).toString())
                        .setDescription(Objects.requireNonNull(feed.get("Desc")).toString())
                        .setComments(Objects.requireNonNull(feed.get("Comment")).toString())

                        .setQ1(Float.parseFloat(Objects.requireNonNull(feed.get("Q1")).toString()))
                        .setQ2(Float.parseFloat(Objects.requireNonNull(feed.get("Q2")).toString()))
                        .setQ3(Float.parseFloat(Objects.requireNonNull(feed.get("Q3")).toString())));
            }
        }

        if (companymeetings != null) {
            for (HashMap<String, Object> meet : companymeetings.values()) {
                float q1_average = 0;
                float q2_average = 0;
                float q3_average = 0;

                int actualpeople = 0;

                ArrayList<String> commentsList = new ArrayList<>();

                try {
                    HashMap<String, HashMap<String, HashMap<String, Object>>> list
                            = (HashMap<String, HashMap<String, HashMap<String, Object>>>) meet.get("Feedback");

                    HashMap<String, HashMap<String, Object>> answers = list.get("Answers");

                    if (list != null) {

                        for (HashMap<String, Object> answer : answers.values()) {
                            ++actualpeople;

                            q1_average = q1_average + Float.parseFloat(answer.get("Q1").toString());
                            q2_average = q2_average + Float.parseFloat(answer.get("Q2").toString());
                            q3_average = q3_average + Float.parseFloat(answer.get("Q3").toString());

                            commentsList.add((String) answer.get("Comment"));
                        }

                        q1_average = q1_average / actualpeople;
                        q2_average = q2_average / actualpeople;
                        q3_average = q3_average / actualpeople;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                meetings.add(new MeetingListElement()
                        .setTitle((String) meet.get("Title"))
                        .setDescription(meet.get("Desc").toString())
                        .setExpectedPeople(Integer.parseInt(meet.get("ExpectedPeople").toString()))
                        .setActualPeople(actualpeople)
                        .setQ1(q1_average)
                        .setQ2(q2_average)
                        .setQ3(q3_average)
                        .setCommentsList(commentsList));

            }
        }
        Log.d(TAG, "FEEDBACK: " + feedbacks);

    }
}


/**
 * A simple {@link Fragment} subclass.
 */
public class FeedbackHomeFragment extends Fragment implements IDataObserver {

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();

    private String userID = DummyUser.USERID;
    private String companyID = "001";
    private boolean isCompany = false;
    private boolean isConnectedToFeedback = false;
    private boolean isConnectedToMeeting = false;

    FeedbackValueListener feedbackValueListener;
    MeetingValueListener meetingValueListener;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference feedbackRef;
    DatabaseReference meetingRef;

    FeedbackData data;

    RecyclerView recyclerView;

    public void refresh() {
        data = new FeedbackData(feedbackValueListener, meetingValueListener);
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
        meetingValueListener = new MeetingValueListener(this);

        String email = user.getEmail();

        if (email != null) {
            if (!email.isEmpty()) {
                isCompany = true;
                companyID = email.split("\\.")[1];
            }
        }

        data = new FeedbackData(feedbackValueListener, meetingValueListener);

        if (isCompany) {
            try {
                feedbackRef.removeEventListener(feedbackValueListener);
            } catch (Exception e) {
                e.getMessage();
            }

            if (!isConnectedToMeeting) {
                meetingRef = database.getReference("Meeting/" + companyID);
                meetingRef.addValueEventListener(meetingValueListener);
                isConnectedToMeeting = true;
            }
        } else {
            try {
                meetingRef.removeEventListener(meetingValueListener);
            } catch (Exception e) {
                e.getMessage();
            }

            if (!isConnectedToFeedback) {
                feedbackRef = database.getReference("User/" + userID);
                feedbackRef.addValueEventListener(feedbackValueListener);
                isConnectedToFeedback = true;
            }
        }


        return recyclerView;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    RecyclerView.Adapter adapter = new RecyclerView.Adapter<ListViewHolder>() {

        @Override
        public int getItemCount() {
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
            vh.feedbackView.setOnClickListener(vh);
            vh.feedbackView.setBackgroundResource(R.drawable.ripple_effect);
            vh.linearLayout.addView(vh.feedbackView);
            return vh;
        }

        @Override
        public void onBindViewHolder(ListViewHolder vh, int position) {
            vh.titleTextView.setText(data.meetings.get(position).getTitle());
            vh.descriptionTextView.setText(data.meetings.get(position).getDescription());  // TEXT HERE
        }
    };


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

            if (!isCompany) {
                FeedbackViewFragment feedbackViewFragment = new FeedbackViewFragment();
                feedbackViewFragment.setValues(data.meetings.get(position));

                getActivity().getSupportFragmentManager().beginTransaction().add(
                        R.id.frameLayoutEmployee, feedbackViewFragment).addToBackStack(null).commit();

            } else {
                MeetingGraphViewFragment meetingGraphViewFragment = new MeetingGraphViewFragment();
                meetingGraphViewFragment.setValues(data.meetings.get(position));

                getActivity().getSupportFragmentManager().beginTransaction().add(
                        R.id.frameLayoutEmployee, meetingGraphViewFragment).addToBackStack(null).commit();
            }

        }
    }

}
