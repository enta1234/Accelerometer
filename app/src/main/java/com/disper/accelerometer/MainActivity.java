package com.disper.accelerometer;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.disper.accelerometer.fragment.Myfragment;

import java.text.DecimalFormat;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.txtScaleX) TextView txtScaleX;
    @Bind(R.id.txtScaleY) TextView txtScaleY;
    @Bind(R.id.txtScaleZ) TextView txtScaleZ;
    @Bind(R.id.text) TextView txtTypeOrien;

    SensorManager sensorManager;
    Sensor sensor;
    public static String KEY_ACTION = "KEY_ACTION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getSupportFragmentManager().beginTransaction().add(R.id.container_fragment, new Myfragment()).commit();

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

    }

    public void onResume() {
        super.onResume();
        sensorManager.registerListener(accelListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);

    }

    public void onStop() {
        super.onStop();
        sensorManager.unregisterListener(accelListener);
    }

    //Class Show Value
    SensorEventListener accelListener = new SensorEventListener() {
         public void onAccuracyChanged(Sensor sensor, int acc) {

        }

        public void onSensorChanged(SensorEvent event) {

            DecimalFormat df = new DecimalFormat();
            df.applyPattern("0.00");

            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            txtScaleX.setText("X : " + df.format(x));
            txtScaleY.setText("Y : " + df.format(y));
            txtScaleZ.setText("Z : " + df.format(z));

            if ((x < 0.99 && x > -0.99) && (y < 0.99 && y > -0.99)) {

                txtTypeOrien.setText("Balance");
                Bundle bundle = new Bundle();
                Myfragment mf = new Myfragment();
                bundle.getString(KEY_ACTION, String.valueOf(txtTypeOrien.getText()));
                mf.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment,mf).commit();

            } else if (x < 1 && (y > -0.99 && y < 0.99)) {
                txtTypeOrien.setText("Right");
            } else if (x > -1 && (y > -0.99 && y < 0.99)) {
                txtTypeOrien.setText("Left");
            } else if (y < 1 && (x > -0.99 && x < 0.99)) {
                txtTypeOrien.setText("Roll Up");
            } else if (y > -1 && (x > -0.99 && x < 0.99)) {
                txtTypeOrien.setText("Roll Down");
            } else {
                txtTypeOrien.setText("Orientation Oblique ");
            }

        }
    };

}
