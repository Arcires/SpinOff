package group3.spinoff.employeeUI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import group3.spinoff.R;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonSettingsBack;
    private FrameLayout frameLayoutSettings;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        buttonSettingsBack = findViewById(R.id.SettingsBack);
        buttonSettingsBack.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        if (view == buttonSettingsBack) {
            finish();
            overridePendingTransition(R.anim.slideinleft_anim, R.anim.slideoutright_anim);
        }
    }
}
