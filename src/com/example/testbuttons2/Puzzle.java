package com.example.testbuttons2;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import com.example.testbuttons2.R;
import com.example.testbuttons2.MainActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.SparseIntArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class Puzzle extends Activity {
	//Temp string for basic design, will be stored in a file eventually
	public static final String square_2 = "1 1 1 2 2 1 2 2";
	public static final String square_3 = "1 1 1 2 1 3 2 1 2 2 2 3 3 1 3 2 3 3";
	public static final String square_4 = "1 1 1 2 1 3 1 4 2 1 2 2 2 3 2 4 3 1 3 2 3 3 3 4 4 1 4 2 4 3 4 4";
	public static final String square_5 = "1 1 1 2 1 3 1 4 1 5 2 1 2 2 2 3 2 4 2 5 3 1 3 2 3 3 3 4 3 5 4 1 4 2 4 3 4 4 4 5 5 1 5 2 5 3 5 4 5 5";
	public static final String EXTRA_TIME = ".com.example.testbuttons2.TIME";
	
	private ArrayList<Integer> numberOrder;
	private int[] colorOrder = new int[30];
	private ArrayList<Integer> wtf;
	private int pointer = 0;
    private long init,now,time;
    private boolean pause;
    private TextView display;
    private Handler handler;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.puzzle);
		Intent intent = getIntent();
		ArrayList<Integer> colors = setColors(intent);
		String difficulty = setDifficulty(intent);
		numberOrder = new ArrayList<Integer>();
		wtf = new ArrayList<Integer>();

		ArrayList<Coordinates> puzzleDesign = buildCoord(difficulty);
		buildGrid(colors, puzzleDesign);
		buildArray();
		nextColor();
		init = System.currentTimeMillis();
		//timer
		handler = new Handler();
		display = (TextView) findViewById(R.id.chronometer1);
		pause = false;
		final Runnable updater = new Runnable() {
            @Override
            public void run() {     	
            	if(!pause) {
                    now=System.currentTimeMillis();
                    time=now-init;
                    display.setText(" " + String.format("%.2f", time/1000.00) );
                    handler.postDelayed(this, 30);
            	}
            }
        };
        handler.post(updater);
        //showElapsedTime();
		
	}
	
	public void start() {
		setContentView(R.layout.puzzle);
	}
	
	public void buildArray() {
		for(int i = 0; i < 30; i++) {
			if(colorOrder[i] != 0) {
				wtf.add(colorOrder[i]);
			}
		}
	}
	
	
	//takes a string and converts it into a set of coordinates
	public ArrayList<Coordinates> buildCoord(String s) {
		ArrayList<Coordinates> puzzleDesign = new ArrayList<Coordinates>();
		Scanner sc = new Scanner(s);
		while (sc.hasNextInt()) {
			int x = sc.nextInt();
			if(sc.hasNextInt()) {
				int y = sc.nextInt();
				puzzleDesign.add(new Coordinates(x,y));
			}
		}
		return puzzleDesign;
	}
	
	//Builds a puzzle with specified colors and design
	public void buildGrid(ArrayList<Integer> colors, ArrayList<Coordinates> puzzleDesign) {
		boolean[][] colorTracker = new boolean[colors.size()][(puzzleDesign.size()-1+colors.size())/colors.size()];
		SparseIntArray numberTracker = new SparseIntArray();
		Random r = new Random();
		Random c = new Random();
		int elementsLeft = puzzleDesign.size();
		int count = 0;

		int setCount = puzzleDesign.size()%colorTracker.length;
		while (elementsLeft > 0) {

			int colorIndex = c.nextInt(colors.size());
			int index = r.nextInt(((puzzleDesign.size()-1)%colorTracker[0].length)+1);
			
			//Verify number is legal
			boolean done = false;
			while(!done) {
				if((numberTracker.get(index) < colorTracker.length && puzzleDesign.size()%colorTracker.length == 0) || 
						(numberTracker.get(index) < colorTracker.length) && index != colorTracker[0].length-1) {
					numberTracker.put(index, numberTracker.get(index)+1);
					done = true;
				} else 	if (index ==  colorTracker[0].length-1 && numberTracker.get(index) < puzzleDesign.size()%colorTracker.length) {
					numberTracker.put(index, numberTracker.get(index)+1);
					done = true;
				}  else {
					index++;
					index = index%colorTracker[0].length;
				}
			}

			//Verify color is legal

			count++;
			if(setCount > 0 || colorTracker.length == 1 || puzzleDesign.size()%colorTracker.length == 0) {
				numberOrder.add(((count-1)%colorTracker[colorIndex].length)+1);
				if(count%colorTracker[colorIndex].length == 0 && !(puzzleDesign.size()%colorTracker.length == 0)) {
					setCount--;
					count--;
				}
			} else {
				numberOrder.add(((count-1)%(colorTracker[colorIndex].length-1))+1);
			}
			int temp = index;

			while(colorOrder[temp] != 0) {
				temp+=colorTracker[0].length;
			}
			while (colorTracker[colorIndex][index]) {
				colorIndex++;
				colorIndex = colorIndex%colors.size();
			}

			colorTracker[colorIndex][index] = true;

			colorOrder[temp] = colors.get(colorIndex);
			buildButton(puzzleDesign.get(index).getX(), puzzleDesign.get(index).getY(), 
						getTileSize(puzzleDesign), colors.get(colorIndex), 
						count, index);
			elementsLeft--;
		}

	}
	
	
	//finish later
	public void buildButton(int x, int y, int tileSize, int color, int id, int number) {
      	int dip = convertToDp(280/tileSize);
      	int sip = convertToDp((int)((280/tileSize)/3.1+.5));
		//Create button
		
		 Button btn = new Button(this); 
         btn.setText(""+(number+1));
         btn.setTextColor(Color.parseColor("#000000"));
         btn.setTextSize(sip);
         btn.setBackgroundColor(color);
         btn.setOnClickListener(new MyClickListener(color) {

         });
        //Place button
         TableLayout table = (TableLayout) findViewById( R.id.tableLayout1 );

         int buttonsInRow = 0;
         int numRows = table.getChildCount();
         TableRow row = null;
         if( numRows > 0 ){
             row = (TableRow) table.getChildAt( numRows - 1 );
             buttonsInRow = row.getChildCount();         
         }

         if( numRows == 0 || buttonsInRow == tileSize ){
             row = new TableRow( this );
             table.addView( row );
             buttonsInRow = 0;
         }
         if( buttonsInRow < tileSize ){
             row.addView(btn, dip, dip);
             
         } 
	}
	
	public int convertToDp(int input) { 
		// Get the screen's density scale 
		final float scale = getResources().getDisplayMetrics().density; 
		// Convert the dps to pixels, based on density scale 
		return (int) (input * scale + 0.5f); 
	}
	
	
	
	@SuppressLint("NewApi")
	public boolean validate(View v, int color) {
		Button btn = (Button)v;
//		ColorDrawable buttonColor = (ColorDrawable) v.getBackground();
//		int colorId = buttonColor.getColor();
		
		if(numberOrder.get(pointer) == Integer.parseInt((String)btn.getText()) && wtf.get(pointer) == color) {
			return true;
		} else {
			return false;
		}
	}
	
	@SuppressWarnings("static-access")
	public void nextColor() {
		if (numberOrder.size()-pointer > 0) {
			Button btn1 = (Button)findViewById(R.id.button1);
			btn1.setBackgroundColor(wtf.get(pointer));
			btn1.setText(""+numberOrder.get(pointer));
			btn1.setTextSize(convertToDp(13));
		} else {
			Button btn1 = (Button)findViewById(R.id.button1);
			btn1.setVisibility(btn1.INVISIBLE);
		}
		if (numberOrder.size()-pointer > 1) {
			Button btn2 = (Button)findViewById(R.id.button2);
			btn2.setBackgroundColor(wtf.get(pointer+1));
			btn2.setText(""+numberOrder.get(pointer+1));
			btn2.setTextSize(convertToDp(13));
		} else {
			Button btn2 = (Button)findViewById(R.id.button2);
			btn2.setVisibility(btn2.INVISIBLE);
		}
		if (numberOrder.size()-pointer > 2) {
			Button btn3 = (Button)findViewById(R.id.button3);
			btn3.setBackgroundColor(wtf.get(pointer+2));
			btn3.setText(""+numberOrder.get(pointer+2));
			btn3.setTextSize(convertToDp(13));
		} else {
			Button btn3 = (Button)findViewById(R.id.button3);
			btn3.setVisibility(btn3.INVISIBLE);
		}
	}
	
	//Returns the max horizontal or vertical tiles in the board
	public int getTileSize(ArrayList<Coordinates> puzzleDesign) {
		return Math.max(getPuzzleWidth(puzzleDesign), getPuzzleHeight(puzzleDesign));
	}
	
	//Scans the puzzle design and determines max width
	public int getPuzzleWidth(ArrayList<Coordinates> puzzleDesign) {
		int maxWidth = 0;
		SparseIntArray width = new SparseIntArray();
		for(Coordinates c : puzzleDesign) {
				width.put(c.getX(), width.get(c.getX())+1);
				maxWidth = Math.max(maxWidth, width.get(c.getX()));
		}
		return maxWidth;
	}
	
	//Scans the puzzle design and determines max width
	public int getPuzzleHeight(ArrayList<Coordinates> puzzleDesign) {
		int maxHeight = 0;
		SparseIntArray height = new SparseIntArray();
		for(Coordinates c : puzzleDesign) {
				height.put(c.getY(), height.get(c.getY())+1);
				maxHeight = Math.max(maxHeight, height.get(c.getY()));
		}
		return maxHeight;
	}
	public void endgame() {
		pause = true;
		Intent back = new Intent(this, VictoryScreen.class);
		//back.putExtra(EXTRA_TIME, "10000");
		back.putExtra(EXTRA_TIME, ""+(now-init));
		startActivity(back);

	}
	
	public ArrayList<Integer> setColors(Intent intent) {
		ArrayList<Integer> colors = new ArrayList<Integer>();
		if(Integer.parseInt(intent.getStringExtra((String)MainActivity.EXTRA_DIFFICULTY)) > 0) {
			colors.add(Color.rgb(51, 181, 229));
		}
		if(Integer.parseInt(intent.getStringExtra((String)MainActivity.EXTRA_DIFFICULTY)) > 4) {
			colors.add(Color.rgb(170, 66, 238));
			colors.add(Color.rgb(153, 204, 00));
		}
		return colors;
	}
	
	public static String setDifficulty(Intent intent) {
		String difficulty = "1 1";
		if(Integer.parseInt(intent.getStringExtra((String)MainActivity.EXTRA_DIFFICULTY))%4 == 1) {
			difficulty = square_2;
		} else if (Integer.parseInt(intent.getStringExtra((String)MainActivity.EXTRA_DIFFICULTY))%4 == 2) {
			difficulty = square_3;
		} else if (Integer.parseInt(intent.getStringExtra((String)MainActivity.EXTRA_DIFFICULTY))%4 == 3) {
			difficulty = square_4;
		} else if (Integer.parseInt(intent.getStringExtra((String)MainActivity.EXTRA_DIFFICULTY))%4 == 0) {
			difficulty = square_5;
		}
		return difficulty;
	}
	private class MyClickListener implements OnClickListener {

	    private int color;

	    public MyClickListener(int color) {
	       this.color = color;
	    }

	    public void onClick(View v) {
	    	if(validate(v, color)) {
       		 pointer++;
       		 nextColor();
       		 v.setVisibility(v.INVISIBLE);
       		 if(pointer == numberOrder.size()) {
       			 endgame();
       		 }	 
	    	}
       	 }

	    public int getColor() {
	      return color;
	    }

	 }
//	private void showElapsedTime() {
//	    long elapsedMillis = SystemClock.elapsedRealtime() - mChronometer.getBase();            
//	    Toast.makeText(ChronoExample.this, "Elapsed milliseconds: " + elapsedMillis, 
//	            Toast.LENGTH_SHORT).show();
//	}
}


