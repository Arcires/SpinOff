package group3.spinoff;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import group3.spinoff.employeeUI.MainEmployeeUI;

public class CompanyLoginActivity extends AppCompatActivity implements View.OnClickListener {

    private CircularProgressButton buttonCompanyLogIn;
    private TextView textViewLoginHelp;
    private EditText editTextCompanyMail, editTextCompanyPass;
    private CheckBox checkBox;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_login);

        buttonCompanyLogIn = findViewById(R.id.buttonCompanyLogIn);
        buttonCompanyLogIn.setOnClickListener(this);

        textViewLoginHelp = findViewById(R.id.textViewLoginHelp);
        textViewLoginHelp.setOnClickListener(this);

        editTextCompanyMail = findViewById(R.id.editTextCompanyMail);
        editTextCompanyPass = findViewById(R.id.editTextCompanyPass);

        checkBox = findViewById(R.id.checkBoxTestUser);
        checkBox.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View view) {
        if (view == buttonCompanyLogIn) {

            String mail = editTextCompanyMail.getText().toString();
            String pass = editTextCompanyPass.getText().toString();


            if (mail.equals("") || pass.equals("")) {
                Toast.makeText(this, "Indtast venligst både mail og password.", Toast.LENGTH_SHORT).show();
            } else if (isNetworkConnected()) {
                buttonCompanyLogIn.startAnimation();
                firebaseSignIn(mail, pass);
            } else {
                Toast.makeText(this, "Intet internet detekteret.\nTjek din netforbindelse", Toast.LENGTH_SHORT).show();
            }
        } else if (view == textViewLoginHelp) {
            Toast.makeText(this, "Trykkede på Glemt kodeord", Toast.LENGTH_SHORT).show();
        }
        if (checkBox.isChecked()) {
            editTextCompanyMail.setText("dtu@spinoff.476");
            editTextCompanyPass.setText("testpass");
        } else {
            editTextCompanyMail.setText("");
            editTextCompanyPass.setText("");
        }
    }


    private void firebaseSignIn(final String mail, final String pass) {
        mAuth.signInWithEmailAndPassword(mail, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    Toast.makeText(getApplicationContext(), R.string.company_login_login_success, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), MainEmployeeUI.class);
                    finish();
                    startActivity(i);
                } else {
                    System.out.println("Userlogin failed: " + mail + ", " + pass + "\n" + task.getException());
                    Toast.makeText(getApplicationContext(), R.string.company_login_login_failed, Toast.LENGTH_SHORT).show();
                    editTextCompanyPass.setText("");
                    buttonCompanyLogIn.revertAnimation();
                }
            }
        });
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
}