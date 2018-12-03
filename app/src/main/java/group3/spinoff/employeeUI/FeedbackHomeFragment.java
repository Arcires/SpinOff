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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import group3.spinoff.R;
import group3.spinoff.employeeUI.data.MeetingListElement;
import group3.spinoff.employeeUI.views.FeedbackViewFragment;
import group3.spinoff.firebase.FeedbackValueListener;
import group3.spinoff.firebase.MeetingValueListener;

class FeedbackData {
    List<MeetingListElement> meetings;


    public FeedbackData(FeedbackValueListener feedbackValueListener, MeetingValueListener meetingValueListener) {
        ArrayList<HashMap<String, Object>> feedbacks = feedbackValueListener.getFeedbacks();
        HashMap<String, HashMap<String, Object>> companymeetings = meetingValueListener.getMeetings();

        meetings = new ArrayList<>();

        for (HashMap<String, Object> feed : feedbacks) {

            meetings.add(new MeetingListElement()
            .setCompanyName(feed.get("CompanyName").toString())
            .setDescription(feed.get("Desc").toString())
            .setComments(feed.get("Comment").toString())

                    .setQ1(Float.parseFloat(feed.get("Q1").toString()))
                    .setQ2(Float.parseFloat(feed.get("Q2").toString()))
                    .setQ3(Float.parseFloat(feed.get("Q3").toString())));


        }

        for (HashMap<String, Object> meet : companymeetings.values()) {

            meetings.add(new MeetingListElement()
            .setCompanyName(meet.get("Title").toString())
            .setDescription(meet.get("Desc").toString())
            .setActualPeople(Integer.parseInt(meet.get("ExpectedPeople").toString())));

        }

        //Log.d(TAG, "FEEDBACK: " + feedbacks);

    }
}


/**
 * A simple {@link Fragment} subclass.
 */
public class FeedbackHomeFragment extends Fragment {

    private String userID = "DEFAULT_USER_ID_1";
    private String companyID = "001";
    private boolean isCompany = true;
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


        //FirebaseDB firebaseDB = new FirebaseDB();


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
        //vh.logoImageView = vh.feedbackView.findViewById(R.id.feedback_list_elements_image);
        vh.feedbackView.setOnClickListener(vh);
        vh.feedbackView.setBackgroundResource(android.R.drawable.list_selector_background);
        //vh.logoImageView.setOnClickListener(vh);
//      vh.logoImageView.setBackgroundResource(android.R.drawable.btn_default);
        vh.linearLayout.addView(vh.feedbackView);
        return vh;
    }

    @Override
    public void onBindViewHolder(ListViewHolder vh, int position) {
        vh.titleTextView.setText(data.meetings.get(position).getCompanyName());
        vh.descriptionTextView.setText(data.meetings.get(position).getDescription());  // TEXT HERE
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

        FeedbackViewFragment.setValues(data.meetings.get(position));

        getActivity().getSupportFragmentManager().beginTransaction().add(
                R.id.frameLayoutEmployee, new FeedbackViewFragment()).addToBackStack("FeedbackListItem").commit();

    }
}

}
