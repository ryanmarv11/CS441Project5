package com.example.cs441project5;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private Accelerometer accelerometer;
    private Gyroscope gyroscope;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accelerometer = new Accelerometer(this);
        gyroscope = new Gyroscope(this);
        this.accelerometer.setLisener(new Accelerometer.Listener() {
            @Override
            public void onTranslation(float translationX, float translationY, float translationZ) {
                if(translationX > 1.0f)
                {
                    getWindow().getDecorView().setBackgroundColor(Color.RED);
                }
                else if(translationX < -1.0f)
                {
                    getWindow().getDecorView().setBackgroundColor(Color.BLUE);
                }
            }
        });

        gyroscope.setListener(new Gyroscope.Listener() {
            @Override
            public void onRotation(float rotationX, float RotationY, float rotationZ) {
                if(rotationZ > 1.0f)
                {
                    getWindow().getDecorView().setBackgroundColor(Color.GREEN);
                }
                else if(rotationZ < -1.0f)
                {
                    getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
                }
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();

        accelerometer.register();
        gyroscope.register();
    }

    @Override
    protected void onPause() {
        super.onPause();

        accelerometer.unregister();
        gyroscope.unregister();
    }
}