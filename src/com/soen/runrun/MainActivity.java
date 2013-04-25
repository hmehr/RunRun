package com.soen.runrun;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		

	}
	
	public void start (View view){
		Preference prefs = getUserInputs();
		
		Intent intent = new Intent(this,TimerActivity.class);
		intent.putExtra("com.soen.runrun.preference", prefs);
		startActivity(intent);
	}

	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	// Controller methods. They need to be moved to a controller class. Don't
	// belong here.
	// ------------------------------------------------------------------------------------
	private String getEditTextFieldText(int fieldId) {
		EditText field = (EditText) findViewById(fieldId);		
		return field.getText().toString();
	}

	private String getEditSpinnerText(int fieldId) {
		Spinner field = (Spinner) findViewById(fieldId);
		return field.getSelectedItem().toString();
	}
	
	private Preference getUserInputs() {
		int distance = Integer
				.parseInt(getEditTextFieldText(R.id.distanceText));
		int initialTimeInterval = Integer
				.parseInt(getEditTextFieldText(R.id.timeIntervalText));
		int decrementRate = Integer
				.parseInt(getEditTextFieldText(R.id.timeDecrementText));
		String beepSound = getEditSpinnerText(R.id.spinner1);

		String musicSound = "background.mp3";
		String runnerName = getEditTextFieldText(R.id.nameText);

		Preference prefs = new Preference(distance, initialTimeInterval,
				decrementRate, beepSound, musicSound, runnerName);
		
		return prefs;
	}
	// ------------------------------------------------------------------------------------
	// ------------------------------------------------------------------------------------

}
