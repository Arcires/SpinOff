package group3.spinoff;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class RoleSelectionActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonAppInfo, buttonEmployeeLogin;
    private TextView textViewCompanyLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_selection);

        textViewCompanyLogin = findViewById(R.id.textViewCompanyLogIn);
        buttonAppInfo = findViewById(R.id.buttonAppInfo);
        buttonEmployeeLogin = findViewById(R.id.buttonEmployeeLogIn);
        TextView textViewCopyright = findViewById(R.id.textViewCopyright);

        textViewCompanyLogin.setOnClickListener(this);
        buttonAppInfo.setOnClickListener(this);
        buttonEmployeeLogin.setOnClickListener(this);

        textViewCompanyLogin.setPaintFlags(textViewCompanyLogin.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        textViewCopyright.setPaintFlags(textViewCopyright.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);


    }

    @Override
    public void onClick(View view) {
        if (view == textViewCompanyLogin) {
            Intent i = new Intent(this, CompanyLoginActivity.class);
            startActivity(i);
        } else if (view == buttonAppInfo) {
            Intent i = new Intent(this, AppInfoActivity.class);
            startActivity(i);
        } else if (view == buttonEmployeeLogin) {
            Toast.makeText(this, "Klikkede på medarbejderlogin.", Toast.LENGTH_SHORT).show();
        }
    }
}
