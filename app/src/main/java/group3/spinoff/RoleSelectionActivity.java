package group3.spinoff;

import android.content.Intent;
import android.content.pm.ActivityInfo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import group3.spinoff.employeeUI.MainEmployeeUI;

public class RoleSelectionActivity extends AppCompatActivity implements View.OnClickListener {

    private CircularProgressButton buttonEmployeeLogin;
    private Button buttonCompanyLogin;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_selection);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        buttonCompanyLogin = findViewById(R.id.buttonCompanyLogIn);
        buttonEmployeeLogin = findViewById(R.id.buttonEmployeeLogIn);

        buttonCompanyLogin.setOnClickListener(this);
        buttonEmployeeLogin.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user;

    }

    @Override
    public void onClick(View view) {
        if (view == buttonCompanyLogin) {
            Intent i = new Intent(this, CompanyLoginActivity.class);
            startActivity(i);
            overridePendingTransition(R.anim.slideinright_anim, R.anim.slideoutleft_anim);
        } else if (view == buttonEmployeeLogin) {
            buttonEmployeeLogin.startAnimation();
            mAuth.signInAnonymously().addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplication().getApplicationContext(), R.string.login_employee_success, Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplication(), MainEmployeeUI.class);
                        overridePendingTransition(R.anim.slideinright_anim, R.anim.slideoutleft_anim);
                        finish();
                        startActivity(i);
                    } else {
                        Toast.makeText(getApplicationContext(), R.string.login_employee_failed, Toast.LENGTH_SHORT).show();
                        buttonEmployeeLogin.revertAnimation();
                    }
                }
            });

        }
    }


}
