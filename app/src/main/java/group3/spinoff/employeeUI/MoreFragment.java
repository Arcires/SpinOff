package group3.spinoff.employeeUI;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import group3.spinoff.AppInfoActivity;
import group3.spinoff.R;
import group3.spinoff.RoleSelectionActivity;

public class MoreFragment extends Fragment implements View.OnClickListener {

    private Button buttonSettings;
    private Button buttonLogOut;
    private Button buttonInfo;

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();


    TextView textViewLoginType;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more,
                container, false);

        buttonSettings = view.findViewById(R.id.buttonMoreSettings);
        buttonLogOut = view.findViewById(R.id.buttonMoreLogout);
        buttonInfo = view.findViewById(R.id.buttonMoreInfo);

        buttonSettings.setOnClickListener(this);

        textViewLoginType = view.findViewById(R.id.textViewLoginType);

        textViewLoginType.setText(setupUserType());

        buttonLogOut.setOnClickListener(this);

        buttonInfo.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

        /*
        buttonSettings.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {
                                                  Intent i = new Intent(getContext(), SettingsActivity.class);
                                                  startActivity(i);
                                                  ((Activity) getContext()).overridePendingTransition(R.anim.slideinright_anim, R.anim.slideoutleft_anim);
                                              }
                                          }
        );
        buttonRulesAndTerms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), RulesPage.class);
                startActivity(i);
                ((Activity) getContext()).overridePendingTransition(R.anim.slideinright_anim, R.anim.slideoutleft_anim);
            }
        });
        buttonAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), AppInfoActivity.class);
                startActivity(i);
                ((Activity) getContext()).overridePendingTransition(R.anim.slideinright_anim, R.anim.slideoutleft_anim);

            }
        });
        buttonLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (user != null) {
                        if (user.getEmail() != null) {
                            System.out.println("Loggede ud fra bruger: " + user.getEmail());
                            Toast.makeText(getActivity(), "Loggede ud fra bruger: " + user.getEmail(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), "Loggede ud som anonym medarbejder", Toast.LENGTH_SHORT).show();
                        }
                        mAuth.signOut();
                        Intent i = new Intent(view.getContext(), RoleSelectionActivity.class);
                        getActivity().finish();
                        startActivity(i);
                    }
                } catch (Exception e) {
                    e.getMessage();
                    Toast.makeText(getActivity(), "Log ud mislykkedes. Ingen bruger registreret.", Toast.LENGTH_SHORT).show();
                }

            }
        });
        */

        return view;
    }

    private String setupUserType() {

        String loginEntity, loginOutput;

        try {
            if (user.isAnonymous()) {
                loginOutput = getResources().getString(R.string.general_employee);
                return loginOutput;
            } else {
                loginEntity = user.getEmail().split("@")[0];
                loginOutput = loginEntity.substring(0, 1).toUpperCase() + loginEntity.substring(1);
                return loginOutput;
            }
        } catch (Exception e) {
            loginOutput = getResources().getString(R.string.general_error_reading);
            return loginOutput;
        }
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.buttonMoreSettings:
                Intent i = new Intent(getContext(), SettingsActivity.class);
                startActivity(i);
                ((Activity) getContext()).overridePendingTransition(R.anim.slideinright_anim, R.anim.slideoutleft_anim);
                break;
            case R.id.buttonMoreInfo:
                Intent j = new Intent(getContext(), AppInfoActivity.class);
                startActivity(j);
                ((Activity) getContext()).overridePendingTransition(R.anim.slideinright_anim, R.anim.slideoutleft_anim);
                break;
            case R.id.buttonMoreLogout:
                try {
                    if (user != null) {
                        //Snackbar sb = Snackbar.make(getActivity().findViewById(R.id.snackbar_placement), "", Snackbar.LENGTH_SHORT);
                        if (user.getEmail() != null) {
                            //Snackbar.make(getActivity().findViewById(R.id.buttonEmployeeLogIn), "Helloe", Snackbar.LENGTH_SHORT).show();
                            //String text = getResources().getString(R.string.employee_more_loggedoutcompanytext) + user.getEmail();
                            //sb.setText(text).show();
                            Toast.makeText(getActivity(), getResources().getString(R.string.employee_more_loggedoutcompanytext) + user.getEmail(), Toast.LENGTH_SHORT).show();
                        } else {
                            //String text = getResources().getString(R.string.employee_more_loggedoutemployeetext);
                            //sb.setText(text).show();
                            Toast.makeText(getActivity(), getResources().getString(R.string.employee_more_loggedoutemployeetext), Toast.LENGTH_SHORT).show();
                        }
                        
                        mAuth.signOut();
                        Intent k = new Intent(view.getContext(), RoleSelectionActivity.class);
                        getActivity().finish();
                        startActivity(k);

                    }
                } catch (Exception e) {
                    e.getMessage();
                    Toast.makeText(getActivity(), "Log ud mislykkedes. Ingen bruger registreret.", Toast.LENGTH_SHORT).show();
                }

        }
    }
}