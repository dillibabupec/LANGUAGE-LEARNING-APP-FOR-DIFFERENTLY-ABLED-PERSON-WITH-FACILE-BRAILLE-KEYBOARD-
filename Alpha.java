package com.example.languagelearn;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Alpha extends Activity {
 Button b1,b2,b3,b4,b5;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alpha);
		b1=(Button)findViewById(R.id.button1);
		b2=(Button)findViewById(R.id.button2);
		b3=(Button)findViewById(R.id.button3);
		b4=(Button)findViewById(R.id.button4);
		b5=(Button)findViewById(R.id.button5);
		
		b1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Intent inn=new Intent(getApplicationContext(),D_lesson1.class);
			startActivity(inn);
			}
	 				});

		b2.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Intent inn=new Intent(getApplicationContext(),D_lesson3.class);
			startActivity(inn);
			}
	 				});
		b3.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Intent inn=new Intent(getApplicationContext(),D_lesson5.class);
			startActivity(inn);
			}
	 				});
		b4.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Intent inn=new Intent(getApplicationContext(),D_lesson7.class);
			startActivity(inn);
			}
	 				});
		b5.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Intent inn=new Intent(getApplicationContext(),MainActivity.class);
			startActivity(inn);
			}
	 				});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.alpha, menu);
		return true;
	}

}
