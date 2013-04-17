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
//import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
//import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
//import android.widget.TextView;

public class KinematicsActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kinematics);
        
//        addAdaption();
    }

//    public void addAdaption() {
//        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
//        int mathlevel = Integer.parseInt(sharedPrefs.getString("pref_mathlevel", "-1"));
//        int agelevel = Integer.parseInt(sharedPrefs.getString("pref_agelevel", "6"));
//        
//        TextView kinematics_explanation = (TextView) findViewById(R.id.kinematics_explanation);
//        
////        if (agelevel < 7) {
////        	
////        }
//    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_kinematics, menu);
        return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem item) {

    	switch(item.getItemId()) {
    	
    		case R.id.menu_settings:
    			Intent i = new Intent(getApplicationContext(), SettingsActivity.class);
    			startActivity(i);
    	    	return true;
    	    	
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
    
    public void goToAccelerometerTestScreen(View view) {
    	Intent i = new Intent(getApplicationContext(), AccelerometerTestActivity.class);
    	startActivity(i);   	
    }
    
    public void goToProjectileMotionScreen(View view) {
    	Intent i = new Intent(getApplicationContext(), ProjectileMotionActivity.class);
    	startActivity(i);   	
    }  
}
