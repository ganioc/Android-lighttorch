package com.boxshell.lesson.lighttorch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class MainActivityStatic extends AppCompatActivity {
    private final static String TAG="stati";
    private static boolean bOn = false;

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
        getSupportActionBar().setElevation(0);

        //View appBarLayout = (View) findViewById(R.id.appBarLayout);
        //appBarLayout.setElevation(0);
        bOn = false;

        View lightFab = (View) findViewById(R.id.lightFab);
        lightFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bOn = (bOn)?false:true;
                Log.d(TAG, "Clicked" + Boolean.toString(bOn));
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
