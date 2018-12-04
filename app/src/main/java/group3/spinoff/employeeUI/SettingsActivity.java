package group3.spinoff.employeeUI;

import android.app.Fragment;
import android.os.Bundle;
import android.preference.Preference;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import group3.spinoff.R;

public class SettingsActivity extends AppCompatActivity {

    ImageButton back;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settingsactivity);

        getFragmentManager().beginTransaction().replace(R.id.frameLayoutSet, new SettingsFragment()).commit();
        back = findViewById(R.id.SettingsBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == back){
                    finish();
                    overridePendingTransition(R.anim.slideinleft_anim, R.anim.slideoutright_anim);
                }
            }
        });
    }
}
