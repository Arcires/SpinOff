package group3.spinoff;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import group3.spinoff.employeeUI.MainEmployeeUI;

public class RoleSelectionActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonEmployeeLogin;
    private TextView textViewCompanyLogin, textViewCopyright;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_selection);

        textViewCompanyLogin = findViewById(R.id.textViewCompanyLogIn);
        buttonEmployeeLogin = findViewById(R.id.buttonEmployeeLogIn);
        textViewCopyright = findViewById(R.id.textViewCopyright);

        textViewCompanyLogin.setOnClickListener(this);
        buttonEmployeeLogin.setOnClickListener(this);

        textViewCompanyLogin.setPaintFlags(textViewCompanyLogin.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        textViewCopyright.setPaintFlags(textViewCopyright.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);


        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user;

    }

    @Override
    public void onClick(View view) {
        if (view == textViewCompanyLogin) {
            Intent i = new Intent(this, CompanyLoginActivity.class);
            startActivity(i);
        } else if (view == buttonEmployeeLogin) {
            mAuth.signInAnonymously().addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplication().getApplicationContext(), "Loggede ind som anonym medarbejder", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplication(), MainEmployeeUI.class);
                        finish();
                        startActivity(i);
                    } else {
                        Toast.makeText(getApplicationContext(), "Anonymt login mislykkedes. Tjek din internetforbindelse.", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }


}
