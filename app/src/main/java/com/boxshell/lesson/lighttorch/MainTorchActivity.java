package com.boxshell.lesson.lighttorch;

import android.os.PersistableBundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

public class MainTorchActivity extends AppCompatActivity
        implements ControlFragment.ControlFragmentInterface,
    PreviewFragment.PreviewFragmentInterface
{
    private final static String TAG = "MainTorchActivity";
    private final static String TAG_LIGHT_STATE = "LightState";
    private static boolean bOn;                 //light is on or not
    private static FlashLight objFlash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_torch);

        Log.d(TAG, "into MainTorchActivity onCreate()");

        FragmentManager fm = getSupportFragmentManager();
        if (fm.findFragmentById(R.id.previewFragment) == null
                || fm.findFragmentById(R.id.controlFragment) == null) {

            initFragments();

        }

        Log.d(TAG, "can define some widget callback here. No, ");

        if(savedInstanceState != null){
            bOn = savedInstanceState.getBoolean(TAG_LIGHT_STATE);
            Log.d(TAG, "getSavedInstanceState, reveal to be:"+ bOn);
        }else{
            bOn = false;
        }

        //show_light(bOn);
        if(!objFlash.init_light(this)){
            Toast.makeText(this, R.string.light_error, Toast.LENGTH_LONG);

        }

        if(bOn){
            objFlash.on();
        }

    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "into onDestroy()");
        super.onDestroy();

        if(objFlash.getState()){

            objFlash.quit();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "into onResume()");

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState");

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState:" + bOn);
        outState.putBoolean(TAG_LIGHT_STATE, bOn);
    }

    private void initFragments() {

        Log.d(TAG, "into initFragments()");

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        PreviewFragment previewFragment = new PreviewFragment();
        ControlFragment controlFragment = new ControlFragment();

        ft.add(R.id.previewFragment, previewFragment);
        ft.add(R.id.controlFragment, controlFragment);
        //ft.add(R.id.controlFragment, controlFragment);
        ft.commit();
        Log.d(TAG, "out of initFragment");
    }

    public void onFabClicked() {
        //switch the picture
        Log.d(TAG, "fab clicked");
        bOn = bOn?false:true;

        show_light(bOn);

        if(objFlash.getState()){
            if(bOn){
                objFlash.on();
            }else{
                objFlash.off();
            }

        }
        else{
            Toast.makeText(this,R.string.light_error,Toast.LENGTH_LONG);
        }
    }

    private void show_light(Boolean bState){
        Log.d(TAG, "clicked:" + Boolean.toString(bState));
        show_home_img(bState);
        change_fab_color(bState);
    }

    private void change_fab_color(Boolean bState){
        ControlFragment cf = (ControlFragment)getSupportFragmentManager()
                .findFragmentById(R.id.controlFragment);
        if(cf!= null){
            Log.d(TAG, "controlfragment is not null");
            cf.changeButtonColor(bState);
        }

    }
    private void show_home_img(Boolean bState){
        PreviewFragment pf = (PreviewFragment)getSupportFragmentManager()
                .findFragmentById(R.id.previewFragment);
        if(pf!= null){
            Log.d(TAG, "pf is not null");
            pf.changeImg(bState);
        }

    }
    // to get bOn
    public Boolean getLightState(){
        return bOn;
    }
}
