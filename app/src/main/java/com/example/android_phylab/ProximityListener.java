package com.example.android_phylab;

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
public class ProximityListener extends Activity implements View.OnClickListener{
    Button proximityBut;
    SensorManager sensorManager;
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity_listener);

        proximityBut=(Button)findViewById(R.id.proximityBut);
        proximityBut.setOnClickListener(this);

        text=(TextView)findViewById(R.id.text);

        //获得传感器管理器对象
        sensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
    }

    public class InnerProximityListener implements SensorEventListener{
        @Override
        public void onSensorChanged(SensorEvent event) {
            //距离传感器测试手机屏幕距离别的物体的记录，只有两个值：0和5
            //距离很近时为0，否则为5
//            System.out.println(event.values[0]+"");
            text.setText("距离状态（距离很近时为0，否则为5）：" + event.values[0]);
        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {}
    }

    @Override
    public void onClick(View v) {
        if(v==proximityBut){
            Sensor proximitySensor=sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
            Boolean res=sensorManager.registerListener(new InnerProximityListener(),proximitySensor,SensorManager.SENSOR_DELAY_NORMAL);
//            Toast.makeText(this,"绑定距离传感器："+res,Toast.LENGTH_LONG).show();
        }
    }
}
