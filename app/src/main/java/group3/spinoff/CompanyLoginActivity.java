package group3.spinoff;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import group3.spinoff.employeeUI.MainEmployeeUI;

public class CompanyLoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonCompanyLogIn;
    private TextView textViewLoginHelp;
    private EditText editTextCompanyMail, editTextCompanyPass;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_login);

        buttonCompanyLogIn = findViewById(R.id.textViewCompanyLogIn);
        buttonCompanyLogIn.setOnClickListener(this);

        textViewLoginHelp = findViewById(R.id.textViewLoginHelp);
        textViewLoginHelp.setOnClickListener(this);

        editTextCompanyMail = findViewById(R.id.editTextCompanyMail);
        editTextCompanyPass = findViewById(R.id.editTextCompanyPass);

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View view) {
        if (view == buttonCompanyLogIn) {

            String mail = editTextCompanyMail.getText().toString();
            String pass = editTextCompanyPass.getText().toString();

            if (mail.equals("") || pass.equals("")) {
                Toast.makeText(this, "Indtast venligst både mail og password", Toast.LENGTH_SHORT).show();
            }
            firebaseSignIn(mail, pass);

        } else if (view == textViewLoginHelp) {
            Toast.makeText(this, "Trykkede på Glemt kodeord", Toast.LENGTH_SHORT).show();
        }
    }

    private void firebaseSignIn(final String mail, final String pass) {
        mAuth.signInWithEmailAndPassword(mail, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    Toast.makeText(getApplicationContext(), "Userlogin successful: " + mail, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), MainEmployeeUI.class);
                    finish();
                    startActivity(i);
                } else {
                    System.out.println("Userlogin failed: " + mail + ", " + pass + "\n" + task.getException());
                    Toast.makeText(getApplicationContext(), R.string.company_login_login_failed, Toast.LENGTH_SHORT).show();
                    editTextCompanyPass.setText("");
                }
            }
        });

    }
}
