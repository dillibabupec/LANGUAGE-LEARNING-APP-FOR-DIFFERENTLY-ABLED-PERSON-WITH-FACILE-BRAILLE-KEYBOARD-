package com.example.languagelearn;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
//Button b1;
public class Signlang extends Activity {
	Button b1,b2;
	TextView t1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signlang);
		b1=(Button)findViewById(R.id.button1);
		t1 = (TextView) findViewById(R.id.textView1);
		b2=(Button)findViewById(R.id.button2);
		 b2.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
			Intent inn=new Intent(getApplicationContext(),Alpha.class);
			startActivity(inn);
				}
		 				});
	}
	
	@Override 
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.signlang, menu);
		return true;
	}}
	
