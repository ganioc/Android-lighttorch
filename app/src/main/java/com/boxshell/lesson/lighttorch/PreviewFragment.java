package com.boxshell.lesson.lighttorch;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class PreviewFragment extends Fragment {
    private final static String TAG = "PreviewFragment";
    PreviewFragmentInterface activity;

    public interface PreviewFragmentInterface {
        public Boolean getLightState();
    }
/*    public interface PreviewFragmentInterface {
        public void changeImg();
    }*/

    public PreviewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "into onCreateView");
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_preview, container, false);

        Log.d(TAG, "get from activity:" + activity.getLightState());
        Boolean bOn = activity.getLightState();
        baseChangeImg(bOn, v);

        return v;
    }

    @Override
    public void onDestroyView() {
        Log.d(TAG, "into onDestroyView()");

        super.onDestroyView();
    }

    @Override
    public void onAttach(Context context) {
        Log.d(TAG, "into onAttach");
        super.onAttach(context);
        activity = (PreviewFragmentInterface) context;

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

    // change the background of home image
    public void changeImg(Boolean  on){
        Log.d(TAG, "changeImg");
        ImageView iv = (ImageView)getActivity().findViewById(R.id.imageHome);
        if(on) {
            iv.setImageResource(R.drawable.static_sunrise);
        }
        else {
            iv.setImageResource(R.drawable.static_night);
        }
    }

    private void baseChangeImg( Boolean on, View v ){
        Log.d(TAG, "baseChangeImg");
        ImageView iv = (ImageView) v.findViewById(R.id.imageHome);
        if(on) {
            iv.setImageResource(R.drawable.static_sunrise);
        }
        else {
            iv.setImageResource(R.drawable.static_night);
        }
    }

}
