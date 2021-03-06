package group3.spinoff.employeeUI.views.meetinggraphview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import group3.spinoff.R;

public class Q1Fragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.graphscreen_q1, container, false);

        GraphView graph = view.findViewById(R.id.graph1);

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(0, 6),
                new DataPoint(1, 2),
                new DataPoint(2, 2)
        });
        graph.setTitle(getResources().getString(R.string.graphview_title_q1));
        graph.setTitleTextSize(56);
        graph.addSeries(series);

        return view;

    }
}
