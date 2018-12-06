package group3.spinoff.employeeUI.views.meetinggraphview;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import group3.spinoff.R;
import group3.spinoff.employeeUI.FeedbackHomeFragment;
import group3.spinoff.employeeUI.data.MeetingListElement;


class StableArrayAdapter extends ArrayAdapter<String> {

    HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

    public StableArrayAdapter(Context context, int textViewResourceId,
                              List<String> objects) {
        super(context, textViewResourceId, objects);
        for (int i = 0; i < objects.size(); ++i) {
            mIdMap.put(objects.get(i), i);
        }
    }

    @Override
    public long getItemId(int position) {
        String item = getItem(position);
        return mIdMap.get(item);
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }
}

//Graph documentation: http://www.android-graphview.org/
//Documentation can be found here: https://github.com/lopspower/CircularProgressBar

public class MeetingGraphViewFragment extends Fragment {

    private TextView textViewFeedbackGraphsMainTitle, textViewGraphsMeetingTitle,
            textViewGraphsMeetingTitleContent, textViewGraphsMeetingDesc,
            textViewGraphsMeetingDescContent, textViewGraphsCircleProgressCount;

    private CircularProgressBar circularProgressBar;

    private ViewPager viewPager;

    private ListView listView;

    MeetingListElement informations;

    private LinearLayout layoutDots;
    private ImageView[] imageViewdots;

    public void setValues(MeetingListElement meetingListElement) {
        this.informations = meetingListElement;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_meetinggraph, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbarGraphs);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        ActionBar ab = ((AppCompatActivity) getActivity()).getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowTitleEnabled(false);

        textViewFeedbackGraphsMainTitle = view.findViewById(R.id.textViewGraphsMainTitle);
        textViewGraphsMeetingTitle = view.findViewById(R.id.textViewGraphsMeetingTitle);
        textViewGraphsMeetingTitleContent = view.findViewById(R.id.textViewGraphsMeetingTitleContent);
        textViewGraphsMeetingDesc = view.findViewById(R.id.textViewGraphsMeetingDesc);
        textViewGraphsMeetingDescContent = view.findViewById(R.id.textViewGraphsMeetingDescContent);
        textViewGraphsCircleProgressCount = view.findViewById(R.id.textViewGraphsCircProgressCount);

        //TODO Setting up the circular progress bar. Define the current progress(answer count) and the max progress (people count). MUST BE INTS!
        circularProgressBar = view.findViewById(R.id.circleProgressAnswerCount);

        setCircularProgress(informations.getActualPeople(), informations.getExpectedPeople());

        textViewGraphsMeetingTitleContent.setText(informations.getTitle());
        textViewFeedbackGraphsMainTitle.setText(informations.getTitle());
        textViewGraphsMeetingDescContent.setText(informations.getDescription());
        textViewGraphsCircleProgressCount.setText(informations.getActualPeople()
                + "/" + informations.getExpectedPeople());


        GraphViewPager graphViewPager = new GraphViewPager(getActivity().getSupportFragmentManager());
        graphViewPager.setValues(informations);

        viewPager = view.findViewById(R.id.viewPagerGraphs);
        viewPager.setAdapter(graphViewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                createDots(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        listView = view.findViewById(R.id.listViewComments);

        layoutDots = view.findViewById(R.id.graphDotsLayout);


        final StableArrayAdapter adapter = new StableArrayAdapter(this.getContext(),
                android.R.layout.simple_list_item_1, informations.getCommentsList());

        listView.setAdapter(adapter);

        createDots(0);

        return view;
    }

    private void setCircularProgress(int current, int max) {
        circularProgressBar.setProgress(current);
        circularProgressBar.setProgressMax(max);
    }

    private void createDots(int currentPosition) {
        if (layoutDots != null) {
            layoutDots.removeAllViews();
            int viewPagerItemsCount = viewPager.getAdapter().getCount();
            imageViewdots = new ImageView[viewPagerItemsCount];

            for (int i = 0; i < viewPagerItemsCount; i++) {
                imageViewdots[i] = new ImageView(getContext());
                if (i == currentPosition) {
                    imageViewdots[i].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.active_dots));
                } else {
                    imageViewdots[i].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.inactive_dots));
                }

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.setMargins(4, 0, 4, 0);

                layoutDots.addView(imageViewdots[i], params);
            }

        }
    }

    @Nullable
    @Override
    public View getView() {
        return super.getView();
    }
}
