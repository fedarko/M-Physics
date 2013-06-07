package com.mfedarko.m_physics;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

public class PMNonAngledLaunchActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pmnon_angled_launch);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_pmnon_angled_launch, menu);
        return true;
    }
    
    @Override
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
}
