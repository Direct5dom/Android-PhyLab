package com.example.android_phylab;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

//1,获得SensorManager对象
//2,获得想要的Sensor对象
//3,绑定监听器
public class OrientationListener extends Activity implements View.OnClickListener{
    Button orientationBut;
    SensorManager sensorManager;
    TextView text,accText,luxText;
    float gravity[]=new float[3];
    float linear_acceleration[]=new float[3];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orientation_listener);

        orientationBut=(Button)findViewById(R.id.orientationBut);
        orientationBut.setOnClickListener(this);

        text=(TextView)findViewById(R.id.text);
        accText=(TextView)findViewById(R.id.accText);
        luxText=(TextView)findViewById(R.id.luxText);

        //获得传感器管理器对象
        sensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
    }

    public class OrientaationListener implements SensorEventListener{
        @Override
        public void onSensorChanged(SensorEvent event) {
            //（需要手机屏幕向上，向下的话南北会反掉）设备绕Z轴旋转，Y轴正方向与地磁北极方向的夹角，顺时针方向为正，范围【0，180】
            float azimuth=event.values[0];
            //设备绕X轴旋转的角度，当Z轴向Y轴正方向旋转时为正，反之为负，范围【-180,180】
            float pitch=event.values[1];
            //设备绕Y轴旋转的角度，当Z轴向X轴正方向旋转时为负，反之为正，范围【-90,90】
            float roll=event.values[2];

            text.setText("设备绕Z轴旋转的角度："+azimuth);
            accText.setText("设备绕X轴旋转的角度："+pitch);
            luxText.setText("设备绕Y轴旋转的角度："+roll);
        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {}
    }

    @Override
    public void onClick(View v) {
        if(v==orientationBut){
            Sensor orientationSensor=sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
            Boolean res=sensorManager.registerListener(new OrientaationListener(),orientationSensor,SensorManager.SENSOR_DELAY_NORMAL);
//            Toast.makeText(this,"绑定方向传感器："+res,Toast.LENGTH_LONG).show();
        }
    }
}
