package com.example.languagelearn;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Deaf extends Activity {

	Button eng,french,german;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_deaf);
		eng=(Button)findViewById(R.id.button1);
		french=(Button)findViewById(R.id.button2);
		german=(Button)findViewById(R.id.button3);
		
		eng.setOnClickListener(new OnClickListener() {
			
			public void onClick1(View arg0) {
				// TODO Auto-generated method stub
				Intent inn=new Intent(getApplicationContext(),English.class);
				startActivity(inn);
			}
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent inn=new Intent(getApplicationContext(),English.class);
				startActivity(inn);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.deaf, menu);
		return true;
	}

}
