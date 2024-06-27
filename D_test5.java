package com.example.languagelearn;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class D_test5 extends Activity {

	Button b1,b2;
	ImageView a1,a2,a3,a4;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_d_test5);
		b1=(Button)findViewById(R.id.button1);
		b2=(Button)findViewById(R.id.button2);
		a1=(ImageView)findViewById(R.id.imageView1);
		a2=(ImageView)findViewById(R.id.imageView2);
		a3=(ImageView)findViewById(R.id.imageView3);
		a4=(ImageView)findViewById(R.id.imageView4);
		b1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Intent inn=new Intent(getApplicationContext(),D_lesson6.class);
			startActivity(inn);
			}
	 				});

		b2.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Intent inn=new Intent(getApplicationContext(),D_test6.class);
			startActivity(inn);
			}
	 				});
		
		a3.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				Toast.makeText(getApplication(), "retry", Toast.LENGTH_LONG).show();
			}

	 				});
		a2.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Toast.makeText(getApplication(), "correct", Toast.LENGTH_LONG).show();
				Intent inn=new Intent(getApplicationContext(),D_test6.class);
			startActivity(inn);
			}
	 				});
		a4.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Toast.makeText(getApplication(), "retry", Toast.LENGTH_LONG).show();
			}
	 				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.d_test5, menu);
		return true;
	}

}

