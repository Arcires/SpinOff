package group3.spinoff;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RoleSelectionActivity extends AppCompatActivity implements View.OnClickListener {
    Button buttonCompanyLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_selection);

        buttonCompanyLogin = (Button) findViewById(R.id.buttonCompanyLogIn);

        buttonCompanyLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == buttonCompanyLogin) {
            Intent i = new Intent(this, CompanyLoginActivity.class);
            startActivity(i);
        }
    }
}
