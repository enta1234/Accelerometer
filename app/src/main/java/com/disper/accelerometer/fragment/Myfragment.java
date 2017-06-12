package com.disper.accelerometer.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.disper.accelerometer.R;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Myfragment extends Fragment {

    @Bind(R.id.txtPoint)
    TextView txtPoint;
    @Bind(R.id.txtOrein)
    TextView txtOrein;

    private String[] arrOrein = {"Roll Top", "Roll Down", "Left", "Right"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);
        ButterKnife.bind(this, view);

        txtPoint.setText("0");
        txtOrein.setText(setRandom());

        return view;
    }

    private String setRandom() {
        int idx = new Random().nextInt(arrOrein.length);
        String random = (arrOrein[idx]);
        return random;
    }
}
