package group3.spinoff.employeeUI;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import group3.spinoff.R;

public class MainEmployeeUI extends AppCompatActivity {

    private FrameLayout frameLayoutEmployee;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_settings:
                    Toast.makeText(MainEmployeeUI.this, "You clicked settings", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_feedback:
                    Toast.makeText(MainEmployeeUI.this, "You clicked feedback", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_enterpin:
                    Toast.makeText(MainEmployeeUI.this, "You clicked enter pin", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_subscriptions:
                    Toast.makeText(MainEmployeeUI.this, "You clicked subscriptions", Toast.LENGTH_SHORT).show();
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

    }

}
