package group3.spinoff.employeeUI;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import group3.spinoff.AppInfoActivity;
import group3.spinoff.R;
import group3.spinoff.RoleSelectionActivity;

public class MoreFragment extends Fragment {


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more,
                container, false);

        final Button buttonSettings = view.findViewById(R.id.buttonMoreSettings);
        final Button buttonRulesAndTerms = view.findViewById(R.id.buttonMoreTermsAndConditions);
        final Button buttonAboutUs = view.findViewById(R.id.buttonMoreAboutUs);
        final Button buttonLogOut = view.findViewById(R.id.buttonMoreLogOut);

        final FirebaseAuth mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = mAuth.getCurrentUser();


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

        return view;
    }
}