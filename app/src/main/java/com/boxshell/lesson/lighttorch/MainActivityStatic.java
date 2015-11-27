package com.boxshell.lesson.lighttorch;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.security.Policy;


public class MainActivityStatic extends AppCompatActivity {
    private final static String TAG="stati";
    private static boolean bOn = false;

    private static Camera camera;
    private static Camera.Parameters para;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_static);

        Intent intent = getIntent();
        Log.d(TAG, intent.getStringExtra("head"));

        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getSupportActionBar().setTitle("");
        //getSupportActionBar().setElevation(0);
        //getSupportActionBar().setElevation(0);

        //View appBarLayout = (View) findViewById(R.id.appBarLayout);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ;
            //getSupportActionBar().setElevation(0);
        }
        //bOn = false;

        View lightFab = (View) findViewById(R.id.lightFab);
        lightFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bOn = (bOn)?false:true;
                Log.d(TAG, "Clicked" + Boolean.toString(bOn));

                turn_on_led(bOn);
            }
        });

        init_torch();


    }

    private void turn_on_led(boolean bOn) {
        View lightFab = (View) findViewById(R.id.lightFab);
        ImageView img = (ImageView) findViewById(R.id.imageStatic);
        FloatingActionButton clickBtn = (FloatingActionButton)findViewById(R.id.lightFab);

        if(bOn){
            img.setImageResource(R.drawable.static_sunrise);
            torch_on();
            clickBtn.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
        }
        else{
            img.setImageResource(R.drawable.static_night);
            torch_off();
            clickBtn.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));
        }
    }

    private void torch_off() {
        para.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
        camera.setParameters(para);
        camera.stopPreview();


    }

    private void torch_on() {
        para.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        camera.setParameters(para);
        camera.startPreview();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop()");
        super.onStop();
        if(camera != null){
            camera.stopPreview();
            camera.release();
            Log.d(TAG, "torch released");
            camera = null;
        }
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "in onResume");
        super.onResume();
        if(camera == null) {
            init_torch();
        }
        turn_on_led(bOn);
    }

    @Override
    protected void onRestart() {
        Log.d(TAG,"onRestart()");
        super.onRestart();
    }

    private void init_torch(){
        PackageManager pm= getPackageManager();

        if(!pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            Log.d(TAG, "no camera flash");
            Toast.makeText(this,"no flash",Toast.LENGTH_LONG);
            return;
        }
        camera = Camera.open();
        para = camera.getParameters();
    }
}
