package com.boxshell.lesson.lighttorch;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by yangjun on 15/11/27.
 */
public class FlashLight {
    private final static String TAG="flashlight";
    private static Camera camera;
    private static Camera.Parameters para;
    private static Boolean state;

    public  static Boolean init_light(Context cxt){
        PackageManager pm = cxt.getPackageManager();

        if(!pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            state = false;
            return false;
        }
        camera = Camera.open();
        para = camera.getParameters();
        state = true;
        return true;
    }
    public static  void on(){
        para.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        camera.setParameters(para);
        camera.startPreview();
    }
    public static  void off(){
        para.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
        camera.setParameters(para);
        camera.stopPreview();
    }

    public static void quit(){
        if(camera != null){
            camera.stopPreview();
            camera.release();
            Log.d(TAG, "torch released");
            camera = null;
            state = false;
        }
    }
    public static Boolean getState(){
        return state;
    }
}
