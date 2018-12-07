package group3.spinoff.employeeUI;

import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import group3.spinoff.R;
import group3.spinoff.employeeUI.views.FeedbackViewFragment;

public class MainEmployeeUI extends AppCompatActivity implements View.OnClickListener, FeedbackViewFragment.OnFragmentInteractionListener, FragmentManager.OnBackStackChangedListener {

    FrameLayout frameLayoutEmployee;

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListenerEmployee
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_more:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutEmployee, new MoreFragment()).commit();
                    overridePendingTransition(R.anim.slideinleft_anim, R.anim.slideoutright_anim);
                    return true;
                case R.id.navigation_feedback:
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutEmployee, new FeedbackHomeFragment()).commit();
                    overridePendingTransition(R.anim.slideinleft_anim, R.anim.slideoutright_anim);
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
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListenerEmployee);
        navigation.setSelectedItemId(R.id.navigation_meeting);

        checkIfCompany(navigation);

        frameLayoutEmployee = findViewById(R.id.frameLayoutEmployee);
        getSupportFragmentManager().addOnBackStackChangedListener(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutEmployee, new FeedbackHomeFragment()).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutEmployee, new MeetingFragment()).commit();

    }

    private void checkIfCompany(BottomNavigationView navigation) {
        if (!user.isAnonymous()) {
            Menu menuCompany = navigation.getMenu();
            menuCompany.findItem(R.id.navigation_feedback).setIcon(R.drawable.ic_graph);
            menuCompany.findItem(R.id.navigation_feedback).setTitle(R.string.employee_ui_statistics);
        }
    }


    @Override
    public void onClick(View view) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public boolean onSupportNavigateUp() {
        getSupportFragmentManager().popBackStack();

        return true;
    }

    @Override
    public void onBackStackChanged() {
    }

}
