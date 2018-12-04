package group3.spinoff;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Button;

public class AppInfoActivity extends AppCompatActivity {


    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_info);

      back = findViewById(R.id.buttonAboutUsBack);
      back.setOnClickListener(new OnClickListener() {
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
