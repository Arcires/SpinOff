package group3.spinoff.employeeUI.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import group3.spinoff.R;
import group3.spinoff.employeeUI.views.meetinggraphs.Meeting;
import group3.spinoff.employeeUI.views.meetinggraphs.MeetingInfoItem;

public class MeetingViewFragment extends Fragment implements View.OnClickListener {

    private TextView textViewMeetingTitle, textViewMeetingDesc;
    private Button buttonMeetingGraphsBack, buttonExportData;
    private ListView listViewMeetingData;

    static int[] meetingDataTitles = {R.string.meetinggraphs_itemtitle, R.string.meetinggraphs_itemdesc, R.string.meetinggraphs_itempeople};
    ArrayList<Meeting> meetingInfoList = new ArrayList<>();


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meetinggraphs, container, false);

        buttonMeetingGraphsBack = view.findViewById(R.id.buttonGraphsBack);
        buttonMeetingGraphsBack.setOnClickListener(this);

        buttonExportData = view.findViewById(R.id.buttonExportData);
        buttonExportData.setOnClickListener(this);

        retrieveMeetingInfo();

        listViewMeetingData = view.findViewById(R.id.listViewMeetingData);
        listViewMeetingData.setAdapter(meetingDataAdapter);

        return view;

    }

    //Is responsible for retrieving the information needed.
    //TODO Tilføj mere logik - se UserFragment fra tidligere projekt.
    //TODO Skal hente data ind fra ét specifikt møde.
    private void retrieveMeetingInfo() {
        DatabaseReference meetingInfoRef = FirebaseDatabase.getInstance().getReference("Meeting");
        meetingInfoRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    //Sets up the list itself.
    BaseAdapter meetingDataAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return meetingDataTitles.length;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            MeetingInfoItem meetingInfoItem;
            String[] meetingInformation = new String[3];

            if (view == null) {
                view = getLayoutInflater().inflate(R.layout.meetinginfo_item, null);
                meetingInfoItem = new MeetingInfoItem();
                meetingInfoItem.meetingItemTitle = view.findViewById(R.id.textViewMeetingInfoTitle);
                meetingInfoItem.meetingItemDesc = view.findViewById(R.id.textViewMeetingInfoValue);
                view.setTag(meetingInfoItem);
            } else {
                meetingInfoItem = (MeetingInfoItem) view.getTag();
            }
            if (meetingInfoList.size() > 0) {
                meetingInformation[0] = meetingInfoList.get(0).getMeetingTitle();
                meetingInformation[1] = meetingInfoList.get(0).getMeetingDesc();
                meetingInformation[2] = String.valueOf(meetingInfoList.get(0).getMeetingCount());
                meetingInfoItem.meetingItemTitle.setText(meetingDataTitles[position]);
                meetingInfoItem.meetingItemDesc.setText(meetingInformation[position]);//userInformation[position] + "");
            }
            return view;
        }
    };

    @Override
    public void onClick(View view) {
        if (view == buttonMeetingGraphsBack) {
            getActivity().finish();
        } else if (view == buttonExportData) {
            Toast.makeText(view.getContext(), R.string.meetinggraphs_exportcsvmessage, Toast.LENGTH_SHORT).show();
        }
    }
}
