package com.developers.roadsecure;

/**
 * Created by Jasbir Singh on 3/17/2016.
 */
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class AlertDFragment extends DialogFragment {

    private TextView mCountdownView;
    Button b;
    int flag = 0;

    public void CountDownDialog(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.aler_dialog, container);
        mCountdownView = (TextView) view.findViewById(R.id.countdownTimer);
        b = (Button) view.findViewById(R.id.cancel);


        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = 1;
                dismiss();
            }
        });

        final CountDownTimer gameTimer = new CountDownTimer(10000, 1000) {

            @Override
            public void onTick(long l) {
                mCountdownView.setText(""+((int)Math.round(l/1000.0)-1));
            }

            @Override
            public void onFinish() {
                if(flag==0) {
                    Intent i = new Intent(getActivity(), Hospital.class);
                    startActivity(i);
                    dismiss();
                }
            }
        };



        gameTimer.start();
    }
}