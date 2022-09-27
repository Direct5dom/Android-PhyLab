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
public class AccerationSensorListener extends Activity implements View.OnClickListener{
    Button accelerationBut;
    SensorManager sensorManager;
    TextView text,accText,luxText;
    float gravity[]=new float[3];
    float linear_acceleration[]=new float[3];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceration_sensor_listener);

        accelerationBut=(Button)findViewById(R.id.accelerationBut);
        accelerationBut.setOnClickListener(this);

        text=(TextView)findViewById(R.id.text);
        accText=(TextView)findViewById(R.id.accText);
        luxText=(TextView)findViewById(R.id.luxText);

        //获得传感器管理器对象
        sensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
    }

    public class InnerAccerationSensorListener implements SensorEventListener{
        @Override
        public void onSensorChanged(SensorEvent event) {
            final float alpha=0.8f;

            //event.values[0]X轴加速度，负方向为正
            //event.values[1]Y轴加速度，负方向为正
            //event.values[2]Z轴加速度，负方向为正
            gravity[0]=alpha*gravity[0]+(1-alpha)*event.values[0];
            gravity[1]=alpha*gravity[1]+(1-alpha)*event.values[1];
            gravity[2]=alpha*gravity[2]+(1-alpha)*event.values[2];

            linear_acceleration[0]=event.values[0]-gravity[0];
            linear_acceleration[1]=event.values[1]-gravity[1];
            linear_acceleration[2]=event.values[2]-gravity[2];

            //通过以上公式可以抛去三个方向上的重力加速度，只剩下纯加速度
            text.setText("X轴加速度：" + linear_acceleration[0]);
            accText.setText("Y轴加速度："+linear_acceleration[1]);
            luxText.setText("Z轴加速度："+linear_acceleration[2]);
        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {}
    }

    @Override
    public void onClick(View v) {
        if(v==accelerationBut){
            Sensor accelerometerSensor=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            Boolean res=sensorManager.registerListener(new InnerAccerationSensorListener(),accelerometerSensor,SensorManager.SENSOR_DELAY_NORMAL);
//            Toast.makeText(this,"绑定加速度传感器："+res,Toast.LENGTH_LONG).show();
        }
    }
}
