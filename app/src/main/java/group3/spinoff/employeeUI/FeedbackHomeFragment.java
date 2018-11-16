package group3.spinoff.employeeUI;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import group3.spinoff.R;

class FeedbackData {
    List<String> meetings = Arrays.asList("Spinoff", "Company 1", "Company 9", "DTU", "Company 4",
            "DTU", "DTU", "DTU");

    List<String> description = Arrays.asList("DTU meeting interview", "Daily Scrum meeting",
            "Workplace meeting", "Machine Learning course", "Simple meeting", "Big Data course",
            "Advanced Mobile Application course", "Data Security course");

}


/**
 * A simple {@link Fragment} subclass.
 */
public class FeedbackHomeFragment extends Fragment {

    FeedbackData data = new FeedbackData();

    RecyclerView recyclerView;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        recyclerView = new RecyclerView(this.getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapter);

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

            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutEmployee, new FeedbackView()).commit();

        }
    }

}
