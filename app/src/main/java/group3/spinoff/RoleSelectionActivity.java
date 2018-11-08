package group3.spinoff;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import group3.spinoff.employeeUI.MainEmployeeUI;

public class RoleSelectionActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonEmployeeLogin;
    private TextView textViewCompanyLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_selection);

        textViewCompanyLogin = findViewById(R.id.textViewCompanyLogIn);
        buttonEmployeeLogin = findViewById(R.id.buttonEmployeeLogIn);
        TextView textViewCopyright = findViewById(R.id.textViewCopyright);

        textViewCompanyLogin.setOnClickListener(this);
        buttonEmployeeLogin.setOnClickListener(this);

        textViewCompanyLogin.setPaintFlags(textViewCompanyLogin.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        textViewCopyright.setPaintFlags(textViewCopyright.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);


    }

    @Override
    public void onClick(View view) {
        if (view == textViewCompanyLogin) {
            Intent i = new Intent(this, CompanyLoginActivity.class);
            startActivity(i);
        } else if (view == buttonEmployeeLogin) {
            Intent i = new Intent(this, MainEmployeeUI.class);
            startActivity(i);
        }
    }
}
