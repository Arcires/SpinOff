package group3.spinoff.employeeUI;

import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import group3.spinoff.R;
import group3.spinoff.employeeUI.views.FeedbackViewFragment;

public class MainEmployeeUI extends AppCompatActivity implements  View.OnClickListener, FeedbackViewFragment.OnFragmentInteractionListener {

    FrameLayout frameLayoutEmployee;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_more:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutEmployee, new MoreFragment()).commit();
                    return true;
                case R.id.navigation_feedback:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutEmployee, new FeedbackHomeFragment()).commit();
                    return true;
                case R.id.navigation_meeting:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutEmployee, new MeetingFragment()).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_employee_ui);

        BottomNavigationView navigation = findViewById(R.id.navigation_employee);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.navigation_meeting);

        frameLayoutEmployee = findViewById(R.id.frameLayoutEmployee);

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutEmployee, new FeedbackHomeFragment()).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutEmployee, new MeetingFragment()).commit();


    }


    @Override
    public void onClick(View view) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
