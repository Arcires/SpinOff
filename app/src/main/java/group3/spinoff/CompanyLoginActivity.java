package group3.spinoff;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import group3.spinoff.employeeUI.MainEmployeeUI;

public class CompanyLoginActivity extends AppCompatActivity implements View.OnClickListener {

    private CircularProgressButton buttonCompanyLogIn;
    private TextView textViewLoginHelp;
    private EditText editTextCompanyMail, editTextCompanyPass;
    private CheckBox checkBox;
    private FirebaseAuth mAuth;
    private Toolbar toolbar;


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

        toolbar = findViewById(R.id.toolbarSettings);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public void onClick(View view) {
        if (view == buttonCompanyLogIn) {

            String mail = editTextCompanyMail.getText().toString();
            String pass = editTextCompanyPass.getText().toString();

            if (mail.equals("") || pass.equals("") || mail.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, R.string.login_employee_noinput, Toast.LENGTH_SHORT).show();
            } else if (isNetworkConnected()) {
                buttonCompanyLogIn.startAnimation();
                firebaseSignIn(mail, pass);
            } else {
                Toast.makeText(this, R.string.general_no_internet, Toast.LENGTH_SHORT).show();
            }
        } else if (view == textViewLoginHelp) {
            Snackbar.make(view, R.string.snackbar_forgot_pass, Snackbar.LENGTH_SHORT).show();
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
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    finishAffinity();
                    overridePendingTransition(R.anim.slideinright_anim, R.anim.slideoutleft_anim);
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

        return Objects.requireNonNull(cm).getActiveNetworkInfo() != null;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        overridePendingTransition(R.anim.slideinleft_anim, R.anim.slideoutright_anim);
        return true;
    }
}