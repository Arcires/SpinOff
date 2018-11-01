package group3.spinoff.employeeUI;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import group3.spinoff.R;
import group3.spinoff.employeeUI.dummy.DummyContent;

public class MainEmployeeUI extends AppCompatActivity implements FeedbackItemFragment.OnListFragmentInteractionListener, View.OnClickListener {

    FrameLayout frameLayoutEmployee;
    FloatingActionButton fab;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_settings:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutEmployee, new ProfileFragment()).commit();
                    return true;
                case R.id.navigation_feedback:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutEmployee, new FeedbackHomeFragment()).commit();
                    return true;
                case R.id.navigation_enterpin:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutEmployee, new PinFragment()).commit();
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
        fab = findViewById(R.id.floatingActionButtonEmployee);
        fab.setOnClickListener(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutEmployee, new FeedbackHomeFragment()).commit();

    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {
        Toast.makeText(MainEmployeeUI.this, "You clicked on " + item, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        if (view == fab) {
            Toast.makeText(this, "Du trykkede på toast", Toast.LENGTH_SHORT).show();
        }
    }
}
