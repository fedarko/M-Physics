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
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
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
    
    public void goToWhatScreen(View view) {
    	Intent i = new Intent(getApplicationContext(), WhatIsPhysicsActivity.class);
    	startActivity(i);
    }
    
    public void goToWhereScreen(View view) {
    	Intent i = new Intent(getApplicationContext(), WhereToStartActivity.class);
    	startActivity(i);
    }
    
    public void goToKinematicsScreen(View view) {
    	Intent i = new Intent(getApplicationContext(), KinematicsActivity.class);
    	startActivity(i);
    }    

    public void goToNLScreen(View view) {
    	Intent i = new Intent(getApplicationContext(), NewtonsLawsActivity.class);
    	startActivity(i);
    }
    
    public void goToForcesScreen(View view) {
    	Intent i = new Intent(getApplicationContext(), ForcesActivity.class);
    	startActivity(i);   	
    }
        
    public void goToMassWeightScreen(View view) {
    	Intent i = new Intent(getApplicationContext(), MassWeightActivity.class);
    	startActivity(i);
    }
    
    public void goToUnitsScreen(View view) {
    	Intent i = new Intent(getApplicationContext(), UnitsActivity.class);
    	startActivity(i);
    }
    
    public void goToWEPScreen(View view) {
    	Intent i = new Intent(getApplicationContext(), WEPActivity.class);
    	startActivity(i);
    }
    
    public void goToCreditsScreen(View view) {
    	Intent i = new Intent(getApplicationContext(), CreditsActivity.class);
    	startActivity(i);
    }
}
