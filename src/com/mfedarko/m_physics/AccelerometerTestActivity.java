/* Copyright 2013 Marcus Fedarko
 * Contact Email: marcus.fedarko@gmail.com
 * 
 * This file is part of M-Physics.
 * 
 *     M-Physics is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  M-Physics is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with M-Physics.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.mfedarko.m_physics;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.TextView;

public class AccelerometerTestActivity extends Activity implements SensorEventListener, OnTouchListener {
	private SensorManager mSensorManager;
	private Sensor mAccelerometer;

	private boolean hasAccelerometer;
	
	private ImageView accelerometer_test_sprite;
	
	private TextView xAccelText;
	private TextView yAccelText;
	private TextView zAccelText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer_test);
        accelerometer_test_sprite = (ImageView) findViewById(R.id.accelerometer_test_sprite);
        
        xAccelText = (TextView) findViewById(R.id.accelerometer_test_xAccelText);
        yAccelText = (TextView) findViewById(R.id.accelerometer_test_yAccelText);
        zAccelText = (TextView) findViewById(R.id.accelerometer_test_zAccelText);
        
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        
        View view = findViewById(R.id.accelerometer_test);
        view.setOnTouchListener(this);
    }

    public void onResume() {
    	super.onResume();
    	hasAccelerometer = mSensorManager.registerListener(this, mAccelerometer, 0);
    	if (!hasAccelerometer) {
    		xAccelText.setText(R.string.no_accelerometer_errortext);
    		yAccelText.setText("");
    		zAccelText.setText("");
    	}
    }
    
    public void onPause() {
    	super.onPause();
    	mSensorManager.unregisterListener(this);
    }
    
	public void onAccuracyChanged(Sensor sensor, int accuracy) {	
		// Don't need anything here for now
	}

	public void onSensorChanged(SensorEvent event) {
		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
			double accelX = event.values[0];
			double accelY = event.values[1];
			double accelZ = event.values[2];
			accelerometer_test_sprite.scrollBy((int) (2 * accelX), (int) (-2 * accelY));		
			
			xAccelText.setText(String.format("x-accel = %.3f", accelX));
			yAccelText.setText(String.format("y-accel = %.3f", accelY));
			zAccelText.setText(String.format("z-accel = %.3f", accelZ));
		}
	}

	public boolean onTouch(View v, MotionEvent event) {
		int xdest = -((int) event.getX() - (accelerometer_test_sprite.getWidth() / 2));
		int ydest = -((int) event.getY() - (accelerometer_test_sprite.getHeight() / 2));		
		accelerometer_test_sprite.scrollTo(xdest, ydest);
		return false;
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_kinematics, menu);
        return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch(item.getItemId()) {
		    	
			case R.id.menu_credits:
				Intent i1 = new Intent(getApplicationContext(), CreditsActivity.class);
				startActivity(i1);
		    	return true;    	
		    	
		    default:
		    	return super.onOptionsItemSelected(item);
    	}
    }    
}