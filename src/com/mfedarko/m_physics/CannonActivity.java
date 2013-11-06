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
import android.graphics.Matrix;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

public class CannonActivity extends Activity {

	private double initial_velocity, cannon_angle;
	private EditText initial_velocity_edittext, cannon_angle_edittext;
	private double vx, viy, t, dx, dy;
	private TextView vitext, angletext, timetext, dxtext, dytext, verteqtext, horzeqtext;
	
	private ImageView cannon_sprite;
	
	private boolean correct_data;
	private String ball_tag = "Ball";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cannon);
        
        cannon_sprite = (ImageView) findViewById(R.id.cannon_sprite);
    	
        vitext = (TextView) findViewById(R.id.cannon_velocity_text);
        angletext = (TextView) findViewById(R.id.cannon_angle_text);
        timetext = (TextView) findViewById(R.id.cannon_t);
        dxtext = (TextView) findViewById(R.id.cannon_dx);
        dytext = (TextView) findViewById(R.id.cannon_dy);
        verteqtext = (TextView) findViewById(R.id.cannon_verteq);
        horzeqtext = (TextView) findViewById(R.id.cannon_horzeq);
        
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_cannon, menu);
        return true;
    }

    public boolean getValuesFromFields() {
    	/* Gets values from the edittext fields and checks to make sure they're valid.
    	 * Returns true if all fields are correct and calculations can proceed, and returns
    	 * false if there are errors with user-entered data. */
    	cannon_angle_edittext = (EditText) findViewById(R.id.cannon_angle_et);
    	initial_velocity_edittext = (EditText) findViewById(R.id.cannon_initialvelocity_et);
    	try {
    		cannon_angle = Double.parseDouble(cannon_angle_edittext.getText().toString());
        	initial_velocity = Double.parseDouble(initial_velocity_edittext.getText().toString());
    	}
    	catch (NumberFormatException e) {
    		Toast.makeText(getApplicationContext(), "Invalid number entered.", Toast.LENGTH_SHORT).show(); 
    		return false;
    	}
    	
    	if ((cannon_angle > 90) || (cannon_angle < 0)) {
    		Toast.makeText(getApplicationContext(), "Cannon angle must be between 0 and 90 degrees.", Toast.LENGTH_SHORT).show();
    		return false;
    	}
    	if ((initial_velocity > 80) || (initial_velocity < 0)) {
    		Toast.makeText(getApplicationContext(), "Initial velocity must be between 0 and 80 m/s.", Toast.LENGTH_SHORT).show();
    		return false;    	    		
    	}
    	return true;
    }
    
    public void doCalculations() {
    	/* Calculates all the necessary values related to the motion of the projectile. */
    	// vx = vcos(angle)
    	vx = initial_velocity * Math.cos(Math.toRadians(cannon_angle));
    	// viy = vsin(angle)
    	viy = initial_velocity * Math.sin(Math.toRadians(cannon_angle));
    	
    	/* vfy = viy + ayt
    	 * SOLVING FOR TIME:
    	 * 0 = viy + gt
    	 * Since g = -9.8 and the user has entered viy, solve for t.
    	 * t = -viy/g -> This is the time it takes to get to the peak of the projectile's path.
    	 * Since projectiles travel in a "symmetrical path", the projectile will take a time of 2t
    	 * to fall back to the ground, regardless of vix.
    	 * 
    	 * HORIZONTAL DISTANCE:
    	 * Solve for time as above, then use:
    	 * dx = vx(t)
    	 * This is a modified form of dx = vixt + .5(ax)(t)^2
    	 * Since ax = 0,
    	 * dx = vx(t)
    	 * 
    	 * VERTICAL DISTANCE TO PEAK OF PROJECTILE:
    	 * Solve for time as above, then use:
    	 * dy = viy(t) + 0.5(g)(t^2)
    	 * Plug in [viy for viy] and [0.5t for t] to get dy.
    	 */
    	t = 2 * (viy / 9.8);
    	dx = vx * t;
    	dy = (viy * (0.5 * t)) + (-4.9 * (Math.pow((0.5 * t), 2))); 		
    }

    public void updateTextViews() {

    	vitext.setText(String.format("Initial Velocity: %.3f", initial_velocity));
    	angletext.setText(String.format("Launch Angle: %.3f", cannon_angle));
    	timetext.setText(String.format("Elapsed time: %.3f", t));   
    	dxtext.setText(String.format("Horizontal Distance traveled: %.3f", dx));
    	dytext.setText(String.format("Vertical Distance to peak of path: %.3f", dy));

    	verteqtext.setText(Html.fromHtml(String.format("<i>y = %.3ft - 4.9t<sup><small>2</small></sup></i>", viy)));    		
    	horzeqtext.setText(Html.fromHtml(String.format("<i>x = %.3ft</i>", vx)));
    }
    
    public void rotateCannon(double angle) {
    	/* Rotates the cannon to indicate angle of projectile launch. */
    	Matrix m = new Matrix();
    	cannon_sprite.setScaleType(ScaleType.MATRIX);
    	m.postRotate((float) (-angle + 90), 10, 15);
    	cannon_sprite.setImageMatrix(m);
    }

    public void moveCannonball() {
    	/* Moves the cannon ball. */

    	RelativeLayout layout = (RelativeLayout) findViewById(R.id.cannon_activity_layout);
    	RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    	double dynamicTime = 0.0;
    	float x = 14;
    	float y = layout.getHeight() - 25;
    	
    	ImageView ball;
    	int groundHeight = layout.getHeight();
    	while (y < groundHeight) {
    		// Create a large trail of "cannonballs" to show the path of the projectile
    		dynamicTime += 0.1;
    		/* The equation for y position is:
    		 * dy = viy(t) + 0.5g(t^2)
    		 * Derive this to get instantaneous vertical velocity:
    		 * vy = viy + gt
    		 * (g = -9.8 m/s^2, of course)
    		 */    		
    		y -= ((viy) - (4.9 * dynamicTime));
    		// Since vx is constant, we change x by just vx every "second"
    		x += vx;
    		ball = new ImageView(getApplicationContext());
    		ball.setTag(ball_tag);
    		ball.setImageResource(R.drawable.cannonball_sprite);
    		ball.setMinimumWidth(layout.getWidth());
    		ball.setMinimumHeight(layout.getHeight());
    		
    		Matrix ballMatrix = new Matrix();
    		ball.setScaleType(ScaleType.MATRIX);
    		ballMatrix.reset();
    		ballMatrix.postTranslate(x, y);
    		ball.setImageMatrix(ballMatrix);
    		layout.addView(ball, params);
    	}
    }
    
	public void shootCannon(View view) {
    	/* Called when the user presses "Go!" */
    	
    	correct_data = getValuesFromFields();      	
    	if (correct_data == true) {
	    	doCalculations();    
    		updateTextViews();	
	    	rotateCannon(cannon_angle);
	      	moveCannonball();
    	}
    }
    
	public void clearPaths(View view) {
		/* Called when the user presses "Clear" */
		
		RelativeLayout layout = (RelativeLayout) findViewById(R.id.cannon_activity_layout);
		int NumberOfViewsToCheck = layout.getChildCount();
    	for(int i = 0; i < NumberOfViewsToCheck; i++) {
    		try {
	    		if (layout.getChildAt(i).getTag() == ball_tag) {
	    			layout.removeViewAt(i);
	    			// Every time we remove a view, we have to decrement i or we'll skip the next view.
	    			i--;
	    		}
    		}
    		catch (NullPointerException e) {}
    	}
    	rotateCannon(90); // reset cannon angle to straight up
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