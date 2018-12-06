package group3.spinoff.employeeUI.views.meetinggraphview;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import group3.spinoff.R;
import group3.spinoff.employeeUI.data.MeetingListElement;

public class AverageGraphFragment extends Fragment {

    MeetingListElement informations;

    public void setValues(MeetingListElement meetingListElement) {
        this.informations = meetingListElement;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.graphscreen_average, container, false);

        GraphView graph = view.findViewById(R.id.graphAverage);

        setUpGraph(graph);

        return view;

    }

    private void setUpGraph(GraphView graph) {


        GridLabelRenderer gridLabelRenderer = graph.getGridLabelRenderer();

        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[]{
                new DataPoint(1, 0),
                new DataPoint(1, informations.getQ1()),
                new DataPoint(2, informations.getQ2()),
                new DataPoint(3, informations.getQ3())
        });
        series.setColor(getResources().getColor(R.color.colorPrimary));
        series.setSpacing(15);
        series.setDrawValuesOnTop(true);

        gridLabelRenderer.setHorizontalAxisTitle(getResources().getString(R.string.grapview_graphxlabel));
        gridLabelRenderer.setHorizontalAxisTitleColor(getResources().getColor(R.color.colorPrimaryWhite));
        gridLabelRenderer.setVerticalAxisTitle(getResources().getString(R.string.grapview_graphylabel));

        gridLabelRenderer.setVerticalAxisTitleColor(getResources().getColor(R.color.colorPrimaryWhite));
        gridLabelRenderer.setGridColor(getResources().getColor(R.color.colorPrimaryWhite));
        gridLabelRenderer.setHorizontalLabelsColor(getResources().getColor(R.color.colorPrimaryWhite));
        gridLabelRenderer.setVerticalLabelsColor(getResources().getColor(R.color.colorPrimaryWhite));

        graph.setTitle(getResources().getString(R.string.graphview_title_average));
        graph.setTitleColor(getResources().getColor(R.color.colorPrimaryWhite));
        graph.setTitleTextSize(56);
        graph.setHorizontalScrollBarEnabled(true);

        graph.addSeries(series);
    }

}