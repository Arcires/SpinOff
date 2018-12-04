package group3.spinoff.employeeUI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import group3.spinoff.R;

public class RulesPage extends AppCompatActivity {

    ImageButton back;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.termsandrules_layout);

        back = findViewById(R.id.termsAndRulesBack);
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
