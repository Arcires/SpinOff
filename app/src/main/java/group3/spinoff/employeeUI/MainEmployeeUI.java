package group3.spinoff.employeeUI;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import group3.spinoff.R;
import group3.spinoff.employeeUI.views.FeedbackViewFragment;

public class MainEmployeeUI extends AppCompatActivity implements  View.OnClickListener, FeedbackViewFragment.OnFragmentInteractionListener {

    FrameLayout frameLayoutEmployee;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_settings:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutEmployee, new MoreFragment()).commit();
                    return true;
                case R.id.navigation_feedback:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutEmployee, new FeedbackHomeFragment()).commit();
                    return true;
                case R.id.navigation_enterpin:
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
