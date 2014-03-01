package com.example.kalkulatorfelgiopon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
	
	private Button changeOpony;
	private Button changeFelgi;
	private Button changeOpony2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ImageView imageView = (ImageView) findViewById(R.id.imageView1);
	    imageView.setImageResource(R.drawable.opona4);
		
		changeOpony = (Button)findViewById(R.id.changeActivityOpony);
		changeFelgi = (Button)findViewById(R.id.changeActivityFelgi);
		changeOpony2 = (Button)findViewById(R.id.changeActivityOpony2);
		initButtonsOnClickListeners();
		
	}
	
	private void initButtonsOnClickListeners() {
		changeOpony.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				openActivityOpony();
			}
		});
		
		changeFelgi.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				openActivityFelgi();
			}
		});
		changeOpony2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				openActivityOpony2();
			}
		});
	}

	protected void openActivityOpony() {
		Intent intent = new Intent(getApplicationContext(), OponyActivity.class);
		startActivity(intent);
		
	}

	protected void openActivityFelgi() {
		Intent intent = new Intent(getApplicationContext(), FelgiActivity.class);
		startActivity(intent);
		
	}
	
	protected void openActivityOpony2() {
		Intent intent = new Intent(getApplicationContext(), Opony2Activity.class);
		startActivity(intent);
		
	}
	

}

