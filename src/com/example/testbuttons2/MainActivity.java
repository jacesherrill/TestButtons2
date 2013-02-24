package com.example.testbuttons2;

import com.example.testbuttons2.Puzzle;
import com.example.testbuttons2.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	public static final String EXTRA_COLOR = "com.example.testbuttons2.COLOR";
	public static final String EXTRA_DIFFICULTY = "com.example.testbuttons2.DIFFICULTY";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	/**
	 * Displays the puzzle interface and begins the puzzle.
	 * @param view
	 */
	public void superEasyPuzzle(View view) {
		Intent intent = new Intent(this, Puzzle.class);
		intent.putExtra(EXTRA_COLOR, Color.BLUE);
		intent.putExtra(EXTRA_DIFFICULTY, ""+2);
		startActivity(intent);
	}
	public void easyPuzzle(View view) {
		Intent intent = new Intent(this, Puzzle.class);
		intent.putExtra(EXTRA_COLOR, Color.BLUE);
		intent.putExtra(EXTRA_DIFFICULTY, ""+3);
		startActivity(intent);
	}
	
	public void mediumPuzzle(View view) {
		Intent intent = new Intent(this, Puzzle.class);
		intent.putExtra(EXTRA_COLOR, Color.BLUE);
		intent.putExtra(EXTRA_DIFFICULTY, ""+4);
		startActivity(intent);
	}
	
	public void hardPuzzle(View view) {
		Intent intent = new Intent(this, Puzzle.class);
		intent.putExtra(EXTRA_COLOR, Color.BLUE);
		intent.putExtra(EXTRA_DIFFICULTY, ""+5);
		startActivity(intent);
	}
	
	public void level1(View view) {
		Intent intent = new Intent(this, Puzzle.class);
		intent.putExtra(EXTRA_DIFFICULTY, ""+1);
		startActivity(intent);
	}
	
	public void level2(View view) {
		Intent intent = new Intent(this, Puzzle.class);
		intent.putExtra(EXTRA_DIFFICULTY, ""+2);
		startActivity(intent);
	}
	public void level3(View view) {
		Intent intent = new Intent(this, Puzzle.class);
		intent.putExtra(EXTRA_DIFFICULTY, ""+3);
		startActivity(intent);
	}
	public void level4(View view) {
		Intent intent = new Intent(this, Puzzle.class);
		intent.putExtra(EXTRA_DIFFICULTY, ""+4);
		startActivity(intent);
	}
	public void level5(View view) {
		Intent intent = new Intent(this, Puzzle.class);
		intent.putExtra(EXTRA_DIFFICULTY, ""+5);
		startActivity(intent);
	}
	public void level6(View view) {
		Intent intent = new Intent(this, Puzzle.class);
		intent.putExtra(EXTRA_DIFFICULTY, ""+6);
		startActivity(intent);
	}
	public void level7(View view) {
		Intent intent = new Intent(this, Puzzle.class);
		intent.putExtra(EXTRA_DIFFICULTY, ""+7);
		startActivity(intent);
	}
	public void level8(View view) {
		Intent intent = new Intent(this, Puzzle.class);
		intent.putExtra(EXTRA_DIFFICULTY, ""+8);
		startActivity(intent);
	}
	
	
	
}