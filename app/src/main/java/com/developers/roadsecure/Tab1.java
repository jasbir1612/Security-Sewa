package com.developers.roadsecure;


import android.app.AlertDialog;



import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;


import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cardiomood.android.controls.gauge.SpeedometerGauge;

// gforce
/**
 * A simple {@link Fragment} subclass.
 */
public class Tab1 extends Fragment implements LocationListener {
    private SpeedometerGauge speedometer;
    private View v;
    private float previousSpeed;
    private long previousTime;
    private boolean first=true;
    int flag = 0;

    public Tab1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.fragment_tab1, container, false);
        LocationManager lm = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        //Intializing
        this.onLocationChanged(null);


        AlertDFragment countDownDialog = new AlertDFragment();
        countDownDialog.setCancelable(false);
        countDownDialog.show(getActivity().getSupportFragmentManager(), "fragment_countdownTimer");

        return v;
    }


    @Override
    public void onLocationChanged(Location location) {
        float nCurrentSpeed;
        TextView t = (TextView) v.findViewById(R.id.textView);
        TextView t1 = (TextView) v.findViewById(R.id.acc);
        TextView t2 = (TextView) v.findViewById(R.id.gforce);
        String fontPath="fonts/digital-7.ttf";
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), fontPath);
        if (location == null) {
            t.setText("-.- km/h");
        } else {
            if (first) {
                previousTime = (System.currentTimeMillis() / 1000) - 1;
                previousSpeed = location.getSpeed() * 3.6f;
                first = false;
            }
            float currentSpeed = location.getSpeed() * 3.6f;
            long latestTime = System.currentTimeMillis() / 1000;
            long diff = latestTime - previousTime;
            t.setText(currentSpeed + " km/h");
            t.setTypeface(tf);
            double currentm = currentSpeed * 0.28;
            double previousm = previousSpeed * 0.28;
            double acc = (currentm - previousm) / diff;
            t1.setText(acc + " m/s^2");
            double gforce = acc / 9.8;
            t2.setText(gforce + " force");


            if(gforce>1)
            {
                AlertDFragment countDownDialog = new AlertDFragment();
                countDownDialog.setCancelable(false);
                countDownDialog.show(getActivity().getSupportFragmentManager(), "fragment_countdownTimer");
            }


            previousSpeed = currentSpeed;
            previousTime = latestTime;
            //static speedometer
            speedometer = (SpeedometerGauge) v.findViewById(R.id.speedometer);
            speedometer.setMaxSpeed(100);
            speedometer.setLabelConverter(new SpeedometerGauge.LabelConverter() {
                @Override
                public String getLabelFor(double progress, double maxProgress) {
                    return String.valueOf((int) Math.round(progress));
                }
            });
            speedometer.setMaxSpeed(100);
            speedometer.setMajorTickStep(5);
            speedometer.setMinorTicks(4);
            speedometer.addColoredRange(0, 50, Color.GREEN);
            speedometer.addColoredRange(50, 80, Color.YELLOW);
            speedometer.addColoredRange(80, 100, Color.RED);
            speedometer.setSpeed(currentSpeed, 1000, 300);
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
