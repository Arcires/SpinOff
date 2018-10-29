package group3.spinoff.employeeUI;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import group3.spinoff.R;

public class PinFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rod = inflater.inflate(R.layout.fragment_pin, container, false);

        return rod;

    }

}
