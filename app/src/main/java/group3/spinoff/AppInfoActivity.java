package group3.spinoff;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class AppInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonAboutUsExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_info);

        buttonAboutUsExit = findViewById(R.id.buttonAboutUsBack);
        buttonAboutUsExit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == buttonAboutUsExit) {
            finish();
        }
    }
}
