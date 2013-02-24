package com.example.testbuttons2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class VictoryScreen extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.victory);
		Intent intent = getIntent();
		String message = intent.getStringExtra(Puzzle.EXTRA_TIME);
		
		TextView textView = (TextView)findViewById(R.id.score);
		textView.setText(message.substring(0,message.length()-3) + "."+message.substring(message.length()-3)+" seconds");
		
	}
	
	public void homescreen(View view) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}
}
