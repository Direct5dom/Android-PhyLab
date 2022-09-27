package com.example.android_phylab;

import com.example.android_phylab.LightSensorListener;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
//import android.support.annotation.NonNull;
//import android.support.annotation.RequiresApi;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

//1,获得SensorManager对象
//2,获得想要的Sensor对象
//3,绑定监听器
public class MainActivity extends Activity implements View.OnClickListener{
    Button findBut,openAccelerationBut,openLightBut,openOrientationBut,openProximityBut,openGPSModuleBut;
    SensorManager sensorManager;

    @SuppressLint("MissingPermission")
    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findBut = (Button) findViewById(R.id.findBut);
        findBut.setOnClickListener(this);
        openLightBut = (Button) findViewById(R.id.openLightBut);
        openLightBut.setOnClickListener(this);
        openAccelerationBut = (Button) findViewById(R.id.openAccelerationBut);
        openAccelerationBut.setOnClickListener(this);
        openOrientationBut = (Button) findViewById(R.id.openOrientationBut);
        openOrientationBut.setOnClickListener(this);
        openProximityBut = (Button) findViewById(R.id.openProximityBut);
        openProximityBut.setOnClickListener(this);
        openGPSModuleBut = (Button) findViewById(R.id.openGPSModuleBut);
        openGPSModuleBut.setOnClickListener(this);

        //获得传感器管理器对象
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }

    @Override
    public void onClick(View v) {
        if(v==findBut){
            //获取手机上所有传感器的列表
            List<Sensor> sensors=sensorManager.getSensorList(Sensor.TYPE_ALL);
            for(Sensor sensor:sensors){
                System.out.println(sensor.getName());
            }
        }
        else if(v==openLightBut){
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, com.example.android_phylab.LightSensorListener.class);
            startActivity(intent);
            Toast.makeText(this,"打开光线传感器",Toast.LENGTH_LONG).show();
        }
        else if(v==openAccelerationBut){
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, com.example.android_phylab.AccerationSensorListener.class);
            startActivity(intent);
            Toast.makeText(this,"打开加速度传感器",Toast.LENGTH_LONG).show();
        }
        else if(v==openOrientationBut){
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, com.example.android_phylab.OrientationListener.class);
            startActivity(intent);
            Toast.makeText(this,"打开方向传感器",Toast.LENGTH_LONG).show();
        }
        else if(v==openProximityBut){
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, com.example.android_phylab.ProximityListener.class);
            startActivity(intent);
            Toast.makeText(this,"打开距离传感器",Toast.LENGTH_LONG).show();
        }
        else if(v==openGPSModuleBut){
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, com.example.android_phylab.GPSModule.class);
            startActivity(intent);
            Toast.makeText(this,"打开GPS模块",Toast.LENGTH_LONG).show();
        }
    }
}
