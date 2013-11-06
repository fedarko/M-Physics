package com.mfedarko.m_physics;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

public class AccelerometerInfoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_accelerometer_info);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_accelerometer_info, menu);
		return true;
	}


	@Override
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
