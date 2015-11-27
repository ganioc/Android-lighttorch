package com.boxshell.lesson.lighttorch;


import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ControlFragment extends Fragment {
    private final static String TAG = "ControlFragment";
    ControlFragmentInterface activity;

    public interface ControlFragmentInterface {
        public void onFabClicked();
        public Boolean getLightState();
    }

    public ControlFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "into onCreateView");
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_control, container, false);

        FloatingActionButton btn = (FloatingActionButton)myView.findViewById(R.id.lightFab);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.onFabClicked();
            }
        });
        Boolean bOn = activity.getLightState();
        baseChangeButtonColor(bOn, myView);
        return myView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void onDestroyView() {
        Log.d(TAG,"into onDestroyView()");

        super.onDestroyView();
    }

    @Override
    public void onAttach(Context context) {
        Log.d(TAG, "into onAttach");
        super.onAttach(context);
        activity = (ControlFragmentInterface)context;

    }

    @Override
    public void onResume() {
        Log.d(TAG, "into onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d(TAG, "into onPause");
        super.onPause();
    }

    public void changeButtonColor(Boolean on){
        Log.d(TAG, "changeFabColor");
        FloatingActionButton fab = (FloatingActionButton)getActivity().
                findViewById(R.id.lightFab);
        if(on) {
            fab.setBackgroundTintList(ColorStateList.valueOf(getResources()
                    .getColor(R.color.colorAccent)));
        }
        else {
            fab.setBackgroundTintList(ColorStateList.valueOf(getResources()
                    .getColor(R.color.colorPrimary)));
        }
    }

    public void baseChangeButtonColor(Boolean on, View v){
        Log.d(TAG, "basechangeFabColor");
        FloatingActionButton fab = (FloatingActionButton)v.
                findViewById(R.id.lightFab);
        if(on) {
            fab.setBackgroundTintList(ColorStateList.valueOf(getResources()
                    .getColor(R.color.colorAccent)));
        }
        else {
            fab.setBackgroundTintList(ColorStateList.valueOf(getResources()
                    .getColor(R.color.colorPrimary)));
        }
    }
}