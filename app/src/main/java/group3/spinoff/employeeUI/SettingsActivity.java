package group3.spinoff.employeeUI;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import group3.spinoff.R;

public class SettingsActivity extends PreferenceActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
