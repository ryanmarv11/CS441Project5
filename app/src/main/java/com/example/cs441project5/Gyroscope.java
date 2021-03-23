package com.example.cs441project5;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class Gyroscope {

    public interface Listener
    {
        void onRotation(float rotationX, float RotationY, float rotationZ);
    }
    private Listener listener;

    public void setListener(Listener l)
    {
        this.listener = l;
    }

    private SensorManager sensorManager;
    private Sensor sensor;
    private SensorEventListener sensorEventListener;

    Gyroscope(Context context)
    {
        this.sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        this.sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        this.sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if(listener != null)
                {
                    listener.onRotation(sensorEvent.values[0], sensorEvent.values[1], sensorEvent.values[2]);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
    }

    public void register()
    {
        this.sensorManager.registerListener(this.sensorEventListener, this.sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
    public void unregister()
    {
        this.sensorManager.unregisterListener(sensorEventListener);
    }
}
