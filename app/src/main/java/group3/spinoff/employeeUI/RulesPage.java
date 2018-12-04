package group3.spinoff.employeeUI;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import group3.spinoff.R;

public class RulesPage extends AppCompatActivity implements View.OnClickListener {

    Button buttonRulesBack;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termsandrules_layout);

        buttonRulesBack = findViewById(R.id.termsAndRulesBack);
        buttonRulesBack.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        if (view == buttonRulesBack) {
            finish();
            overridePendingTransition(R.anim.slideinleft_anim, R.anim.slideoutright_anim);
        }

    }
}
