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

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class KinematicsActivity extends Activity {

	private TextView motionterms_overview;
	private TextView kineqs_overview;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kinematics);
        motionterms_overview = (TextView) findViewById(R.id.motionterms_overview);
        kineqs_overview = (TextView) findViewById(R.id.kineqs_overview);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_kinematics, menu);
        return true;
    }

    public void onResume() {
    	super.onResume();
    	SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
    	String age = preferences.getString("pref_age", "10");
    	if (age.equals("10")) {
    		motionterms_overview.setText(R.string.motionterms_overview10);
    		kineqs_overview.setText(R.string.kineqs_overview10);
    	}
    	else if (age.equals("11")) {
    		motionterms_overview.setText(R.string.motionterms_overview11);
    		kineqs_overview.setText(R.string.kineqs_overview11);
    	}
    	else if (age.equals("14")) {
    		motionterms_overview.setText(R.string.motionterms_overview14);
    		kineqs_overview.setText(R.string.kineqs_overview14);
    	}
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
    
    public void goToKinEqsScreen(View view) {
    	Intent i = new Intent(getApplicationContext(), KinEqsActivity.class);
    	startActivity(i);
    }
    
    public void goToMotionTermsScreen(View view) {
    	Intent i = new Intent(getApplicationContext(), MotionTermsActivity.class);
    	startActivity(i);
    }
    
    public void goToAccelerometerScreen(View view) {
    	Intent i = new Intent(getApplicationContext(), AccelerometerGeneralActivity.class);
    	startActivity(i);   	
    }
    
    public void goToProjectileMotionScreen(View view) {
    	Intent i = new Intent(getApplicationContext(), ProjectileMotionActivity.class);
    	startActivity(i);   	
    }  
}
