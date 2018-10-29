package group3.spinoff.employeeUI;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import group3.spinoff.R;

class LandeOgByerData {
    List<String> lande = Arrays.asList("Danmark", "Norge", "Sverige", "Island", "Færøerne", "Finland",
            "Frankrig", "Spanien", "Portugal", "Nepal", "Indien", "Kina", "Japan", "Thailand");

    List<List<String>> byer = Arrays.asList(
            Arrays.asList("København", "Århus", "Odense", "Aalborg", "Ballerup"),
            Arrays.asList("Oslo", "Trondheim"),
            Arrays.asList("Stockholm", "Malmø", "Lund"),
            Arrays.asList("Reykjavík", "Kópavogur", "Hafnarfjörður", "Dalvík"),
            Arrays.asList("Tórshavn", "Klaksvík", "Fuglafjørður"),
            Arrays.asList("Helsinki", "Espoo", "Tampere", "Vantaa"),
            Arrays.asList("Paris", "Lyon"),
            Arrays.asList("Madrid", "Barcelona", "Sevilla"),
            Arrays.asList("Lissabon", "Porto"),
            Arrays.asList("Kathmandu", "Bhaktapur"),
            Arrays.asList("Mumbai", "Delhi", "Bangalore"),
            Arrays.asList("Shanghai", "Zhengzhou"),
            Arrays.asList("Tokyo", "Osaka", "Hiroshima", "Kawasaki", "Yokohama"),
            Arrays.asList("Bankok", "Sura Thani", "Phuket"));
}


/**
 * A simple {@link Fragment} subclass.
 */
public class FeedbackHomeFragment extends Fragment {

    LandeOgByerData data = new LandeOgByerData();

    HashSet<Integer> åbneLande = new HashSet<>(); // hvilke lande der lige nu er åbne

    RecyclerView recyclerView;

    public FeedbackHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feedback_home, container, false);
    }

}
