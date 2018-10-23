package group3.spinoff;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RoleSelectionActivity extends AppCompatActivity implements View.OnClickListener {
    private Button buttonCompanyLogin, buttonAppInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_selection);

        buttonCompanyLogin = findViewById(R.id.buttonCompanyLogIn);
        buttonAppInfo = findViewById(R.id.buttonAppInfo);

        buttonCompanyLogin.setOnClickListener(this);
        buttonAppInfo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == buttonCompanyLogin) {
            Intent i = new Intent(this, CompanyLoginActivity.class);
            startActivity(i);
        } else if (view == buttonAppInfo) {
            Intent i = new Intent(this, AppInfoActivity.class);
            startActivity(i);
        }
    }
}
