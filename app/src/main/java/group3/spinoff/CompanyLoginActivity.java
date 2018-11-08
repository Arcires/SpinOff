package group3.spinoff;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CompanyLoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonCompanyLogIn;
    private TextView textViewLoginHelp;
    private EditText editTextCompanyCVR, editTextCompanyPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_login);

        buttonCompanyLogIn = findViewById(R.id.textViewCompanyLogIn);
        buttonCompanyLogIn.setOnClickListener(this);

        textViewLoginHelp = findViewById(R.id.textViewLoginHelp);
        textViewLoginHelp.setOnClickListener(this);

        editTextCompanyCVR = findViewById(R.id.editTextCompanyCVR);
        editTextCompanyPass = findViewById(R.id.editTextCompanyPass);

    }

    @Override
    public void onClick(View view) {
        if (view == buttonCompanyLogIn) {
            Toast.makeText(this, "Logger ind på bruger: " + editTextCompanyCVR.getText() + "\nmed kodeord: " + editTextCompanyPass.getText(), Toast.LENGTH_SHORT).show();
        } else if (view == textViewLoginHelp) {
            Toast.makeText(this, "Trykkede på Glemt kodeord", Toast.LENGTH_SHORT).show();
        }
    }
}
