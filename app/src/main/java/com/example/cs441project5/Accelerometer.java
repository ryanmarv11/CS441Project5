package com.example.cs441project5;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class Accelerometer {
    private SensorManager sensorManager;
    private Sensor sensor;
    private SensorEventListener sensorEventListener;

    public interface Listener
    {
        void onTranslation(float translationX, float translationY, float translationZ);
    }
    private Listener listener;

    public void setLisener(Listener l)
    {
        this.listener = l;
    }
    Accelerometer(Context context)
    {
        this.sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        this.sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        this.sensorEventListener = new SensorEventListener()
        {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent)
            {
                if(listener != null)
                {
                    listener.onTranslation(sensorEvent.values[0], sensorEvent.values[1], sensorEvent.values[2]);
                }
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy)
            {

            }
        };
    }
    public void register()
    {
        this.sensorManager.registerListener(this.sensorEventListener, this.sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
    public void unregister()
    {
    this.sensorManager.unregisterListener(this.sensorEventListener);
    }
}
