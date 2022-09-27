package com.example.android_phylab;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

//1,获得SensorManager对象
//2,获得想要的Sensor对象
//3,绑定监听器
public class LightSensorListener extends Activity implements View.OnClickListener{
    Button lightBut;
    SensorManager sensorManager;
    TextView timesTampText,resolutionText,accuracyText,intensityText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_sensor_listener);

        lightBut=(Button)findViewById(R.id.lightBut);
        lightBut.setOnClickListener(this);

        timesTampText=(TextView)findViewById(R.id.timesTampText);
        resolutionText=(TextView)findViewById(R.id.resolutionText);
        accuracyText=(TextView)findViewById(R.id.accuracyText);
        intensityText=(TextView)findViewById(R.id.intensityText);

        //获得传感器管理器对象
        sensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
    }

    public class InnerLightSensorListener implements SensorEventListener {
        @SuppressLint("SetTextI18n")
        @Override
        //传感器的数据被打包成event，主要的检测数据放在enent.values[]数组中
        public void onSensorChanged(SensorEvent event) {
//            System.out.println(event.timestamp);//时间戳
//            System.out.println(event.sensor.getResolution());//分辨率（能识别出最小数值）
//            System.out.println(event.accuracy);//精度（等级）
//            System.out.println(event.values[0]);//光线强度
            timesTampText.setText("时间戳：" + event.timestamp);
            resolutionText.setText("分辨率（能识别出最小数值）：" + event.sensor.getResolution());
            accuracyText.setText("精度（等级）：" + event.accuracy);
            intensityText.setText("光线强度：" + event.values[0]);
        }
        @Override
        //传感器精度变化时调用这个函数
        public void onAccuracyChanged(Sensor sensor, int accuracy) {}
    }

    @Override
    public void onClick(View v) {
        if(v==lightBut){
            //得到默认的加速度传感器
            Sensor lightSensor=sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
            //绑定监听器（上下文接口，要监听的传感器，传感器采样率<时间间隔>）,返回结果
            Boolean res=sensorManager.registerListener(new InnerLightSensorListener(),lightSensor,SensorManager.SENSOR_DELAY_NORMAL);
//            Toast.makeText(this,"绑定光线传感器："+res,Toast.LENGTH_LONG).show();
        }
    }
}
