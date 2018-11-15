package group3.spinoff.employeeUI;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import group3.spinoff.R;

class LandeOgByerData {
    List<String> meetings = Arrays.asList("Spinoff", "Company 1", "Company 9", "DTU", "Company 4",
            "DTU", "DTU", "DTU");

    List<String> description = Arrays.asList("DTU meeting interview", "Daily Scrum meeting",
            "Workplace meeting", "Machine Learning course", "Simple meeting", "Big Data course",
            "Advanced Mobile Application course", "Data Security course");

    List<List<String>> byer = Arrays.asList(
            Arrays.asList("null"));
}


/**
 * A simple {@link Fragment} subclass.
 */
public class FeedbackHomeFragment extends Fragment {

    LandeOgByerData data = new LandeOgByerData();

    HashSet<Integer> åbneLande = new HashSet<>(); // hvilke lande der lige nu er åbne

    RecyclerView recyclerView;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        recyclerView = new RecyclerView(this.getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapter);

        //getActivity().setContentView(recyclerView);

        if (savedInstanceState!=null) {
            åbneLande = (HashSet<Integer>) savedInstanceState.getSerializable("åbneLande");
            recyclerView.getLayoutManager().onRestoreInstanceState(savedInstanceState.getParcelable("liste"));
        }

        return recyclerView;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) { // Understøttelse for skærmvending - kan evt udelades
        super.onSaveInstanceState(outState);
        outState.putSerializable("åbneLande", åbneLande);
        outState.putParcelable("liste", recyclerView.getLayoutManager().onSaveInstanceState());
    }

    RecyclerView.Adapter adapter = new RecyclerView.Adapter<EkspanderbartListeelemViewholder>() {

        @Override
        public int getItemCount()  {
            return data.meetings.size();
        }

        @Override
        public EkspanderbartListeelemViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
            LinearLayout rodLayout = new LinearLayout(parent.getContext());
            rodLayout.setOrientation(LinearLayout.VERTICAL);
            EkspanderbartListeelemViewholder vh = new EkspanderbartListeelemViewholder(rodLayout);
            vh.rodLayout = rodLayout;
            vh.landeview = getLayoutInflater().inflate(R.layout.lekt04_listeelement, parent, false);
            vh.overskrift = vh.landeview.findViewById(R.id.listeelem_overskrift);
            vh.beskrivelse = vh.landeview.findViewById(R.id.listeelem_beskrivelse);
            vh.åbnLukBillede = vh.landeview.findViewById(R.id.listeelem_billede);
            vh.landeview.setOnClickListener(vh);
            vh.landeview.setBackgroundResource(android.R.drawable.list_selector_background); // giv visuelt feedback når der trykkes på baggrunden
            vh.åbnLukBillede.setOnClickListener(vh);
//      vh.åbnLukBillede.setBackgroundResource(android.R.drawable.btn_default);
            vh.rodLayout.addView(vh.landeview);
            return vh;
        }

        @Override
        public void onBindViewHolder(EkspanderbartListeelemViewholder vh, int position) {
            vh.overskrift.setText(data.meetings.get(position));
            vh.beskrivelse.setText(data.description.get(position));  // TEXT HERE
        }
    };


    /**
     * En Viewholder husker forskellige views i et listeelement, sådan at søgninger i viewhierakiet
     * med findViewById() kun behøver at ske EN gang.
     * Se https://developer.android.com/training/material/lists-cards.html
     */
    class EkspanderbartListeelemViewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        LinearLayout rodLayout;
        TextView overskrift;
        TextView beskrivelse;
        ImageView åbnLukBillede;
        View landeview;
        ArrayList<TextView> underviews = new ArrayList<>();

        public EkspanderbartListeelemViewholder(View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View v) {
            final int position = getAdapterPosition();

                int id = v.getId();
                Toast.makeText(v.getContext(), "Klik på by nummer " + id + " i "+data.meetings.get(position), Toast.LENGTH_SHORT).show();


            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutEmployee, new FeedbackView()).commit();

            //android.app.Fragment feedback = new FeedbackView();
            //FragmentManager fragmentManager=getActivity().getFragmentManager();
            //FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            //fragmentTransaction.replace(R.id.frameLayoutEmployee, feedback,"tag");
            //fragmentTransaction.addToBackStack(null);
            //fragmentTransaction.commit();

        }
    }

}
